package net.csplab.adroid.kotlin.loomisbroadcastreceivercomponent

//import android.os.Build.VERSION_CODES.R
import android.content.BroadcastReceiver
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import net.csplab.adroid.kotlin.loomisbroadcastreceivercomponent.databinding.ActivityMainBinding
import utility.UtilityActions

import receivers.ConsumePartyPayProviderBroadcastReciver
import utility.UtilityActions.Util.collectActionsForProvider1

// This is the test class View:
// We start operation from here.

// Taks description: make a component
// Extensible BroadcastReciever

//Context c = getApplicationContext();// flag would be require Calling startActivity() from outside of an Activity  context requires the FLAG_ACTIVITY_NEW_TASK flag
//Intent i = new Intent(action_string);
//i.setPackage(context.getPackageName());//this did the trick actually
//i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//context.startActivity(i);

class MainActivity : AppCompatActivity() {
    // NOTES HERE
    private var TAG = MainActivity::class.java.simpleName

    private lateinit var bind: ActivityMainBinding
    lateinit var tstBroadcaster: ConsumePartyPayProviderBroadcastReciver
    private var intentFilterActions = IntentFilter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bind.root)

        // Get Views
        val tvHeader = bind.tvHeaderPay
        val editTextNumber = bind.editTextNumber
        //val tvChoosePayProvider = bind.tvChoosePayProvider
        val btStartPayProvider1 = bind.btStartPayProvider1
        val btStartPayProvider2 = bind.btStartPayProvider2

        // Comment: System : We do not have a particular order of actions, ordered broadcast is for
        // listeners not for actions
        // Chk@: We could do with a timer and a sendBroadcast, also put action in list so we can
        // reference inot an array of action first and last is Start and end.

        // Get The Views from the layouts
        Log.i(TAG, "onCreate: $packageName")
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
        var cardType = 1  // cardtype 1 and 2 corresponds to a specialiation of card type and pay provider
        when(cardType){
//            /tstBroadcaster = ConsumePartyPayProviderBroadcastReciver()
        } //else {
        //}

        btStartPayProvider1.setOnClickListener {

            val sAction = collectActionsForProvider1("pack")
            //collectActionsForProvider2()
            // Register ACTIONS for special PayProvider BroadcastReceiver
            intentFilterActions = IntentFilter().apply {
                addAction(Intent.ACTION_BATTERY_CHANGED)    // For test
                addAction(Intent.ACTION_POWER_DISCONNECTED) // For test
                //addAction(packageName + "ACTION_PAYID1_INIT") // Init: Should be the user pressing Pay on Mainactivity@
                addAction(packageName + ".ACTION_PAYID1_START") // start
                addAction(packageName + ".ACTION_PAYID1_STEP1")
                addAction(packageName + ".ACTION_PAYID1_STEP2")
                addAction(packageName + ".ACTION_PAYID1_STEP3")
                //Arbitrary number of action steps ... Ho do we put them in
                addAction(packageName + ".ACTION_PAYID1_END") // End of ACTION EVENT
            }

        }

        btStartPayProvider2.setOnClickListener {
            val sAction = UtilityActions.collectActionsForProvider2("pack")
            // Changed utility class with a companion object
            //collectActionsForProvider2()
            // Register ACTIONS for special PayProvider BroadcastReceiver
            intentFilterActions = IntentFilter().apply {
                //addAction(packageName + "ACTION_PAYID1_INIT") // Init: Should be the user pressing Pay on Mainactivity@
                addAction(sAction.first()) // start
                for (s in sAction) {
                    addAction(s)
                }
                //Arbitrary number of action steps ... Ho do we put them in
                addAction(sAction.last()) // End of ACTION EVENT
            }

        }

        // Utility Class: Move to

        //========================================================
        tstBroadcaster = ConsumePartyPayProviderBroadcastReciver()
        // Is this a problem that this is not in the resume part,
        // Do we only need the register in resume
    }

    override fun onResume() {
        super.onResume()
        registerReceiver(tstBroadcaster, intentFilterActions)
    }


    override fun onPause() {
        super.onPause()
        unregisterReceiver(tstBroadcaster)
    }
}
