package com.example.weightogo

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class `1SplashActivity` : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // Optional: Add a delay before showing buttons (e.g., for splash branding)
        Handler(Looper.getMainLooper()).postDelayed({
            // Enable buttons after the splash delay
            findViewById<Button>(R.id.loginButton).isEnabled = true
            findViewById<Button>(R.id.signUpButton).isEnabled = true
        }, 2000) // 2-second delay

        // Find buttons
        val loginButton = findViewById<Button>(R.id.loginButton)
        val signUpButton = findViewById<Button>(R.id.signUpButton)

        // Navigate to LoginActivity
        loginButton.setOnClickListener {
            val intent = Intent(this, `2LoginActivity`::class.java)
            startActivity(intent)
        }

        // Navigate to SignUpActivity
        signUpButton.setOnClickListener {
            val intent = Intent(this, `3SignUpActivity`::class.java)
            startActivity(intent)
        }
    }
}
