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

        tstBroadcaster = ConsumePartyPayProviderBroadcastReciver()
        // Is this a problem that this is not in the resume part,
        // Do we only need the register in resume
    }


    override fun onResume() {
        super.onResume()

        //val inf = IntentFilter(Intent.ACTION_BATTERY_CHANGED)

        // Different Filter adders
        // Make Intent ready with extras!
        //Intent().setComponent()
//        val inf = IntentFilter()
//        inf.addAction(packageName + "PAYPROVIDER_NAME" + "ACTION_PAY_PAY_INIT")
//        inf.addAction(Intent.ACTION_BATTERY_CHANGED)
//        inf.addAction(Intent.ACTION_POWER_DISCONNECTED)

        // Place this somewhere more "central"
        var cardType = 1 // cardtype 1 and 2 corresponds to a specialiation of card type and pay provider
        when(cardType){
//            /tstBroadcaster = ConsumePartyPayProviderBroadcastReciver()
        }

        // Register ACTIONS for special PayProvider BroadcastReceiver
        val inf = IntentFilter().apply {
            addAction(Intent.ACTION_BATTERY_CHANGED)    // For test
            addAction(Intent.ACTION_POWER_DISCONNECTED) // For test
            addAction(packageName + "PAYPROVIDER_NAME" + "ACTION_PAY_INIT") // Init
            addAction(packageName + "PAYPROVIDER_NAME" + "ACTION_PAY_START") // start
            addAction(packageName + "PAYPROVIDER_NAME" + "ACTION_PAY_STEP1")
            addAction(packageName + "PAYPROVIDER_NAME" + "ACTION_PAY_STEP2")
            addAction(packageName + "PAYPROVIDER_NAME" + "ACTION_PAY_STEP3")
            //Arbitrary number of action steps ... Ho do we put them in
            addAction(packageName + "PAYPROVIDER_NAME" + "ACTION_PAY_END") // End of ACTION EVENT

        }
        registerReceiver(tstBroadcaster, inf)
    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(tstBroadcaster)
    }
}
