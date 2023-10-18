package com.example.inputactivity

import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
class ResultActivity : AppCompatActivity() {
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var ageTextView: TextView
    private lateinit var weightTextView: TextView
    private lateinit var heightTextView: TextView
    private lateinit var genderTextView: TextView
    private lateinit var bmrTextView: TextView
    private lateinit var calorieNeedsTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        sharedPreferences = getSharedPreferences("MetabolismApp", MODE_PRIVATE)

        ageTextView = findViewById(R.id.ageTextView)
        weightTextView = findViewById(R.id.weightTextView)
        heightTextView = findViewById(R.id.heightTextView)
        genderTextView = findViewById(R.id.genderTextView)
        bmrTextView = findViewById(R.id.bmrTextView)
        calorieNeedsTextView = findViewById(R.id.calorieNeedsTextView)

        val age = sharedPreferences.getInt("age", 0)
        val weight = sharedPreferences.getFloat("weight", 0.0f)
        val height = sharedPreferences.getFloat("height", 0.0f)
        val isMale = sharedPreferences.getBoolean("isMale", true)
        val bmr = sharedPreferences.getFloat("bmr", 0.0f)
        val calorieNeeds = sharedPreferences.getFloat("calorieNeeds", 0.0f)

        ageTextView.text = "Age: $age years"
        weightTextView.text = "Weight: ${"%.2f".format(weight)} kg"
        heightTextView.text = "Height: ${"%.2f".format(height)} cm"
        genderTextView.text = if (isMale) "Gender: Male" else "Gender: Female"
        bmrTextView.text = "BMR: ${"%.2f".format(bmr)}"
        calorieNeedsTextView.text = "Calorie Needs: ${"%.2f".format(calorieNeeds)}"
    }
}
