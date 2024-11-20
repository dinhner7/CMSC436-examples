package com.example.banneradvertisingv3

import android.os.Bundle
import android.os.SystemClock
import android.widget.Chronometer
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView

class MainActivity : AppCompatActivity() {
    private lateinit var chrono : Chronometer
    private var started : Boolean = false
    private lateinit var startStopButton : AppCompatButton
    private lateinit var resetButton : AppCompatButton

    // V5
    private lateinit var adView : AdView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        chrono = findViewById( R.id.stop_watch )
        startStopButton = findViewById( R.id.start_stop )
        resetButton = findViewById( R.id.reset )
        startStopButton.setOnClickListener { startStop() }
        resetButton.setOnClickListener { reset() }

        // V3
        adView = AdView( this )
        var adSize : AdSize = AdSize( AdSize.FULL_WIDTH, AdSize.AUTO_HEIGHT )
        adView.setAdSize( adSize )
        var adUnitId : String = "ca-app-pub-3940256099942544/6300978111"
        adView.adUnitId = adUnitId

        var builder : AdRequest.Builder = AdRequest.Builder( )
        builder.addKeyword( "workout" ).addKeyword( "fitness" )
        var request : AdRequest = builder.build()

        var adLayout : LinearLayout = findViewById( R.id.ad_view )
        adLayout.addView( adView )
        adView.loadAd( request )

    }

    fun startStop( ) {
        if( started ) {
            started = false
            // stop the chronometer
            startStopButton.setBackgroundResource( R.drawable.start_button )
            startStopButton.text = "START"
            chrono.stop()
        } else {
            started = true
            // start the chrono
            startStopButton.setBackgroundResource( R.drawable.stop_button )
            startStopButton.text = "STOP"
            // V2
            resetChrono()

            chrono.start()
        }
    }

    // V2
    fun resetChrono( ) {
        var chronoText : String = chrono.text.toString()
        var idleMilliseconds : Long = ClockUtility.milliseconds( chronoText )
        chrono.base = SystemClock.elapsedRealtime() - idleMilliseconds
    }

    fun reset( ) {
        if( !started ) {
            chrono.base = SystemClock.elapsedRealtime()
        }
    }

    // V5
    override fun onPause() {
        adView.pause()
        super.onPause()
    }

    override fun onDestroy() {
        adView.destroy()
        super.onDestroy()
    }

    override fun onResume() {
        super.onResume()
        adView.resume()
    }
}