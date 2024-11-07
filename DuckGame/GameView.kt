package com.example.duckhuntingv3

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Point
import android.graphics.Rect
import android.util.Log
import android.view.View

class GameView : View {
    private lateinit var paint : Paint
    private lateinit var duck : Bitmap
    private lateinit var ducks : Array<Bitmap>
    private var duckFrame : Int = 0
    private lateinit var duckRect : Rect
    private var width : Int = 0
    private var height : Int = 0

    private lateinit var game : Game

    constructor(context : Context, width : Int, height : Int ) : super( context ) {
        this.width = width
        this.height = height
        paint = Paint( )
        paint.isAntiAlias = true
        paint.color = Color.BLACK
        paint.strokeWidth = 20.0f

        duck = BitmapFactory.decodeResource( resources, R.drawable.duck )
        var scale : Float = width.toFloat()/ ( duck.width * 5 )
        duckRect = Rect( width - width / 5, 0, width, (scale * duck.height).toInt()  )

        ducks = Array<Bitmap>( TARGETS.size, { i -> BitmapFactory.decodeResource( resources, TARGETS[i] ) } )

        game = Game( duckRect, 5, .03f, .2f )
        game.setBulletSpeed( width * .0003f )
        game.setDuckSpeed( width * .00003f )
        game.setHuntingRect( Rect( 0, 0, width, height ) )
        game.setDeltaTime( DELTA_TIME )
        game.setCannon( Point( 0, height ), width/30, width / 15, width / 50 )
    }

    fun getGame( ) : Game {
        return game
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        Log.w( "MainActivity", "Inside onDraw" )
        // canvas.drawCircle( 50.0f, 200.0f, 25.0f, paint )

        var cx : Float = game.getCannonCenter().x.toFloat()
        var cy : Float = game.getCannonCenter().y.toFloat()
        canvas.drawCircle( cx, cy, game.getCannonRadius().toFloat(), paint )
        // canvas.drawCircle( 0.0f, height.toFloat(), 100.0f, paint )
        var endX : Float = cx + game.getBarrelLength() * Math.cos( game.getCannonAngle().toDouble() ).toFloat()
        var endY : Float = cy - game.getBarrelLength() * Math.sin( game.getCannonAngle().toDouble() ).toFloat()
        canvas.drawLine( cx, cy, endX, endY, paint )
        // canvas.drawLine( 0.0f, height.toFloat(), 300.0f, height - 300.0f, paint )

        if( game.isDuckShot() )
            canvas.drawBitmap( ducks[0], null, game.getDuckRect(), paint )
        else {
            canvas.drawBitmap(ducks[duckFrame], null, game.getDuckRect(), paint)
            duckFrame = (duckFrame + 1) % TARGETS.size
        }
        // canvas.drawBitmap( duck, null, game.getDuckRect(), paint )

        // V2: draw the bullet
        var bulletX : Float = game.getBulletCenter().x.toFloat()
        var bulletY : Float = game.getBulletCenter().y.toFloat()
        var bulletRadius : Float = game.getBulletRadius().toFloat()
        canvas.drawCircle( bulletX, bulletY, bulletRadius, paint )
    }

    companion object {
        val DELTA_TIME : Int = 100

        val TARGETS = intArrayOf( R.drawable.anim_duck0, R.drawable.anim_duck1,
            R.drawable.anim_duck2, R.drawable.anim_duck1 )
    }
}