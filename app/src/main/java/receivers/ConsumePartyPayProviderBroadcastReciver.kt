package receivers

import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

class ConsumePartyPayProviderBroadcastReciver : PayProviderBroadcastReceiver() {
    override fun onReceive(ctx: Context, intent: Intent) {
        super.onReceive(ctx, intent)
        val iAction = intent?.action
        if (iAction == Intent.ACTION_POWER_DISCONNECTED){
            //doSomething()
            // Also update UI
            // How to update ui interface
            Log.d("Consumer", "ACTION_POWER_DISCONNECTED")
            Toast.makeText(ctx ,"Power Disconnected", Toast.LENGTH_LONG).show()

        } else if (iAction == Intent.ACTION_POWER_CONNECTED){
            Log.d("Consumer", "ACTION_POWER_CONNECTED")
            Toast.makeText(ctx ,"Power CONNECTED:connected", Toast.LENGTH_LONG).show()
        }
        
        // ===============================================
        // Action Walkthrough
        /*
        * val inf = IntentFilter().apply {
            addAction(Intent.ACTION_BATTERY_CHANGED)    // For test
            addAction(Intent.ACTION_POWER_DISCONNECTED) // For test
            addAction(packageName + "PAYPROVIDER_NAME" + "ACTION_PAY_INIT") // Init
            addAction(packageName + "PAYPROVIDER_NAME" + "ACTION_PAY_START") // start
            addAction(packageName + "PAYPROVIDER_NAME" + "ACTION_PAY_STEP1")
            addAction(packageName + "PAYPROVIDER_NAME" + "ACTION_PAY_STEP2")
            addAction(packageName + "PAYPROVIDER_NAME" + "ACTION_PAY_STEP3")
            //Arbitrary number of action steps ... Ho do we put them in
            addAction(packageName + "PAYPROVIDER_NAME" + "ACTION_PAY_END") // End of ACTION EVENT*/

        //if (iAction = is)
        
    }
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
}