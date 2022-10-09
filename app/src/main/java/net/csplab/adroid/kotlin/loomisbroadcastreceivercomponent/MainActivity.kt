package net.csplab.adroid.kotlin.loomisbroadcastreceivercomponent

//import android.os.Build.VERSION_CODES.R

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import net.csplab.adroid.kotlin.loomisbroadcastreceivercomponent.databinding.ActivityMainBinding
import receivers.BankOfBankBroadcastReceiver
import receivers.ConsumePartyPayProviderBroadcastReciver
import receivers.PayProviderBroadcastReceiver
import utility.UtilityActions
import utility.UtilityActions.Util.collectActionsForProvider1

// This is the test class View:
// We start operation from here.
// Tasks description: make a component
// Extensible BroadcastReciever

//Context c = getApplicationContext();// flag would be require Calling startActivity() from outside of an Activity  context requires the FLAG_ACTIVITY_NEW_TASK flag
//Intent i = new Intent(action_string);
//i.setPackage(context.getPackageName());//this did the trick actually
//i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//context.startActivity(i);

class MainActivity : AppCompatActivity() {
    private var readyToBroadCast: Boolean = false

    // NOTES HERE
    private var TAG = MainActivity::class.java.simpleName

    private lateinit var bind: ActivityMainBinding
    lateinit var tstBroadcaster: PayProviderBroadcastReceiver
    private var intentFilterActions = IntentFilter()
    private lateinit var sAction: List<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bind.root)

        val actionBar: ActionBar? = supportActionBar
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(false) // Disable the button
            actionBar.setDisplayHomeAsUpEnabled(true) // Remove the left caret
            actionBar.setDisplayShowHomeEnabled(true) // Remove the icon
        }

        // Get Views
        val tvHeader = bind.tvHeaderPay
        val editTextNumber = bind.editTextNumber
        //val tvChoosePayProvider = bind.tvChoosePayProvider
        val btStartPayProvider1 = bind.btStartPayProvider1
        val btStartPayProvider2 = bind.btStartPayProvider2
        val btStartPayTestReceiverActivity = bind.btStartPayTestReceiverActivity

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

        // Different Filter adders
        // Make Intent ready with extras!
        //Intent().setComponent()
//        val inf = IntentFilter()
//        inf.addAction(packageName + "PAYPROVIDER_NAME" + "ACTION_PAY_PAY_INIT")
//        inf.addAction(Intent.ACTION_BATTERY_CHANGED)
//        inf.addAction(Intent.ACTION_POWER_DISCONNECTED)

        // Place this somewhere more "central"
        var cardType =
            1  // cardtype 1 and 2 corresponds to a specialiation of card type and pay provider
        when (cardType) {
//            /tstBroadcaster = ConsumePartyPayProviderBroadcastReciver()
        } //else {
        //}

        btStartPayTestReceiverActivity.setOnClickListener {

            val sAction = collectActionsForProvider1("pack")

            // Register ACTIONS for special PayProvider BroadcastReceiver
            // Maybe move of of this place which should be fore packing data into
            // the intent. Combining Action and Extras
            intentFilterActions = IntentFilter().apply {
                addAction(Intent.ACTION_BATTERY_CHANGED)    // For test
                addAction(Intent.ACTION_POWER_DISCONNECTED) // For test

                addAction(sAction.first()) // start
                for (s in sAction.subList(1, sAction.size - 1)) {
                    Log.d(TAG, "p1: Action Strings Read: $s")
                    addAction(s)
                }
                //Arbitrary number of action steps ... Ho do we put them in
                addAction(sAction.last()) // End of ACTION EVENT

                // Walktrough Actions and pack them, control by click button, maybe move this somewhere
                // Else, maybe to a new button
                var actionStepNum = 0
                for (i in 0..sAction.size) {
                    intent.action = sAction[actionStepNum]

                    if (actionStepNum == 0) {
                        intent.putExtra("KEY_NAME", "Kylie Minogue")

                    }
                    else if (actionStepNum == 1)
                        intent.putExtra("KEY_NAME", "Id")
                        intent.putExtra("KEY_NAME", "460967")
                    // Function to add variable amount of extra
                    // Set Component to explixitly communicate within App / Package
                    //intent.setComponent(net.csplab.adroid.kotlin.loomisbroadcastreceivercomponent.ReceiverTestActivity::class.java.simpleName)
                    sendBroadcast(intent) // Chk@ in BroadcastReceiver
                }
                    //intent.setClassName(
                    //"net.csplab.adroid.kotlin.loomisbroadcastreceivercomponent",
                    //"net.csplab.adroid.kotlin.loomisbroadcastreceivercomponent.ReceiverTestActivity" )

                startActivity(intent)

                //addAction(packageName + "ACTION_PAYID1_INIT") // Init: Should be the user pressing Pay on Mainactivity@

//                addAction(packageName + ".ACTION_PAYID1_START") // start
//                addAction(packageName + ".ACTION_PAYID1_STEP1")
//                addAction(packageName + ".ACTION_PAYID1_STEP2")
//                addAction(packageName + ".ACTION_PAYID1_STEP3")
//                //Arbitrary number of action steps ... Ho do we put them in
//                addAction(packageName + ".ACTION_PAYID1_END") // End of ACTION EVENT
            }
        }

        btStartPayProvider1.setOnClickListener {
            val sAction = UtilityActions.collectActionsForProvider2("pack")
            // Changed utility class with a companion object
            //collectActionsForProvider2()


            // Register ACTIONS for special PayProvider BroadcastReceiver
            intentFilterActions = IntentFilter().apply {
                //addAction(packageName + "ACTION_PAYID1_INIT") // Init: Should be the user pressing Pay on Mainactivity@
                addAction(sAction.first()) // start
                for (s in sAction.subList(1, sAction.size - 1)) {
                    Log.d(TAG, "p2: Action Strings Read: $s Sizelist ${sAction.size}\"")
                    addAction(s)
                }
                //Arbitrary number of action steps ... Ho do we put them in
                addAction(sAction.last()) // End of ACTION EVENT
            }
            readyToBroadCast = true
            tstBroadcaster = ConsumePartyPayProviderBroadcastReciver(sAction,sAction.size)
        }

        btStartPayProvider2.setOnClickListener {
            sAction = UtilityActions.collectActionsForProvider2("pack")
            // Changed utility class with a companion object
            //collectActionsForProvider2()
            // Register ACTIONS for special PayProvider BroadcastReceiver
            intentFilterActions = IntentFilter().apply {
                //addAction(packageName + "ACTION_PAYID1_INIT") // Init: Should be the user pressing Pay on Mainactivity@
                addAction(sAction.first()) // start
                for (s in sAction.subList(1, sAction.size - 1)) {
                    Log.d(TAG, "p2: Action Strings Read: $s Sizelist ${sAction.size}")
                    addAction(s)
                }
                //Arbitrary number of action steps ... Ho do we put them in
                addAction(sAction.last()) // End of ACTION EVENT
            }
            readyToBroadCast = true
            tstBroadcaster = BankOfBankBroadcastReceiver(sAction,sAction.size)
        }
        // Utility Class: Move to
        //========================================================

        //tstBroadcaster = ConsumePartyPayProviderBroadcastReciver(sAction,sAction.size)
        // Is this a problem that this is not in the resume part,
        // Do we only need the register in resume
    }

    override fun onResume() {
        super.onResume()

        if (readyToBroadCast) {
            registerReceiver(tstBroadcaster, intentFilterActions)
        }
    }

    override fun onPause() {
        super.onPause()
        if (readyToBroadCast) {
            unregisterReceiver(tstBroadcaster)
        }
    }
}
