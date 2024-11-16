package com.example.weightogo.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.weightogo.R
import com.example.weightogo.data.WeightEntry
import java.time.format.DateTimeFormatter

class HistoryListItemAdapter(private val context: Context, private val weightEntries: List<WeightEntry>) : BaseAdapter() {

    override fun getCount(): Int = weightEntries.size

    override fun getItem(position: Int): Any = weightEntries[position]

    override fun getItemId(position: Int): Long = weightEntries[position].entryDate.toEpochDay()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.activity_history_list_item, parent, false)
        val entry = weightEntries[position]

        val entryDate = view.findViewById<TextView>(R.id.entry_date)
        val entryWeight = view.findViewById<TextView>(R.id.entry_weight)
        val hasPhotoIcon = view.findViewById<ImageView>(R.id.has_photo_icon)

        val dateFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy")
        entryDate.text = dateFormatter.format(entry.entryDate)
        entryWeight.text = "${entry.entryWeight} lbs"

        if (entry.progressPhoto != null) {
            hasPhotoIcon.visibility = View.VISIBLE
        } else {
            hasPhotoIcon.visibility = View.INVISIBLE
        }

        return view
    }
}
