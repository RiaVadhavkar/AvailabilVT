package com.example.availabilvt

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewAdapter(private val cnt: Int) :
    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): RecyclerViewAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_item, parent, false)
        return ViewHolder(v)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = cnt

    class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view){
        var participantsText: TextView
        var availabilityText: TextView
        var timeText: TextView

        init {
            participantsText = view.findViewById(R.id.participants)
            availabilityText = view.findViewById(R.id.availability)
            timeText = view.findViewById(R.id.time)
        }
    }
}