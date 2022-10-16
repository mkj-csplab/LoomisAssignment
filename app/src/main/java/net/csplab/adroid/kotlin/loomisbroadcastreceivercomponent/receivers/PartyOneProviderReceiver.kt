package net.csplab.adroid.kotlin.loomisbroadcastreceivercomponent.receivers

import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import models.ActionsExtra

class PartyOneProviderReceiver(
    override var providerName: String?,
    override var mActionExtras: List<ActionsExtra>,
    override val mTimeoutLength: Long = 15000L,

    //override var actionsCompleted: List<Boolean>
) : PayProviderReceiver() {
    private var TAG = PartyOneProviderReceiver::class.java.simpleName

    override fun onReceive(ctx: Context, intent: Intent) {
        super.onReceive(ctx, intent)
        Log.d(TAG, "PartyOneProvider:onReceive")

        createTimeoutTimer(mTimeoutLength, ctx)
        Toast.makeText(ctx, "PartyOneProvider:onReceive", Toast.LENGTH_LONG).show()

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

        //! Set Function for dealing with busines/ protocol logic that must be specialised
        val numActionRegistered = mActionExtras.size //

        Log.d(TAG, "PartyOneProvider:onReceive:Actions: $mActionExtras")
        if (actionReceived == mActionExtras[0].action){
            var key1val = intent.getStringExtra("KEY1")
            Log.d(TAG, "PartyOneProvider:onReceive:Action0: ${mActionExtras[0].action} : KeyVal: $key1val")
        } else if (actionReceived == mActionExtras[1].action){
            var key1val = intent.getStringExtra("KEY2")
            Log.d(TAG, "PartyOneProvider:onReceive:KeyVal: $key1val")
        } else if (actionReceived == mActionExtras[2].action) {
            var key1val = intent.getStringExtra("KEY3")
            Log.d(TAG, "PartyOneProvider:onReceive:KeyVal: $key1val")
        } else if (actionReceived == mActionExtras[3].action) {
            var key1val = intent.getStringExtra("KEY4")
            Log.d(TAG, "PartyOneProvider:onReceive:KeyVal: $key1val")
        }


    } // END onReceive

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

//                intent.putExtra("KEY2_2", "Amount")
//                intent.putExtra("KEY2_2", "Balance")
//                intent.putExtra("KEY2_3", "Idnum")
//                intent.putExtra("KEY2_3", "SWIFT")
//                intent.putExtra("KEY2_3", "IBAN")
//            }
//            else if (intent.action == sAction[2]){
//                intent.putExtra("KEY4", "BYE!")
//            }
//        //        if (iAction == "net.csplab.adroid.kotlin.loomisbroadcastreceivercomponent.ACTION_PAYID1_START") {// Is action correct, look in intent.ACTIONS
// =====

//    override fun onReceive(ctx: Context?, intent: Intent?) {
//        val intt = intent?.action
//        if (intt == Intent.ACTION_POWER_DISCONNECTED){
//            //doSomething()
//            // Also update UI
//            // How to update ui interface
//            Log.d("Consumer", "ACTION_POWER_DISCONNECTED")
//            Toast.makeText(ctx ,"Power Disconnected", Toast.LENGTH_LONG).show()
//
//        } else if (intt == Intent.ACTION_POWER_CONNECTED){
//            Log.d("Consumer", "ACTION_POWER_CONNECTED")
//            Toast.makeText(ctx ,"Power CONNECTED:connected", Toast.LENGTH_LONG).show()
//        }
//
//    }
//}