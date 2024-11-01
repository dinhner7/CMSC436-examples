package com.example.firebaseinclassapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        var firebase : FirebaseDatabase = FirebaseDatabase.getInstance()
        var reference : DatabaseReference = firebase.getReference( "country" )
        reference.setValue( "China" )

        // set up event handling
        var listener : DataListener = DataListener( )
        reference.addValueEventListener( listener )
    }

    inner class DataListener : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            Log.w( "MainActivity", "Inside onDataChange" )
            if( snapshot.value != null ) {
                Log.w( "MainActivity", "value read is " + snapshot.value.toString() )
            }
        }

        override fun onCancelled(error: DatabaseError) {
            Log.w( "MainActivity", "error: " + error.toString() )
        }

    }
}