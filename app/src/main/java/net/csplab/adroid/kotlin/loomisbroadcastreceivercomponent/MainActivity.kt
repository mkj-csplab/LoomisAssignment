package net.csplab.adroid.kotlin.loomisbroadcastreceivercomponent

//import android.os.Build.VERSION_CODES.R
import android.content.BroadcastReceiver
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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
    private var TAG = MainActivity::class.java.simpleName
    lateinit var tstBroadcaster: ConsumePartyPayProviderBroadcastReciver
    private var intentFilterActions = IntentFilter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Get The Views from the layouts
        Log.i(TAG, "onCreate:")
        //Send Broadcast
        //sendBroadcast(Intent.)
        //val intentSet = Intent()
        //intentSet.action
        //val intentBundle = Intent().extras
        //sendBroadcast(intentSet)

        // =============================================
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

        // Utility Class: Move to
        val pack = packageName
        collectActionsForProvider1(pack)
        //collectActionsForProvider2()
        // Register ACTIONS for special PayProvider BroadcastReceiver
        intentFilterActions = IntentFilter().apply {
            addAction(Intent.ACTION_BATTERY_CHANGED)    // For test
            addAction(Intent.ACTION_POWER_DISCONNECTED) // For test
            //addAction(packageName + "ACTION_PAYID1_INIT") // Init: Should be the user pressing Pay on Mainactivity@
            addAction(packageName + "ACTION_PAYID1_START") // start
            addAction(packageName + "ACTION_PAYID1_STEP1")
            addAction(packageName + "ACTION_PAYID1_STEP2")
            addAction(packageName + "ACTION_PAYID1_STEP3")
            //Arbitrary number of action steps ... Ho do we put them in
            addAction(packageName + "ACTION_PAYID1_END") // End of ACTION EVENT

        }

        //========================================================
        tstBroadcaster = ConsumePartyPayProviderBroadcastReciver()
        // Is this a problem that this is not in the resume part,
        // Do we only need the register in resume
    }


    override fun onResume() {
        super.onResume()
        registerReceiver(tstBroadcaster, intentFilterActions)
    }

    // In utility ss, maybe as static array
    private fun collectActionsForProvider1(pack: String): List<String> {
        //action_init: String = ctx.getPackage // ACTION_PAY_INIT") // Init
        val s1 = packageName + "ACTION_PAYID1_START" // start
        val s2 = packageName + "ACTION_PAYID1_STEP1"
        val s3 = packageName + "ACTION_PAYID1_STEP2"
        val s4 = packageName + "ACTION_PAYID1_STEP3"
        //Arbitrary number of action steps ... How to add them, dynamically or set per specialisation
        val s5 = packageName + "ACTION_PAYID1_END" // End of ACTION EVENT
        Log.d(TAG, "Print Package Name $packageName : Action names $s1 $s2 $s3 $s4 $s5")
        val customActions = listOf(s1,s2,s3,s4,s5)
        return customActions
    }
    private fun collectActionsForProvider2(pack: String): List<String> {
        // Chk@ : Put int list, maybe use size for that?
        //action_init: String = ctx.getPackage // ACTION_PAY_INIT") // Init
        val s1 = packageName + "ACTION_PAYID1_START" // start
        val s2 = packageName + "ACTION_PAYID1_STEP1"
        val s3 = packageName + "ACTION_PAYID1_STEP2"
        val s4 = packageName + "ACTION_PAYID1_END" // End of ACTION EVENT
        Log.d(TAG, "Print Package Name $packageName : Action names $s1 $s2 $s3 $s4")
        val customActions = listOf(s1,s2,s3,s4)
        return customActions
    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(tstBroadcaster)
    }

}
