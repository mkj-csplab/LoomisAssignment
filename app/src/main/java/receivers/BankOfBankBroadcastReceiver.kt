package receivers

import android.content.Context
import android.content.Intent
import android.util.Log
import net.csplab.adroid.kotlin.loomisbroadcastreceivercomponent.MainActivity
import utility.UtilityActions
//import kotlin.collections.EmptyMap.keys

class BankOfBankBroadcastReceiver : PayProviderBroadcastReceiver() {
    private var TAG = BankOfBankBroadcastReceiver::class.java.simpleName

    override fun onReceive(ctx: Context, intent: Intent) {
        super.onReceive(ctx, intent)
        //val serviceIntent = Intent("GoToService")...
        //ctx.startService()
        val actionReceived = intent.action
        val extras = intent.extras

        // Chk@: Divide action sets so we can go UA.ActionProvider$Name.ACTION_ANACTION
        if (actionReceived == UtilityActions.ACTION_PAYID2_START){
            val extraKeys = extras?.keySet()
            val keySetSize =  extraKeys?.size

            Log.d(TAG, "Keys are printed $keySetSize")

        } else if (actionReceived == UtilityActions.ACTION_PAYID2_END){

        }
    }
}