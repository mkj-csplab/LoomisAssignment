package receivers

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.CountDownTimer
import android.os.SystemClock
import android.util.Log
import android.widget.Toast

abstract class PayProviderBroadcastReceiver : BroadcastReceiver() {
    private val TAG = PayProviderBroadcastReceiver::class.java.simpleName

    abstract var actionIDS: List<String> // The list of Ids
    //abstract val actionsNum: Int

    ///private lateinit var actionBrTimer: CountDownTimer
    //abstract var actionsCompleted: List<Boolean> // getNumberOf Extras

    protected var alarmMgr: AlarmManager? = null
    protected lateinit var alarmIntent: PendingIntent


    override fun onReceive(ctx: Context, intent: Intent) {
    }

    // :::Setup protocol per pay provider, speficic logic for provider goes here :
    // Actions Extras and proccessing Logic
    abstract fun protocolSetup(ctx: Context, intent: Intent)

    fun setActions(actionList: List<String>) {
    }

    fun timerAlert(ctx: Context){
        alarmMgr = ctx.getSystemService(android.content.Context.ALARM_SERVICE) as AlarmManager
        alarmIntent = Intent(ctx, PayProviderBroadcastReceiver::class.java).let { intent ->
            intent.putExtra("TIMEOUT_ALARM", "TIMEOUT")
            PendingIntent.getBroadcast(ctx, 13, intent, 0)
        }

        alarmMgr?.setExact(
            AlarmManager.ELAPSED_REALTIME_WAKEUP,
            SystemClock.elapsedRealtime() + 5 * 1000,
            alarmIntent
        )
    }

}

//val rec = Intent.ACTION_ANSWER
//
//val action: String? = intent?.getAction()
//
////inf.addAction(Intent.ACTION_BATTERY_CHANGED)
////inf.addAction(Intent.ACTION_POWER_DISCONNECTED)
//
//if(Intent.ACTION_POWER_CONNECTED == action) {
//    Toast.makeText(ctx, "Power is connected", Toast.LENGTH_LONG).show()
//    //ctx.startService(
//    //  Intent(PhoneService.ACTION_POWER_CONNECTED))
//} else if (Intent.ACTION_POWER_DISCONNECTED == action) {
//    Toast.makeText(ctx, "Power is DISconnected", Toast.LENGTH_LONG).show()
//    //ctx.startService(
//    //  Intent(PhoneService.ACTION_POWER_CONNECTED) )
//}
