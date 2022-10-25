package net.csplab.adroid.kotlin.loomisbroadcastreceivercomponent.receivers

import android.content.Context
import android.content.Intent
import android.util.Log
import net.csplab.adroid.kotlin.loomisbroadcastreceivercomponent.models.ActionExtra

class PartyOneReceiver(
    override val providerName: String?,
    override var mActionsExtras: List<ActionExtra>,
    override val mTimeoutLength: Long = 15000L,
    //override //mActionTimeout.cancel()var actionsCompleted: List<Boolean>
) : PayProviderReceiver() {

//}

private var TAG = PartyOneReceiver::class.java.simpleName
    private var mActionCounter = mActionsExtras.size // Counting action to be completed/received
    private var mActionCount = 0 // Counting action to be completed/received

    //! Receive is called once per action (action that is broadcasted)
    override fun onReceive(ctx: Context, intent: Intent) {
        super.onReceive(ctx, intent)
        Log.d(TAG, "PartyOneProvider:onReceive")

        //Toast.makeText(ctx, "PartyOneProvider:onReceive", Toast.LENGTH_LONG).show()

        //! Descr: The timeout is initialized at the start of BroadCast:onReceive
        //! because payment start is initialized from the user (activity), that could depend on
        //! payment device, etc.
        startTimeout(mTimeoutLength)

        val actionReceived = intent.action
        var intentExtras = intent.extras
        var mNumberActionsRegistered = mActionsExtras.size
        //! For the action that entered us into onReceive, get ALL Keys for the intent extras data
        var extraKeySet = intentExtras?.keySet()
        //! Using the keys from intent extra, extract all
        //val values = mutableListOf<String>()  // values.add(intentExtras?.get(k).toString())
        val valuesMap = mutableMapOf<String, String>()

        //! Extract values from intents extras, into a map of values
        for (k in extraKeySet!!) {
            Log.d(TAG, "PartyOneProvider:onRecieve:Keys: $k")
            valuesMap.put(k, intentExtras?.get(k).toString())
        }

        Log.d(TAG, "PartyOneProvider:onRecieve: keyset ${extraKeySet}")
        Log.d(TAG, "PartyOneProvider:onReceive:iAction: $actionReceived  NumActions $mNumberActionsRegistered ; ${mActionsExtras}")
        Log.d(TAG, "PartyOneProvider:onReceive:Val: ${valuesMap}")
        Log.d(TAG, "PartyOneProvider:onReceive:Actions: $mActionsExtras")
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
        //! CHK: This these entry lines move them
        mActionCount++

        //! Foreach action we receive do something with the data extracted extra,
        // !Compare the recorded Actions sets to the incoming data
        if (actionReceived == mActionsExtras[0].action){
            //! CHK ALL THESE intents data keys etc move ouside the IF what tp do
            //! 1. DO SPECIFIC STUFF FOR ACTION
            //! 2. THEN GET ALL INTENTS Extras per key
            //! 3. Chk that we got all actions and intents, ie count ACTIONS Received
            doSomethingAction1(valuesMap)
            // !!! var key1val = intent.getStringExtra(keyFromPair)  // KEY1
            // ! Log.d(TAG, "PartyOneProvider:onReceive:Action0: ${mActionExtras[0].action} : KeyVal: $key1val")
        } else if (actionReceived == mActionsExtras[1].action){
            doSomethingAction2(valuesMap)
        } else if (actionReceived == mActionsExtras[2].action) {
            doSomethingAction3(valuesMap)
        } else if (actionReceived == mActionsExtras[3].action) {
            //var key1val = intent.getStringExtra("KEY4")
             if (mActionCount == mActionsExtras.size && valuesMap.get("KEY4_PAY_END") == "BYE!" ){
                Log.d(TAG, "PartyOneProvider:onReceive:BYE")
                 mActionTimeout.cancel()
                 mActionCount = 0 // Not necc
             }
        }
        //! IF ALL ACTIONS and intents extras received and before timeout : NumActions = ActionsExtras.size
        //! Cancel time out, reset ACTION COUNT
    }

    //! CHK> Should this be here remove
    override fun setActionsForReceiver(actionList: List<String>) {
        //! Add ACTIONS for that receive
    }
}
