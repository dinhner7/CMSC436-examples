package com.example.xmlcontentappv1

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.io.InputStream
import javax.xml.parsers.SAXParser
import javax.xml.parsers.SAXParserFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        var factory : SAXParserFactory = SAXParserFactory.newInstance()
        var parser : SAXParser = factory.newSAXParser()

        var handler : SAXHandler = SAXHandler( )
        var iStream : InputStream = resources.openRawResource( R.raw.test )
        parser.parse( iStream, handler )

        // V1
        var items : ArrayList<Item> = handler.getItems()
        for( item in items ) {
            Log.w( "MainActivity", item.toString() )
        }

    }
}