package com.example.weightogo

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_signup.*

class `3SignUpActivity` : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        // Initialize Firebase Auth and Firestore
        firebaseAuth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()

        // Handle Sign Up button click
        signUpButton.setOnClickListener {
            val fullName = fullNameInput.text.toString().trim()
            val email = emailInput.text.toString().trim()
            val password = passwordInput.text.toString().trim()
            val confirmPassword = passwordConfirmInput.text.toString().trim()

            if (validateInput(fullName, email, password, confirmPassword)) {
                registerUser(fullName, email, password)
            }
        }

        // Handle "Sign In" text click
        signInText.setOnClickListener {
            // Navigate to Login screen
            val intent = Intent(this, `2LoginActivity`::class.java)
            startActivity(intent)
            finish()
        }
    }

    // Validate user input
    private fun validateInput(fullName: String, email: String, password: String, confirmPassword: String): Boolean {
        return when {
            fullName.isEmpty() -> {
                showToast("Full name is required")
                false
            }
            email.isEmpty() -> {
                showToast("Email is required")
                false
            }
            !isValidEmail(email) -> {
                showToast("Please enter a valid email")
                false
            }
            password.isEmpty() -> {
                showToast("Password is required")
                false
            }
            password.length < 6 -> {
                showToast("Password must be at least 6 characters")
                false
            }
            confirmPassword.isEmpty() -> {
                showToast("Please confirm your password")
                false
            }
            password != confirmPassword -> {
                showToast("Passwords do not match")
                false
            }
            else -> true
        }
    }

    // Register user with Firebase Authentication
    private fun registerUser(fullName: String, email: String, password: String) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    saveUserToFirestore(fullName, email)
                } else {
                    showToast("Registration failed: ${task.exception?.message}")
                }
            }
    }

    // Save user details to Firestore
    private fun saveUserToFirestore(fullName: String, email: String) {
        val userId = firebaseAuth.currentUser?.uid ?: return
        val user = hashMapOf(
            "fullName" to fullName,
            "email" to email,
            "userId" to userId
        )

        firestore.collection("users")
            .document(userId)
            .set(user)
            .addOnSuccessListener {
                showToast("Sign-up successful!")
                navigateToLogin()
            }
            .addOnFailureListener { e ->
                showToast("Error saving user details: ${e.message}")
            }
    }

    // Simple email validation
    private fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    // Show a toast message
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    // Navigate to LoginActivity
    private fun navigateToLogin() {
        val intent = Intent(this, `2LoginActivity`::class.java)
        startActivity(intent)
        finish()
    }
}
