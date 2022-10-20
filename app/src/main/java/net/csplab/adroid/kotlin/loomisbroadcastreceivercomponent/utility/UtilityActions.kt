package net.csplab.adroid.kotlin.loomisbroadcastreceivercomponent.utility

import android.os.Build.ID
import net.csplab.adroid.kotlin.loomisbroadcastreceivercomponent.models.ActionExtra

const val PACKAGENAME: String = "net.csplab.adroid.kotlin.loomisbroadcastreceivercomponent"
const val ID_PROVIDER_PARTYONE = "PARTYONE_ID"
const val ID_PROVIDER_BANKOFBANK = "BANKOFBANK_ID"
//! Etc const val per

// ! CHK: check INSTANCE access
open class UtilityActions {
    final val TAG = UtilityActions::class.java.simpleName

    object ActionSets {
        val actionStrings1 = setupActionsForProviderPartyOne()
        var actionsExtraPartyOne: List<ActionExtra> = listOf<ActionExtra>(
            // Actions is locked and number of Extradata and KEYS are locked after Broadcast Provider instantiation
            // DO SET DATA value, ie set time
            ActionExtra(
                actionStrings1[0],
                //concatenatePkgName("ACTION_PAY_START"), // Call ActionString instead
                mutableListOf<Pair<String, String>>(Pair("KEY1_ID", "ID456789"))
            ),
            ActionExtra(
                actionStrings1[1],
                //"ACTION_PAY1",
                mutableListOf<Pair<String, String>>(Pair("KEY2_PAY_DESCR", "Michael"), Pair("KEY2_PAY_DESCR", "Jensen")),
            ),
            ActionExtra(
                actionStrings1[2],
                //"ACTION_PAY2",
                mutableListOf<Pair<String, String>>(Pair("KEY3_BALANCE_SUFFICIENTS", "OK"))
            ),
            ActionExtra(
                actionStrings1[3], // END Sentinel!
                mutableListOf<Pair<String, String>>(Pair("KEY4_PAY_END", "BYE1")) // No Extras! @
            ),
            )

        //! Chk@: ACTION => PACKAGENAME + . + ACTION_NAME
        val actionStrings2 = setupActionsForProviderBankOfBank()
        var actionsExtraBankOfBank: List<ActionExtra> = mutableListOf<ActionExtra>(
            // Actions is locked and number of Extradata and KEYS are locked after Broadcast Provider instantiation
            // DO SET DATA value, ie set time
            ActionExtra(
                actionStrings2[0],
                //"ACTION_PAY_START",
                mutableListOf<Pair<String, String>>(Pair("KEY1_PAY_START", "ID456789"))
            ),
            ActionExtra(
                actionStrings2[1], //"ACTION_PAY1",
                mutableListOf<Pair<String, String>>(Pair("KEY2_PAY1", ""), Pair("KEY2_PAY2", ""))
            ),
            ActionExtra(
                actionStrings2[2], //"ACTION_PAY_END",
                mutableListOf<Pair<String, String>>(Pair("KEY3_PAY_END", "BYE!"))
            ),
            )

        //!
        // TODO("Change to new ACTION string Creation format")
//        private fun concatenatePkgName(s: String): String {
//            val packageAction = PACKAGENAME + "." + s
//            return packageAction
//        }
    }
//        // Set these in each broadcast implementation

       companion object Util { //PAYBROADCAST
//            //! Obsolete: Kept for reference
//            enum class PayProvider1(action: String) {
//                ACTION_PAYID1_START(
//                    "net.csplab.adroid.kotlin.loomisbroadcastreceivercomponent.PAYID1_START"),
//                ACTION_PAYID1_STEP1("net.csplab.adroid.kotlin.loomisbroadcastreceivercomponent.PAYID1_STEP1"),
//                ACTION_PAYID1_END(
//                    "net.csplab.adroid.kotlin.loomisbroadcastreceivercomponent.PAYID1_END");
//                companion object {
//                    fun getAction() = values()
//                }
//            }
//
//            //! Obsolete: Kept for reference
//            enum class PayProvider2(action: String) {
//                ACTION_PAYID2_START("net.csplab.adroid.kotlin.loomisbroadcastreceivercomponent.PAYID2_START"),
//                ACTION_PAYID2_STEP1(
//                    "net.csplab.adroid.kotlin.loomisbroadcastreceivercomponent.PAYID2_STEP1"),
//                ACTION_PAYID2_STEP2(
//                    "net.csplab.adroid.kotlin.loomisbroadcastreceivercomponent.PAYID2_STEP2"),
//                ACTION_PAYID2_END(
//                    "net.csplab.adroid.kotlin.loomisbroadcastreceivercomponent.PAYID2_END");
//                companion object {
//                    fun getAction() = values()
//                }
//            }

            // Chk@ Needs to be updated with new utility class names
            //! PartyOne Provider: Information for  Receiver: Fill the provider wiht ACTION name strings
            @JvmStatic
            fun setupActionsForProviderPartyOne(): List<String> {
                //action_init: String = ctx.getPackage // ACTION_PAY_INIT") // Init
                val customActionList = mutableListOf<String>(
                PACKAGENAME + "." + ID_PROVIDER_PARTYONE + "_" + "ACTION_START", // start
                PACKAGENAME + "." + ID_PROVIDER_PARTYONE + "_" + "ACTION_STEP_S",
                PACKAGENAME + "." + ID_PROVIDER_PARTYONE + "_" + "ACTION_STEP_T",
                //Arbitrary number of action steps ... How to add them, dynamically or set per specialisation
                PACKAGENAME + "." + ID_PROVIDER_PARTYONE + "_" + "ACTION_END") // End of ACTION EVENT
                //Log.d(TAG, "Print Package Name $PACKAGENAME : Action names $s1 $s2 $s3 $s4")
                return customActionList
                // ADD-STEP
            }

            //! Provider BankOfBank: Information for  Receiver: Fill the provider with ACTION name strings
            @JvmStatic
            fun setupActionsForProviderBankOfBank(): List<String> {
                // Chk@ : Put int list, maybe use size for that?
                val customActionList = mutableListOf<String>(
                PACKAGENAME + "." + ID_PROVIDER_BANKOFBANK + "_" + "ACTION_START",
                PACKAGENAME + "." + ID_PROVIDER_BANKOFBANK + "_" + "ACTION_STEP_X",
                PACKAGENAME + "." + ID_PROVIDER_BANKOFBANK + "_" + "ACTION_END" )// End of ACTION EVENT
                //Log.d(TAG, "Print Package Name $PACKAGENAME)
                return customActionList
            }
        }

    //! Seems to belong to ActionExtra class/instance, or para: AE
     fun prepareAddCustomAction(action: String, actionIndex: Int){
         //!! UtilityCall -> AE.addCustomActions()
     }

     fun prepareAddIntentExtraToAction(actionIndex: Int, extrasKeyIndex: Int,  key: String, value: String){
         //!! UtilityCall -> AE.addExtraToAction()
     }
}