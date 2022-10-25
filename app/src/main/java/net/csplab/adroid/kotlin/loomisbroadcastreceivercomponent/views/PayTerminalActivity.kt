package net.csplab.adroid.kotlin.loomisbroadcastreceivercomponent.views

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import net.csplab.adroid.kotlin.loomisbroadcastreceivercomponent.databinding.ActivityPayTerminalBinding
import net.csplab.adroid.kotlin.loomisbroadcastreceivercomponent.models.ActionExtra
import net.csplab.adroid.kotlin.loomisbroadcastreceivercomponent.receivers.*
import net.csplab.adroid.kotlin.loomisbroadcastreceivercomponent.utility.ID_PROVIDER_PARTYONE
import net.csplab.adroid.kotlin.loomisbroadcastreceivercomponent.utility.UtilityActions
import net.csplab.adroid.kotlin.loomisbroadcastreceivercomponent.utility.UtilityActions.ActionSets.actionsExtraPartyOne
import net.csplab.adroid.kotlin.loomisbroadcastreceivercomponent.utility.UtilityActions.Util.prepareAddCustomAction
import net.csplab.adroid.kotlin.loomisbroadcastreceivercomponent.utility.UtilityActions.Util.prepareAddIntentExtraToAction
import java.text.SimpleDateFormat
import java.util.*

// This is the test class View:
// We start operation from here.
// Tasks description: make a component
// Extensible BroadcastReceiver

class PayTerminalActivity : AppCompatActivity(), TimeoutContainer.TimeoutListener {
    private var TAG = PayTerminalActivity::class.java.simpleName

    private var mReadyToBroadCast: Boolean = false
    private lateinit var bind: ActivityPayTerminalBinding

    lateinit var mPartyOneReceiver: PartyOneReceiver
    lateinit var mBankOfBankReceiver: BankOfBankReceiver

    // private PayProviderReceiver mTstBroadcaster = null;
    private lateinit var mIntentFilterActionsPartyOne: IntentFilter
    private lateinit var mIntentFilterActionsBankOfBank: IntentFilter
    private lateinit var mActionSet: List<String>

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
        val btStartPartyOneProvider = bind.btStartPartyOneProvider
        val btStartBankOfBankProvider = bind.btStartBankOfBankProvider
        val btStartPayTestReceiverActivity = bind.btStartPayTestReceiverActivity

        //! Goto Java Activity
        btJavaActivity.setOnClickListener {
            var i = Intent(this, PayTerminalJavaActivity::class.java)
            startActivity(i)
        }

        setRepeatingTimer(4000L, 1000L)

        initPartyOneReceiver()
        initBankOfBankReceiver()

        // Chk@: We could do with a timer and a sendBroadcast, also put action in list so we can
        //! reference an array.

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
//
//                startActivity(intent)
//            }
//        }

