package com.jve386.bmicalculator.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.slider.RangeSlider
import com.jve386.bmicalculator.R
import java.text.DecimalFormat

class MainActivity : ComponentActivity() {

    private var isMaleSelected: Boolean = true
    private var isFemaleSelected: Boolean = false
    private var currentWeight: Int = 70
    private var currentAge: Int = 25
    private var currentHeight: Int = 120

    private lateinit var rsHeight: RangeSlider
    private lateinit var tvWeight: TextView
    private lateinit var tvAge: TextView
    private lateinit var btnCalculate: Button

    companion object {
        const val IMC_KEY = "IMC_RESULT"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        initComponents()
        initListeners()
        initUI()
    }

    private fun initComponents() {
        rsHeight = findViewById(R.id.rsHeight)
        btnCalculate = findViewById(R.id.btnCalculate)

        findViewById<FloatingActionButton>(R.id.btnSubtractWeight).apply {
            backgroundTintList = getColorStateList(R.color.background_fab)
            setOnClickListener { updateWeight(-1) }
        }

        findViewById<FloatingActionButton>(R.id.btnPlusWeight).apply {
            backgroundTintList = getColorStateList(R.color.background_fab)
            setOnClickListener { updateWeight(1) }
        }

        findViewById<FloatingActionButton>(R.id.btnSubtractAge).apply {
            backgroundTintList = getColorStateList(R.color.background_fab)
            setOnClickListener { updateAge(-1) }
        }

        findViewById<FloatingActionButton>(R.id.btnPlusAge).apply {
            backgroundTintList = getColorStateList(R.color.background_fab)
            setOnClickListener { updateAge(1) }
        }

        tvWeight = findViewById(R.id.tvWeight)
        tvAge = findViewById(R.id.tvAge)
    }

    private fun initListeners() {
        findViewById<TextView>(R.id.tvHeight).apply {
            rsHeight.addOnChangeListener { _, value, _ ->
                currentHeight = value.toInt()
                text = "$currentHeight cm"
            }
        }

        findViewById<Button>(R.id.btnCalculate).setOnClickListener {
            val result = calculateIMC()
            navigateToResult(result)
        }

        findViewById<CardView>(R.id.viewMale).setOnClickListener { toggleGender() }
        findViewById<CardView>(R.id.viewFemale).setOnClickListener { toggleGender() }
    }

    private fun navigateToResult(result: Double) {
        val intent = Intent(this, IMC_evaluation::class.java)
        intent.putExtra(IMC_KEY, result)
        startActivity(intent)
    }

    private fun calculateIMC(): Double {
        val df = DecimalFormat("#.##")
        val imc = currentWeight / (currentHeight.toDouble() / 100 * currentHeight.toDouble() / 100)
        return df.format(imc).toDouble()
    }

    private fun updateWeight(value: Int) {
        currentWeight += value
        tvWeight.text = currentWeight.toString()
    }

    private fun updateAge(value: Int) {
        currentAge += value
        tvAge.text = currentAge.toString()
    }

    private fun toggleGender() {
        isMaleSelected = !isMaleSelected
        isFemaleSelected = !isFemaleSelected
        setGenderColor()
    }

    private fun setGenderColor() {
        val maleColor = if (isMaleSelected) R.color.background_component_selected else R.color.background_component
        val femaleColor = if (isFemaleSelected) R.color.background_component_selected else R.color.background_component

        findViewById<CardView>(R.id.viewMale).setCardBackgroundColor(ContextCompat.getColor(this, maleColor))
        findViewById<CardView>(R.id.viewFemale).setCardBackgroundColor(ContextCompat.getColor(this, femaleColor))
    }

    private fun initUI() {
        setGenderColor()
        tvWeight.text = currentWeight.toString()
        tvAge.text = currentAge.toString()
    }
}
