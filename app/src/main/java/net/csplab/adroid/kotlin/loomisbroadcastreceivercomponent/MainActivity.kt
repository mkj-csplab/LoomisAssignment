package net.csplab.adroid.kotlin.loomisbroadcastreceivercomponent

//import android.os.Build.VERSION_CODES.R

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import net.csplab.adroid.kotlin.loomisbroadcastreceivercomponent.databinding.ActivityMainBinding
import receivers.BankOfBankReceiver
import receivers.ConsumePartyPayProviderReciver
import receivers.PayProviderReceiver
import utility.UtilityActions
import java.util.*

// This is the test class View:
// We start operation from here.
// Tasks description: make a component
// Extensible BroadcastReciever

class MainActivity : AppCompatActivity() {
    private var TAG = MainActivity::class.java.simpleName
    private var readyToBroadCast: Boolean = false

    private lateinit var bind: ActivityMainBinding
    lateinit var mTstBroadcaster: PayProviderReceiver
    private var mIntentFilterActions = IntentFilter()
    private lateinit var mStrAction: List<String>

    lateinit var mTimeOut: Timer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bind.root)

        // Nav Back [ <- ]
        val actionBar: ActionBar? = supportActionBar
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(false) // Disable the button
            actionBar.setDisplayHomeAsUpEnabled(true) // add left caret
            actionBar.setDisplayShowHomeEnabled(true) // enable icon
        }


        // Get The Views from the layouts
        val tvHeader = bind.tvHeaderPay
        val editTextNumber = bind.editTextNumber
        //val tvChoosePayProvider = bind.tvChoosePayProvider
        val btStartPayProvider1 = bind.btStartPayProvider1
        val btStartPayProvider2 = bind.btStartPayProvider2
        val btStartPayTestReceiverActivity = bind.btStartPayTestReceiverActivity

//        // Timer from MainActivity
//        mTimeOut.schedule(object: TimerTask() {
//            override fun run() {
//                this@MainActivity.runOnUiThread(
//                    Runnable {
//                        tvHeader.text = "Timer: " + Date().time
//                    }
//
//                )
//            }
//        },1000)

        // Comment: System : We do not have a particular order of actions, ordered broadcast is for
        // listeners not for actions
        // Chk@: We could do with a timer and a sendBroadcast, also put action in list so we can
        // reference an array.

        val actionStart = UtilityActions.Util.PayProvider2.ACTION_PAYID2_START.toString()
        Log.i(TAG, "onCreate: $packageName")
        Log.i(TAG, "onCreate: actionStart: $actionStart")

        btStartPayTestReceiverActivity.setOnClickListener {
            val sAction = UtilityActions.collectActionsForProviderConsumerPartyPay("pack")

            // Register ACTIONS for special PayProvider BroadcastReceiver
            // Maybe move of of this place which should be fore packing data into
            // the intent. Combining Action and Extras
            val intentFilter = IntentFilter().apply {
                addAction(sAction.first()) // start
                for (s in sAction.subList(1, sAction.size - 1)) {
                    Log.d(TAG, "p1: Action Strings Read: $s")
                    addAction(s)
                }
                //Arbitrary number of action steps ... Ho do we put them in
                addAction(sAction.last()) // End of ACTION EVENT
            }

                intent.action = sAction[0]
                // Walk through Actions and pack them, control by click button, maybe move this somewhere
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
                    // Set Component to explicitly communicate within App / Package
                    //intent.setComponent(net.csplab.adroid.kotlin.loomisbroadcastreceivercomponent.ReceiverTestActivity::class.java.simpleName)
                    //sendBroadcast(intent) // Chk@ in BroadcastReceiver
                }
                // ---------------------------------------------------------------
                    intent.setClassName(
                      "net.csplab.adroid.kotlin.loomisbroadcastreceivercomponent",
                      "net.csplab.adroid.kotlin.loomisbroadcastreceivercomponent.ReceiverTestActivity" )
                startActivity(intent)
      }

        // ConsumerPartyPayProviderBroadcastReceiver
        btStartPayProvider1.setOnClickListener {
            val sAction = UtilityActions.collectActionsForProviderBankOfBank("pack")

            ////Arbitrary number of action steps ... Ho do we put them in
            // Register ACTIONS for special PayProvider BroadcastReceiver
            mIntentFilterActions = IntentFilter().apply {
                // intentFilterActions.addAction("net.csplab.adroid.kotlin.loomisbroadcastreceivercomponent.PAYID1_START")
                // Example of ACTION string
                for (s in sAction) {
                    Log.d(TAG, "p2: Action Strings Read: $s Sizelist ${sAction.size} \n")
                    addAction(s)
                }
            }

            readyToBroadCast = true
            mTstBroadcaster = ConsumePartyPayProviderReciver(sAction)

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
            mStrAction = UtilityActions.collectActionsForProviderBankOfBank("pack")

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
            mTstBroadcaster = BankOfBankReceiver(mStrAction)

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
