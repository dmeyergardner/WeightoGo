package com.example.weightogo

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.weightogo.data.WeightEntry
import com.example.weightogo.data.WeightEntryRepository
import com.example.weightogo.utils.BMICalculation
import java.text.DecimalFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class DashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        // Initialize buttons
        val addWeightButton = findViewById<ImageButton>(R.id.addWeightButton)
        val historyButton = findViewById<ImageButton>(R.id.historyButton)
        val statsButton = findViewById<ImageButton>(R.id.statsButton)
        val settingsButton = findViewById<ImageButton>(R.id.settingsButton)

        // Handle Add Weight button click
        addWeightButton.setOnClickListener {
            val intent = Intent(this, AddWeightActivity::class.java)
            startActivity(intent)
        }

        // Handle History button click
        historyButton.setOnClickListener {
            val intent = Intent(this, HistoryActivity::class.java)
            startActivity(intent)
        }

        // Handle Stats button click
        statsButton.setOnClickListener {
            val intent = Intent(this, StatsActivity::class.java)
            startActivity(intent)
        }

        // Handle Settings button click
        settingsButton.setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }

        // Initialize BMI calculations
        setupBMIMetrics()

        // Bottom Navigation
        setupBottomNavigation()
    }

    private fun setupBMIMetrics() {
        // Retrieve user settings and repository
        val app = applicationContext as WeightToGoApplication
        val userSettings = app.getUserSettings()
        val repository = app.getRepository(applicationContext)

        // Perform BMI calculations
        val calculator = BMICalculation(repository.getAllEntries(), userSettings)
        val df = DecimalFormat("#0.0")

        // Update metrics in the UI
        findViewById<TextView>(R.id.valueBMI).text = df.format(calculator.getBMI())
        findViewById<TextView>(R.id.valueWeeklyAvgLoss).text = df.format(calculator.getAverageWeeklyLoss())
        findViewById<TextView>(R.id.valueTotalLoss).text = df.format(calculator.getTotalLoss())

        val valueLeftToGoal = findViewById<TextView>(R.id.valueLeftToGoal)
        val labelLeftToGoal = findViewById<TextView>(R.id.labelLeftToGoal)

        // Show or hide "Left to Goal" based on user settings
        if (userSettings.goalWeight == 0.0) {
            valueLeftToGoal.visibility = View.INVISIBLE
            labelLeftToGoal.visibility = View.INVISIBLE
        } else {
            valueLeftToGoal.visibility = View.VISIBLE
            labelLeftToGoal.visibility = View.VISIBLE
            valueLeftToGoal.text = df.format(calculator.getLeftToGoal())
        }

        // Show the congratulations button if the goal is met
        val buttonHeart = findViewById<ImageButton>(R.id.buttonHeart)
        if (calculator.getLeftToGoal() <= 0) {
            buttonHeart.visibility = View.VISIBLE
            buttonHeart.setOnClickListener {
                val intent = Intent(this, CongratulationsActivity::class.java)
                startActivity(intent)
            }
        } else {
            buttonHeart.visibility = View.INVISIBLE
        }
    }

    private fun setupBottomNavigation() {
        findViewById<ImageButton>(R.id.homeNav).setOnClickListener {
            // Already on the home screen
        }

        findViewById<ImageButton>(R.id.messagesNav).setOnClickListener {
            val intent = Intent(this, MessagesActivity::class.java)
            startActivity(intent)
        }

        findViewById<ImageButton>(R.id.weightNav).setOnClickListener {
            val intent = Intent(this, WeightActivity::class.java)
            startActivity(intent)
        }

        findViewById<ImageButton>(R.id.profileNav).setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }
    }
}
