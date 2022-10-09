package receivers

import android.content.Context
import android.content.Intent
import android.os.CountDownTimer
import android.util.Log
import android.widget.Toast
import net.csplab.adroid.kotlin.loomisbroadcastreceivercomponent.MainActivity
import utility.UtilityActions
import java.util.Timer
import java.util.TimerTask

//import kotlin.collections.EmptyMap.keys

class BankOfBankBroadcastReceiver(
    override var actionIDS: List<String>,
    override val actionsNum: Int

) : PayProviderBroadcastReceiver() {
    private var TAG = BankOfBankBroadcastReceiver::class.java.simpleName
    private lateinit var actionBrTimer: CountDownTimer

    override fun onReceive(ctx: Context, intent: Intent) {
        super.onReceive(ctx, intent)
        //val serviceIntent = Intent("GoToService")...
        //ctx.startService()
        val actionReceived = intent.action
        Log.d(TAG, "actionReceived: $actionReceived")
        Toast.makeText(ctx, "BANKOFBANK", Toast.LENGTH_LONG).show()

        val extras = intent.extras

        actionBrTimer = object : CountDownTimer(10000, 1000) {
            override fun onTick(t0: Long) {

            }
            override fun onFinish() {
                //abortBroadcast()  // Question
            }
        }
        actionBrTimer.start()

        // Chk@: Divide action sets so we can go UA.ActionProvider$Name.ACTION_ANACTION
        //Timer("Setting up", false).schedule(200){}//TIMER


        if (actionReceived == UtilityActions.ACTION_PAYID2_START) {
            val extraKeys = extras?.keySet()
            val keySetSize = extraKeys?.size

            Log.d(TAG, "Keys are printed $keySetSize, $actionReceived")

        } else if (actionReceived == UtilityActions.ACTION_PAYID2_STEP1) {

        } else if (actionReceived == UtilityActions.ACTION_PAYID2_STEP2) {


        }
    }
}