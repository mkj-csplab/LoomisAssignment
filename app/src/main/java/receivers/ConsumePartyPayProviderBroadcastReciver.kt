package receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

class ConsumePartyPayProviderBroadcastReciver : BroadcastReceiver() {
    override fun onReceive(ctx: Context?, intent: Intent?) {
        val intt = intent?.action
        if (intt == Intent.ACTION_POWER_DISCONNECTED){
            //doSomething()
            // Also update UI
            // How to update ui interface
            Log.d("Consumer", "ACTION_POWER_DISCONNECTED")
            Toast.makeText(ctx ,"Power Disconnected", Toast.LENGTH_LONG).show()

        } else if (intt == Intent.ACTION_POWER_CONNECTED){
            Log.d("Consumer", "ACTION_POWER_CONNECTED")
            Toast.makeText(ctx ,"Power CONNECTED:connected", Toast.LENGTH_LONG).show()
        }

    }
}