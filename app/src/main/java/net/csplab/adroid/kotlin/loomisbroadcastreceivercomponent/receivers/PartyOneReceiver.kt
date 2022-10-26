package net.csplab.adroid.kotlin.loomisbroadcastreceivercomponent.receivers

import android.app.*
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler;
import android.util.Log
import android.widget.Toast
import net.csplab.adroid.kotlin.loomisbroadcastreceivercomponent.models.ActionExtra
import java.util.*

class PartyOneReceiver(
    override val providerName: String?,
    override var mActionsExtras: List<ActionExtra>,
    override val mTimeoutLength: Long = 15000L,
    //override //mActionTimeout.cancel()var actionsCompleted: List<Boolean>
) : PayProviderReceiver() {
private var TAG = PartyOneReceiver::class.java.simpleName
    private var mActionCounter = mActionsExtras.size // Counting action to be completed/received
    private var mActionCount = 0 // Counting action to be completed/received

    private var mAliveToast: Timer = Timer()
    private lateinit var mContext: Context

    companion object{
        var NOTIFICATION_ID = "notification-id"
        var NOTIFICATION = "notification"
    }

    //! Receive is called once per action (action that is broadcasted)
    override fun onReceive(ctx: Context, intent: Intent) {
        super.onReceive(ctx, intent)
        this.mContext = ctx
        //Toast.makeText(ctx, "PartyOneProvider:onReceive", Toast.LENGTH_LONG).show()
        Log.d(TAG, "PartyOneProvider:onReceive:intent.action> ${intent.action}")
        //Toast.makeText(ctx, "PartyOneProvider:onReceive", Toast.LENGTH_LONG).show()

        //runShowAlive(ctx)

        //! Descr: The timeout is initialized at the start of BroadCast:onReceive
        //! because payment start is initialized from the user (activity), that could depend on
        //! payment device, etc.

        startTimeout(mTimeoutLength)

        //! Setup Notification
        //notificationAtReceiver(ctx, intent)

        //! Starting Action and Intent Retrieval
        val handler: Handler = Handler()
        val pendingResult: PendingResult = goAsync()

        val threadProtocol = Thread(object: Runnable {
            override fun run() {
                val resultCodePI: Int = pendingResult.resultCode
                val resultData: String? = pendingResult.resultData
                val resultExtras: Bundle = pendingResult.getResultExtras(true)
                val stringExtra = resultExtras.getString("stringExtra")

//                handler.post(Runnable {
//                    Toast.makeText(ctx, "pendingResult:handler" + stringExtra, Toast.LENGTH_LONG).show()
//                })

                val actionReceived = intent.action
                var intentExtras = intent.extras
                val intentExtraSize = intent.extras?.size()
                var mNumberActionsRegistered = mActionsExtras.size
                //! For the action that entered us into onReceive, get ALL Keys for the intent extras data
                var extraKeySet = intentExtras?.keySet()
                //! Using the keys from intent extra, extract all
                //val values = mutableListOf<String>()  // values.add(intentExtras?.get(k).toString())
                val valuesMap = mutableMapOf<String, String>()

                Log.d(TAG, "onReceive:intentextrasize: $intentExtraSize")
                //! Extract values from intents extras, into a map of values
                valuesMap.clear()
                for (k in extraKeySet!!) {
                    Log.d(TAG, "PartyOneProvider:onRecieve:Keys: $k")
                    valuesMap.put(k, intentExtras?.get(k).toString())
                }


                Log.d(TAG, "PartyOneProvider:onRecieve: keyset ${extraKeySet}")
                Log.d(TAG, "PartyOneProvider:onReceive:iAction: $actionReceived  NumActions $mNumberActionsRegistered ; ${mActionsExtras}")
                Log.d(TAG, "PartyOneProvider:onReceive:Val: ${valuesMap}")
                Log.d(TAG, "PartyOneProvider:onReceive:intentExtras: ${intentExtras}")

                //! Set Function for dealing with business/protocol logic that must be specialised
                if (actionReceived != null) {
                    protocolReceivingData(actionReceived, intent, valuesMap)
                }

                pendingResult.setResult(resultCode, resultData, resultExtras)
                Log.d(TAG, "pendingResult:Finished:ReadyToAbort!")
                //pendingResult.abortBroadcast
                pendingResult.finish()

                //pendingResult.abortBroadcast
            }
        })
        threadProtocol.start()//
        //mActionTimeout.dis
        //mActionTimeout.cancel()
//        //! IF ALL ACTIONS and intents extras receieved and before timeout : NumActions = ActionsExtras.size
//        //! Cancel time out, reset ACTION COUNT
//        //! mActionTimeout.cancel() ; actionCount = 0

    } // END onReceive

    //! descr: Protocol specific functions for specialised 3. party provider : All doSomething**( methods
    private fun doSomethingAction1(values: MutableMap<String, String>) {
        //! TODO( "Do some with the gathered information according to the specific ACTION")
        // TODFOmActionCounter
    }

    private fun doSomethingAction2(values: MutableMap<String, String>) {
        //! TODO("Do some action")
    }

    private fun doSomethingAction3(values: MutableMap<String, String>) {
        //! TODO("Do some action")
    }

    private fun doSomethingAction4(values: MutableMap<String, String>) {
        //! TODO("Do some action")
    }

    //! Descr: Specific protocol logic for processing under each Actions
    // TODO Check all of this function
    override fun protocolReceivingData(actionReceived: String, intent: Intent, valuesMap: MutableMap<String, String>) {
        //! CHK: This these entry lines move them
        mActionCount++
        Log.d(TAG, "protocol:Receive:actionReceived: $actionReceived")
        //! Foreach action we receive do something with the data extracted extra,
        // !Compare the recorded Actions sets to the incoming data
        if (actionReceived == mActionsExtras[0].action) {
            //! CHK ALL THESE intents data keys etc move ouside the IF what tp do
            //! 1. DO SPECIFIC STUFF FOR ACTION
            //! 2. THEN GET ALL INTENTS Extras per key
            //! 3. Chk that we got all actions and intents, ie count ACTIONS Received
            doSomethingAction1(valuesMap)
            // !!! var key1val = intent.getStringExtra(keyFromPair)  // KEY1
            // ! Log.d(TAG, "PartyOneProvider:onReceive:Action0: ${mActionExtras[0].action} : KeyVal: $key1val")
        } else if (actionReceived == mActionsExtras[1].action) {
            doSomethingAction2(valuesMap)
        } else if (actionReceived == mActionsExtras[2].action) {
            // @CHK: DOnt do somnething here
        }else if (actionReceived == mActionsExtras[3].action) {
            doSomethingAction3(valuesMap)
        } else if (actionReceived == mActionsExtras[4].action) {
            Log.d(TAG, "ActionReceived 4: ${mActionsExtras[4].action}")
            //var key1val = intent.getStringExtra("KEY4")
            //if (mActionCount == mActionsExtras.size && valuesMap.get("KEY4_PAY_END") == "BYE!" ){
            if (valuesMap.get("KEY5") == "BYE!" ){
            Log.d(TAG, "PartyOneProvider:onReceive:timeout.cancel:BYE")
            mActionTimeout.cancel()
            mActionCount = 0 // Not neccessary
            }
        //! IF ALL ACTIONS and intents extras received and before timeout : NumActions = ActionsExtras.size
        //! Cancel time out, reset ACTION COUNT
        }
    }

    //! CHK> Should this be here remove
    override fun setActionsForReceiver(actionList: List<String>) {
        //! Add ACTIONS for that receive
    }

//    fun notificationAtReceiver(ctx: Context, intent: Intent){
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
//            val channel = NotificationChannel(NOTIFICATION_ID, NOTIFICATION, NotificationManager
//                .IMPORTANCE_HIGH).apply {
//                lightColor = Color.GREEN
//                enableLights(true)
//                description = "Hey its a channel"
//            }
//            val manager = ctx.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//            manager.createNotificationChannel(channel)
//        }
//
//        val pendingIntent = TaskStackBuilder.create(ctx).run {
//            addNextIntentWithParentStack(intent)
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_MUTABLE)
//            } else {
//                getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)
//            }
//        }
//        //val notification = Notification(intent.getParcelableExtra(NOTIFICATION))
//        val notification = Notification(
//            intent.getParcelableExtra(NOTIFICATION))
//        with(NotificationManagerCompat.from(ctx)) {
//            // notificationId is a unique int for each notification that you must define
//            notify(17, notification)
//        }
//    }

    private fun runShowAlive(ctx: Context) {
        mAliveToast = Timer()
        Toast.makeText(ctx, "runShowAlive!", Toast.LENGTH_LONG).show()
        mAliveToast.scheduleAtFixedRate(object: TimerTask() {
            override fun run() {
                Toast.makeText(ctx, "BornToBeAlive!", Toast.LENGTH_LONG).show()
                Log.d(TAG, "PayProvider:HeartBeat ")
            } },5000, 5000)
    }
}
