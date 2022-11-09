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
import android.app.DatePickerDialog
import android.widget.EditText
import java.util.*
import java.util.Calendar;
import android.app.TimePickerDialog;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.DatePicker;
import android.widget.TimePicker;


class ScheduleFragment : Fragment() {

//    lateinit var date: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_schedule, container, false)
        var img = view.findViewById(R.id.buildingImage) as ImageView
        var date = view.findViewById(R.id.datePicker) as EditText
        val button = view.findViewById(R.id.submitButton) as Button

        img.setImageResource(R.drawable.mcbryde)

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
