package com.example.weightogo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_profile")
data class UserProfile(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val startWeight: Float,
    val goalWeight: Float,
    val heightFt: Int,
    val heightIn: Int,
    val isFirstTime: Boolean = true
)
