package com.example.availabilvt

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.*


class AvailabilityFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    lateinit var viewAdapter: RecyclerView.Adapter<*>

    val classes :  MutableList<Classroom> = mutableListOf()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view = inflater.inflate(R.layout.fragment_availability, container, false)
        val viewModel = ViewModelProvider(requireActivity()).get(MapViewModel::class.java)



        ///////////
        classes.add(Classroom("McBryde", "100", "7:00", "9:00", mutableListOf("Monday", "Wednesday", "Friday") ,6043))
        classes.add(Classroom("McBryde", "113", "11:30", "13:30", mutableListOf("Tuesday", "Thursday") ,1634))
        classes.add(Classroom("McBryde", "126", "8:00", "9:00", mutableListOf("Monday", "Wednesday", "Friday") ,926))
        classes.add(Classroom("McBryde", "129", "12:30", "15:00", mutableListOf("Tuesday", "Thursday") ,1631))
        classes.add(Classroom("McBryde", "134", "16:30", "18:00", mutableListOf("Tuesday", "Thursday") ,619))

        classes.add(Classroom("NCB", "110A", "12:30", "13:30", mutableListOf("Monday", "Wednesday", "Friday") ,1197))
        classes.add(Classroom("NCB", "110B", "11:30", "13:30", mutableListOf("Tuesday", "Thursday") ,1228))
        classes.add(Classroom("NCB", "120", "15:00", "17:30", mutableListOf("Monday", "Wednesday", "Friday") ,2388))
        classes.add(Classroom("NCB", "130A", "12:30", "15:00", mutableListOf("Tuesday", "Thursday") ,1188))
        classes.add(Classroom("NCB", "160", "10:00",  "12:00", mutableListOf("Tuesday", "Thursday") ,3086))

        classes.add(Classroom("Surge", "103A", "12:30", "13:30", mutableListOf("Monday", "Wednesday", "Friday") ,1404))
        classes.add(Classroom("Surge", "104A", "11:30", "13:30", mutableListOf("Tuesday", "Thursday") ,1660))
        classes.add(Classroom("Surge", "104B", "8:00", "9:00", mutableListOf("Monday", "Wednesday", "Friday") ,2335))
        classes.add(Classroom("Surge", "104C", "12:30", "15:00", mutableListOf("Tuesday", "Thursday") ,2334))
        classes.add(Classroom("Surge", "104D", "16:30", "18:00", mutableListOf("Tuesday", "Thursday") ,1573))

        classes.add(Classroom("Goodwin", "115", "7:00", "13:30", mutableListOf("Monday", "Wednesday", "Friday") ,1764))
        classes.add(Classroom("Goodwin", "125", "11:30",  "14:30", mutableListOf("Tuesday", "Thursday") ,1876))
        classes.add(Classroom("Goodwin", "135", "8:00",  "17:30", mutableListOf("Monday", "Wednesday", "Friday") ,1874))
        classes.add(Classroom("Goodwin", "145", "12:30", "15:00", mutableListOf("Tuesday", "Thursday") ,1451))
        classes.add(Classroom("Goodwin", "155", "10:00", "12:00", mutableListOf("Tuesday", "Thursday") ,1446))

        classes.add(Classroom("Torgersen", "1010", "7:00", "13:30", mutableListOf("Monday", "Wednesday", "Friday") ,1404))
        classes.add(Classroom("Torgersen", "1030", "11:30","13:30", mutableListOf("Tuesday", "Thursday") ,1660))
        classes.add(Classroom("Torgersen", "1060", "8:00", "9:00", mutableListOf("Monday", "Wednesday", "Friday") ,2335))
        classes.add(Classroom("Torgersen", "2150", "12:30", "15:00", mutableListOf("Tuesday", "Thursday") ,2334))
        classes.add(Classroom("Torgersen", "3100", "16:30", "18:00", mutableListOf("Tuesday", "Thursday") ,1573))

        ///////////
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
        viewAdapter = RecyclerViewAdapter(25, classes)
        recyclerView.adapter = viewAdapter
        (viewAdapter as RecyclerViewAdapter).filterClasses(markerChoice.value!!, timeStart.value!!, timeEnd.value!!, date.value!!, part.value!!)


        return view
    }

    inner class RecyclerViewAdapter(private var cnt: Int, private var classes: MutableList<Classroom> ) :
        RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

        //private var classesLocal : MutableList<Classroom> = mutableListOf()

        override fun onCreateViewHolder(parent: ViewGroup,
                                        viewType: Int): RecyclerViewAdapter.ViewHolder {
            val v = LayoutInflater.from(parent.context)
                .inflate(R.layout.card_item, parent, false)
            return ViewHolder(v)
        }
        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.view.findViewById<TextView>(R.id.room).text = "Room: " + classes[position].room
            holder.view.findViewById<TextView>(R.id.time).text = classes[position].timeStart + " - " + classes[position].timeEnd
            holder.view.findViewById<TextView>(R.id.participants).text = "Particpants: " + classes[position].space

        }

        fun filterClasses(markerChoice: String, timeStart: String, timeEnd: String, date: String, part: String) {

            classes = classes.filter{ m -> m.building.equals(markerChoice, ignoreCase = true)}.toMutableList()
            classes = classes.filter{ m -> m.space >= part.toInt()}.toMutableList()

            val inFormat = SimpleDateFormat("MM/dd/yyyy")
            val myDate: Date = inFormat.parse(date)
            val simpleDateFormat = SimpleDateFormat("EEEE")
            val dayName: String = simpleDateFormat.format(myDate)

            classes = classes.filter{ m -> m.daysOfTheWeek.toString().contains(dayName, ignoreCase = true)}.toMutableList()

            classes = classes.filter{ m-> m.timeStart.replace(":", "").toInt() <= timeStart.replace(":", "").toInt() }.toMutableList()
            classes = classes.filter{ m-> m.timeEnd.replace(":", "").toInt() >= timeEnd.replace(":", "").toInt() }.toMutableList()

            cnt = classes.size
            notifyDataSetChanged()

        }

        // Return the size of your dataset (invoked by the layout manager)
        override fun getItemCount() = cnt

        inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view){
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
}


