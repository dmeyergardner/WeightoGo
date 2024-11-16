package com.example.weightogo

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Congrats : AppCompatActivity() {
    @Inject lateinit var weightEntryRepository: WeightEntryRepository
    @Inject lateinit var userSettings: UserSettings
    /* Cite Seifken 2024
     * Shows a Congratulations message and icon when user has met their goal.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.enableEdgeToEdge()
        setContentView(R.layout.activity_congrats)
        ViewCompat.setOnApplyWindowInsetsListener(
            findViewById(R.id.congrats_layout)
        ) { v: View, insets: WindowInsetsCompat ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Get an instance of the Calculator so we can find the "started on" date.
        val calculator = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            BMIcalculation(weightEntryRepository.getAll(), userSettings)
        } else {
            BMIcalculationLegacy(weightEntryRepository.getAll(), userSettings)
    }

    // switch activities
    fun congratulationsToSummary(v: View?) {
        startActivity(Intent(this@CongratsActivity, SummaryActivity::class.java))
    }
}
