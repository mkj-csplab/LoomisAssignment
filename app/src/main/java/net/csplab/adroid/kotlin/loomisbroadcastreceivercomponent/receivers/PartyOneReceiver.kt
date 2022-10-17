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

    override fun onReceive(ctx: Context, intent: Intent) {
        super.onReceive(ctx, intent)
        createTimeoutTimer(mTimeoutLength, ctx)
        Log.d(TAG, "PartyOneProvider:onReceive")
        //Toast.makeText(ctx, "PartyOneProvider:onReceive", Toast.LENGTH_LONG).show()

        val actionReceived = intent.action
        var intentExtras = intent.extras
        var mNumberActionsRegistered = mActionExtras.size

        Log.d(TAG, "PartyOneProvider:onReceive:iAction: $actionReceived  NumActions $mNumberActionsRegistered ; ${mActionExtras}")
        //Log.d(TAG, "iAction: ${intentExtras?.keySet()}")

        var extraKeySet = intentExtras?.keySet()
        Log.d(TAG, "PartyOneProvider:onRecieve: keyset ${extraKeySet}")

        val values = mutableListOf<String>()

        for (k in extraKeySet!!) {
            Log.d(TAG, "PartyOneProvider:onRecieve:Keys: $k")
            values.add(intentExtras?.get(k).toString())
        }
        var valuesSize = values.size

        Log.d(TAG, "PartyOneProvider:Values: $valuesSize")

        //! Set Function for dealing with business/protocol logic that must be specialised
        //protocolReceivingData(ae)
        val numActionRegistered = mActionExtras.size //

        Log.d(TAG, "PartyOneProvider:onReceive:Actions: $mActionExtras")

        // CHK: Foreach actionextra,
        // Compaer the recorded Actions sets to the incoming data
        if (actionReceived == mActionExtras[0].action){
            //! CHK ALL THESE intents data keys etc move ouside the IF what tp do
            //! 1. DO SPECIFIC STUFF FOR ACTION
            //! 2. THEN GET ALL INTENTS Extras per key
            //! 3. Chk that we got all actions and intents, ie count ACTIONS Received
            //var extras = mActionExtras[0].extras  // chk: What do we need this for? Nothing
            val ie = intentExtras // Extract data per key
            //! @Dont need this, we get the data fomr the intent ; var keyFromPair = extras.get(0).first
            Log.d(TAG, "PartyOneProvider:onReceive:intentExtras: ${intentExtras}")
            // !!! var key1val = intent.getStringExtra(keyFromPair)  // KEY1
           //! Log.d(TAG, "PartyOneProvider:onReceive:Action0: ${mActionExtras[0].action} : KeyVal: $key1val")
        } else if (actionReceived == mActionExtras[1].action){
            var key1val = intent.getStringExtra("KEY2")
            var keys = intent.extras?.keySet()

            Log.d(TAG, "PartyOneProvider:onReceive:KeyVal: $key1val")
        } else if (actionReceived == mActionExtras[2].action) {
            var key1val = intent.getStringExtra("KEY3")
            Log.d(TAG, "PartyOneProvider:onReceive:KeyVal: $key1val")
        } else if (actionReceived == mActionExtras[3].action) {
            var key1val = intent.getStringExtra("KEY4")
            Log.d(TAG, "PartyOneProvider:onReceive:KeyVal: $key1val")

        }
        //! IF ALL ACTIONS and intents extras receieved and before timeout : NumActions = ActionsExtras.size
        //! Cancel time out, reset ACTION COUNT
        //! mActionTimeout.cancel() ; actionCount = 0
    } // END onReceive

    //! Descr:
    private fun protocolReceivingData(ae: List<ActionExtra>) {

    }

    override fun protocolSetup(ctx: Context, intent: Intent) {
        //! Write logic for specialized code for a provider here
    }

    override fun setActionsForReceiver(actionList: List<String>) {
        //! Add ACTIONS for that receivce
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

