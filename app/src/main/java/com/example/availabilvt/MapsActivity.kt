package com.example.availabilvt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.availabilvt.databinding.ActivityMapsBinding

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        // Add a marker in Sydney and move the camera
        val burg = LatLng(37.229676, -80.423370)
        val ncb = LatLng(37.22955757387947, -80.42696695587784)
        val surge = LatLng(37.233194976104734, -80.42303508448178)
        val mcb = LatLng(37.23106324116719, -80.42174318388022)
        val goodwin = LatLng(37.23265832123776, -80.4256937784734)
        val torg = LatLng(37.22980391219889, -80.42020954188546)
        mMap.addMarker(MarkerOptions().position(ncb).title("New Classroom Building"))
        mMap.addMarker(MarkerOptions().position(surge).title("Surge"))
        mMap.addMarker(MarkerOptions().position(mcb).title("McBryde"))
        mMap.addMarker(MarkerOptions().position(goodwin).title("Goodwin"))
        mMap.addMarker(MarkerOptions().position(torg).title("Torgersen"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(burg, 16f));
    }
}