package com.example.weighttogo

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.weighttogo.ui.theme.WeighttogoTheme

class SettingsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeighttogoTheme {
                SettingsScreen(onSave = { beginningWeight, height ->
                    saveUserSettings(beginningWeight, height)
                })
            }
        }
    }

    private fun saveUserSettings(beginningWeight: String, height: String) {
        // Save to SharedPreferences
        val sharedPreferences = getSharedPreferences("userSettings", Context.MODE_PRIVATE)
        with(sharedPreferences.edit()) {
            putString("beginningWeight", beginningWeight)
            putString("height", height)
            apply()
        }

        // Notify user and finish activity
        Toast.makeText(this, "Settings saved successfully!", Toast.LENGTH_SHORT).show()
        finish()
    }
}

@Composable
fun SettingsScreen(onSave: (String, String) -> Unit) {
    var beginningWeight by remember { mutableStateOf("") }
    var height by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Settings") }
            )
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Beginning Weight Input
                OutlinedTextField(
                    value = beginningWeight,
                    onValueChange = { beginningWeight = it },
                    label = { Text("Beginning Weight") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth()
                )

                // Height Input
                OutlinedTextField(
                    value = height,
                    onValueChange = { height = it },
                    label = { Text("Height (cm)") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth()
                )

                // Save Button
                Button(
                    onClick = { onSave(beginningWeight, height) },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Save")
                }
            }
        }
    )
}
