package com.example.weightogo

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.weightogo.data.WeightToGoDatabase
import kotlinx.coroutines.launch

class `9ProfileEditActivity` : AppCompatActivity() {

    companion object {
        const val REQUEST_IMAGE_PICK = 1 // Unique request code for image picking
    }

    private lateinit var profilePic: ImageView
    private lateinit var firstNameInput: EditText
    private lateinit var lastNameInput: EditText
    private lateinit var genderInput: AutoCompleteTextView
    private lateinit var saveButton: Button
    private lateinit var cancelButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_edit)

        // Initialize form elements
        profilePic = findViewById(R.id.profilePic)
        firstNameInput = findViewById(R.id.firstNameInput)
        lastNameInput = findViewById(R.id.lastNameInput)
        genderInput = findViewById(R.id.genderInput)
        saveButton = findViewById(R.id.saveButton)
        cancelButton = findViewById(R.id.cancelButton)

        val cameraIcon = findViewById<ImageView>(R.id.cameraIcon)

        // Gender selection options
        val genderOptions = arrayOf("Male", "Female", "Non-Binary", "Doesn’t Matter")
        val genderAdapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, genderOptions)
        genderInput.setAdapter(genderAdapter)

        // Profile picture selection
        profilePic.setOnClickListener { openImagePicker() }
        cameraIcon.setOnClickListener { openImagePicker() }

        // Handle Cancel button click
        cancelButton.setOnClickListener {
            clearInputs()
        }

        // Handle Save button click
        saveButton.setOnClickListener {
            saveProfileData()
        }

        // Load existing profile data
        loadProfileData()
    }

    // Method to open the image picker
    private fun openImagePicker() {
        try {
            val intent = Intent(Intent.ACTION_PICK).apply {
                type = "image/*" // Only allow image files
            }
            startActivityForResult(intent, REQUEST_IMAGE_PICK)
        } catch (e: Exception) {
            Toast.makeText(this, "Unable to open image picker", Toast.LENGTH_SHORT).show()
        }
    }

    // Handle the result of image selection
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_PICK && resultCode == Activity.RESULT_OK) {
            val imageUri: Uri? = data?.data
            if (imageUri != null) {
                profilePic.tag = imageUri.toString() // Save URI as a tag
                Glide.with(this)
                    .load(imageUri)
                    .placeholder(R.drawable.ic_account_circle)
                    .into(profilePic)
            } else {
                Toast.makeText(this, "No image selected", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Clear all input fields
    private fun clearInputs() {
        firstNameInput.text.clear()
        lastNameInput.text.clear()
        genderInput.text.clear()
        profilePic.setImageResource(R.drawable.ic_account_circle) // Reset to placeholder image
        Toast.makeText(this, "Inputs cleared", Toast.LENGTH_SHORT).show()
    }

    // Save profile data to the database
    private fun saveProfileData() {
        val firstName = firstNameInput.text.toString()
        val lastName = lastNameInput.text.toString()
        val gender = genderInput.text.toString()
        val profilePicUri = profilePic.tag?.toString() ?: "" // Get URI from tag or default to empty

        if (firstName.isEmpty() || lastName.isEmpty() || gender.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
        } else {
            val profile = UserProfile(
                firstName = firstName,
                lastName = lastName,
                gender = gender,
                profilePicUri = profilePicUri
            )

            // Save to database
            lifecycleScope.launch {
                val db = WeightToGoDatabase.getDatabase(this@`9ProfileEditActivity`)
                db.userProfileDao().insertUserProfile(profile)
                Toast.makeText(this@`9ProfileEditActivity`, "Profile saved successfully!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Load existing profile data from the database
    private fun loadProfileData() {
        lifecycleScope.launch {
            val db = WeightToGoDatabase.getDatabase(this@`9ProfileEditActivity`)
            val profile = db.userProfileDao().getUserProfileById(1) // Assume user ID is 1

            if (profile != null) {
                firstNameInput.setText(profile.firstName)
                lastNameInput.setText(profile.lastName)
                genderInput.setText(profile.gender)
                if (profile.profilePicUri.isNotEmpty()) {
                    Glide.with(this@`9ProfileEditActivity`)
                        .load(Uri.parse(profile.profilePicUri))
                        .placeholder(R.drawable.ic_account_circle)
                        .into(profilePic)
                    profilePic.tag = profile.profilePicUri // Save URI to tag for saving
                }
            }
        }
    }
}
