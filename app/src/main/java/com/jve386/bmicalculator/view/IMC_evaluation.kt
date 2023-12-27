package com.jve386.bmicalculator.view

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import com.jve386.bmicalculator.R
import com.jve386.bmicalculator.view.MainActivity.Companion.IMC_KEY
import com.jve386.bmicalculator.view.ui.theme.BMIcalculatorTheme

@Suppress("DEPRECATION")

class IMC_evaluation : ComponentActivity() {

    private lateinit var tvResult: TextView
    private lateinit var tvIMC: TextView
    private lateinit var tvDescription: TextView
    private lateinit var btnRecalculate: Button
    private lateinit var tvAdvice: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.imc_evaluation)
        val result: Double = intent.extras?.getDouble(IMC_KEY) ?: -1.0
        initComponents()
        initUI(result)
        initListeners()
    }

    private fun initListeners() {
        btnRecalculate.setOnClickListener { onBackPressed() }
    }


    private fun initUI(result: Double) {
        tvIMC.text = result.toString()
        val (title, textColor, description) = when (result) {
            in 0.00..18.50 -> Triple(getString(R.string.title_lowWeight), R.color.weight_low, getString(R.string.description_lowWeight))
            in 18.51..24.99 -> Triple(getString(R.string.title_normalWeight), R.color.weight_normal, getString(R.string.description_normalWeight))
            in 25.00..29.99 -> Triple(getString(R.string.title_overWeight), R.color.weight_over, getString(R.string.description_overWeight))
            in 30.00..99.00 -> Triple(getString(R.string.title_obeseWeight), R.color.weight_obese, getString(R.string.description_obeseWeight))
            else -> Triple(getString(R.string.error), R.color.weight_obese, getString(R.string.error))
        }

        tvResult.text = title
        tvResult.setTextColor(ContextCompat.getColor(this, textColor))
        tvDescription.text = description
        tvAdvice.text = getString(R.string.advice)
    }

    private fun initComponents() {
        tvResult = findViewById(R.id.tvResult)
        tvIMC = findViewById(R.id.tvIMC)
        tvDescription = findViewById(R.id.tvDescription)
        btnRecalculate = findViewById(R.id.btnRecalculate)
        tvAdvice = findViewById(R.id.advice)
    }
}
