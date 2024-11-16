package com.example.weightogo

import android.content.SharedPreferences

class UserSettings(preferences: SharedPreferences) {

    var firstTime: Boolean = preferences.getBoolean("firstTime", true)
    var startWeightLbs: Double = preferences.getFloat("startWeightLbs", 0.0f).toDouble()
    var heightFt: Int = preferences.getInt("heightFt", 0)
    var heightIn: Int = preferences.getInt("heightIn", 0)
    var goalWeight: Double = preferences.getFloat("goalWeight", 0.0f).toDouble()

    fun save(preferences: SharedPreferences) {
        val editor = preferences.edit()
        editor.putBoolean("firstTime", firstTime)
        editor.putFloat("startWeightLbs", startWeightLbs.toFloat())
        editor.putInt("heightFt", heightFt)
        editor.putInt("heightIn", heightIn)
        editor.putFloat("goalWeight", goalWeight.toFloat())
        editor.apply()
    }

    val heightCm: Double
        get() = ((heightFt * 12 + heightIn) * 2.54)
}
