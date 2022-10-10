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

// This is the test class View:
// We start operation from here.
// Tasks description: make a component
// Extensible BroadcastReciever

class MainActivity : AppCompatActivity() {
    private var readyToBroadCast: Boolean = false

    // NOTES HERE
    private var TAG = MainActivity::class.java.simpleName

    private lateinit var bind: ActivityMainBinding
    lateinit var mTstBroadcaster: PayProviderBroadcastReceiver
    private var mIntentFilterActions = IntentFilter()
    private lateinit var mStrAction: List<String>

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

//        var cardType = 1  // cardtype 1 and 2 corresponds to a specialiation of card type and pay provider
//        when (cardType) {
//            /tstBroadcaster = ConsumePartyPayProviderBroadcastReciver()
//            } //else { //}

//        btStartPayTestReceiverActivity.setOnClickListener {
//
//            val sAction = collectActionsForProvider1("pack")
//
//            // Register ACTIONS for special PayProvider BroadcastReceiver
//            // Maybe move of of this place which should be fore packing data into
//            // the intent. Combining Action and Extras
//            intentFilterActions = IntentFilter().apply {
//                addAction(Intent.ACTION_BATTERY_CHANGED)    // For test
//                addAction(Intent.ACTION_POWER_DISCONNECTED) // For test
//
//                addAction(sAction.first()) // start
//                for (s in sAction.subList(1, sAction.size - 1)) {
//                    Log.d(TAG, "p1: Action Strings Read: $s")
//                    addAction(s)
//                }
//                //Arbitrary number of action steps ... Ho do we put them in
//                addAction(sAction.last()) // End of ACTION EVENT
//
//                intent.action = sAction[0]
//                // Walktrough Actions and pack them, control by click button, maybe move this somewhere
//                // Else, maybe to a new button
////                var actionStepNum = 0
////                for (i in 0..sAction.size) {
////                    intent.action = sAction[actionStepNum]
////
////                    if (actionStepNum == 0) {
////                        intent.putExtra("KEY_NAME", "Kylie Minogue")
////                    }
////                    else if (actionStepNum == 1)
////                        intent.putExtra("KEY_NAME", "Id")
////                        intent.putExtra("KEY_NAME", "460967")
////                    // Function to add variable amount of extra
////                    // Set Component to explixitly communicate within App / Package
//                    //intent.setComponent(net.csplab.adroid.kotlin.loomisbroadcastreceivercomponent.ReceiverTestActivity::class.java.simpleName)
//                    //sendBroadcast(intent) // Chk@ in BroadcastReceiver
////                }
//                // ------------
//                    intent.setClassName(
//                    "net.csplab.adroid.kotlin.loomisbroadcastreceivercomponent",
//                    "net.csplab.adroid.kotlin.loomisbroadcastreceivercomponent.ReceiverTestActivity" )
//                startActivity(intent)
//                // ---
//
//                //addAction(packageName + "ACTION_PAYID1_INIT") // Init: Should be the user pressing Pay on Mainactivity@
//
////                addAction(packageName + ".ACTION_PAYID1_START") // start
////                addAction(packageName + ".ACTION_PAYID1_STEP1")
////                addAction(packageName + ".ACTION_PAYID1_STEP2")
////                addAction(packageName + ".ACTION_PAYID1_STEP3")
////                //Arbitrary number of action steps ... Ho do we put them in
////                addAction(packageName + ".ACTION_PAYID1_END") // End of ACTION EVENT
//            }
//        }
//
        // ConsumerPartyPayProviderBroadcastReceiver
        btStartPayProvider1.setOnClickListener {
            val sAction = UtilityActions.collectActionsForProvider2("pack")

            // Register ACTIONS for special PayProvider BroadcastReceiver
            mIntentFilterActions = IntentFilter().apply {
                //addAction(packageName + "ACTION_PAYID1_INIT") // Init: Should be the user pressing Pay on Mainactivity@
                addAction(sAction.first()) // start
                for (s in sAction.subList(1, sAction.size - 1)) {
                    Log.d(TAG, "p2: Action Strings Read: $s Sizelist ${sAction.size} \n")
                    addAction(s)
                }
                //Arbitrary number of action steps ... Ho do we put them in
                addAction(sAction.last()) // End of ACTION EVENT
            }
            //intentFilterActions.addAction("net.csplab.adroid.kotlin.loomisbroadcastreceivercomponent.PAYID1_START")

            readyToBroadCast = true
            mTstBroadcaster = ConsumePartyPayProviderBroadcastReciver(sAction)

            registerReceiver(mTstBroadcaster, mIntentFilterActions)

            intent = Intent()
            for(i in 0..sAction.size - 1) {
                intent.action = sAction[i]
                Log.d(TAG, "Intent => $intent.action :: $sAction[i] :: Action.size: $sAction.size ")
                val ia = intent.action
                Log.d(TAG, "$ia")
                if (intent.action == sAction[0]) {
                    intent.putExtra("KEY1", "ID4325")
                } else if (intent.action == sAction[1]) {
                    intent.putExtra("KEY2_1", "Amount")
                    intent.putExtra("KEY2_2", "Balance")
                    intent.putExtra("KEY2_3", "Idnum")
                    intent.putExtra("KEY2_4", "SWIFT")
                    intent.putExtra("KEY2_5", "IBAN")
                } else if (intent.action == sAction[2]) {
                    intent.putExtra("KEY3", "BYE!")
                }
                sendBroadcast(intent) // send intent to ConsumerPartyProvider, Timing
            }
            //intent.action = "net.csplab.adroid.kotlin.loomisbroadcastreceivercomponent.PAYID1_START"

        }

