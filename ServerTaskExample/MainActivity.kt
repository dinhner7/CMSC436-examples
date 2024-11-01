package com.example.server

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    private  lateinit var tv : TextView

    // results

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        tv = findViewById( R.id.tv )

        // var task : ThreadTask = ThreadTask( this )
        // var task : ServerTask = ServerTask( this )
        // var task : ServerTaskPost = ServerTaskPost( this )
        var task : ServerTaskJson = ServerTaskJson( this )
        task.start()

        // do not use results here
    }

    // setResults method

    fun updateView( s : String ) {
        tv.text = s
    }

    // {"languages":["Java","PHP", "Kotlin"], "university":"UMD"}
    fun parseJson( json : String ) : String {
        var language = ""
        try {
            var jsonObject: JSONObject = JSONObject(json)
            var languages: JSONArray = jsonObject.getJSONArray("languages")
            language = languages.getString(1 ) // PHP
        } catch( e : JSONException ) {
            Log.w( "MainActivity", "exception: " + e.message )
        }
        return language
    }

    companion object {
        const val URL : String = "http://cmsc436-2301.cs.umd.edu/test.php"
        const val URL_GET : String
            = "http://cmsc436-2301.cs.umd.edu/testGet.php?name=Bob&age=17"
        const val URL_POST : String = "http://cmsc436-2301.cs.umd.edu/testPost.php"
        const val URL_JSON : String = "http://cmsc436-2301.cs.umd.edu/json.php"
    }
}