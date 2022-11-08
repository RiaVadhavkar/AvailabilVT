package com.example.availabilvt

import android.media.Image
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.navigation.fragment.findNavController
import com.google.android.gms.maps.MapView


class ScheduleFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_schedule, container, false)
        var img = view.findViewById(R.id.buildingImage) as ImageView
        val button = view.findViewById(R.id.submitButton) as Button

        img.setImageResource(R.drawable.mcbryde)


        button.setOnClickListener {
            findNavController().navigate(R.id.action_scheduleFragment_to_availabilityFragment)
        }

        return view
    }
}