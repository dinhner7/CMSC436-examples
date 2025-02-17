package com.example.tipcalculatorv4

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
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

    // private lateinit var button : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        tipCalc = TipCalculator( .20, 100.0 )

        setContentView(R.layout.activity_main)
        amountET = findViewById( R.id.amount_bill )
        tipET = findViewById( R.id.amount_tip_percent )
        totalTV = findViewById( R.id.total )
        tipTV = findViewById( R.id.tip )

        // button = findViewById( R.id.button )

        // set up event handling by code
        // button.setOnClickListener { v -> calculate( button ) }
        // step 2:
        var tch : TextChangeHandler = TextChangeHandler()
        // step 3:
        tipET.addTextChangedListener( tch )
        amountET.addTextChangedListener( tch 234)
    }

    fun calculate( ) {
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

    // step 1
    inner class TextChangeHandler : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            // do nothing
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            // do nothing
        }

        override fun afterTextChanged(p0: Editable?) {
            // calculate()
            this@MainActivity.calculate()
        }

    }
}