        // BankOfBankBroadcastReceiver
        btStartPayProvider2.setOnClickListener {
            mStrAction = UtilityActions.collectActionsForProvider2("pack")

            // Register ACTIONS for special PayProvider BroadcastReceiver
            mIntentFilterActions = IntentFilter().apply {
                // Init: Should be the user pressing Pay on MainActivity@
                addAction(mStrAction.first()) // start
                for (s in mStrAction.subList(1, mStrAction.size - 1)) {
                    Log.d(TAG, "p2: Action Strings Read: $s Sizelist ${mStrAction.size}")
                    addAction(s)
                }
                //Arbitrary number of action steps ... Ho do we put them in
                addAction(mStrAction.last()) // End of ACTION EVENT
                //addAction("net.csplab.adroid.kotlin.loomisbroadcastreceivercomponent.PAYID1_START")
            }
            readyToBroadCast = true
            mTstBroadcaster = BankOfBankBroadcastReceiver(mStrAction)

            registerReceiver(mTstBroadcaster, mIntentFilterActions)

            intent = Intent()
            //  Run trough all possible ACTIONS and ready them
            for(i in 0..mStrAction.size - 1) {
                Log.d(TAG, "sAction")
                intent.action = mStrAction[i]

                // The Intent is for one broadcast, so we have to add the appropiate Extras for each Action:
                // Then -> Ready next intent with action and extras to broadcast
                if (intent.action == mStrAction[0]){
                    intent.putExtra("KEY1", "ID4325")
                } else if (intent.action == mStrAction[1]){
                    intent.putExtra("KEY2_2", "Amount")
                    intent.putExtra("KEY2_2", "Balance")
                    intent.putExtra("KEY2_3", "Idnum")
                    intent.putExtra("KEY2_3", "SWIFT")
                    intent.putExtra("KEY2_3", "IBAN")
                } else if (intent.action == mStrAction[2]){
                    intent.putExtra("KEY3", "5000")
                }else if (intent.action == mStrAction[3]){
                    intent.putExtra("KEY4", "BYE!")
                }
                sendBroadcast(intent)
            }
            //intent.action = "net.csplab.adroid.kotlin.loomisbroadcastreceivercomponent.PAYID1_START"
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
            registerReceiver(mTstBroadcaster, mIntentFilterActions)
        }
    }

    override fun onPause() {
        super.onPause()
        if (readyToBroadCast) {
            unregisterReceiver(mTstBroadcaster)
        }
    }
}
