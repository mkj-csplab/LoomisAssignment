package net.csplab.adroid.kotlin.loomisbroadcastreceivercomponent.utility

const val PACKAGENAME: String = "net.csplab.adroid.kotlin.loomisbroadcastreceivercomponent"

public open class UtilityActions {
    final val TAG = UtilityActions::class.java.simpleName

        // Set these in each broadcast implementation
        companion object Util { //PAYBROADCAST
            //
            enum class PayProvider1(action: String) {
                ACTION_PAYID1_START(
                    "net.csplab.adroid.kotlin.loomisbroadcastreceivercomponent.PAYID1_START"),
                ACTION_PAYID1_STEP1("net.csplab.adroid.kotlin.loomisbroadcastreceivercomponent.PAYID1_STEP1"),
                ACTION_PAYID1_END(
                    "net.csplab.adroid.kotlin.loomisbroadcastreceivercomponent.PAYID1_END");
                companion object {
                    fun getAction() = values()
                }
            }

            enum class PayProvider2(action: String) {
                ACTION_PAYID2_START("net.csplab.adroid.kotlin.loomisbroadcastreceivercomponent.PAYID2_START"),
                ACTION_PAYID2_STEP1(
                    "net.csplab.adroid.kotlin.loomisbroadcastreceivercomponent.PAYID2_STEP1"),
                ACTION_PAYID2_STEP2(
                    "net.csplab.adroid.kotlin.loomisbroadcastreceivercomponent.PAYID2_STEP2"),
                ACTION_PAYID2_END(
                    "net.csplab.adroid.kotlin.loomisbroadcastreceivercomponent.PAYID2_END");
                companion object {
                    fun getAction() = values()
                }
            }

            // Chk@ Needs to be updated with new utility class names
            // ConsumerPartyPayProvider Receiver
            fun collectActionsForProviderPartyOne(): List<String> {
                //action_init: String = ctx.getPackage // ACTION_PAY_INIT") // Init
                val customActionList = mutableListOf<String>(
                PACKAGENAME + ".PAYID1_START", // start
                PACKAGENAME + ".PAYID1_STEP1",
                PACKAGENAME + ".PAYID1_STEP2",
                //Arbitrary number of action steps ... How to add them, dynamically or set per specialisation
                PACKAGENAME + ".PAYID1_END") // End of ACTION EVENT
                //Log.d(TAG, "Print Package Name $PACKAGENAME : Action names $s1 $s2 $s3 $s4")
                return customActionList
            }

            // BankOfBank Receiver
            fun collectActionsForProviderBankOfBank(): List<String> {
                // Chk@ : Put int list, maybe use size for that?
                val customActionList = mutableListOf<String>(
                PACKAGENAME + ".ACTION_PAYID2_START",
                PACKAGENAME + ".ACTION_PAYID2_STEP1",
                PACKAGENAME + ".ACTION_PAYID2_END" )// End of ACTION EVENT
                //Log.d(TAG, "Print Package Name $PACKAGENAME)
                return customActionList
            }
        }

}