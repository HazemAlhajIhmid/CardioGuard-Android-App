package com.cardioguard.heartdisease

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout
import com.google.gson.Gson
import com.cardioguard.heartdisease.databinding.ActivityRiskCalculatorBinding
import com.cardioguard.heartdisease.models.PredictionRequest
import com.cardioguard.heartdisease.viewmodels.PredictionViewModel

class RiskCalculatorActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRiskCalculatorBinding
    private val viewModel: PredictionViewModel by viewModels()

    private val sexOptions by lazy { resources.getStringArray(R.array.sex_options) }
    private val cpOptions by lazy { resources.getStringArray(R.array.chest_pain_options) }
    private val booleanOptions by lazy { resources.getStringArray(R.array.boolean_options) }
    private val restecgOptions by lazy { resources.getStringArray(R.array.rest_ecg_options) }
    private val slopeOptions by lazy { resources.getStringArray(R.array.slope_options) }
    private val thalOptions by lazy { resources.getStringArray(R.array.thal_options) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRiskCalculatorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        setupDropdowns()
        setupObservers()
        setupInfoButtons()

        binding.btnPredict.setOnClickListener {
            if (validateInputs()) {
                predictHeartDisease()
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressedDispatcher.onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setupDropdowns() {
        binding.inputSex.setAdapter(ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, sexOptions))
        binding.inputCp.setAdapter(ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, cpOptions))
        binding.inputFbs.setAdapter(ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, booleanOptions))
        binding.inputRestecg.setAdapter(ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, restecgOptions))
        binding.inputExang.setAdapter(ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, booleanOptions))
        binding.inputSlope.setAdapter(ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, slopeOptions))
        binding.inputCa.setAdapter(ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, (0..3).map { it.toString() }.toTypedArray()))
        binding.inputThal.setAdapter(ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, thalOptions))
    }

    private fun showInfoDialog(title: String, message: String) {
        AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton(android.R.string.ok, null)
            .show()
    }

    private fun setupInfoButtons() {
        binding.infoAge.setOnClickListener { showInfoDialog(getString(R.string.info_title_age), getString(R.string.info_desc_age)) }
        binding.infoSex.setOnClickListener { showInfoDialog(getString(R.string.info_title_sex), getString(R.string.info_desc_sex)) }
        binding.infoCp.setOnClickListener { showInfoDialog(getString(R.string.info_title_chest_pain), getString(R.string.info_desc_chest_pain)) }
        binding.infoTrestbps.setOnClickListener { showInfoDialog(getString(R.string.info_title_trestbps), getString(R.string.info_desc_trestbps)) }
        binding.infoChol.setOnClickListener { showInfoDialog(getString(R.string.info_title_cholesterol), getString(R.string.info_desc_cholesterol)) }
        binding.infoFbs.setOnClickListener { showInfoDialog(getString(R.string.info_title_fasting_blood_sugar), getString(R.string.info_desc_fasting_blood_sugar)) }
        binding.infoRestecg.setOnClickListener { showInfoDialog(getString(R.string.info_title_rest_ecg), getString(R.string.info_desc_rest_ecg)) }
        binding.infoThalach.setOnClickListener { showInfoDialog(getString(R.string.info_title_max_heart_rate), getString(R.string.info_desc_max_heart_rate)) }
        binding.infoExang.setOnClickListener { showInfoDialog(getString(R.string.info_title_exercise_angina), getString(R.string.info_desc_exercise_angina)) }
        binding.infoOldpeak.setOnClickListener { showInfoDialog(getString(R.string.info_title_oldpeak), getString(R.string.info_desc_oldpeak)) }
        binding.infoSlope.setOnClickListener { showInfoDialog(getString(R.string.info_title_slope), getString(R.string.info_desc_slope)) }
        binding.infoCa.setOnClickListener { showInfoDialog(getString(R.string.info_title_ca), getString(R.string.info_desc_ca)) }
        binding.infoThal.setOnClickListener { showInfoDialog(getString(R.string.info_title_thal), getString(R.string.info_desc_thal)) }
    }

    private fun validateRequiredField(layout: TextInputLayout, text: CharSequence?): Boolean {
        if (text.isNullOrBlank()) {
            layout.error = getString(R.string.validation_error_required)
            return false
        }
        layout.error = null
        return true
    }

    private fun validateNumericField(layout: TextInputLayout, text: CharSequence?): Boolean {
        if (!validateRequiredField(layout, text)) return false
        if (text.toString().toIntOrNull() == null) {
            layout.error = getString(R.string.validation_error_whole_number)
            return false
        }
        layout.error = null
        return true
    }

    private fun validateDecimalField(layout: TextInputLayout, text: CharSequence?): Boolean {
        if (!validateRequiredField(layout, text)) return false
        if (text.toString().toFloatOrNull() == null) {
            layout.error = getString(R.string.validation_error_valid_number)
            return false
        }
        layout.error = null
        return true
    }

    private fun validateInputs(): Boolean {
        val layouts = listOf(
            binding.layoutAge, binding.layoutSex, binding.layoutCp, binding.layoutTrestbps,
            binding.layoutChol, binding.layoutFbs, binding.layoutRestecg, binding.layoutThalach,
            binding.layoutExang, binding.layoutOldpeak, binding.layoutSlope, binding.layoutCa, binding.layoutThal
        )

        val checks = listOf(
            { validateNumericField(binding.layoutAge, binding.inputAge.text) },
            { validateRequiredField(binding.layoutSex, binding.inputSex.text) },
            { validateRequiredField(binding.layoutCp, binding.inputCp.text) },
            { validateNumericField(binding.layoutTrestbps, binding.inputTrestbps.text) },
            { validateNumericField(binding.layoutChol, binding.inputChol.text) },
            { validateRequiredField(binding.layoutFbs, binding.inputFbs.text) },
            { validateRequiredField(binding.layoutRestecg, binding.inputRestecg.text) },
            { validateNumericField(binding.layoutThalach, binding.inputThalach.text) },
            { validateRequiredField(binding.layoutExang, binding.inputExang.text) },
            { validateDecimalField(binding.layoutOldpeak, binding.inputOldpeak.text) },
            { validateRequiredField(binding.layoutSlope, binding.inputSlope.text) },
            { validateRequiredField(binding.layoutCa, binding.inputCa.text) },
            { validateRequiredField(binding.layoutThal, binding.inputThal.text) }
        )

        var firstErrorLayout: TextInputLayout? = null
        val allValid = checks.mapIndexed { index, check ->
            val isValid = check()
            if (!isValid && firstErrorLayout == null) {
                firstErrorLayout = layouts[index]
            }
            isValid
        }.all { it }

        if (!allValid) {
            firstErrorLayout?.let { 
                it.requestFocus()
                Toast.makeText(this, getString(R.string.validation_error_proceed), Toast.LENGTH_SHORT).show()
            }
        }

        return allValid
    }

    private fun predictHeartDisease() {
        val request = PredictionRequest(
            age = binding.inputAge.text.toString().toInt(),
            sex = sexOptions.indexOf(binding.inputSex.text.toString()),
            cp = cpOptions.indexOf(binding.inputCp.text.toString()),
            trestbps = binding.inputTrestbps.text.toString().toInt(),
            chol = binding.inputChol.text.toString().toInt(),
            fbs = booleanOptions.indexOf(binding.inputFbs.text.toString()),
            restecg = restecgOptions.indexOf(binding.inputRestecg.text.toString()),
            thalach = binding.inputThalach.text.toString().toInt(),
            exang = booleanOptions.indexOf(binding.inputExang.text.toString()),
            oldpeak = binding.inputOldpeak.text.toString().toFloat(),
            slope = slopeOptions.indexOf(binding.inputSlope.text.toString()),
            ca = binding.inputCa.text.toString().toInt(),
            thal = thalOptions.indexOf(binding.inputThal.text.toString())
        )

        viewModel.predict(request)
    }

    private fun setupObservers() {
        viewModel.isLoading.observe(this) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
            binding.btnPredict.isEnabled = !isLoading
        }

        viewModel.error.observe(this) { error ->
            error?.let {
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            }
        }

        viewModel.predictionResult.observe(this) { result ->
            result?.let {
                val intent = Intent(this, ResultsActivity::class.java)
                intent.putExtra("prediction_response", Gson().toJson(it))
                startActivity(intent)
            }
        }
    }
}
