package com.example.xmlcontentappv3

import android.util.Log
import javax.xml.parsers.SAXParser
import javax.xml.parsers.SAXParserFactory

class ParseTask : Thread {

    private lateinit var activity : MainActivity

    constructor( activity : MainActivity ) {
        this.activity= activity
    }

    override fun run() {
        super.run()
        var items : ArrayList<Item>? = accessData( MainActivity.URL )
        activity.runOnUiThread{ activity.displayList( items ) }
    }

    fun accessData( url : String ) : ArrayList<Item>? {
        try {
            var factory : SAXParserFactory = SAXParserFactory.newInstance()
            var parser : SAXParser = factory.newSAXParser()
            var handler : SAXHandler = SAXHandler( )
            parser.parse( url, handler )
            return handler.getItems()
        } catch( e : Exception ) {
            Log.w( "MainActivity", "exception: " + e.message )
            return null
        }
    }
}