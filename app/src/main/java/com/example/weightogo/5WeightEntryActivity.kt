package com.example.weightogo

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.weightogo.data.WeightEntry
import com.example.weightogo.data.WeightToGoDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

class `5WeightEntryActivity` : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weight_goal)

        // Initialize form elements
        val weightGoalDate = findViewById<EditText>(R.id.weightGoalDate)
        val calendarIcon = findViewById<ImageView>(R.id.calendarIcon)
        val weightGoalInput = findViewById<EditText>(R.id.weightGoal)
        val heightInput = findViewById<EditText>(R.id.heightInput)
        val bmiValue = findViewById<TextView>(R.id.bmiValue)
        val temperatureInput = findViewById<EditText>(R.id.temperatureInput)
        val targetCaloriesInput = findViewById<EditText>(R.id.targetCaloriesInput)
        val cancelButton = findViewById<Button>(R.id.cancelButton)
        val saveButton = findViewById<Button>(R.id.saveButton)
        val loadingIndicator = findViewById<ProgressBar>(R.id.loadingIndicator)

        // Setup date picker dialog
        setupDatePicker(weightGoalDate, calendarIcon)

        // Cancel button to reset inputs
        cancelButton.setOnClickListener {
            clearInputs(weightGoalInput, weightGoalDate, heightInput, temperatureInput, targetCaloriesInput, bmiValue)
        }

        // Save button with validation and database integration
        saveButton.setOnClickListener {
            saveData(
                weightGoalInput,
                weightGoalDate,
                heightInput,
                temperatureInput,
                targetCaloriesInput,
                bmiValue,
                loadingIndicator
            )
        }
    }

    /**
     * Set up the DatePickerDialog for the weight goal date.
     */
    private fun setupDatePicker(weightGoalDate: EditText, calendarIcon: ImageView) {
        val calendar = Calendar.getInstance()
        val datePickerDialog = DatePickerDialog(
            this,
            { _, year, month, dayOfMonth ->
                weightGoalDate.setText("${month + 1}/$dayOfMonth/$year")
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        weightGoalDate.setOnClickListener { datePickerDialog.show() }
        calendarIcon.setOnClickListener { datePickerDialog.show() }
    }

    /**
     * Clears all input fields and resets BMI value.
     */
    private fun clearInputs(
        weightGoalInput: EditText,
        weightGoalDate: EditText,
        heightInput: EditText,
        temperatureInput: EditText,
        targetCaloriesInput: EditText,
        bmiValue: TextView
    ) {
        weightGoalInput.text.clear()
        weightGoalDate.text.clear()
        heightInput.text.clear()
        temperatureInput.text.clear()
        targetCaloriesInput.text.clear()
        bmiValue.text = "N/A"
        Toast.makeText(this, "Inputs cleared", Toast.LENGTH_SHORT).show()
    }

    /**
     * Validates and saves the data to the database.
     */
    private fun saveData(
        weightGoalInput: EditText,
        weightGoalDate: EditText,
        heightInput: EditText,
        temperatureInput: EditText,
        targetCaloriesInput: EditText,
        bmiValue: TextView,
        loadingIndicator: ProgressBar
    ) {
        val weightGoal = weightGoalInput.text.toString()
        val height = heightInput.text.toString()
        val temperature = temperatureInput.text.toString()
        val targetCalories = targetCaloriesInput.text.toString()

        if (weightGoal.isEmpty() || height.isEmpty() || temperature.isEmpty() || targetCalories.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
        } else {
            try {
                // Show loading indicator
                loadingIndicator.visibility = ProgressBar.VISIBLE

                // Calculate BMI
                val bmi = calculateBMI(weightGoal.toDouble(), height.toDouble())
                bmiValue.text = String.format("%.2f", bmi)

                // Save data to the database
                lifecycleScope.launch(Dispatchers.IO) {
                    val weightEntry = WeightEntry(
                        date = weightGoalDate.text.toString(),
                        weightGoal = weightGoal.toDouble(),
                        height = height.toDouble(),
                        bmi = bmi,
                        temperature = temperature.toDouble(),
                        targetCalories = targetCalories.toInt()
                    )
                    val db = WeightToGoDatabase.getDatabase(this@`5WeightEntryActivity`)
                    db.weightEntryDao().insertWeightEntry(weightEntry)

                    withContext(Dispatchers.Main) {
                        Toast.makeText(
                            this@`5WeightEntryActivity`,
                            "Data saved successfully!",
                            Toast.LENGTH_SHORT
                        ).show()
                        clearInputs(weightGoalInput, weightGoalDate, heightInput, temperatureInput, targetCaloriesInput, bmiValue)
                        loadingIndicator.visibility = ProgressBar.GONE
                    }
                }
            } catch (e: NumberFormatException) {
                Toast.makeText(this, "Invalid numeric input", Toast.LENGTH_SHORT).show()
            }
        }
    }

    /**
     * Calculates BMI based on weight (kg) and height (cm).
     */
    private fun calculateBMI(weight: Double, height: Double): Double {
        val heightInMeters = height / 100 // Convert height to meters
        return weight / (heightInMeters * heightInMeters)
    }
}