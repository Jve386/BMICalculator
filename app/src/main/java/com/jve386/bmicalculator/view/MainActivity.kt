package com.jve386.bmicalculator.view

import android.content.res.ColorStateList
import android.os.Bundle
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.cardview.widget.CardView
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.slider.RangeSlider
import com.jve386.bmicalculator.R
import com.jve386.bmicalculator.view.ui.theme.BMIcalculatorTheme
import java.text.DecimalFormat

class MainActivity : ComponentActivity() {

    private var isMaleSelected: Boolean = true
    private var isFemaleSelected: Boolean = false
    private var currentWeight: Int = 60
    private var currentAge: Int = 25

    private lateinit var viewMale: CardView
    private lateinit var viewFemale: CardView
    private lateinit var tvHeight: TextView
    private lateinit var rsHeight: RangeSlider
    private lateinit var btnSubtractWeight: FloatingActionButton
    private lateinit var btnPlusWeight: FloatingActionButton
    private lateinit var tvWeight: TextView
    private lateinit var btnPlusAge: FloatingActionButton
    private lateinit var btnSubtractAge: FloatingActionButton
    private lateinit var tvAge:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        initComponents()
        initListeners()
        initUI()
    }


    private fun initComponents() {
        viewMale = findViewById(R.id.viewMale)
        viewFemale = findViewById(R.id.viewFemale)
        tvHeight = findViewById(R.id.tvHeight)
        rsHeight = findViewById(R.id.rsHeight)
        //added bg directly to variable to fix a bug with float buttons
        btnSubtractWeight = findViewById(R.id.btnSubtractWeight)
        btnSubtractWeight.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(this, R.color.background_fab))
        btnPlusWeight = findViewById(R.id.btnPlusWeight)
        btnPlusWeight.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(this, R.color.background_fab))
        tvWeight = findViewById(R.id.tvWeight)

        btnSubtractAge = findViewById(R.id.btnSubtractAge)
        btnSubtractAge.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(this, R.color.background_fab))
        btnPlusAge = findViewById(R.id.btnPlusAge)
        btnPlusAge.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(this, R.color.background_fab))
        tvAge = findViewById(R.id.tvAge)

    }

    private fun initListeners() {
        viewMale.setOnClickListener {
            changeGender()
            setGenderColor()
        }
        viewFemale.setOnClickListener {
            changeGender()
            setGenderColor()
        }
        rsHeight.addOnChangeListener { _, value, _ ->
            val df = DecimalFormat("#.##")
            val result = df.format(value)
            tvHeight.text = "$result cm"
        }
        btnPlusWeight.setOnClickListener {
            currentWeight ++
            setWeight()
        }
        btnSubtractWeight.setOnClickListener {
            currentWeight --
            setWeight()
        }
        btnPlusAge.setOnClickListener {
            currentAge ++
            setAge()
        }
        btnSubtractAge.setOnClickListener {
            currentAge --
            setAge()
        }
    }

    private fun setWeight(){
        tvWeight.text = currentWeight.toString()
    }
    private fun setAge(){
        tvAge.text = currentAge.toString()
    }

    private fun changeGender() {
        isMaleSelected = !isMaleSelected
        isFemaleSelected = !isFemaleSelected
    }

    private fun setGenderColor() {

        viewMale.setCardBackgroundColor(getBackGroundColor(isMaleSelected))
        viewFemale.setCardBackgroundColor(getBackGroundColor(isFemaleSelected))

    }

    private fun getBackGroundColor(isSelectedComponent: Boolean): Int {
        val colorReference = if (isSelectedComponent) {
            R.color.background_component_selected
        } else {
            R.color.background_component
        }

        return ContextCompat.getColor(this, colorReference)
    }

    private fun initUI() {
        setGenderColor()
        setWeight()
        setAge()
    }
}
