package com.example.interstitialad

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback

class MainActivity : AppCompatActivity() {
    private lateinit var ad : InterstitialAd

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        var builder : AdRequest.Builder = AdRequest.Builder( )
        builder.addKeyword( "workout" )
        var request : AdRequest = builder.build()

        var adUnitId : String = "ca-app-pub-3940256099942544/1033173712"
        var adLoad : AdLoad = AdLoad( )
        InterstitialAd.load( this, adUnitId, request, adLoad )

    }

    fun goToSecondActivity() {

        Log.w( "MainActivity", "Inside MainActivity::go" )
        var intent : Intent = Intent( this, SecondActivity::class.java )
        this.startActivity( intent )
    }


    inner class AdLoad : InterstitialAdLoadCallback() {
        override fun onAdFailedToLoad(p0: LoadAdError) {
            super.onAdFailedToLoad(p0)
            Log.w( "MainActivity", "Ad failed to load" )
        }

        override fun onAdLoaded(p0: InterstitialAd) {
            super.onAdLoaded(p0)
            ad = p0
            ad.show( this@MainActivity )

            // V1: Manage ad viewing and clicking
            var manageAdShowing : ManageAdShowing = ManageAdShowing()
            ad.fullScreenContentCallback = manageAdShowing

        }
    }

    // V1: Management of viweing and clicking on the ad by the user
    inner class ManageAdShowing : FullScreenContentCallback() {
        override fun onAdClicked() {
            super.onAdClicked()
            Log.w( "MainActivity", "Inside onAdClicked" )
        }

        override fun onAdImpression() {
            super.onAdImpression()
            Log.w( "MainActivity", "Inside onAdImpression" )
        }

        override fun onAdShowedFullScreenContent() {
            super.onAdShowedFullScreenContent()
            Log.w( "MainActivity", "Inside onAdShowedFullScreenContent" )
        }

        override fun onAdFailedToShowFullScreenContent(p0: AdError) {
            super.onAdFailedToShowFullScreenContent(p0)
            Log.w( "MainActivity", "Inside onADFailedToShow..." )
        }

        override fun onAdDismissedFullScreenContent() {
            super.onAdDismissedFullScreenContent()
            Log.w( "MainActivity", "Inside onAdDismissedFullScreenContent" )
            // go to second activity
            var intent : Intent = Intent( this@MainActivity, SecondActivity::class.java )
            startActivity( intent )
        }
    }

}