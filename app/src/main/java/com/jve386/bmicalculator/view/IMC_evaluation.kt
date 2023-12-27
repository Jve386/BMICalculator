package com.jve386.bmicalculator.view

import android.os.Bundle
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
import com.jve386.bmicalculator.R
import com.jve386.bmicalculator.view.MainActivity.Companion.IMC_KEY
import com.jve386.bmicalculator.view.ui.theme.BMIcalculatorTheme

class IMC_evaluation : ComponentActivity() {

    private lateinit var tvResult: TextView
    private lateinit var tvIMC: TextView
    private lateinit var tvDescription: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.imc_evaluation)
        var result:Double = intent.extras?.getDouble(IMC_KEY) ?: -1.0
        initComponents()
        initUI(result)
    }

    private fun initUI(result: Double) {
        when(result){
            in 0.00..18.50 -> { //Bajo peso
                tvIMC
                tvResult
                tvDescription
            }
            in 18.51..24.99 -> { //Peso normal
                tvIMC
                tvResult
                tvDescription
            }
            in 25.00..29.99 -> { //Sobrepeso
                tvIMC
                tvResult
                tvDescription
            }
            in 30.00..99.00 -> { //Obesidad
                tvIMC
                tvResult
                tvDescription
            }
            else -> { //error
                tvIMC.text = "error"
                tvResult.text = "error"
                tvDescription.text = "error"
            }
        }
    }

    private fun initComponents() {
        tvResult = findViewById(R.id.tvResult)
        tvIMC = findViewById(R.id.tvIMC)
        tvDescription = findViewById(R.id.tvDescription)
    }
}
