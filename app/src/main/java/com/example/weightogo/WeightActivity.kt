package com.example.weightogo

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class WeightActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weight)

        // Initialize form elements
        val newWeightInput = findViewById<TextInputEditText>(R.id.newWeightInput)
        val dateText = findViewById<TextView>(R.id.dateText)
        val timeText = findViewById<TextView>(R.id.timeText)
        val enterButton = findViewById<Button>(R.id.enterButton)
        val cancelButton = findViewById<Button>(R.id.cancelButton)

        // Handle date picker click
        findViewById<ImageView>(R.id.datePickerIcon).setOnClickListener {
            // TODO: Implement date picker logic
        }

        // Handle time picker click
        findViewById<ImageView>(R.id.timePickerIcon).setOnClickListener {
            // TODO: Implement time picker logic
        }

        // Handle Cancel button click
        cancelButton.setOnClickListener {
            // Clear the input fields
            newWeightInput.text?.clear()
        }

        // Handle Enter button click
        enterButton.setOnClickListener {
            val newWeight = newWeightInput.text.toString()

            // Validate and update weight
            if (newWeight.isEmpty()) {
                newWeightInput.error = "Please enter your weight"
            } else {
                // TODO: Implement logic to save the new weight and update the progress bar
            }
        }
    }

    private lateinit var dbHelper: WeightDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weight)

        dbHelper = WeightDatabaseHelper(this)

        val weightInput = findViewById<TextInputEditText>(R.id.newWeightInput)
        val enterButton = findViewById<Button>(R.id.enterButton)

        enterButton.setOnClickListener {
            val weight = weightInput.text.toString().toDoubleOrNull()

            if (weight != null) {
                // Get current date and time
                val date = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date())
                val time = SimpleDateFormat("hh:mm a", Locale.getDefault()).format(Date())

                // Save the entry in SQLite
                val result = dbHelper.addWeightEntry(weight, date, time)

                if (result > 0) {
                    Toast.makeText(this, "Weight entry added", Toast.LENGTH_SHORT).show()
                    weightInput.text?.clear() // Clear the input field
                } else {
                    Toast.makeText(this, "Error adding entry", Toast.LENGTH_SHORT).show()
                }
            } else {
                weightInput.error = "Please enter a valid weight"
            }
        }
    }
}