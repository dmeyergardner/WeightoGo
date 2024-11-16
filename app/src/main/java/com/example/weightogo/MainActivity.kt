package com.example.weightogo

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)

        // Handle navigation item selection
        bottomNavigationView.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.navigation_home -> {
                    // Navigate to DashboardActivity
                    val intent = Intent(this, `4DashboardActivity`::class.java)
                    startActivity(intent)
                    true
                }
                R.id.navigation_messages -> {
                    // Messages button does nothing, placeholder
                    true
                }
                R.id.navigation_weight -> {
                    // Navigate to WeightEntryActivity
                    val intent = Intent(this, `5WeightEntryActivity`::class.java)
                    startActivity(intent)
                    true
                }
                R.id.navigation_profile -> {
                    // Load UserSettingsFragment
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container, UserSettingsFragment())
                        .commit()
                    true
                }
                else -> false
            }
        }

        // Set default screen to DashboardActivity
        if (savedInstanceState == null) {
            val intent = Intent(this, `4DashboardActivity`::class.java)
            startActivity(intent)
        }
    }
}
