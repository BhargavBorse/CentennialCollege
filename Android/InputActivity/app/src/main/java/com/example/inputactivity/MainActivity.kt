package com.example.inputactivity

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import android.widget.EditText
import android.widget.RadioButton
import android.widget.CheckBox

class InputActivity : AppCompatActivity() {
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sharedPreferences = getSharedPreferences("MetabolismApp", MODE_PRIVATE)
    }

    fun calculateMetabolism(view: View) {
        val ageEditText: EditText = findViewById(R.id.ageEditText)
        val weightEditText: EditText = findViewById(R.id.weightEditText)
        val heightEditText: EditText = findViewById(R.id.heightEditText)
        val maleRadioButton: RadioButton = findViewById(R.id.maleRadioButton)
        val femaleRadioButton: RadioButton = findViewById(R.id.femaleRadioButton)
        val noExerciseRadioButton: RadioButton = findViewById(R.id.noExerciseRadioButton)
        val lightExerciseRadioButton: RadioButton = findViewById(R.id.lightExerciseRadioButton)
        val moderateExerciseRadioButton: RadioButton = findViewById(R.id.moderateExerciseRadioButton)
        val hardExerciseRadioButton: RadioButton = findViewById(R.id.hardExerciseRadioButton)
        val webPageCheckBox: CheckBox = findViewById(R.id.webPageCheckBox)


        val age = ageEditText.text.toString().toInt()
        val weight = weightEditText.text.toString().toDouble()
        val height = heightEditText.text.toString().toDouble()
        val isMale = maleRadioButton.isChecked
        val activityLevel = when {
            noExerciseRadioButton.isChecked -> 1.2
            lightExerciseRadioButton.isChecked -> 1.375
            moderateExerciseRadioButton.isChecked -> 1.55
            hardExerciseRadioButton.isChecked -> 1.725
            else -> 1.0
        }

        val bmr = if (isMale) {
            (10 * weight) + (6.25 * height) - (5 * age) + 5
        } else {
            (10 * weight) + (6.25 * height) - (5 * age) - 161
        }

        val calorieNeeds = bmr * activityLevel

        val editor = sharedPreferences.edit()
        editor.putInt("age", age)
        editor.putFloat("weight", weight.toFloat())
        editor.putFloat("height", height.toFloat())
        editor.putBoolean("isMale", isMale)
        editor.putFloat("bmr", bmr.toFloat())
        editor.putFloat("calorieNeeds", calorieNeeds.toFloat())
        editor.apply()

        val intent = Intent(this, ResultActivity::class.java)
        startActivity(intent)
    }
}
