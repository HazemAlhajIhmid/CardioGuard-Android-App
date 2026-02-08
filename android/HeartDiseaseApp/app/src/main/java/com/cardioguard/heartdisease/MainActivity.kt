package com.cardioguard.heartdisease

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import com.cardioguard.heartdisease.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViews()
        setupClickListeners()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        val languageItem = menu?.findItem(R.id.action_change_language)
        val currentLocale = AppCompatDelegate.getApplicationLocales().toLanguageTags()
        if (currentLocale.contains("ar")) {
            languageItem?.title = getString(R.string.menu_language_english)
        } else {
            languageItem?.title = getString(R.string.menu_language_arabic)
        }
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_change_language -> {
                toggleAppLanguage()
                true
            }
            R.id.action_close -> {
                finishAffinity() // Closes all activities and the app
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun toggleAppLanguage() {
        val currentLocale = AppCompatDelegate.getApplicationLocales().toLanguageTags()
        val newLang = if (currentLocale.contains("ar")) "en" else "ar"

        val appLocale: LocaleListCompat = LocaleListCompat.forLanguageTags(newLang)
        AppCompatDelegate.setApplicationLocales(appLocale)
    }


    private fun setupViews() {
        // Set up toolbar
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = getString(R.string.app_name)

        // Display statistics (These are static for now)
        binding.apply {
            accuracyKnn.text = "82%"
            accuracyNb.text = "82%"
            accuracyDt.text = "70%"
            recordsCount.text = "303"
            modelsCount.text = "3"
        }
    }

    private fun setupClickListeners() {
        binding.apply {
            // Risk Calculator Card
            cardRiskCalculator.setOnClickListener {
                startActivity(Intent(this@MainActivity, RiskCalculatorActivity::class.java))
            }

            // About Project Card
            cardAbout.setOnClickListener {
                startActivity(Intent(this@MainActivity, AboutActivity::class.java))
            }
        }
    }
}
