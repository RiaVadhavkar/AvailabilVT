package com.example.availabilvt

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.icu.text.SimpleDateFormat
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import java.util.*


class ScheduleFragment : Fragment() {

//    lateinit var date: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_schedule, container, false)

        var building = view.findViewById(R.id.buildingName) as TextView
        var image = view.findViewById(R.id.buildingImage) as ImageView
        var startTime = view.findViewById(R.id.startTime) as EditText
        var endTime = view.findViewById(R.id.endTime) as EditText
        var date = view.findViewById(R.id.datePicker) as EditText
        val button = view.findViewById(R.id.submitButton) as Button



        building.setText("Building: McBryde")
        image.setImageResource(R.drawable.mcbryde)


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

        return view
    }
}
