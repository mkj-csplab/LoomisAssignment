package net.csplab.adroid.kotlin.loomisbroadcastreceivercomponent.receivers

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.SystemClock
import android.util.Log
import android.widget.Toast
import java.util.*

abstract class PayProviderReceiver : BroadcastReceiver() {
    private val TAG = PayProviderReceiver::class.java.simpleName

    abstract var mActionIDS: List<String> // The list of Ids
    abstract var providerName:String? // 3. Party ProviderName
    //abstract var actionsCompleted: List<Boolean> // getNumberOf

    protected var mActionTimeout: Timer = Timer()
    private var mBrHeartBeat: Timer = Timer() //! heartbeat is just inbuild in baseclass


    protected var alarmMgr: AlarmManager? = null
    protected lateinit var alarmIntent: PendingIntent

    init {
        //! Test for liveness, whether app is in back or foreground
        runHeartBeat()
        Log.d(TAG, "PayProvider:init heartbeat")
        // Otherwise use a handler.
    }

    // Set Timer Here
    // Set &  Build Notifications

    // Chk@: Abstract or base implementation @?
    override fun onReceive(ctx: Context, intent: Intent) {}

    //! HeartBeat for ALL PayProviders
    private fun runHeartBeat() {
        mBrHeartBeat = Timer()
        mBrHeartBeat.scheduleAtFixedRate(object: TimerTask() {
            override fun run() {

            } },5000, 5000)
    }

    // ::Setup protocol per pay provider, specific logic for provider goes here :
    // Actions Extras and processing Logic
    abstract fun protocolSetup(ctx: Context, intent: Intent)

    // Add the ACTIONS that this specific pay provider has
    abstract fun setActionsForReceiver(actionList: List<String>)
    //abstract fun setActionsForReceiver2(actionList: List<String>)

    protected fun setTimeout(timeoutLength: Long){
        mActionTimeout.schedule(object: TimerTask() {
            override fun run() {
                Log.d(TAG, "utility.Timeout after $timeoutLength")
                //! Not on main thread, broadcaster run normally on main thread (UI Thread): Do Interface
                //Toast.makeText(ctx, "Timer: ${Date().hours}:${Date().minutes}:${Date().seconds}", Toast.LENGTH_LONG).show()
                //this@MainActivity.runOnUiThread(
                //Runnable {
                //      tvWhatsTheTime.text = "Timer: ${Date().hours}:${Date().minutes}:${Date().seconds}"
                // })
            }}, timeoutLength)
        //mActionTimeout.cancel()
    }

    fun timerAlertSetup(ctx: Context){
        alarmMgr = ctx.getSystemService(android.content.Context.ALARM_SERVICE) as AlarmManager
        alarmIntent = Intent(ctx, PayProviderReceiver::class.java).let { intent ->
            intent.putExtra("TIMEOUT_ALARM", "TIMEOUT")
            PendingIntent.getBroadcast(ctx, 13, intent, 0)
        }
    }

    fun setTimer(seconds: Int){
        alarmMgr?.setExact(
            AlarmManager.ELAPSED_REALTIME_WAKEUP,
            SystemClock.elapsedRealtime() + seconds * 1000,
            alarmIntent)
    }
}
