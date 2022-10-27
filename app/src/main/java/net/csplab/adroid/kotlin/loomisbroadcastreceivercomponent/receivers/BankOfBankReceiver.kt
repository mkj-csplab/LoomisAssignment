package net.csplab.adroid.kotlin.loomisbroadcastreceivercomponent.receivers

import android.app.AlarmManager
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import net.csplab.adroid.kotlin.loomisbroadcastreceivercomponent.models.ActionExtra

//import kotlin.collections.EmptyMap.keys

class BankOfBankReceiver(
    override var providerName: String?,
    override var mActionsExtras: List<ActionExtra>,
    override val mTimeoutLength: Long = 20000L,
) : PayProviderReceiver() {
    private val TAG = BankOfBankReceiver::class.java.simpleName
    private lateinit var mTimeoutContainer: TimeoutContainer
    //private val mActionCounter = mActionExtras.size

    override fun onReceive(ctx: Context, intent: Intent) {
        super.onReceive(ctx, intent)  //! Creates Timer, what about timeoutlength
        Log.d(TAG, "BankOfBank:onReceive")
        startTimeout(mTimeoutLength)//, ctx) // chk: Move into first action before starting timer

        val actionReceived = intent?.action
        val isItATimeout = intent.extras?.get("TIMEOUT_ALARM")  //! CHK:CHECK That we registered "TIMEOUT_ALARM"

        val intentExtras = intent.extras
        val extraKeySet = intentExtras?.keySet()
        val keySetSize = extraKeySet?.size
        val valuesMap = mutableMapOf<String, String>()

        Log.d(TAG, "Bank:actionReceived: $actionReceived")
        Toast.makeText(ctx, "BANKOFBANK", Toast.LENGTH_LONG).show()

        for (k in extraKeySet!!) {
            Log.d(TAG, "BankOfBankProvider:onRecieve:Keys: $k")
            valuesMap.put(k, intentExtras?.get(k).toString())
        }
        Log.d(TAG, "BankOfBankProvider:onReceive:Val: ${valuesMap}")
        Log.d(TAG, "BankOfBankProvider:onReceive:Actions: $mActionsExtras")
        Log.d(TAG, "BankOfBankProvider:onReceive:intentExtras: ${intentExtras}")

        // Chk@: Divide action sets so we can go UA.ActionProvider$Name.ACTION_ANACTION
        Log.d(TAG, ":KeysetSize: $keySetSize :: Extrakeys: $extraKeySet")
        Log.d(TAG, "OnReceive:ActionReceived: $actionReceived")

        if (actionReceived != null) {
            protocolReceivingData(actionReceived ,intent, valuesMap)
        }
//        //! TOOO: NEXT -> Fix these to be the correct action strings!!! <== P2
//        //if (actionReceived == UtilityActions.Util.PayProvider2.ACTION_PAYID2_START.toString()) {
//        if (actionReceived == mActionExtras[0].action) {
//            Log.d(TAG, "OnReceive:START")
//            Log.d(TAG, "OnReceive:Keys are printed $keySetSize, $actionReceived")
//            doSomethingAction1(valuesMap)
//        } else if (actionReceived == mActionExtras[1].action) {
//            Log.d(TAG, "OnReceive:STEP1")
//            doSomethingAction2(valuesMap)
//        } else if (actionReceived == mActionExtras[2].action) {
//            Log.d(TAG, "OnReceive:END")
//            doSomethingAction3(valuesMap)
//        }
//        //! IF not received all actions and intent extras at this time, make a count
//        //mActionTimeout.cancel()

    }//# onReceive

    override fun protocolReceivingData(
        actionReceived: String,
        intent: Intent,
        valuesMap: MutableMap<String, String>
    ) {
        var actionCount: Int = 0
        //! TOOO: NEXT -> Fix these to be the correct action strings!!! <== P2
        Log.d(TAG, "OnReceive:actionReceived: $actionReceived")
        if (actionReceived == mActionsExtras[0].action) {
            Log.d(TAG, "OnReceive:START")
            doSomethingAction1(valuesMap)
        } else if (actionReceived == mActionsExtras[1].action) {
            Log.d(TAG, "OnReceive:STEP1")
            doSomethingAction2(valuesMap)
        } else if (actionReceived == mActionsExtras[2].action) {

            //! @Chk Needs to fix correct actions and number of actionbs + extras
            Log.d(TAG, "Bank:OnReceive:2 ${mActionsExtras[2].action}")
            doSomethingAction3(valuesMap)
            mActionTimeout.cancel()

        }else if (actionReceived == mActionsExtras[3].action) {

            Log.d(TAG, "Bank:OnReceive:END ${mActionsExtras[3].action}")
            doSomethingAction3(valuesMap)

        }
        //! IF not received all actions and intent extras at this time, make a count
        //mActionTimeout.cancel()
//        if (actionCount == mActionsExtras.size)
//        {
//            mActionTimeout.cancel() ;
//            actionCount = 0 // Not necc
//        }

    }

    private fun doSomethingAction1(values: MutableMap<String, String>) {
        //! TODO("Do some action")
    }

    private fun doSomethingAction2(values: MutableMap<String, String>) {
        //! TODO( "Do some with the gathered information accrding to ")
    }

    private fun doSomethingAction3(values: MutableMap<String, String>) {
        //TODO("Not yet implemented")
    }

    override fun setActionsForReceiver(actionList: List<String>) {
        //TODO("Not yet implemented")
    }

    fun updateTextInUIRegister(callBack: TimeoutContainer.TimeoutListener){
        //mTimeoutListener = callBack
        //mTimeoutListener.updateTimer("TIMEISHIGH!")
    }

    fun timeoutAlarmCheck0(isItATimeout: String){
        if (isItATimeout != null && isItATimeout.equals("TIMEOUT")){
            Log.d(TAG, "TIMEOUT")
            alarmMgr?.cancel(alarmIntent)
        }else {
            Log.d(TAG, "NOT TIMEOUT") // Good
        }
        alarmMgr?.setExact(AlarmManager.ELAPSED_REALTIME_WAKEUP, 5000, alarmIntent)
    }
}


