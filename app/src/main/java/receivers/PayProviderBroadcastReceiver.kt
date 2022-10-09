package receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

abstract class PayProviderBroadcastReceiver : BroadcastReceiver() {
    //TAG: String = "PayProviderBroadcastReceiver"
    private val TAG = PayProviderBroadcastReceiver::class.java.simpleName
    //override fun onReceive(ctx: Context?, intent: Intent?) {
    override fun onReceive(ctx: Context, intent: Intent) {
        val rec = Intent.ACTION_ANSWER

        val action: String? = intent?.getAction()

        //inf.addAction(Intent.ACTION_BATTERY_CHANGED)
        //inf.addAction(Intent.ACTION_POWER_DISCONNECTED)

        if(Intent.ACTION_POWER_CONNECTED == action) {
            Toast.makeText(ctx, "Power is connected", Toast.LENGTH_LONG).show()
            //ctx.startService(
              //  Intent(PhoneService.ACTION_POWER_CONNECTED))
        } else if (Intent.ACTION_POWER_DISCONNECTED == action) {
            Toast.makeText(ctx, "Power is DISconnected", Toast.LENGTH_LONG).show()
            //ctx.startService(
            //  Intent(PhoneService.ACTION_POWER_CONNECTED) )
        }
    }
}