package net.csplab.adroid.kotlin.loomisbroadcastreceivercomponent.views

//import android.os.Build.VERSION_CODES.R

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import net.csplab.adroid.kotlin.loomisbroadcastreceivercomponent.databinding.ActivityMainBinding
import net.csplab.adroid.kotlin.loomisbroadcastreceivercomponent.receivers.PartyOneProvideReceiver
import net.csplab.adroid.kotlin.loomisbroadcastreceivercomponent.receivers.PayProviderReceiver
import net.csplab.adroid.kotlin.loomisbroadcastreceivercomponent.receivers.TimeoutListener
import net.csplab.adroid.kotlin.loomisbroadcastreceivercomponent.utility.UtilityActions
import java.text.SimpleDateFormat
import java.util.*

// This is the test class View:
// We start operation from here.
// Tasks description: make a component
// Extensible BroadcastReceiver

class MainActivity : AppCompatActivity(), TimeoutListener {
    private var TAG = MainActivity::class.java.simpleName

    private var mReadyToBroadCast: Boolean = false
    private lateinit var bind: ActivityMainBinding

    lateinit var mTstBroadcaster: PayProviderReceiver
    private var mIntentFilterActions = IntentFilter()
    private lateinit var actions: List<String>

    lateinit var tvWhatsTheTime: TextView

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

        // Get The Views from the layouts tvHeader = bind.tvHeaderPay
        tvWhatsTheTime = bind.tvWhatsTheTime
        val editTextNumber = bind.editTextNumber
        //val tvChoosePayProvider = bind.tvChoosePayProvider
        val btStartPayProvider1 = bind.btStartPayProvider1
        val btStartPayProvider2 = bind.btStartPayProvider2
        val btStartPayTestReceiverActivity = bind.btStartPayTestReceiverActivity

