package com.example.weightogo

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class `8BiometricsActivity` : AppCompatActivity() {

    private lateinit var weightGoalInput: EditText
    private lateinit var weightGoalDateInput: EditText
    private lateinit var heightInput: EditText
    private lateinit var temperatureInput: EditText
    private lateinit var calorieIntakeInput: EditText
    private lateinit var saveButton: Button
    private lateinit var cancelButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_biometrics)

        // Initialize UI elements
        weightGoalInput = findViewById(R.id.weightGoalInput)
        weightGoalDateInput = findViewById(R.id.weightGoalDateInput)
        heightInput = findViewById(R.id.heightInput)
        temperatureInput = findViewById(R.id.temperatureInput)
        calorieIntakeInput = findViewById(R.id.calorieIntakeInput)
        saveButton = findViewById(R.id.saveButton)
        cancelButton = findViewById(R.id.cancelButton)

        // Add TextWatcher for validation
        val validatingTextWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                validateFields()
            }
        }

        weightGoalInput.addTextChangedListener(validatingTextWatcher)
        heightInput.addTextChangedListener(validatingTextWatcher)
        calorieIntakeInput.addTextChangedListener(validatingTextWatcher)
        temperatureInput.addTextChangedListener(validatingTextWatcher)

        // Load previously saved data
        loadBiometricData()

        // Set up Save button
        saveButton.setOnClickListener {
            saveBiometricData()
            Toast.makeText(this, "Biometric data saved successfully", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, `7ProfileActivity`::class.java)) // Navigate to Profile Activity
        }

        // Set up Cancel button
        cancelButton.setOnClickListener {
            finish() // Close activity and go back to the previous screen
        }
    }

    // Prefill fields with saved data
    private fun loadBiometricData() {
        val sharedPreferences = getSharedPreferences("userSettings", MODE_PRIVATE)
        weightGoalInput.setText(sharedPreferences.getString("weightGoal", ""))
        weightGoalDateInput.setText(sharedPreferences.getString("weightGoalDate", ""))
        heightInput.setText(sharedPreferences.getString("height", ""))
        temperatureInput.setText(sharedPreferences.getString("temperature", ""))
        calorieIntakeInput.setText(sharedPreferences.getString("calorieIntake", ""))
    }

    // Save entered data
    private fun saveBiometricData() {
        val sharedPreferences = getSharedPreferences("userSettings", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("weightGoal", weightGoalInput.text.toString())
        editor.putString("weightGoalDate", weightGoalDateInput.text.toString())
        editor.putString("height", heightInput.text.toString())
        editor.putString("temperature", temperatureInput.text.toString())
        editor.putString("calorieIntake", calorieIntakeInput.text.toString())
        editor.apply()
    }

    // Validate fields
    private fun validateFields() {
        val isWeightGoalValid = weightGoalInput.text.toString().isNotEmpty()
        val isHeightValid = heightInput.text.toString().isNotEmpty()
        val isCalorieIntakeValid = calorieIntakeInput.text.toString().isNotEmpty()
        val isTemperatureValid = temperatureInput.text.toString().isNotEmpty()

        saveButton.isEnabled = isWeightGoalValid && isHeightValid && isCalorieIntakeValid && isTemperatureValid
    }
}
