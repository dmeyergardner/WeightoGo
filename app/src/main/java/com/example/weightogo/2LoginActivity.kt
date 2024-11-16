package com.example.weightogo

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_login.*

class `2LoginActivity` : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Handle Sign In button click
        signInButton.setOnClickListener {
            val email = emailInput.text.toString().trim()
            val password = passwordInput.text.toString().trim()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please enter both email and password", Toast.LENGTH_SHORT).show()
            } else {
                if (validateCredentials(email, password)) {
                    // Navigate to Dashboard on successful login
                    val intent = Intent(this, `4DashboardActivity`::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this, "Invalid credentials. Please try again.", Toast.LENGTH_SHORT).show()
                }
            }
        }

        // Handle Forgot Password click
        forgotPassword.setOnClickListener {
            // Navigate to Forgot Password screen
            val intent = Intent(this, ForgotPasswordActivity::class.java)
            startActivity(intent)
        }

        // Handle Register click
        registerText.setOnClickListener {
            // Navigate to Register screen
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    // Validate credentials (placeholder logic)
    private fun validateCredentials(email: String, password: String): Boolean {
        // Replace with actual authentication logic (e.g., Firebase, API call)
        return email == "test@example.com" && password == "password123"
    }
}
