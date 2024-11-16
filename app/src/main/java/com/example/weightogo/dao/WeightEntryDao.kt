package com.example.weightogo.dao

import androidx.room.*
import com.example.weightogo.data.WeightEntry

@Dao
interface WeightEntryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeightEntry(entry: WeightEntry)

    @Delete
    suspend fun deleteWeightEntry(entry: WeightEntry)

    @Query("SELECT * FROM weight_entry WHERE entryDate = :entryDate LIMIT 1")
    suspend fun getWeightEntry(entryDate: String): WeightEntry?

    @Query("SELECT * FROM weight_entry ORDER BY entryDate ASC")
    suspend fun getAllWeightEntries(): List<WeightEntry>
}
