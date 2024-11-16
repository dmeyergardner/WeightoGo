package com.example.weightogo

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

/** Cite Seifken 2024
 * Shows a friendly message when finished with vitals/goals setup.
 */
class DoneActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.enableEdgeToEdge()
        setContentView(R.layout.activity_done)
        ViewCompat.setOnApplyWindowInsetsListener(
            findViewById<View>(R.id.done_layout)
        ) { v: View, insets: WindowInsetsCompat ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    // onclick nav handler
    fun doneToSummary(v: View?) {
        startActivity(Intent(this@DoneActivity, DashboardActivity::class.java))
    }

    // onclick nav handler
    fun doneToGoals(v: View?) {
        startActivity(Intent(this@DoneActivity, GoalsActivity::class.java))
    }
}