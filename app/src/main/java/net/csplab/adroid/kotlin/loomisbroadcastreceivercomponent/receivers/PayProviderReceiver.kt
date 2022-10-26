package net.csplab.adroid.kotlin.loomisbroadcastreceivercomponent.receivers

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.SystemClock
import android.util.Log
import net.csplab.adroid.kotlin.loomisbroadcastreceivercomponent.models.ActionExtra
import java.util.*

//! Descr: Number of actions and number of extra-data per action is considered to be unchanging
//! after the instation, only the value content of the extras changes, adding new action or more data
//! to an action requires a reinstantiation of the specific Broadcast provider component
abstract class PayProviderReceiver : BroadcastReceiver() {
    private val TAG = PayProviderReceiver::class.java.simpleName

    abstract val providerName:String? // 3. Party ProviderName
    abstract var mActionsExtras: List<ActionExtra>
    abstract val mTimeoutLength: Long
    //abstract var actionsCompleted: List<Boolean> // Chk: getNumberOfCompleted @?

    private var mBrHeartBeat: Timer = Timer() //! heartbeat is just inbuild in baseclass
    protected var mActionTimeout: Timer = Timer()

    protected var alarmMgr: AlarmManager? = null
    protected lateinit var alarmIntent: PendingIntent

    //! Init & Constructors
    init {
        //! Test for liveness, whether app is in back or foreground
        runHeartBeat()
        Log.d(TAG, "PayProvider:init heartbeat")
        // Otherwise use a handler.
    }

    // Set Timer Here
    // Set & Build Notifications

    // Chk@: Abstract or base implementation @?
    override fun onReceive(ctx: Context, intent: Intent) {}// set timeout her

    //! HeartBeat for All PayProviders
    private fun runHeartBeat() {
        mBrHeartBeat = Timer()
        mBrHeartBeat.scheduleAtFixedRate(object: TimerTask() {

            override fun run() {
                Log.d(TAG, "PayProvider:HeartBeat ")
            } },5000, 5000)
    }

    //! Descr: Setup protocol per pay provider, specific logic for specialised provider goes here:
    //! Descr: Specific protocol logic for processing under each Actions
    //! Input: Actions, Extras and processing. :Functional Logic
    abstract fun protocolReceivingData(actionReceived: String, intent: Intent, valuesMap: MutableMap<String, String>)

    //! Descr: Add the ACTIONS for the specialised pay provider
    abstract fun setActionsForReceiver(actionList: List<String>)

    //! Descr: Setting timeout for receiver: Abstract@? method
    //! Create timer into handling action
    protected fun startTimeout(timeoutLength: Long){
        mActionTimeout.schedule(object: TimerTask() {
            override fun run() {
                Log.d(TAG, "PayProviderReceiver:TimeoutNotify: after $timeoutLength") // @Chk: @No timer in Utility
                abortBroadcast() //! After timeout we abort this broadcast
            //! Not on main thread, broadcaster run normally on main thread (UI Thread): Do Interface
        }}, timeoutLength)

        Log.d(TAG, "PayProvider:Timeout Timer Created timeSet: $timeoutLength")
    }

    //! Deprecated
    //! setting Alarm for Receiver
    fun timerAlertSetup(ctx: Context){
        alarmMgr = ctx.getSystemService(android.content.Context.ALARM_SERVICE) as AlarmManager
        alarmIntent = Intent(ctx, PayProviderReceiver::class.java).let { intent ->
            intent.putExtra("TIMEOUT_ALARM", "TIMEOUT")
            PendingIntent.getBroadcast(ctx, 13, intent, 0 or PendingIntent.FLAG_MUTABLE)
        }
    }

    fun setAlarmTimer(seconds: Int){
        alarmMgr?.setExact(
            AlarmManager.ELAPSED_REALTIME_WAKEUP,
            SystemClock.elapsedRealtime() + seconds * 1000,
            alarmIntent)
    }
}
