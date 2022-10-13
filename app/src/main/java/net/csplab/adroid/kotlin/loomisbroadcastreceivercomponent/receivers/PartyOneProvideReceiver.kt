package net.csplab.adroid.kotlin.loomisbroadcastreceivercomponent.receivers

import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

class PartyOneProvideReceiver(
    override var mActionIDS: List<String>, override var providerName: String?,

    //override var actionsCompleted: List<Boolean>
) : PayProviderReceiver() {
    private var TAG = PartyOneProvideReceiver::class.java.simpleName

    override fun onReceive(ctx: Context, intent: Intent) {
        super.onReceive(ctx, intent)
        Toast.makeText(ctx, "CONSUMERPARTY", Toast.LENGTH_LONG).show()

        val actionReceived = intent.action
        var intentExtras = intent.extras
        var mNumberActionsRegistered = mActionIDS.size

        Log.d(TAG, "onReceive:iAction: $actionReceived  NumActions $mNumberActionsRegistered ; ${mActionIDS}")
        //Log.d(TAG, "iAction: ${intentExtras?.keySet()}")

//        mActionBrTimer = object : CountDownTimer(10000, 1000) {
//            override fun onTick(t0: Long) {
//
//            }
//            override fun onFinish() {
//                //abortBroadcast()  // Question
//            }
//        }
//        mActionBrTimer.start()

        var extraKeySet = intentExtras?.keySet()
        Log.d(TAG, "ConsumerBR:Onrecieve: keyset ${extraKeySet}")

        val values = mutableListOf<String>()

        for (k in extraKeySet!!) {
            Log.d(TAG, "Keys: $k")
            values.add(intentExtras?.get(k).toString())
        }
        var valuesSize = values.size

        Log.d(TAG, "Values: $valuesSize")
        // Set Function for dealing with busines/ protocol logic that must be specialised
        val numActionRegistered = mActionIDS.size //

        if (actionReceived == mActionIDS[0]){
            var key1val = intent.getStringExtra("KEY1")
            // --
        } else if (actionReceived == mActionIDS[1]){

        } else if (actionReceived == mActionIDS[2]) {

        }
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
        //switch (intentAction){
        //    case :
        //    break;
        //    case Intent.ACTION_POWER_DISCONNECTED:
        //    break;
        //}
        //when (iAction) {
        //     ActionString -> {}
        //    Intent.ACTION_POWER_DISCONNECTED -> {}
        //}

        // ===============================================
        // Action Walkthrough
        /*
         addAction(Intent.ACTION_BATTERY_CHANGED)    // For test
            addAction(Intent.ACTION_POWER_DISCONNECTED) // For test
            //addAction(packageName + "ACTION_PAYID1_INIT") // Init: Should be the user pressing Pay on Mainactivity@
            addAction(packageName + "ACTION_PAYID1_START") // start
            addAction(packageName + "ACTION_PAYID1_STEP1")
            addAction(packageName + "ACTION_PAYID1_STEP2")
            addAction(packageName + "ACTION_PAYID1_STEP3")
            //Arbitrary number of action steps ... Ho do we put them in
            addAction(packageName + "ACTION_PAYID1_END") // End of ACTION EVENT
        */
        //if (iAction = is)
        

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