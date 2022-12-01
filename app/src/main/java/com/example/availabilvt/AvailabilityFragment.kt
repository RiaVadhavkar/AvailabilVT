package com.example.availabilvt

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
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
        val viewModel = ViewModelProvider(requireActivity()).get(MapViewModel::class.java)

        val markerChoice = viewModel.getMarker()
        val timeStart = viewModel.getStart()
        val timeEnd = viewModel.getEnd()
        val date = viewModel.getDate()
        val part = viewModel.getPart()

        var timeStartVariable = ""
        var timeEndVariable = ""
        var dateVariable = ""
        var partVariable = ""


        var building = view.findViewById(R.id.location) as TextView
        var dateTime = view.findViewById(R.id.dateTime) as TextView


        markerChoice!!.observe(viewLifecycleOwner, Observer { s ->
            building.text = "Where: " + s
        })

        timeStart!!.observe(viewLifecycleOwner, Observer { s ->
            timeStartVariable = s
        })

        timeEnd!!.observe(viewLifecycleOwner, Observer { s ->
            timeEndVariable = s
        })

        date!!.observe(viewLifecycleOwner, Observer { s ->
            dateVariable = s
        })

        part!!.observe(viewLifecycleOwner, Observer { s ->
            partVariable = s
            dateTime.text = "When: " + dateVariable + ", " + timeStartVariable + " - " + timeEndVariable
        })

        recyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        viewAdapter = RecyclerViewAdapter(10)
        recyclerView.adapter = viewAdapter

        return view
    }
}


