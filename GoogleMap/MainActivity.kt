package com.example.googlemapsv1

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdate
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CircleOptions
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions

class MainActivity : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var map : GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        var fragment : SupportMapFragment =
            supportFragmentManager.findFragmentById( R.id.map ) as SupportMapFragment
        fragment.getMapAsync( this )
    }

    override fun onMapReady(p0: GoogleMap) {
        map = p0

        map.uiSettings.isZoomControlsEnabled = true
        map.uiSettings.isScrollGesturesEnabled = true
        map.uiSettings.isZoomGesturesEnabled = true

        var whiteHouse : LatLng = LatLng( 38.8977, -77.0366 )
        var camera : CameraUpdate = CameraUpdateFactory.newLatLngZoom( whiteHouse, 17.0f )
        map.moveCamera( camera )
        map.addCircle( CircleOptions().center( whiteHouse )
            .radius( 100.0 ).strokeWidth( 10.0f ).strokeColor( Color.RED ) )

        var markerOptions : MarkerOptions = MarkerOptions( )
        markerOptions.position( whiteHouse )
        markerOptions.title( "HELLO" )
        markerOptions.snippet( "How is the food?" )
        map.addMarker( markerOptions )

        map.setOnMarkerClickListener { marker -> processMarkerClick( marker ) }

    }

    fun processMarkerClick( marker : Marker ) : Boolean {
        // marker is a reference to the Marker that was clicked
        Log.w( "MainActivity", "marker's id = " + marker.id )
        Log.w( "MainActivity", "marker's position = " + marker.position )
        Log.w( "MainActivity", "marker's title = " + marker.title )
        return true
    }
}