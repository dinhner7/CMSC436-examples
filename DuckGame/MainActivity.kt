package com.example.duckhuntingv3

import android.graphics.Rect
import android.media.SoundPool
import android.os.Bundle
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Timer

class MainActivity : AppCompatActivity() {
    private lateinit var gameView : GameView
    private lateinit var game : Game

    // V2
    private lateinit var detector : GestureDetector

    // V3
    private lateinit var pool : SoundPool
    private var duckSoundId : Int = 0
    private var hitSoundId : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        // var th : TouchHanlder = TouchHanlder( )
        // var view : View = findViewById( android.R.id.content )
        // view.setOnTouchListener( th )

        var builder : SoundPool.Builder = SoundPool.Builder( )
        pool = builder.build()
        duckSoundId = pool.load( this, R.raw.duck_hit, 1 )
        hitSoundId = pool.load( this, R.raw.cannon_fire, 1 )
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        buildViewByCode( )
        animate( )
    }

    fun animate( ) {
        var timer : Timer = Timer( )
        var task : GameTimerTask = GameTimerTask( this )
        timer.schedule( task, 0L, GameView.DELTA_TIME.toLong() )
    }

    fun buildViewByCode( ) {
        var width : Int = resources.displayMetrics.widthPixels
        var height : Int = resources.displayMetrics.heightPixels

        val rectangle : Rect = Rect( 0, 0, 0, 0 )
        window.decorView.getWindowVisibleDisplayFrame( rectangle )
        Log.w( "MainActivity", "width = " + width )
        Log.w( "MainActivity", "height = " + height )
        Log.w( "MainActivity", "top = " + rectangle.top )

        gameView = GameView( this, width, height - rectangle.top )
        game = gameView.getGame()
        setContentView( gameView )

        // V2
        var th : TouchHandler = TouchHandler( )
        detector = GestureDetector( this, th )
        detector.setOnDoubleTapListener( th )
    }

    fun updateModel( ) {
        game.moveDuck()
        if( game.duckOffScreen() ) {
            // V3
            game.setDuckShot( false )
            game.startDuckFromRightTopHalf()
        } else if (game.duckHit() ) {
            playSound( duckSoundId )
            game.setDuckShot( true )
            game.loadBullet()
        }

        if( game.bulletOffScreen() )
            game.loadBullet()
        else if( game.isBulletFired() )
            game.moveBullet()
    }

    fun updateView( ) {
        // some code here
        gameView.postInvalidate()
    }

    // V2
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if( event != null )
            detector.onTouchEvent( event )
        return super.onTouchEvent(event)
    }

    // V2
    fun updateCannon( e : MotionEvent ) {
        var x : Float = e.x - game.getCannonCenter().x
        var y : Float = game.getCannonCenter().y - e.y
        var angle : Float = Math.atan2( y.toDouble(), x.toDouble() ).toFloat()
        game.setCannonAngle( angle )
    }

    // V3
    fun playSound( id : Int ) {
        pool.play( id, 1.0f, 1.0f, 0, 0, 1.0f )
    }
/*
    inner class TouchHanlder : View.OnTouchListener {
        override fun onTouch(p0: View?, p1: MotionEvent?): Boolean {
            Log.w( "MainActivity", "p1.y = " + p1!!.y )
            Log.w( "MainActivity", "p1.rawY = " + p1!!.rawY )
            return true
        }
    }
*/

    // V2
    inner class TouchHandler : GestureDetector.SimpleOnGestureListener() {
        override fun onDoubleTapEvent(e: MotionEvent): Boolean {
            // update the model, fire the bullet
            if( ! game.isBulletFired() ) {
                playSound( hitSoundId )
                game.fireBullet()
            }
            return super.onDoubleTapEvent(e)
        }

        override fun onScroll(
            e1: MotionEvent?,
            e2: MotionEvent,
            distanceX: Float,
            distanceY: Float
        ): Boolean {
            updateCannon( e2 )
            return super.onScroll(e1, e2, distanceX, distanceY)
        }

        override fun onSingleTapConfirmed(e: MotionEvent): Boolean {
            updateCannon( e )
            return super.onSingleTapConfirmed(e)
        }
    }
}