        // Timer from MainActivity
        mTimeOut = Timer()
        mTimeOut.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                this@MainActivity.runOnUiThread(
                    Runnable {
                        //val sdf: SimpleDateFormat = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault());
                        val sdf: SimpleDateFormat =
                            SimpleDateFormat("HH:mm:ss", Locale.getDefault())
                        val currentDateandTime = sdf.format(Date())
                        tvWhatsTheTime.text = "Timer: $currentDateandTime"
                    })
            }
        }, 8000, 3000)

        // Comment: System : We do not have a particular order of actions, ordered broadcast is for
        // listeners not for actions
        // Chk@: We could do with a timer and a sendBroadcast, also put action in list so we can
        // reference an array.

        val actionStart = UtilityActions.Util.PayProvider2.ACTION_PAYID2_START.toString()
        Log.i(TAG, "onCreate: $packageName")
        Log.i(TAG, "onCreate: actionStart: $actionStart")

        btStartPayTestReceiverActivity.setOnClickListener {

            val sAction = UtilityActions.collectActionsForProviderPartyOne()

            // Register ACTIONS for special PayProvider BroadcastReceiver
            // Maybe move of of this place which should be fore packing data into
            // the intent. Combining Action and Extras
            val intentFilterActions = IntentFilter().apply {
                addAction(Intent.ACTION_BATTERY_CHANGED)    // For test
                addAction(Intent.ACTION_POWER_DISCONNECTED) // For test

                addAction(sAction.first()) // start
                for (s in sAction.subList(1, sAction.size - 1)) {
                    Log.d(TAG, "p1: Action Strings Read: $s")
                    addAction(s)
                }
                //Arbitrary number of action steps ... Ho do we put them in
                addAction(sAction.last()) // End of ACTION EVENT
                intent.action = sAction[0]
                intent.putExtra("KEY_NAME", "Kylie Minogue")
                //intent.setComponent(net.csplab.adroid.kotlin.loomisbroadcastreceivercomponent.ReceiverTestActivity::class.java.simpleName)
                sendBroadcast(intent) // Chk@ in BroadcastReceiver
                intent.setClassName(
                    "net.csplab.adroid.kotlin.loomisbroadcastreceivercomponent",
                    "net.csplab.adroid.kotlin.loomisbroadcastreceivercomponent.views.ReceiverTestActivity"
                )
                //val i2 = Intent(this, ReceiverTestActivity.class)

                startActivity(intent)
            }
        }


        //! Test Send with Intent to Receiver Activity
        btStartPayTestReceiverActivity.setOnClickListener {
            //! Load the Actions for Party One Provider 3.party.
            val sAction = UtilityActions.collectActionsForProviderPartyOne()
//
//            // Register ACTIONS for special PayProvider BroadcastReceiver
//            // Maybe move of of this place which should be fore packing data into
//            // the intent. Combining Action and Extras
//            val intentFilter = IntentFilter().apply {
//                addAction(sAction.first()) // start
//                for (s in sAction.subList(1, sAction.size - 1)) {
//                    Log.d(TAG, "p1: Action Strings Read: $s")
//                    addAction(s)
//                }
//                //Arbitrary number of action steps ... Ho do we put them in
//                addAction(sAction.last()) // End of ACTION EVENT
//            }
//
//            intent.action = sAction[0]
//            // Walk through Actions and pack them, control by click button, maybe move this somewhere
//            // Else, maybe to a new button
//            var actionStepNum = 0
//            for (i in 0..sAction.size) {
//                intent.action = sAction[actionStepNum]
//
//                if(actionStepNum == 0) {
//                    intent.putExtra("KEY_NAME", "Kylie Minogue")
//                }
//                else if (actionStepNum == 1)
//                    intent.putExtra("KEY_NAME_ID", "Id")
//                    intent.putExtra("KEY_NAME_IDNUM", "460967")
//                // Function to add variable amount of extra
//                // Set Component to explicitly communicate within App / Package
            ///intent.setComponent(ReceiverTestActivity::class)
//                //sendBroadcast(intent) // Chk@ in BroadcastReceiver
//            }

            intent.setClassName(
                "net.csplab.adroid.kotlin.loomisbroadcastreceivercomponent",
                "net.csplab.adroid.kotlin.loomisbroadcastreceivercomponent.views.ReceiverTestActivity"
            )
            startActivity(intent)
        }

        // ConsumerPartyPayProviderBroadcastReceiver
        btStartPayProvider1.setOnClickListener {
            //! Get ACTION Set for Provider
            // Register ACTIONS for special PayProvider BroadcastReceiver
            // Chk@: movr preparation outside button click event!

            val providerName = "PartyOneProvider"
            val sActions = UtilityActions.collectActionsForProviderPartyOne()
            prepareProvider(providerName, intent, sActions)
            //mIntentFilterActions =

            mReadyToBroadCast = true
            //! Set Broadcaster type with associated Actions
            mTstBroadcaster = PartyOneProvideReceiver(sActions, providerName)

            registerReceiver(mTstBroadcaster, mIntentFilterActions)

            intent = Intent()
            //! Load Each intent with action and "Extras" data
            for (i in 0..sActions.size - 1) {
                intent.action = sActions[i]
                Log.d(
                    TAG,
                    "Intent => $intent.action :: $sActions[i] :: Action.size: $sActions.size "
                )
                val ia = intent.action
                Log.d(TAG, "$ia")
                if (intent.action == sActions[0]) {
                    intent.putExtra("KEY1", "ID4325")
                } else if (intent.action == sActions[1]) {
                    intent.putExtra("KEY2_1", "Amount")
                    intent.putExtra("KEY2_2", "Balance")
                    intent.putExtra("KEY2_3", "Idnum")
                    intent.putExtra("KEY2_4", "SWIFT")
                    intent.putExtra("KEY2_5", "IBAN")
                } else if (intent.action == sActions[2]) {
                    intent.putExtra("KEY3", "BYE!")
                }
                //! @Chk> Could wrap this in Tinmer. Schedule to test tiime limits
                sendBroadcast(intent) // send intent to ConsumerPartyProvider, Timing
            }
            //intent.action = "net.csplab.adroid.kotlin.loomisbroadcastreceivercomponent.PAYID1_START"
        }

        // BankOfBankBroadcastReceiver
