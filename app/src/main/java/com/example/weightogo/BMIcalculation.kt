package com.example.weightogo

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.weightogo.data.WeightEntry
import java.time.LocalDate
import java.time.temporal.ChronoUnit

@RequiresApi(Build.VERSION_CODES.O)
class BMIcalculation(private val entries: List<WeightEntry>?, private val userSettings: UserSettings?) {

    private var startWeight: Double = 0.0
    private var startDate: LocalDate? = null
    private var latestWeight: Double = 0.0
    private var latestDate: LocalDate? = null
    private var bMI: Double = 0.0
    private var averageWeeklyLoss: Double = 0.0
    private var totalLoss: Double = 0.0
    private var leftToGoal: Double = 0.0

    init {
        recalculateAll()
    }

    /**
     * Recalculate all BMI-related data points.
     */
    @RequiresApi(Build.VERSION_CODES.O)
    private fun recalculateAll() {
        if (entries.isNullOrEmpty()) return

        var currentWeight = userSettings?.startWeightLbs ?: 0.0
        var earliestDate = LocalDate.MAX
        var latestDate = LocalDate.MIN

        for (entry in entries) {
            val entryDate = entry.entryDate
            if (entryDate.isBefore(earliestDate)) {
                earliestDate = entryDate
                startWeight = entry.entryWeight
            }
            if (entryDate.isAfter(latestDate)) {
                latestDate = entryDate
                currentWeight = entry.entryWeight
            }
        }

        this.startDate = earliestDate
        this.latestDate = latestDate
        this.latestWeight = currentWeight
        this.startWeight = userSettings?.startWeightLbs ?: currentWeight

        // Calculate derived statistics
        recalculateBMI()
        recalculateAverageWeeklyLoss()
        recalculateTotalLoss()
        recalculateLeftToGoal()
    }

    /**
     * Calculate BMI.
     */
    private fun recalculateBMI() {
        val heightInMeters = (userSettings?.heightCm ?: 0.0) / 100
        bMI = if (heightInMeters > 0) {
            latestWeight / (heightInMeters * heightInMeters)
        } else {
            0.0
        }
    }

    /**
     * Calculate average weekly weight loss.
     */
    private fun recalculateAverageWeeklyLoss() {
        val weeksBetween = ChronoUnit.DAYS.between(startDate, latestDate).toDouble() / 7.0
        averageWeeklyLoss = if (weeksBetween > 0) {
            (startWeight - latestWeight) / weeksBetween
        } else {
            0.0
        }
    }

    /**
     * Calculate total weight loss.
     */
    private fun recalculateTotalLoss() {
        totalLoss = startWeight - latestWeight
    }

    /**
     * Calculate weight left to goal.
     */
    private fun recalculateLeftToGoal() {
        leftToGoal = latestWeight - (userSettings?.goalWeight ?: latestWeight)
    }

    /**
     * Getters for statistics.
     */
    fun getBMI(): Double = bMI
    fun getAverageWeeklyLoss(): Double = averageWeeklyLoss
    fun getTotalLoss(): Double = totalLoss
    fun getLeftToGoal(): Double = leftToGoal
    fun getStartDate(): LocalDate? = startDate
    fun getLatestDate(): LocalDate? = latestDate
}