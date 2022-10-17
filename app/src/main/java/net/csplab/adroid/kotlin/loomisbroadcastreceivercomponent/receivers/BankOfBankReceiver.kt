package net.csplab.adroid.kotlin.loomisbroadcastreceivercomponent.receivers

import android.app.AlarmManager
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import models.ActionExtra
import net.csplab.adroid.kotlin.loomisbroadcastreceivercomponent.utility.UtilityActions

//import kotlin.collections.EmptyMap.keys

class BankOfBankReceiver(
    override var providerName: String?,
    override var mActionExtras: List<ActionExtra>,
    override val mTimeoutLength: Long = 20000L,
) : PayProviderReceiver() {
    private val TAG = BankOfBankReceiver::class.java.simpleName

    private lateinit var mTimeoutContainer: TimeoutContainer

    private val mCounter = 0

    override fun onReceive(ctx: Context, intent: Intent) {
        super.onReceive(ctx, intent)  //! Creates Timer, what about timeoutlength
        Log.d(TAG, "BankOfBank:onReceive")
        createTimeoutTimer(mTimeoutLength, ctx)

        val actionReceived = intent.action
        val isItATimeout = intent.extras?.get("TIMEOUT_ALARM")

        val extras = intent.extras
        val extraKeys = extras?.keySet()
        val keySetSize = extraKeys?.size

        // TODO: Chk@: Move to Function for timeout init and start etc.

        if (isItATimeout != null && isItATimeout.equals("TIMEOUT")){
            Log.d(TAG, "TIMEOUT")
            alarmMgr?.cancel(alarmIntent)
        }else {
            Log.d(TAG, "NOT TIMEOUT") // Good
        }

        Log.d(TAG, "Bank:actionReceived: $actionReceived")
        Toast.makeText(ctx, "BANKOFBANK", Toast.LENGTH_LONG).show()

        alarmMgr?.setExact(AlarmManager.ELAPSED_REALTIME_WAKEUP, 5000, alarmIntent)

        // Chk@: Divide action sets so we can go UA.ActionProvider$Name.ACTION_ANACTION
        Log.d(TAG, ":KeysetSize: $keySetSize :: Extrakeys: $extraKeys")
        Log.d(TAG, "OnReceive:ActionReceived: $actionReceived")

        if (actionReceived == UtilityActions.Util.PayProvider2.ACTION_PAYID2_START.toString()) {

            Log.d(TAG, "OnReceive:START")
            Log.d(TAG, "OnReceive:Keys are printed $keySetSize, $actionReceived")

        } else if (actionReceived == UtilityActions.Util.PayProvider2.ACTION_PAYID2_STEP1.toString()) {
            Log.d(TAG, "OnReceive:STEP1")
        } else if (actionReceived == UtilityActions.Util.PayProvider2.ACTION_PAYID2_STEP2.toString()) {
            Log.d(TAG, "OnReceive:STEP2")
        }else if (actionReceived == UtilityActions.Util.PayProvider2.ACTION_PAYID2_END.toString()){
            Log.d(TAG, "OnReceive:END")
        }
    }//# onReceive

    override fun protocolSetup(ctx: Context, intent: Intent) {
        //TODO("Not yet implemented")
    }

    override fun setActionsForReceiver(actionList: List<String>) {
        //TODO("Not yet implemented")
    }

    fun updateTextInUIRegister(callBack: TimeoutContainer.TimeoutListener){
        //mTimeoutListener = callBack
        //mTimeoutListener.updateTimer("TIMEISHIGH!")
    }
}
