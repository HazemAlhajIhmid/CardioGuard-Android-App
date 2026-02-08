package com.cardioguard.heartdisease

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cardioguard.heartdisease.databinding.ActivityResultsBinding
import com.cardioguard.heartdisease.databinding.ItemModelResultBinding
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.RadarData
import com.github.mikephil.charting.data.RadarDataSet
import com.github.mikephil.charting.data.RadarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.interfaces.datasets.IRadarDataSet
import com.google.gson.Gson
import com.cardioguard.heartdisease.models.PredictionResponse
import com.cardioguard.heartdisease.models.ModelResult
import com.cardioguard.heartdisease.models.EnsembleResult

class ResultsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val responseJson = intent.getStringExtra("prediction_response")
        if (responseJson == null) {
            finish()
            return
        }
        val predictionResponse = Gson().fromJson(responseJson, PredictionResponse::class.java)

        // Display Risk Level
        setupRiskLevel(predictionResponse.ensemble)

        val modelResults = listOf(
            getString(R.string.results_model_knn) to predictionResponse.knn,
            getString(R.string.results_model_nb) to predictionResponse.naiveBayes,
            getString(R.string.results_model_dt) to predictionResponse.decisionTree
        )

        setupRecyclerView(modelResults)
        setupBarChart(modelResults)
        setupRadarChart(modelResults)
    }

    private fun setupRiskLevel(ensemble: EnsembleResult) {
        // Check if riskScore is a decimal (0-1) or already a percentage (>1)
        val riskPercentage = if (ensemble.riskScore <= 1.0) {
            ensemble.riskScore * 100
        } else {
            ensemble.riskScore
        }
        binding.riskScore.text = String.format("%.2f%%", riskPercentage)
        
        val riskLevel = ensemble.riskLevel.lowercase()
        val riskLevelText = when (riskLevel) {
            "low" -> getString(R.string.results_risk_low)
            "moderate", "medium" -> getString(R.string.results_risk_medium)
            "high" -> getString(R.string.results_risk_high)
            else -> ensemble.riskLevel
        }
        binding.riskLevel.text = riskLevelText
        
        // Set color based on risk level
        val riskColor = when (riskLevel) {
            "low" -> getColor(R.color.secondary)  // Green
            "moderate", "medium" -> getColor(R.color.accent)  // Orange
            "high" -> getColor(R.color.error)  // Red
            else -> getColor(R.color.secondary)
        }
        
        // Create rounded background
        val drawable = GradientDrawable()
        drawable.shape = GradientDrawable.RECTANGLE
        drawable.setColor(riskColor)
        drawable.cornerRadius = 50f
        binding.riskLevel.background = drawable
        binding.riskScore.setTextColor(riskColor)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setupRecyclerView(results: List<Pair<String, ModelResult>>) {
        binding.recyclerViewModels.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewModels.adapter = ModelsAdapter(results)
    }

    private fun setupBarChart(results: List<Pair<String, ModelResult>>) {
        val entries = ArrayList<BarEntry>()
        val labels = ArrayList<String>()
        results.forEachIndexed { index, result ->
            // Confidence is already a percentage from backend
            entries.add(BarEntry(index.toFloat(), result.second.confidence.toFloat()))
            labels.add(result.first)
        }

        val dataSet = BarDataSet(entries, "Model Confidence")
        dataSet.colors = listOf(Color.parseColor("#42A5F5"), Color.parseColor("#66BB6A"), Color.parseColor("#FFA726"))
        val barData = BarData(dataSet)
        binding.barChart.data = barData
        binding.barChart.xAxis.valueFormatter = IndexAxisValueFormatter(labels)
        binding.barChart.description.isEnabled = false
        binding.barChart.legend.isEnabled = false
        binding.barChart.invalidate()
    }

    private fun setupRadarChart(results: List<Pair<String, ModelResult>>) {
        val dataSets = ArrayList<IRadarDataSet>()
        // Use localized labels
        val labels = arrayOf(
            getString(R.string.about_label_accuracy),
            getString(R.string.about_label_precision),
            getString(R.string.about_label_recall),
            getString(R.string.about_label_f1)
        )
        val colors = listOf(Color.parseColor("#42A5F5"), Color.parseColor("#66BB6A"), Color.parseColor("#FFA726"))

        results.forEachIndexed { index, (modelName, modelResult) ->
            val entries = ArrayList<RadarEntry>()
            entries.add(RadarEntry(modelResult.accuracy.toFloat()))
            entries.add(RadarEntry((modelResult.precision * 100).toFloat()))
            entries.add(RadarEntry((modelResult.recall * 100).toFloat()))
            entries.add(RadarEntry((modelResult.f1_score * 100).toFloat()))
            val dataSet = RadarDataSet(entries, modelName)
            val color = colors[index % colors.size]
            dataSet.color = color
            dataSet.fillColor = color
            dataSet.setDrawFilled(true)
            dataSet.valueTextColor = color
            dataSet.lineWidth = 2f
            dataSets.add(dataSet)
        }

        val radarData = RadarData(dataSets)
        binding.radarChart.xAxis.valueFormatter = IndexAxisValueFormatter(labels)
        binding.radarChart.yAxis.axisMinimum = 0f
        binding.radarChart.yAxis.axisMaximum = 100f
        binding.radarChart.description.isEnabled = false
        binding.radarChart.data = radarData
        binding.radarChart.invalidate()
    }

    inner class ModelsAdapter(private val results: List<Pair<String, ModelResult>>) : 
        RecyclerView.Adapter<ModelsAdapter.ModelViewHolder>() {

        inner class ModelViewHolder(val binding: ItemModelResultBinding) : RecyclerView.ViewHolder(binding.root)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ModelViewHolder {
            val binding = ItemModelResultBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return ModelViewHolder(binding)
        }

        override fun onBindViewHolder(holder: ModelViewHolder, position: Int) {
            val (modelName, modelResult) = results[position]
            holder.binding.modelName.text = modelName
            
            // Confidence is already a percentage from backend, no need to multiply by 100
            holder.binding.confidence.text = String.format(getString(R.string.results_confidence_template), modelResult.confidence)
            holder.binding.accuracy.text = getString(R.string.results_accuracy_template, "${modelResult.accuracy}%")
        }

        override fun getItemCount() = results.size
    }
}
