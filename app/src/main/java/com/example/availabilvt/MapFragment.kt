package com.example.availabilvt

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


class MapFragment : Fragment(), OnMapReadyCallback {
    var mapView: MapView? = null
    var map: GoogleMap? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_map, container, false)

        mapView = view.findViewById(R.id.mapView) as MapView
        mapView!!.onCreate(savedInstanceState)
        mapView!!.getMapAsync(this)

        return view
    }

    override fun onResume() {
        super.onResume()
        mapView?.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView?.onPause()
    }

    override fun onStart() {
        super.onStart()
        mapView?.onStart()
    }

    override fun onStop() {
        super.onStop()
        mapView?.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView?.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView?.onLowMemory()
    }


    override fun onMapReady(googleMap: GoogleMap) {
        MapsInitializer.initialize(requireContext())
        map = googleMap
        val burg = LatLng(37.229676, -80.423370)
        val ncb = LatLng(37.22955757387947, -80.42696695587784)
        val surge = LatLng(37.233194976104734, -80.42303508448178)
        val mcb = LatLng(37.23106324116719, -80.42174318388022)
        val goodwin = LatLng(37.23265832123776, -80.4256937784734)
        val torg = LatLng(37.22980391219889, -80.42020954188546)
        map!!.addMarker(MarkerOptions().position(ncb).title("NCB"))
        map!!.addMarker(MarkerOptions().position(surge).title("Surge"))
        map!!.addMarker(MarkerOptions().position(mcb).title("McBryde"))
        map!!.addMarker(MarkerOptions().position(goodwin).title("Goodwin"))
        map!!.addMarker(MarkerOptions().position(torg).title("Torgersen"))
        map!!.moveCamera(CameraUpdateFactory.newLatLngZoom(burg, 16f));

        map!!.setOnMarkerClickListener { marker ->
            findNavController().navigate(R.id.action_mapFragment_to_scheduleFragment)
            true
        }
    }

}