package receivers

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.CountDownTimer
import android.os.SystemClock
import android.util.Log
import android.widget.Toast
import utility.UtilityActions

//import kotlin.collections.EmptyMap.keys

class BankOfBankBroadcastReceiver(
    override var actionIDS: List<String>

) : PayProviderBroadcastReceiver() {
    private var TAG = BankOfBankBroadcastReceiver::class.java.simpleName
    //protected lateinit var mActionBrTimer: CountDownTimer
    private val counter = 0

    override fun onReceive(ctx: Context, intent: Intent) {
        super.onReceive(ctx, intent)

        val actionReceived = intent.action
        val isItATimeout = intent.extras?.get("TIMEOUT_ALARM")

        val extras = intent.extras
        val extraKeys = extras?.keySet()
        val keySetSize = extraKeys?.size


        if (isItATimeout != null && isItATimeout.equals("TIMEOUT")){
            Log.d(TAG, "TIMEOUT")
            alarmMgr?.cancel(alarmIntent)
        }else {
            Log.d(TAG, "NOT TIMEOUT") // Good
        }

        Log.d(TAG, "Bank:actionReceived: $actionReceived")
        Toast.makeText(ctx, "BANKOFBANK", Toast.LENGTH_LONG).show()


        //alarmMgr?.setExact(AlarmManager.ELAPSED_REALTIME_WAKEUP, 5000, alarmIntent)

        // Chk@: Divide action sets so we can go UA.ActionProvider$Name.ACTION_ANACTION
        //Timer("Setting up", false).schedule(200){}//TIMER


        Log.d(TAG, ":KeysetSize: $keySetSize :: Extrakeys: $extraKeys")

        Log.d(TAG, "OnReceive:ActionReceived: $actionReceived")
        if (actionReceived == UtilityActions.ACTION_PAYID2_START) {
            Log.d(TAG, "OnReceive:START")
            Log.d(TAG, "OnReceive:Keys are printed $keySetSize, $actionReceived")

        } else if (actionReceived == UtilityActions.ACTION_PAYID2_STEP1) {
            Log.d(TAG, "OnReceive:STEP1")
        } else if (actionReceived == UtilityActions.ACTION_PAYID2_STEP2) {
            Log.d(TAG, "OnReceive:STEP2")
        }else if (actionReceived == UtilityActions.ACTION_PAYID2_END){
            Log.d(TAG, "OnReceive:END")
        }
    }

    override fun protocolSetup(ctx: Context, intent: Intent) {
        TODO("Not yet implemented")
    }


}
