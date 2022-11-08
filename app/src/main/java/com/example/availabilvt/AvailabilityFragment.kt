package com.example.availabilvt

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class AvailabilityFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_availability, container, false)

        recyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        viewAdapter = RecyclerViewAdapter(10)
        recyclerView.adapter = viewAdapter

        return view
    }
}

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
        var buildingText: TextView
        var participantsText: TextView
        var availabilityText: TextView
        var timeText: TextView

        init {
            buildingText = view.findViewById(R.id.buildingName)
            participantsText = view.findViewById(R.id.participants)
            availabilityText = view.findViewById(R.id.availability)
            timeText = view.findViewById(R.id.time)
        }
    }
}
