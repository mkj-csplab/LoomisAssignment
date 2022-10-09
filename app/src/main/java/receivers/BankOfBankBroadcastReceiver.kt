package receivers

import android.content.Context
import android.content.Intent
import android.os.CountDownTimer
import android.util.Log
import android.widget.Toast
import utility.UtilityActions

//import kotlin.collections.EmptyMap.keys

class BankOfBankBroadcastReceiver(
    override var actionIDS: List<String>

) : PayProviderBroadcastReceiver() {
    private var TAG = BankOfBankBroadcastReceiver::class.java.simpleName
    private lateinit var mActionBrTimer: CountDownTimer
    private val counter = 0

    override fun onReceive(ctx: Context, intent: Intent) {
        super.onReceive(ctx, intent)
        //val serviceIntent = Intent("GoToService")...
        //ctx.startService()
        val actionReceived = intent.action
        Log.d(TAG, "Bank:actionReceived: $actionReceived")
        Toast.makeText(ctx, "BANKOFBANK", Toast.LENGTH_LONG).show()

        mActionBrTimer = object : CountDownTimer(10000, 1000) {
            override fun onTick(t0: Long) {

            }
            override fun onFinish() {
                //abortBroadcast()  // Question
            }
        }
        mActionBrTimer.start()

        // Chk@: Divide action sets so we can go UA.ActionProvider$Name.ACTION_ANACTION
        //Timer("Setting up", false).schedule(200){}//TIMER

        val extras = intent.extras
        val extraKeys = extras?.keySet()
        val keySetSize = extraKeys?.size

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
}
