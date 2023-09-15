package com.example.agoravideocall

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.agoravideocall.databinding.ActivityMaps2Binding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.Marker

class mapsActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    companion object{
        private const val MY_PERMISSION_CODE: Int = 1000
    }

    private lateinit var mLastLocation : Location

    //Location
    lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMaps2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMaps2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)

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

        mMap.uiSettings.isZoomControlsEnabled = true
        mMap.setOnMarkerClickListener(this)
        setUpMap()
//        // Add a marker in Sydney and move the camera
//        val sydney = LatLng(-34.0, 151.0)
//        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }

    private fun setUpMap() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
             ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), MY_PERMISSION_CODE)
            return
        }
        mMap.isMyLocationEnabled = true
        fusedLocationProviderClient.lastLocation.addOnSuccessListener(this) {location ->
             if (location != null)
             {
                 mLastLocation = location
                 val currentLatLong = LatLng(location.latitude, location.latitude)
                 placeMarkerOnMap(currentLatLong)
                 mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLong, 12f))
             }
        }
    }

    private fun placeMarkerOnMap(currentLatLong: LatLng) {
          val markerOptions = MarkerOptions().position(currentLatLong)
          markerOptions.title("$currentLatLong")
          mMap.addMarker(markerOptions)
    }

    override fun onMarkerClick(p0: Marker) = false

}