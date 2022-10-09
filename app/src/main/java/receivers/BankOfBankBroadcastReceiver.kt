package receivers

import android.content.Context
import android.content.Intent
import utility.UtilityActions

class BankOfBankBroadcastReceiver : PayProviderBroadcastReceiver() {
    override fun onReceive(ctx: Context, intent: Intent) {
        super.onReceive(ctx, intent)
        //val serviceIntent = Intent("GoToService")...
        //ctx.startService()
        val actionReceived = intent.action

        if (actionReceived == UtilityActions.ACTION_PAYID1_START){

        } else if (actionReceived == UtilityActions.ACTION_PAYID2_END){

        }
    }
}