        //! PartyOneProvider BroadcastReceiver
        btStartPartyOneProvider.setOnClickListener {
            val providerName = "PartyOneProvider"
            intent = Intent()  //! Ready intent for Action & Extras Packing

            //! DO: recreate list from list to mutableList + add action + create new list initialized with mutableList
            Log.d(TAG, "actionsExtraPartyOne:before:actionName ${actionsExtraPartyOne[actionsExtraPartyOne.size-2].action}")
            var actionsExtraPartyOne = UtilityActions.ActionSets.actionsExtraPartyOne
            Log.d(TAG, "actionsExtraPartyOne:before:size ${actionsExtraPartyOne.size}")
            prepareAddCustomAction(actionsExtraPartyOne, "TRANSACTION_VALIDATE", ID_PROVIDER_PARTYONE)
            Log.d(TAG, "actionsExtraPartyOne:after:size ${actionsExtraPartyOne.size}")
            Log.d(TAG, "actionsExtraPartyOne:before:actionName ${actionsExtraPartyOne[actionsExtraPartyOne.size-2].action}")
            Log.d(TAG, "actionsExtraPartyOne:before:extrasize ${actionsExtraPartyOne[actionsExtraPartyOne.size-2].extras?.size}")
            prepareAddIntentExtraToAction(actionsExtraPartyOne[actionsExtraPartyOne.size-2], 0, "DATA_X", "XVAL")
            prepareAddIntentExtraToAction(actionsExtraPartyOne[actionsExtraPartyOne.size-2], 0, "DATA_Y", "YVAL")
            Log.d(TAG, "actionsExtraPartyOne:before:extrasize ${actionsExtraPartyOne[actionsExtraPartyOne.size-2].extras?.size}")

            mReadyToBroadCast = true
            mPartyOneReceiver = PartyOneReceiver(
                providerName,
                actionsExtraPartyOne,
                //UtilityActions.ActionSets.actionsExtraPartyOne,
                15000L
            )
            registerReceiver(
                mPartyOneReceiver,
                mIntentFilterActionsPartyOne
            ) //! Chk@ mIntentFilterActions should be local to not induce bugs

            //! CHK: NEXT-> sendProtocolDriver(actionsExtras, intent)
            //! For each intent to send with broadcast -> Load each intent with action and "Extras" data
            //chk@! val actionFromAction

            //protocolLogicSendPartyOne(actionsExtras, intent)//
            var actionListX = mutableListOf<String>()
            for (elem in actionsExtraPartyOne){
                actionListX.add(elem.action)
            }
            protocolLogicSendPartyOneAE(actionsExtraPartyOne, intent)
            //protocolLogicSendPartyOne(actionListX, intent)
        }

//# ================================================================================================
        // BankOfBankBroadcastReceiver
        btStartBankOfBankProvider.setOnClickListener {
            val providerName = "BankOfBankProvider"
            intent = Intent()

            mReadyToBroadCast = true

            val actionsExtraBankOfBank = UtilityActions.ActionSets.actionsExtraBankOfBank
            //actionsExtraBankOfBank
            //UtilityActions
            mBankOfBankReceiver = BankOfBankReceiver(
                providerName,
                UtilityActions.ActionSets.actionsExtraBankOfBank, 15000L
            )

            registerReceiver(mBankOfBankReceiver, mIntentFilterActionsBankOfBank)

            protocolLogicSendBankOfBank(actionsExtraBankOfBank, intent)
        }

        //! Test Send with Intent to Receiver Activity
        btStartPayTestReceiverActivity.setOnClickListener {
            //! Load the Actions for Party One Provider 3.party.
            val sAction = UtilityActions.Util.setupActionsForProviderPartyOne()

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

                if(actionStepNum == 0) {
                    intent.putExtra("KEY_NAME", "Kylie Minogue")
                }
                else if (actionStepNum == 1)
                    intent.putExtra("KEY_NAME_ID", "Id")
                    intent.putExtra("KEY_NAME_IDNUM", "460967")
                // Function to add variable amount of extra
                // Set Component to explicitly communicate within App / Package
            }

