package receivers

import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import net.csplab.adroid.kotlin.loomisbroadcastreceivercomponent.MainActivity

class ConsumePartyPayProviderBroadcastReciver(
    override var actionIDS: List<String>,
    override val actionsNum: Int
    //override var actionsCompleted: List<Boolean>
) : PayProviderBroadcastReceiver() {
    private var TAG = ConsumePartyPayProviderBroadcastReciver::class.java.simpleName

    override fun onReceive(ctx: Context, intent: Intent) {
        super.onReceive(ctx, intent)
        Toast.makeText(ctx, "CONSUMERPARTY", Toast.LENGTH_LONG).show()

        val actionReceived = intent.action
        var intentExtras = intent.extras

        Log.d(TAG, "onReceive:iAction: $actionReceived   ; ${actionIDS}")
        //Log.d(TAG, "iAction: ${intentExtras?.keySet()}")

        if (actionReceived == actionIDS[0]){
            var key1val = intent.getStringExtra("KEY1")
            Log.d(TAG, "key1val: $key1val")
        } else if (actionReceived == actionIDS[1]){
            var extraKeySet = intentExtras?.keySet()
            Log.d(TAG, "ConsumerBR:Onrecieve: keyset $extraKeySet")
            for (k in extraKeySet!!) {
                Log.d(TAG, "Keys: $k")
            }
        } else if (actionReceived == actionIDS[2]) {

        }
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

//        for(i in 0..sAction.size - 1) {
//            intent.action = sAction[i]
//            Log.d(TAG, "Intent => $intent.action :: $sAction[i]")
//            val ia = intent.action
//            Log.d(TAG, "$ia")
//            if (intent.action == sAction[0]){
//                intent.putExtra("KEY1", "ID4325")
//            } else if (intent.action == sAction[1]){
//
//                intent.putExtra("KEY2_2", "Amount")
//                intent.putExtra("KEY2_2", "Balance")
//                intent.putExtra("KEY2_3", "Idnum")
//                intent.putExtra("KEY2_3", "SWIFT")
//                intent.putExtra("KEY2_3", "IBAN")
//            }
//            else if (intent.action == sAction[2]){
//                intent.putExtra("KEY4", "BYE!")
//            }
//        }// FOR

//        //        if (iAction == "net.csplab.adroid.kotlin.loomisbroadcastreceivercomponent.ACTION_PAYID1_START") {// Is action correct, look in intent.ACTIONS
// =====
//
//        for(i in 0..sAction.size - 1) {
//            intent.action = sAction[i]
//
//            if (intent.action == sAction[0]){
//                intent.putExtra("KEY1", "ID4325")
//            } else if (intent.action == sAction[1]){
//
//                intent.putExtra("KEY2_2", "Amount")
//                intent.putExtra("KEY2_2", "Balance")
//                intent.putExtra("KEY2_3", "Idnum")
//                intent.putExtra("KEY2_3", "SWIFT")
//                intent.putExtra("KEY2_3", "IBAN")
//            }
//            else if (intent.action == sAction[2]){
//                intent.putExtra("KEY4", "BYE!")
//            }
//        }// FOR

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