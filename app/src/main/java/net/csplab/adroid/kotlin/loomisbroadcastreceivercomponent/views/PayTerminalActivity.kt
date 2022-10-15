package net.csplab.adroid.kotlin.loomisbroadcastreceivercomponent.views

//import android.os.Build.VERSION_CODES.R

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import net.csplab.adroid.kotlin.loomisbroadcastreceivercomponent.databinding.ActivityPayTerminalBinding
import net.csplab.adroid.kotlin.loomisbroadcastreceivercomponent.receivers.BankOfBankReceiver
import net.csplab.adroid.kotlin.loomisbroadcastreceivercomponent.receivers.PartyOneProviderReceiver
import net.csplab.adroid.kotlin.loomisbroadcastreceivercomponent.receivers.PayProviderReceiver
import net.csplab.adroid.kotlin.loomisbroadcastreceivercomponent.receivers.TimeoutListener
import net.csplab.adroid.kotlin.loomisbroadcastreceivercomponent.utility.UtilityActions
import java.text.SimpleDateFormat
import java.util.*

// This is the test class View:
// We start operation from here.
// Tasks description: make a component
// Extensible BroadcastReceiver

class PayTerminalActivity : AppCompatActivity(), TimeoutListener {
    private var TAG = PayTerminalActivity::class.java.simpleName

    private var mReadyToBroadCast: Boolean = false
    private lateinit var bind: ActivityPayTerminalBinding

    lateinit var mTstBroadcaster: PayProviderReceiver
    private var mIntentFilterActions = IntentFilter()
    private lateinit var actionSet: List<String>

    lateinit var tvShowTime: TextView

    lateinit var mRepeatTimer: Timer
    lateinit var mTimeout: Timer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityPayTerminalBinding.inflate(layoutInflater)
        setContentView(bind.root)

        // Nav Back [ <- ]
        val actionBar: ActionBar? = supportActionBar
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(false) // Disable the button
            actionBar.setDisplayHomeAsUpEnabled(true) // add left caret
            actionBar.setDisplayShowHomeEnabled(true) // enable icon
        }

        // Get The Views from the layouts tvHeader = bind.tvHeaderPay
        tvShowTime = bind.tvShowTime
        val btJavaActivity = bind.btJavaActivity

        val editTextNumber = bind.editTextNumber
        //val tvChoosePayProvider = bind.tvChoosePayProvider
        val btStartPayProvider1 = bind.btStartPayProvider1
        val btStartPayProvider2 = bind.btStartPayProvider2
        val btStartPayTestReceiverActivity = bind.btStartPayTestReceiverActivity

        //! Timer from PayTerminalActivity
        mRepeatTimer = Timer()

        //: Check: move to tiemr method!
        mRepeatTimer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                this@PayTerminalActivity.runOnUiThread(
                    Runnable {
                        //val sdf: SimpleDateFormat = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault());
                        val sdf: SimpleDateFormat =
                            SimpleDateFormat("HH:mm:ss", Locale.getDefault())
                        val currentDateandTime = sdf.format(Date())
                        tvShowTime.text = "Timer: $currentDateandTime"
                    })
            }
        }, 6000, 3000)

        btJavaActivity.setOnClickListener {
            var i = Intent(this, PayTerminalJavaActivity::class.java)
            startActivity(i)
        }

        // CHK: Move this comment to top place in class
        // Comment: System : We do not have a particular order of actions, ordered broadcast is for
        // listeners not for actions
        // Chk@: We could do with a timer and a sendBroadcast, also put action in list so we can
        // reference an array.

