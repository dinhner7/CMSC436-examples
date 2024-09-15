package com.example.tipcalculatorv3

//  android.util.Log

class TipCalculator {
    private var tip : Double = 0.15
    private var amount : Double = 0.0

    init {
        // Log.w( "MainActivity", "Inside init" )
    }

    constructor(  tip : Double, amount : Double ) {
        // Log.w( "MainActivity", "Inside constructor" )
        setTip( tip )
        setAmount( amount )
    }

    fun setTip( newTip : Double ) : TipCalculator {
        if( newTip >= 0 )
            tip = newTip
        return this
    }

    fun setAmount( amount : Double ) : Unit {
        if( amount >= 0 )
            this.amount = amount
    }

    fun calculateTip( ) : Double {
        return amount * tip
    }

    fun calculateTotal( ) : Double {
        return amount * ( 1 + tip )
    }
}