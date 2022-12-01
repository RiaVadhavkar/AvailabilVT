package com.example.availabilvt

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.icu.text.SimpleDateFormat
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import java.util.*


class ScheduleFragment : Fragment() {

//    lateinit var date: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_schedule, container, false)
        val viewModel = ViewModelProvider(requireActivity()).get(MapViewModel::class.java)
        val markerChoice = viewModel.getMarker()

        var building = view.findViewById(R.id.buildingName) as TextView
        var image = view.findViewById(R.id.buildingImage) as ImageView
        var startTime = view.findViewById(R.id.startTime) as EditText
        var endTime = view.findViewById(R.id.endTime) as EditText
        var date = view.findViewById(R.id.datePicker) as EditText
        val button = view.findViewById(R.id.submitButton) as Button
        val participants = view.findViewById(R.id.participants) as EditText

        markerChoice!!.observe(viewLifecycleOwner, Observer { s ->
            building.text = "Building: " + s

            when (s){
                "NCB" -> image.setImageResource(R.drawable.ncb)
                "Surge" -> image.setImageResource(R.drawable.surge)
                "McBryde" -> image.setImageResource(R.drawable.mcbryde)
                "Goodwin" -> image.setImageResource(R.drawable.goodwin)
                "Torgersen" -> image.setImageResource(R.drawable.torg)
                else -> Log.d("test", "failure")
            }
        })




        startTime.setOnClickListener {
            val calendar = Calendar.getInstance()

            val hour = calendar.get(Calendar.HOUR_OF_DAY)
            val minute = calendar.get(Calendar.MINUTE)

            val timePickerDialog = TimePickerDialog(
                view.getContext(),
                { view, hourOfDay, minute ->
                    calendar.set(Calendar.MINUTE, minute)
                    calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
                    var time = SimpleDateFormat("HH:mm").format(calendar.time)
                    viewModel.setStart(time)
                    startTime.setText(time)
                },
                hour,
                minute,
                false
            )
            timePickerDialog.show()
        }

        endTime.setOnClickListener {
            val calendar = Calendar.getInstance()

            val hour = calendar.get(Calendar.HOUR_OF_DAY)
            val minute = calendar.get(Calendar.MINUTE)

            val timePickerDialog = TimePickerDialog(
                view.getContext(),
                { view, hourOfDay, minute ->
                    calendar.set(Calendar.MINUTE, minute)
                    calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
                    var time = SimpleDateFormat("HH:mm").format(calendar.time)
                    viewModel.setEnd(time)
                    endTime.setText(time)
                },
                hour,
                minute,
                false
            )
            timePickerDialog.show()
        }

        date!!.setOnClickListener {

            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(
                view.getContext(), DatePickerDialog.OnDateSetListener
                { view, year, monthOfYear, dayOfMonth ->
                    date.setText("" + (monthOfYear + 1) + "/" + dayOfMonth + "/" + year)
                    viewModel.setDate(date.text.toString())
                },
                year,
                month,
                day
            )

            datePickerDialog.show()
        }

        button.setOnClickListener {
            findNavController().navigate(R.id.action_scheduleFragment_to_availabilityFragment)
        }

        participants.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {

            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int){


            }

            override fun onTextChanged(s: CharSequence, start: Int,before: Int, count: Int) {
                viewModel.setPart(s.toString())
            }

        })

        return view
    }
}
