package com.example.server

import android.util.Log
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.io.OutputStream
import java.io.OutputStreamWriter
import java.net.URL
import java.net.URLConnection
import java.util.Scanner

class ServerTaskPost : Thread {
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
            var url: URL = URL( MainActivity.URL_POST )
            Log.w( "MainActivity", "URL created" )

            // write data to the output stream associated with url
            var connection : URLConnection = url.openConnection()
            connection.doOutput = true
            var oStream : OutputStream = connection.getOutputStream()
            var osw : OutputStreamWriter = OutputStreamWriter( oStream )
            var data : String = "name=Mary&age=23"
            osw.write( data )
            osw.flush( )

            // read data from the input stream associated with url
            var iStream : InputStream = connection.getInputStream()
            var isr : InputStreamReader = InputStreamReader( iStream )
            var br : BufferedReader = BufferedReader( isr )
            var s : String? = br.readLine( )
            results = ""
            while( s != null ) {
                results += s
                s = br.readLine()
            }
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
            activity.updateView( results )
        }
    }
}