//        btStartPayProvider2.setOnClickListener {
//            // Load Actions for number BankOfBank 3. party provider
//            prepareProvider("BankOfBankProvider", intent, UtilityActions.collectActionsForProviderBankOfBank())
//            //mStrAction =
//
//            mReadyToBroadCast = true
//            mTstBroadcaster = BankOfBankReceiver(mStrAction)
//
//            registerReceiver(mTstBroadcaster, IntentFilterActions)
//
//            //!
//            intent = Intent()
//            //  Run trough all possible ACTIONS and ready them
//            for(i in 0..mStrAction.size - 1) {
//                Log.d(TAG, "sAction")
//                intent.action = mStrAction[i]
//
//                // The Intent is for one broadcast, so we have to add the appropriate Extras for each Action:
//                // Then -> Ready next intent with action and extras to broadcast
//                if (intent.action == mStrAction[0]){
//                    intent.putExtra("KEY1", "ID4325")
//                } else if (intent.action == mStrAction[1]){
//                    intent.putExtra("KEY2_2", tvWhatsTheTime.text.toString()) // Create message editfield prefill it for testsS
//                    intent.putExtra("KEY2_2", "Balance")
//                    intent.putExtra("KEY2_3", "Idnum")
//                    intent.putExtra("KEY2_3", "SWIFT")
//                    intent.putExtra("KEY2_3", "IBAN")
//                } else if (intent.action == mStrAction[2]){
//                    intent.putExtra("KEY3", "5000")
//                }else if (intent.action == mStrAction[3]){
//                    intent.putExtra("KEY4", "BYE!")
//                }
//                sendBroadcast(intent)
//            }
//            //intent.action = "net.csplab.adroid.kotlin.loomisbroadcastreceivercomponent.PAYID1_START"
//        }
//        // Utility Class: Move to
//        //========================================================
//
//        //tstBroadcaster = ConsumePartyPayProviderBroadcastReciver(sAction,sAction.size)
//        // Is this a problem that this is not in the resume part,
//        // Do we only need the register in resume

    }

    override fun onResume() {
        super.onResume()

        if (mReadyToBroadCast) {
            registerReceiver(mTstBroadcaster, mIntentFilterActions)
        }
    }

    override fun onPause() {
        super.onPause()
        if (mReadyToBroadCast) {
            unregisterReceiver(mTstBroadcaster)
        }
    }

    //! Partition in functions
    // prepareProvider(providername, intent, actions) // intentfilter locally
    //   addActions()  - Add actions to the newlycreated provider
    //   addExtras to each action
    //      addAnExtra()

    fun prepareProvider(providerName: String, intent: Intent, actionSet: List<String>) {
        // Register ACTIONS for special PayProvider BroadcastReceiver
        addActionsForReceiver(actionSet)
        addExtrasData(intent)
        // return provider?
    }

    //! Descr: Add Extras data to intent For sending
    private fun addExtrasData(intent: Intent) {
        // ACTION
        intent.putExtra("PUT_KEY", "4566")
    }

    //! Descr: addAction for specific receiver
    private fun addActionsForReceiver(actions: List<String>): IntentFilter {

        var intentFilterActions = IntentFilter().apply {
            //addAction("net.csplab.adroid.kotlin.loomisbroadcastreceivercomponent.PAYID1_START")
            //! Preparation for protocol state walk, with start and end
            addAction(actions.first()) // start
            for (s in actions.subList(1, actions.size - 1)) {
                Log.d(TAG, "Added Action for receiver: $s : Sizelist ${actions.size}")
                addAction(s)
            }
            //Arbitrary number of action steps
            addAction(actions.last()) // For End of ACTION event
        }
        return intentFilterActions
    }


    override fun updateTimer(info: String) {
        Log.d(TAG, "UpdateTimer: Callback")
        tvWhatsTheTime.text = info
    }
}
