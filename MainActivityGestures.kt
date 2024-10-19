package com.example.gestures

import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity(), GestureDetector.OnGestureListener,
                            GestureDetector.OnDoubleTapListener {
    private lateinit var detector : GestureDetector

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detector = GestureDetector( this, this )
        detector.setOnDoubleTapListener( this )
        setContentView(R.layout.activity_main)

    }

    override fun onDown(p0: MotionEvent): Boolean {

        return true
    }

    override fun onShowPress(p0: MotionEvent) {

    }

    override fun onSingleTapUp(p0: MotionEvent): Boolean {

        return true
    }

    override fun onScroll(p0: MotionEvent?, p1: MotionEvent, p2: Float, p3: Float): Boolean {

        return true
    }

    override fun onLongPress(p0: MotionEvent) {

    }

    override fun onFling(p0: MotionEvent?, p1: MotionEvent, p2: Float, p3: Float): Boolean {

        return true
    }

    override fun onSingleTapConfirmed(p0: MotionEvent): Boolean {

        return true
    }

    override fun onDoubleTap(p0: MotionEvent): Boolean {

        return true
    }

    override fun onDoubleTapEvent(p0: MotionEvent): Boolean {

        return true
    }
}