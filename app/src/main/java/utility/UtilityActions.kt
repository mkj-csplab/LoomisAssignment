package utility

import android.util.Log

const val PACKAGENAME: String = "net.csplab.adroid.kotlin.loomisbroadcastreceivercomponent"

public open class UtilityActions {
    final val TAG = UtilityActions::class.java.simpleName

    companion object Util {
        final val TAG = UtilityActions::class.java.simpleName
        // In utility ss, maybe as static array
        fun collectActionsForProvider1(pack: String): List<String> {
            //action_init: String = ctx.getPackage // ACTION_PAY_INIT") // Init
            val s1 = PACKAGENAME + ".ACTION_PAYID1_START" // start
            val s2 = PACKAGENAME + ".ACTION_PAYID1_STEP1"
            val s3 = PACKAGENAME + ".ACTION_PAYID1_STEP2"
            val s4 = PACKAGENAME + ".ACTION_PAYID1_STEP3"
            //Arbitrary number of action steps ... How to add them, dynamically or set per specialisation
            val s5 = PACKAGENAME + ".ACTION_PAYID1_END" // End of ACTION EVENT
            Log.d(TAG, "Print Package Name $PACKAGENAME : Action names $s1 $s2 $s3 $s4 $s5")
            val customActions = listOf(s1, s2, s3, s4, s5)
            return customActions
        }

        fun collectActionsForProvider2(pack: String): List<String> {
            // Chk@ : Put int list, maybe use size for that?
            //action_init: String = ctx.getPackage // ACTION_PAY_INIT") // Init
            val s1 = PACKAGENAME + ".ACTION_PAYID2_START" // start
            val s2 = PACKAGENAME + ".ACTION_PAYID2_STEP1"
            val s3 = PACKAGENAME + ".ACTION_PAYID2_STEP2"
            val s4 = PACKAGENAME + ".ACTION_PAYID2_END" // End of ACTION EVENT
            Log.d(TAG, "Print Package Name $PACKAGENAME : Action names $s1 $s2 $s3 $s4")
            val customActions = listOf(s1, s2, s3, s4)
            return customActions
        }
    }


}