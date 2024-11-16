package com.example.weightogo

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.weightogo.adapters.HistoryListItemAdapter
import com.example.weightogo.data.WeightEntry
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class `6HistoryActivity` : AppCompatActivity() {

    private lateinit var dbHelper: WeightDatabaseHelper
    private lateinit var historyListView: ListView
    private lateinit var weightEntries: List<WeightEntry>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        dbHelper = WeightDatabaseHelper(this)
        historyListView = findViewById(R.id.historyListView)

        // Load weight entries and set up the ListView
        loadWeightEntries()

        // Set up navigation button actions
        findViewById<View>(R.id.buttonComputer).setOnClickListener { navigateToVitals() }
        findViewById<View>(R.id.buttonScales).setOnClickListener { navigateToSummary() }
        findViewById<View>(R.id.buttonPlus).setOnClickListener { navigateToDataEntryActivity(LocalDate.now()) }
    }

    /**
     * Loads weight entries from the database and sets up the ListView adapter.
     */
    private fun loadWeightEntries() {
        weightEntries = dbHelper.getAllWeightEntries()
        val emptyTextView = findViewById<TextView>(R.id.emptyHistoryText)

        if (weightEntries.isEmpty()) {
            emptyTextView.visibility = View.VISIBLE
            historyListView.visibility = View.GONE
        } else {
            emptyTextView.visibility = View.GONE
            historyListView.visibility = View.VISIBLE
            val adapter = HistoryListItemAdapter(this, weightEntries)
            historyListView.adapter = adapter
        }
    }


    /**
     * Navigates to the DataEntryActivity with the selected date.
     * @param date The selected date for the weight entry.
     */
    private fun navigateToDataEntryActivity(date: LocalDate) {
        val intent = Intent(this, DataEntryActivity::class.java)
        val dateFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy")
        intent.putExtra("SelectedDate", dateFormatter.format(date))
        startActivity(intent)
    }

    /**
     * Navigates to the Vitals screen.
     */
    private fun navigateToVitals() {
        startActivity(Intent(this, VitalsActivity::class.java))
    }

    /**
     * Navigates to the Summary screen.
     */
    private fun navigateToSummary() {
        startActivity(Intent(this, SummaryActivity::class.java))
    }
}
