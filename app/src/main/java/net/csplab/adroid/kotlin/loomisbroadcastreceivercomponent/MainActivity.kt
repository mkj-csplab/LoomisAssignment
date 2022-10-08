package net.csplab.adroid.kotlin.loomisbroadcastreceivercomponent

//import android.os.Build.VERSION_CODES.R
import android.content.BroadcastReceiver
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
//import net.csplab.adroid.kotlin.loomisassignment.R
//import net.csplab.adroid.kotlin.loomisassignment.R

import receivers.ConsumePartyPayProviderBroadcastReciver

// This is the test class View:
// We start operation from here.

// Taks description: make a component
// Extensible BroadcastReciever

//
//Context c = getApplicationContext();// flag would be require Calling startActivity() from outside of an Activity  context requires the FLAG_ACTIVITY_NEW_TASK flag
//Intent i = new Intent(action_string);
//i.setPackage(context.getPackageName());//this did the trick actually
//i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//context.startActivity(i);

class MainActivity : AppCompatActivity() {
    // NOTES HERE
    lateinit var tstBroadcaster: ConsumePartyPayProviderBroadcastReciver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Get The Views from the layouts


        //Send Broadcast
        //sendBroadcast(Intent.)
        //val intentSet = Intent()
        //intentSet.action
        //val intentBundle = Intent().extras
        //sendBroadcast(intentSet)
    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(tstBroadcaster)
    }

    override fun onResume() {
        super.onResume()

        tstBroadcaster = ConsumePartyPayProviderBroadcastReciver()
        //val inf = IntentFilter(Intent.ACTION_BATTERY_CHANGED)

        // Different Filter adders
        // Make Intent ready with extras!
        //Intent().setComponent()
//        val inf = IntentFilter()
//        inf.addAction(packageName + "PAYPROVIDER_NAME" + "ACTION_PAY_PAY_INIT")
//        inf.addAction(Intent.ACTION_BATTERY_CHANGED)
//        inf.addAction(Intent.ACTION_POWER_DISCONNECTED)

        // Place this somewhere more
        val inf = IntentFilter().apply {
            addAction(packageName + "PAYPROVIDER_NAME" + "ACTION_PAY_PAY_INIT") // Init
            addAction(packageName + "PAYPROVIDER_NAME" + "ACTION_PAY_PAY_START") // start
            addAction(Intent.ACTION_BATTERY_CHANGED)
            addAction(Intent.ACTION_POWER_DISCONNECTED)
            addAction(packageName + "PAYPROVIDER_NAME" + "ACTION_PAY_PAY_END")
        }
        registerReceiver(tstBroadcaster, inf)
    }
    //registerReceiver(tstBroadcaster, inf)
}
