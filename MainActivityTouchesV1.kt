package com.example.touchesv1

import android.graphics.Color
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var tv : TextView
    private lateinit var params : RelativeLayout.LayoutParams

    private var startX : Int = 0
    private var startY : Int = 0
    private var startTouchX : Int = 0
    private var startTouchY : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        tv = TextView( this )
        tv.setBackgroundColor( Color.GREEN )

        var th : TouchHandler = TouchHandler( )
        tv.setOnTouchListener( th )

        params = RelativeLayout.LayoutParams( 300, 200 )
        params.topMargin = 150
        params.leftMargin = 50

        var rl : RelativeLayout = RelativeLayout( this )
        rl.addView( tv, params )
        setContentView( rl )

    }

    inner class TouchHandler : View.OnTouchListener {
        override fun onTouch(p0: View?, p1: MotionEvent?): Boolean {
            if( p1 != null ) {
                var action = p1.action
                if( action == MotionEvent.ACTION_DOWN ) {
                    // get original TextView position
                    startX = params.leftMargin
                    startY = params.topMargin
                    // get original touch position
                    startTouchX = p1.rawX.toInt()
                    startTouchY = p1.rawY.toInt()
                } else if( action == MotionEvent.ACTION_MOVE ) {
                    params.leftMargin = startX + p1.rawX.toInt() - startTouchX
                    params.topMargin = startY + p1.rawY.toInt() - startTouchY
                    tv.layoutParams = params
                }
            }
            return true
        }

    }
}