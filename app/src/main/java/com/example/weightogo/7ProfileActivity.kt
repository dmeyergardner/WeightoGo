package com.example.weightogo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.weightogo.data.WeightEntry

class `7ProfileActivity` : AppCompatActivity() {

    private lateinit var userSettings: UserSettings

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        // Load UserSettings
        val sharedPreferences = getSharedPreferences("UserSettings", Context.MODE_PRIVATE)
        userSettings = UserSettings(sharedPreferences)

        // Update user profile details dynamically
        val userNameTextView = findViewById<TextView>(R.id.userName)
        userNameTextView.text = "Amelia Renata" // Replace with actual user data if available

        val bmiTextView = findViewById<TextView>(R.id.bmiText)
        val bmiCalculation = BMIcalculation(getDummyWeightEntries(), userSettings)
        bmiTextView.text = String.format("%.1f", bmiCalculation.getBMI())

        val calorieTextView = findViewById<TextView>(R.id.calorieText)
        calorieTextView.text = "${userSettings.goalWeight}cal" // Example data

        val weightTextView = findViewById<TextView>(R.id.weightText)
        weightTextView.text = "${userSettings.startWeightLbs}lbs"

        // Navigation setup
        setupNavigationListeners()
        setupBottomNavigation()
    }

    private fun setupNavigationListeners() {
        findViewById<LinearLayout>(R.id.biometricsLayout).setOnClickListener {
            startActivity(Intent(this, `8BiometricsActivity`::class.java))
        }

        findViewById<LinearLayout>(R.id.historyLayout).setOnClickListener {
            startActivity(Intent(this, `6HistoryActivity`::class.java))
        }

        findViewById<LinearLayout>(R.id.accountSettingsLayout).setOnClickListener {
            startActivity(Intent(this, AccountSettingsActivity::class.java))
        }

        findViewById<LinearLayout>(R.id.faqsLayout).setOnClickListener {
            startActivity(Intent(this, FAQsActivity::class.java))
        }

        findViewById<LinearLayout>(R.id.helpLayout).setOnClickListener {
            startActivity(Intent(this, HelpActivity::class.java))
        }

        findViewById<ImageView>(R.id.profilePicture).setOnClickListener {
            // Add logic to change profile picture
        }
    }

    private fun setupBottomNavigation() {
        val bottomNavigationView = findViewById<com.google.android.material.bottomnavigation.BottomNavigationView>(R.id.bottomNavigationView)
        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_home -> {
                    startActivity(Intent(this, HomeActivity::class.java))
                    true
                }
                R.id.menu_messages -> {
                    startActivity(Intent(this, MessagesActivity::class.java))
                    true
                }
                R.id.menu_weight -> {
                    startActivity(Intent(this, WeightActivity::class.java))
                    true
                }
                R.id.menu_profile -> true // Already on profile
                else -> false
            }
        }
    }

    private fun getDummyWeightEntries(): List<WeightEntry> {
        // Replace this with actual database or data source logic
        return listOf(
            WeightEntry(entryDate = java.time.LocalDate.now().minusDays(30), entryWeight = 150.0),
            WeightEntry(entryDate = java.time.LocalDate.now(), entryWeight = 145.0)
        )
    }
}
