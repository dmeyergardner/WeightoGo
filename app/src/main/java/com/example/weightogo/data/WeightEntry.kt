package com.example.weightogo.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "weight_entries")
data class WeightEntry(
    @PrimaryKey(autoGenerate = true) val id: Int = 0, // Auto-generated primary key
    val entryDate: LocalDate, // Date of the weight entry
    val weightGoal: Double, // Target weight
    val height: Double, // Height in cm
    val bmi: Double, // Calculated BMI
    val temperature: Double, // User’s temperature (optional data point)
    val targetCalories: Int // Target daily calorie intake
)
