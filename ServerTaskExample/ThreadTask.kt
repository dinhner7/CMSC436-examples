package com.example.server

class ThreadTask : Thread {
    private var results : String = "EMPTY STRING"
    private lateinit var activity : MainActivity

    constructor( activity : MainActivity ) {
        this.activity = activity
    }

    override fun run() {
        super.run()
        results = "CHANGED by the THREAD"
        // call setResults
        // activity.updateView( results )

        var updateGui : UpdateGui = UpdateGui()
        activity.runOnUiThread( updateGui )
    }

    inner class UpdateGui : Runnable {
        override fun run() {
            activity.updateView( results )
        }
    }
}