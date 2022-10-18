package net.csplab.adroid.kotlin.loomisbroadcastreceivercomponent.receivers

import android.content.Context
import android.content.Intent
import android.util.Log
import models.ActionExtra

class PartyOneReceiver(
    override var providerName: String?,
    override var mActionExtras: List<ActionExtra>,
    override val mTimeoutLength: Long = 15000L,
    //override //mActionTimeout.cancel()var actionsCompleted: List<Boolean>
) : PayProviderReceiver() {
    private var TAG = PartyOneReceiver::class.java.simpleName
    private val mActionCounter = mActionExtras.size // Counting action to be completed/received

    override fun onReceive(ctx: Context, intent: Intent) {
        super.onReceive(ctx, intent)
        createTimeoutTimer(mTimeoutLength, ctx)
        Log.d(TAG, "PartyOneProvider:onReceive")
        //Toast.makeText(ctx, "PartyOneProvider:onReceive", Toast.LENGTH_LONG).show()

        val actionReceived = intent.action
        var intentExtras = intent.extras
        var mNumberActionsRegistered = mActionExtras.size
        //! For the action that entered us into onReceive, get ALL Keys forthe intent extras data
        var extraKeySet = intentExtras?.keySet()
        //! Using the keys from intent extra, extract all
        //val values = mutableListOf<String>()  // values.add(intentExtras?.get(k).toString())
        val valuesMap = mutableMapOf<String, String>()

        Log.d(TAG, "PartyOneProvider:onRecieve: keyset ${extraKeySet}")
        Log.d(TAG, "PartyOneProvider:onReceive:iAction: $actionReceived  NumActions $mNumberActionsRegistered ; ${mActionExtras}")

        for (k in extraKeySet!!) {
            Log.d(TAG, "PartyOneProvider:onRecieve:Keys: $k")
            valuesMap.put(k, intentExtras?.get(k).toString())
        }
        Log.d(TAG, "PartyOneProvider:onReceive:Val: ${valuesMap}")
        Log.d(TAG, "PartyOneProvider:onReceive:Actions: $mActionExtras")
        Log.d(TAG, "PartyOneProvider:onReceive:intentExtras: ${intentExtras}")

        //! Set Function for dealing with business/protocol logic that must be specialised
        if (actionReceived != null) {
            protocolReceivingData(actionReceived, intent, valuesMap)
        }
//        //! IF ALL ACTIONS and intents extras receieved and before timeout : NumActions = ActionsExtras.size
//        //! Cancel time out, reset ACTION COUNT
//        //! mActionTimeout.cancel() ; actionCount = 0

    } // END onReceive

    private fun doSomethingAction1(values: MutableMap<String, String>) {
        //! TODO( "Do some with the gathered information according to the specific ACTION")
        mActionCounter
    }

    private fun doSomethingAction2(values: MutableMap<String, String>) {
        //! TODO("Do some action")
    }

    private fun doSomethingAction3(values: MutableMap<String, String>) {
        //! TODO("Do some action")
    }

    private fun doSomethingAction4(values: MutableMap<String, String>) {
        //! TODO("Do some action")
    }

    //! Descr: Specific protocol logic for processing under each Actions
    override fun protocolReceivingData(actionReceived: String, intent: Intent, valuesMap: MutableMap<String, String>) {
        var actionCount: Int = 0
        //! Foreach action we receive do something with the data extracted extra,
        // !Compare the recorded Actions sets to the incoming data
        if (actionReceived == mActionExtras[0].action){
            //! CHK ALL THESE intents data keys etc move ouside the IF what tp do
            //! 1. DO SPECIFIC STUFF FOR ACTION
            //! 2. THEN GET ALL INTENTS Extras per key
            //! 3. Chk that we got all actions and intents, ie count ACTIONS Received
            //var extras = mActionExtras[0].extras  // chk: What do we need this for? Nothing

            doSomethingAction1(valuesMap)
            // !!! var key1val = intent.getStringExtra(keyFromPair)  // KEY1
            // ! Log.d(TAG, "PartyOneProvider:onReceive:Action0: ${mActionExtras[0].action} : KeyVal: $key1val")
            actionCount++
        } else if (actionReceived == mActionExtras[1].action){
            val extraKeySet = intent.extras?.keySet()
            //for (key in extraKeySet){
            //    Log.d(TAG, "PartyOneProvider:onReceive:KeyVal: $key")
            //}
            doSomethingAction2(valuesMap)
            actionCount++
        } else if (actionReceived == mActionExtras[2].action) {
            //var key1val = intent.getStringExtra("KEY3")
            doSomethingAction3(valuesMap)
            actionCount++
        } else if (actionReceived == mActionExtras[3].action) {
            //var key1val = intent.getStringExtra("KEY4")
            doSomethingAction4(valuesMap)
            actionCount++
        }
        //! IF ALL ACTIONS and intents extras receieved and before timeout : NumActions = ActionsExtras.size
        //! Cancel time out, reset ACTION COUNT
        if (actionCount == mActionExtras.size)
        {
            mActionTimeout.cancel() ;
            actionCount = 0 // Not necc
        }


    }

//    //! Descr: Describe IN BASE CLASS - See Above - move
//    override fun protocolSetup(ctx: Context, intent: Intent) {
//        //! Write logic for specialized code for a provider here
//    }

    override fun setActionsForReceiver(actionList: List<String>) {
        //! Add ACTIONS for that receive
    }
}

//StringBuilder().apply {
//            append("Action: ${intent.action}\n")
//            append("URI: ${intent.toUri(Intent.URI_INTENT_SCHEME)}\n")
//            toString().also { log ->
//                Log.d(TAG, "onReceive: PayProvider: " + log)
//                Toast.makeText(ctx, log, Toast.LENGTH_LONG).show()
//            }
//        }