            intent.setClassName(
                "net.csplab.adroid.kotlin.loomisbroadcastreceivercomponent",
                "net.csplab.adroid.kotlin.loomisbroadcastreceivercomponent.views.ReceiverTestActivity"
            )
            startActivity(intent)
        }

    }// #onCreate


    private fun protocolLogicSendPartyOneAE(actionsExtras: List<ActionExtra>, intent: Intent) {
        //! Get AE and walk trrought actions and for each eaction set intent extras
        //! Ie. Set intent.action from AE[0].action AE[0].extra:List
        for (i in 0..actionsExtras.size - 1) {
            //! Load intent with action type (String)
            intent.action = actionsExtras[i].action
            Log.d(TAG, "Intent => $intent.action :: $actionsExtras[i] :: Action.size: $actionsExtras.size ")

            //! get current action
            Log.d(TAG, "${intent.action}")
            if (intent.action == actionsExtras[0].action) {
                intent.putExtra("KEY1", "ID4325")
            } else if (intent.action == actionsExtras[1].action) {
                intent.putExtra("KEY2_1", "Amount")
                intent.putExtra("KEY2_2", "Balance")
                intent.putExtra("KEY2_3", "Idnum")
                intent.putExtra("KEY2_4", "IBAN")
            } else if (intent.action == actionsExtras[2].action) {
                intent.putExtra("KEY3", "BYE!")
            }
            //! @Chk> Could wrap this in Timer. Schedule to test time limits
            //sendBroadcast(intent) // send intent to PartyOneProvider, Timing
            sendOrderedBroadcast(intent, null)
        }
    }

    private fun protocolLogicSendBankOfBank(actionsExtras: List<ActionExtra>, intent: Intent) {
        //!  Run trough all possible ACTIONS and make ready for broadcast
        for (i in 0..actionsExtras.size - 1) {
            Log.d(TAG, "sAction")
            intent.action = actionsExtras[i].action

            // The Intent is for one broadcast, so we have to add the appropriate Extras for each Action:
            // Then -> Ready next intent with action and extras to broadcast
            if (intent.action == actionsExtras[0].action) {
                intent.putExtra("KEY1", "ID4325")
            } else if (intent.action == actionsExtras[1].action) {
                intent.putExtra(
                    "KEY2_2",
                    tvShowTime.text.toString()
                ) // Create message editfield prefill it for testsS
                intent.putExtra("KEY2_2", "Balance")
                intent.putExtra("KEY2_3", "Idnum")
                intent.putExtra("KEY2_3", "IBAN")
            } else if (intent.action == actionsExtras[2].action) {
                intent.putExtra("KEY3", "5000")
            } else if (intent.action == actionsExtras[3].action) {
                intent.putExtra("KEY4", "BYE!")
            }
            //! Broadcast is Async
            sendOrderedBroadcast(intent, null)
        }
        //intent.action = "net.csplab.adroid.kotlin.loomisbroadcastreceivercomponent.PAYID1_START"
    }

    override fun onResume() {
        super.onResume()

        if (mReadyToBroadCast) { //! CHK:Maybe need a ReadyToBroadcast per receiver
            //! TODO Fix registerReceiver and unregister in onResume and onPause to match
            //! Code update in button click test receiver
            //  if (mPartyOneReceiver != null) { //! chk: IntentFilters test for null @?
            registerReceiver(mPartyOneReceiver, mIntentFilterActionsPartyOne)
            //}
            //if (mBankOfBankReceiver != null){
            registerReceiver(mBankOfBankReceiver, mIntentFilterActionsBankOfBank)
            //}
        }
    }

    override fun onPause() {
        super.onPause()
        //! If App set on pause during broadcast - App going to background
        if (mReadyToBroadCast) {
        unregisterReceiver(mPartyOneReceiver)
        unregisterReceiver(mBankOfBankReceiver)
        mReadyToBroadCast = false
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
    fun prepareProvider(
        //providerName: String,
        //intent: Intent,
        actionSet: List<String>
    ): IntentFilter {
        // Register ACTIONS for special PayProvider BroadcastReceiver
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
        //mIntentFilterActions = packActionsWithData(actionSet)
        // return provider?
    }

    //! Descr: USe This ?
    private fun packActionsWithData(actionSet: List<String>): IntentFilter {
        return IntentFilter()
    }

    //! Descr: addAction for specific receiver
    private fun addActionsForReceiver(actions: List<String>): IntentFilter {
        return IntentFilter("") // placeholder
    }

    override fun updateTimer(info: String) {
        Log.d(TAG, "UpdateTimer: Callback")
        tvShowTime.text = info
    }

    fun setRepeatingTimer(delay: Long = 6000L, duration: Long = 3000L) {
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
                        val currentTime = sdf.format(Date())
                        tvShowTime.text = "Timer: $currentTime"
                    })
            }
        }, delay, duration)
    }

    private fun initPartyOneReceiver(){
        //! Get ACTION Set for Provider
        // Register ACTIONS for special PayProvider BroadcastReceiver
        // Chk@: move preparation outside button click event!
        //: Get the ACTION String for provider
        val actionsExtras = UtilityActions.setupActionsForProviderPartyOne()
        //!! NOTE: In Sender End (Activity) KEEP actionsExtras List of strings, do not use
        //!! The List<ActionExtra> object from utility UtilityActions class,
        //!! !DO use it in Receivers
        //: Prepare Provider by packing ActionExtra Object with collection on actions and extra data

        mIntentFilterActionsPartyOne =
            prepareProvider(actionsExtras)
    }

    private fun initBankOfBankReceiver() {
        // Load Actions for number BankOfBank 3. party provider
        val actionStrings = UtilityActions.Util.setupActionsForProviderBankOfBank()

        mIntentFilterActionsBankOfBank = prepareProvider(actionStrings)
    }

}