//        btStartPayTestReceiverActivity.setOnClickListener {
//
//            val sAction = UtilityActions.Util.setupActionsForProviderPartyOne()
//
//            // Register ACTIONS for special PayProvider BroadcastReceiver
//            // Maybe move of of this place which should be fore packing data into
//            // the intent. Combining Action and Extras
//            val intentFilterActions = IntentFilter().apply {
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
//                intent.action = sAction[0]
//                intent.putExtra("KEY_NAME", "Kylie Minogue")
//                //intent.setComponent(net.csplab.adroid.kotlin.loomisbroadcastreceivercomponent.ReceiverTestActivity::class.java.simpleName)
//                sendBroadcast(intent) // Chk@ in BroadcastReceiver
//                intent.setClassName(
//                    "net.csplab.adroid.kotlin.loomisbroadcastreceivercomponent",
//                    "net.csplab.adroid.kotlin.loomisbroadcastreceivercomponent.views.ReceiverTestActivity"
//                )
//                //val i2 = Intent(this, ReceiverTestActivity.class)
//
//                startActivity(intent)
//            }
//        }


        //! PartyOneProvider BroadcastReceiver
        btStartPayProvider1.setOnClickListener {
            val providerName = "PartyOneProvider"
            // ! Intent () HERE_?

            //! Get ACTION Set for Provider
            // Register ACTIONS for special PayProvider BroadcastReceiver
            // Chk@: move preparation outside button click event!
            //: Get the ACTION String for provider
            val sActions = UtilityActions.Util.setupActionsForProviderPartyOne()
            //: Prepare Provider by packing ActionExtra Object with collection on actions and extra data
            prepareProvider(providerName, intent, sActions) //! CHK: Timeoutlength L

            mReadyToBroadCast = true
            //! Set Broadcaster type with associated Actions
            mTstBroadcaster = PartyOneProviderReceiver(providerName, UtilityActions.ActionSets.actionsExtraPartyOne)

            registerReceiver(mTstBroadcaster, mIntentFilterActions) //! Chk@ mIntentFilterActions should be local to no induce bugsa

            intent = Intent()  // chk:Move this to start of bt action.

            //! For each intent to send with broadcast -> Load each intent with action and "Extras" data
            for (i in 0..sActions.size - 1) {
                intent.action = sActions[i]
                Log.d(TAG, "Intent => $intent.action :: $sActions[i] :: Action.size: $sActions.size ")

                //!
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
        btStartPayProvider2.setOnClickListener {
            val providerName = "BankOfBankProvider"
            // Load Actions for number BankOfBank 3. party provider

            val actionStrings = UtilityActions.Util.setupActionsForProviderBankOfBank()

            prepareProvider(providerName, intent, actionStrings)

            mReadyToBroadCast = true
            mTstBroadcaster = BankOfBankReceiver(providerName, UtilityActions.ActionSets.actionsExtraBankOfBank)

            val intentFilterActions = IntentFilter() // TODO do nothing
            registerReceiver(mTstBroadcaster, intentFilterActions)

            //!
            intent = Intent()
            //  Run trough all possible ACTIONS and ready them
            for(i in 0..actionStrings.size - 1) {
                Log.d(TAG, "sAction")
                intent.action = actionStrings[i]

                // The Intent is for one broadcast, so we have to add the appropriate Extras for each Action:
                // Then -> Ready next intent with action and extras to broadcast
                if (intent.action == actionStrings[0]){
                    intent.putExtra("KEY1", "ID4325")
                } else if (intent.action == actionStrings[1]){
                    intent.putExtra("KEY2_2", tvShowTime.text.toString()) // Create message editfield prefill it for testsS
                    intent.putExtra("KEY2_2", "Balance")
                    intent.putExtra("KEY2_3", "Idnum")
                    intent.putExtra("KEY2_3", "SWIFT")
                    intent.putExtra("KEY2_3", "IBAN")
                } else if (intent.action == actionStrings[2]){
                    intent.putExtra("KEY3", "5000")
                }else if (intent.action == actionStrings[3]){
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


        //! Test Send with Intent to Receiver Activity
        btStartPayTestReceiverActivity.setOnClickListener {
            //! Load the Actions for Party One Provider 3.party.
            val sAction = UtilityActions.Util.setupActionsForProviderPartyOne()
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


    }// onCreate

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

    //! Preparing provider data and preparing Provider for Creation is two different things but still add new set while the broadcaster is running?
    //! preparing ActionExxtra data for provider before provider is run(or instantiated)
    //! Chk: Move this method to UtilityActions
    fun prepareProvider(providerName: String, intent: Intent, actionSet: List<String>) {
        // Register ACTIONS for special PayProvider BroadcastReceiver
        mIntentFilterActions = packActionsWithData(actionSet)
        // return provider?
    }


    //! Descr:
    private fun packActionsWithData(actionSet: List<String>) : IntentFilter{

        //! This happens elsewhere se actions into ActionExtra in entry function(onCreate)
        var intentFilterActions = IntentFilter().apply {
            //addAction("net.csplab.adroid.kotlin.loomisbroadcastreceivercomponent.PAYID1_START")
            //! Preparation for protocol state walk, with start and end
            addAction(actionSet.first()) // start
            for (s in actionSet.subList(1, actionSet.size - 1)) {
                Log.d(TAG, "Added Action for receiver: $s : Sizelist ${actionSet.size}")
                addAction(s)
            }
            //Arbitrary number of action steps
            addAction(actionSet.last()) // For End of ACTION event
        }
        return intentFilterActions
    }

    //! Descr: addAction for specific receiver
    private fun addActionsForReceiver(actions: List<String>): IntentFilter {
        return IntentFilter("") // placeholder
    }

    override fun updateTimer(info: String) {
        Log.d(TAG, "UpdateTimer: Callback")
        tvShowTime.text = info
    }

    //! =========================================================
    //! check-code

}
