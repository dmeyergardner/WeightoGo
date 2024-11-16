package com.example.weightogo

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class WeightDatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "weightTracker.db"
        private const val DATABASE_VERSION = 1
        const val TABLE_NAME = "weightEntries"
        const val COLUMN_ID = "id"
        const val COLUMN_WEIGHT = "weight"
        const val COLUMN_DATE = "date"
        const val COLUMN_TIME = "time"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_WEIGHT_TABLE = ("CREATE TABLE $TABLE_NAME ("
                + "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "$COLUMN_WEIGHT REAL, "
                + "$COLUMN_DATE TEXT, "
                + "$COLUMN_TIME TEXT)")
        db?.execSQL(CREATE_WEIGHT_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    // Function to add weight entry
    fun addWeightEntry(weight: Double, date: String, time: String): Long {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COLUMN_WEIGHT, weight)
        values.put(COLUMN_DATE, date)
        values.put(COLUMN_TIME, time)

        val result = db.insert(TABLE_NAME, null, values)
        db.close()
        return result
    }

    // Function to get all weight entries
    fun getAllWeightEntries(): List<WeightEntry> {
        val weightList = ArrayList<WeightEntry>()
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_NAME", null)

        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID))
                val weight = cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_WEIGHT))
                val date = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DATE))
                val time = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TIME))

                val weightEntry = WeightEntry(id, weight, date, time)
                weightList.add(weightEntry)
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()

        return weightList
    }

    // Function to delete a weight entry
    fun deleteWeightEntry(id: Int): Int {
        val db = this.writableDatabase
        val result = db.delete(TABLE_NAME, "$COLUMN_ID = ?", arrayOf(id.toString()))
        db.close()
        return result
    }
}