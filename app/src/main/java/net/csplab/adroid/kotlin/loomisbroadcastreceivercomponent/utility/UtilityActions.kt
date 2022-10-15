package net.csplab.adroid.kotlin.loomisbroadcastreceivercomponent.utility

import models.ActionsExtra

const val PACKAGENAME: String = "net.csplab.adroid.kotlin.loomisbroadcastreceivercomponent"

 open class UtilityActions {
    final val TAG = UtilityActions::class.java.simpleName

    object SET {
        var actionsExtraPartyOne: List<ActionsExtra> = mutableListOf<ActionsExtra>(
            // Actions is locked and number of Extradata and KEYS are locked after Broadcast Provider instantiation
            // DO SET DATA value, ie set time
            ActionsExtra(
                "ACTION_PAY_START",
                mutableListOf<Pair<String, String>>(Pair("ACTION_PAY_START", "ID456789"))
            ),
            ActionsExtra(
                "ACTION_PAY1",
                mutableListOf<Pair<String, String>>(Pair("KEY_PAY1", ""), Pair("", ""))
            ),
            ActionsExtra(
                "ACTION_PAY2",
                mutableListOf<Pair<String, String>>(Pair("KEY_PAY2", ""), Pair("", ""))
            ),
            ActionsExtra(
                "ACTION_PAY_END",
                mutableListOf<Pair<String, String>>(Pair("KEY_PAY_END", ""))
            ),

            )


        var actionsExtraBankOfBank: List<ActionsExtra> = mutableListOf<ActionsExtra>(
            // Actions is locked and number of Extradata and KEYS are locked after Broadcast Provider instantiation
            // DO SET DATA value, ie set time
            ActionsExtra(
                "ACTION_PAY_START",
                mutableListOf<Pair<String, String>>(Pair("ACTION_PAY_START", "ID456789"))
            ),
            ActionsExtra(
                "ACTION_PAY1",
                mutableListOf<Pair<String, String>>(Pair("KEY_PAY1", ""), Pair("", ""))
            ),
            ActionsExtra(
                "ACTION_PAY2",
                mutableListOf<Pair<String, String>>(Pair("KEY_PAY2", ""), Pair("", ""))
            ),
            ActionsExtra(
                "ACTION_PAY_END",
                mutableListOf<Pair<String, String>>(Pair("KEY_PAY_END", ""))
            ),

            )
    }
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
            //! PartyOne Provider: Information for  Receiver: Fill the provider wiht ACTION name strings
            fun setupActionsForProviderPartyOne(): List<String> {
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

            //! Provider BankOfBank: Information for  Receiver: Fill the provider wiht ACTION name strings
            fun setupActionsForProviderBankOfBank(): List<String> {
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