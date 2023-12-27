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
        when (result) {
            in 0.00..18.50 -> { //Bajo peso
                tvResult.text = getString(R.string.title_lowWeight)
                tvResult.setTextColor(ContextCompat.getColor(this, R.color.weight_low))
                tvDescription.text = getString(R.string.description_lowWeight)
                tvAdvice.text = getString(R.string.advice)
            }

            in 18.51..24.99 -> { //Peso normal
                tvResult.text = getString(R.string.title_normalWeight)
                tvResult.setTextColor(ContextCompat.getColor(this, R.color.weight_normal))
                tvDescription.text = getString(R.string.description_normalWeight)
                tvAdvice.text = getString(R.string.advice)
            }

            in 25.00..29.99 -> { //Sobrepeso
                tvResult.text = getString(R.string.title_overWeight)
                tvResult.setTextColor(ContextCompat.getColor(this, R.color.weight_over))
                tvDescription.text = getString(R.string.description_overWeight)
                tvAdvice.text = getString(R.string.advice)
            }

            in 30.00..99.00 -> { //Obesidad
                tvResult.text = getString(R.string.title_obeseWeight)
                tvResult.setTextColor(ContextCompat.getColor(this, R.color.weight_obese))
                tvDescription.text = getString(R.string.description_obeseWeight)
                tvAdvice.text = getString(R.string.advice)
            }

            else -> { //error
                tvIMC.text = getString(R.string.error)
                tvResult.text = getString(R.string.error)
                tvDescription.text = getString(R.string.error)
                tvResult.setTextColor(ContextCompat.getColor(this, R.color.weight_obese))
            }
        }
    }

    private fun initComponents() {
        tvResult = findViewById(R.id.tvResult)
        tvIMC = findViewById(R.id.tvIMC)
        tvDescription = findViewById(R.id.tvDescription)
        btnRecalculate = findViewById(R.id.btnRecalculate)
        tvAdvice = findViewById(R.id.advice)
    }
}
