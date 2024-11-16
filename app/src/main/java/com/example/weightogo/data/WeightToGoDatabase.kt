package com.example.weightogo.data

import WeightEntryDao
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.weightogo.UserProfile
import com.example.weightogo.UserProfileDao

@Database(entities = [WeightEntry::class, UserProfile::class], version = 1, exportSchema = false)
abstract class WeightToGoDatabase : RoomDatabase() {
    abstract fun weightEntryDao(): WeightEntryDao
    abstract fun userProfileDao(): UserProfileDao

    companion object {
        @Volatile
        private var INSTANCE: WeightToGoDatabase? = null

        fun getDatabase(context: Context): WeightToGoDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WeightToGoDatabase::class.java,
                    "weight_to_go_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
