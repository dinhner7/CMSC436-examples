package com.example.tipcalculatorv3

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var tipCalc : TipCalculator
    private lateinit var amountET : EditText
    private lateinit var tipET : EditText
    private lateinit var tipTV : TextView
    private lateinit var totalTV : TextView

    private lateinit var button : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        tipCalc = TipCalculator( .20, 100.0 )

        setContentView(R.layout.activity_main)
        amountET = findViewById( R.id.amount_bill )
        tipET = findViewById( R.id.amount_tip_percent )
        totalTV = findViewById( R.id.total )
        tipTV = findViewById( R.id.tip )

        button = findViewById( R.id.button )

        // set up event handling by code
        button.setOnClickListener { v -> calculate( button ) }
    }

    fun calculate( v : View ) {
        Log.w( "MainActivity", "Inside calculate" )
        try {
            // retrieve user input
            var tipS: String = tipET.text.toString()
            var amountS: String = amountET.text.toString()
            var amount: Double = amountS.toDouble()
            var tip: Double = tipS.toDouble() / 100

            // update the model
            tipCalc.setTip(tip)
            tipCalc.setAmount(amount)

            // ask model to perform calculations
            var total: Double = tipCalc.calculateTotal()
            var tipDollars: Double = tipCalc.calculateTip()

            // update the GUI
            tipTV.text = "$" + tipDollars
            totalTV.text = "$" + total
        } catch( e : Exception ) {
            // pop up a dialog box here?
        }
    }
}