package com.example.server

import android.util.Log
import java.io.InputStream
import java.net.URL
import java.util.Scanner

class ServerTaskJson : Thread {
    private var results : String = "EMPTY STRING"
    private lateinit var activity : MainActivity

    constructor( activity : MainActivity ) {
        this.activity = activity
    }

    override fun run() {
        super.run()
        try {
            // create URL object reference
            // var url: URL = URL(MainActivity.URL)
            // var url: URL = URL( MainActivity.URL_GET )
            var url: URL = URL( MainActivity.URL_JSON )
            Log.w( "MainActivity", "URL created" )
            // create InputStream from URL
            var iStream: InputStream = url.openStream()
            Log.w( "MainActivity", "InputStream created" )
            // read from InputStream
            var scan: Scanner = Scanner(iStream)
            results = ""
            while (scan.hasNext()) {
                results += scan.nextLine()
            }
            // results is a JSON string
        } catch( e : Exception ) {
            // do something here (Toast, ...)
            Log.w( "MainActivity", "Exception: " + e.message )
            Log.w( "MainActivity", "Exception: " + e.toString() )
        }

        // activity.updateView( results )

        var updateGui : UpdateGui = UpdateGui()
        activity.runOnUiThread( updateGui )
    }

    inner class UpdateGui : Runnable {
        override fun run() {
            activity.updateView( activity.parseJson( results ) )
        }
    }
}