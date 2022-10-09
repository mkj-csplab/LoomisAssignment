package utility

import android.content.Intent.ACTION_AIRPLANE_MODE_CHANGED
import android.util.Log

const val PACKAGENAME: String = "net.csplab.adroid.kotlin.loomisbroadcastreceivercomponent"

public open class UtilityActions {
    final val TAG = UtilityActions::class.java.simpleName

        // Set these in each broadcast implementation
        companion object Util { //PAYBROADCAST
            //const val ACTION_AIRPLANE_MODE_CHANGED = "android.intent.action.AIRPLANE_MODE"
            // @Chk: Action IN STRING PART SHOULD BE left out!
            const val ACTION_PAYID1_START = "net.csplab.adroid.kotlin.loomisbroadcastreceivercomponent.ACTION_PAYID1_START"
            const val ACTION_PAYID1_STEP1 = "net.csplab.adroid.kotlin.loomisbroadcastreceivercomponent.PAYID1_STEP1"
            //const val ACTION_PAYID1_STEP2 = "net.csplab.adroid.kotlin.loomisbroadcastreceivercomponent.PAYID1_STEP2"
            //const val ACTION_PAYID1_STEP3 = "net.csplab.adroid.kotlin.loomisbroadcastreceivercomponent.PAYID1_STEP3"
            const val ACTION_PAYID1_END = "net.csplab.adroid.kotlin.loomisbroadcastreceivercomponent.PAYID1_END"

            const val ACTION_PAYID2_START = "net.csplab.adroid.kotlin.loomisbroadcastreceivercomponent.PAYID2_START"
            const val ACTION_PAYID2_STEP1 = "net.csplab.adroid.kotlin.loomisbroadcastreceivercomponent.PAYID2_STEP1"
            const val ACTION_PAYID2_STEP2 = "net.csplab.adroid.kotlin.loomisbroadcastreceivercomponent.PAYID2_STEP2"
            //const val ACTION_PAYID2_STEP3 = "net.csplab.adroid.kotlin.loomisbroadcastreceivercomponent.PAYID2_STEP3"
            //const val ACTION_PAYID2_STEP4 = "net.csplab.adroid.kotlin.loomisbroadcastreceivercomponent.PAYID2_STEP4"
            const val ACTION_PAYID2_END = "net.csplab.adroid.kotlin.loomisbroadcastreceivercomponent.PAYID2_END"

            final val TAG = UtilityActions::class.java.simpleName

            // Chk@ Needs to be updated with new utility class names
            // ConsumerPartyPayProvider Receiver
            fun collectActionsForProvider1(pack: String): List<String> {
                //action_init: String = ctx.getPackage // ACTION_PAY_INIT") // Init
                val s1 = PACKAGENAME + ".ACTION_PAYID1_START" // start
                val s2 = PACKAGENAME + ".ACTION_PAYID1_STEP1"
                val s3 = PACKAGENAME + ".ACTION_PAYID1_STEP2"
                //Arbitrary number of action steps ... How to add them, dynamically or set per specialisation
                val s4 = PACKAGENAME + ".ACTION_PAYID1_END" // End of ACTION EVENT
                //Log.d(TAG, "Print Package Name $PACKAGENAME : Action names $s1 $s2 $s3 $s4")
                val customActions = listOf(s1, s2, s3, s4)
                return customActions
            }

            // BankOfBank Receiver
            fun collectActionsForProvider2(pack: String): List<String> {
                // Chk@ : Put int list, maybe use size for that?
                //action_init: String = ctx.getPackage // ACTION_PAY_INIT") // Init
                val s1 = PACKAGENAME + ".ACTION_PAYID2_START" // start
                val s2 = PACKAGENAME + ".ACTION_PAYID2_STEP1"
                //val s3 = PACKAGENAME + ".ACTION_PAYID2_STEP2"
                val s3 = PACKAGENAME + ".ACTION_PAYID2_END" // End of ACTION EVENT
                //Log.d(TAG, "Print Package Name $PACKAGENAME : Action names $s1 $s2 $s3")
                val customActions = listOf(s1, s2, s3)
                return customActions
            }
        }

}