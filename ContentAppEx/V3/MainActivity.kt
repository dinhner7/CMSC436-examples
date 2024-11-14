package com.example.xmlcontentappv3

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.io.InputStream
import javax.xml.parsers.SAXParser
import javax.xml.parsers.SAXParserFactory

class MainActivity : AppCompatActivity() {
    private lateinit var listView : ListView
    private lateinit var items : ArrayList<Item>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        listView = findViewById( R.id.list_view )

        var task : ParseTask = ParseTask( this )
        task.start( )
    }

    fun displayList( items : ArrayList<Item>? ) {
        if( items != null ) {
            this.items = items
            var titles : ArrayList<String> = ArrayList<String>( )
            for( item in this.items ) {
                Log.w( "MainActivity", item.toString() )
                if( item != null && item.getTitle() != null ) {
                    titles.add( item.getTitle() )
                }
            }
            // populate listView
            var adapter : ArrayAdapter<String> =
                ArrayAdapter<String>(  this, android.R.layout.simple_list_item_1, titles )
            listView.adapter = adapter

        } else {
            Toast.makeText( this, "Sorry, no data found", Toast.LENGTH_LONG ).show( )
        }
    }

    companion object {
        const val URL : String = "https://www.feedforall.com/sample.xml"
    }
}