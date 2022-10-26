@file:Suppress("IllegalIdentifier")
package net.csplab.adroid.kotlin.loomisbroadcastreceivercomponent.receivers

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ActivityScenario.ActivityAction
import androidx.test.core.app.launchActivity
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import androidx.test.platform.app.InstrumentationRegistry
import net.csplab.adroid.kotlin.loomisbroadcastreceivercomponent.utility.UtilityActions
import net.csplab.adroid.kotlin.loomisbroadcastreceivercomponent.views.PayTerminalActivity
import net.csplab.adroid.kotlin.loomisbroadcastreceivercomponent.views.PayTerminalJavaActivity
import org.hamcrest.CoreMatchers.isA
import org.junit.Assert
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.kotlin.isNotNull

//import org.mockito.junit.MockitoJUnit.rule

//! Integration Tests
//https://developer.android.com/training/testing/instrumented-tests

//@SmallTest
@RunWith(AndroidJUnit4::class)
class PayProviderReceiverTest {

    lateinit var mPartyOneReceiver: PartyOneReceiver
    lateinit var mContext: Context

    // Executes each task synchronously using Architecture Components.
    @Rule @JvmField
    var instantExecutorRule = InstantTaskExecutorRule()
    //private lateinit var UtilityActions.ActionSets

    @get:Rule
    val activityRule = ActivityScenarioRule(PayTerminalActivity::class.java)

    @Test
    fun prepare_provider_with_actions_and_extras_data(){
    }

    @Test (timeout=10000L)
    fun test_receiving_intent_actions_with_timeout() {
        val appContext: Context = InstrumentationRegistry.getInstrumentation().targetContext
        Assert.assertEquals("net.csplab.adroid.kotlin.loomisbroadcastreceivercomponent", appContext.getPackageName())
        println("useAppContext : Test Ran")

        val payReceiver = PartyOneReceiver("PayProvider", UtilityActions.ActionSets.actionsExtraPartyOne, 10000L)
        val intentFilter = IntentFilter()
        val actionTest = "TEST_ACTION"
        intentFilter.addAction("TEST_ACTION")
        appContext.registerReceiver(mPartyOneReceiver, intentFilter)
        val intent = Intent()
        intent.action = actionTest
        mPartyOneReceiver.onReceive(mContext, intent)
        assertEquals(1, mPartyOneReceiver.resultData);
    }

    @Before
    fun setUp() {
        // Setup for instrumentation test
        launchActivity<PayTerminalJavaActivity>()

        mContext = InstrumentationRegistry.getInstrumentation().targetContext
        Assert.assertEquals("net.csplab.adroid.kotlin.loomisbroadcastreceivercomponent", mContext.getPackageName())
    }

    @Test
    fun checkContext(){
        val appContext: Context = InstrumentationRegistry.getInstrumentation().targetContext
        Assert.assertEquals("net.csplab.adroid.kotlin.loomisbroadcastreceivercomponent", appContext.getPackageName())
        println("useAppContext : Test Ran")
    }

    @Test
    fun activity_action_do(){
        ActivityScenario.launch(PayTerminalActivity::class.java).use { scenario ->
            scenario.onActivity(
                ActivityAction<PayTerminalActivity> { activity: PayTerminalActivity ->
                    //assertThat("123", isA(String::class.java))
                    assertThat(activity.tvShowTime.text.toString(), isNotNull())
                })
        }
    }

    @Test
    fun activity_PayTerminal_Click_on_activate_PartyOneReceiver_PerformClick() {
        activityRule.scenario.onActivity {
            //protocolReceivingData()

        } //bt_start_party_one_provider
        activityRule.scenario.onActivity {
            //} // Optionally, access the activi
            //val scenario = activityRule.getScenario()
            //scenario.moveToState(Lifecycle.State.CREATED)
            //scenario.
        }
    }

    @Test //! T7
    //Start JAva Activity -> click onm broadcaster test
    fun checkJavaActivitySetup() {
        //launchActivity<PayTerminalJavaActivity>()
        launchActivity<PayTerminalJavaActivity>().use { scenario ->
            //scenario.moveToState(State.CREATED)
            scenario.result
//            scenario.onActivity { activity ->
//                //activity.intent.setClass()
//                startActivity(Intent(activity, ))
//            }
        }
    }

    @Test
    fun do_Transaction_Send_Actions_For_Transactions_Receive_All_Transaction_Actions(){}

    @Test
    fun transaction_Failed_Action_Not_Received_Before_Timeout(){}

    @Test
    fun transaction_Succeded_Completed_All_Actions_Before_Timeout(){}

    @Test
    //! TX1
    fun timeout_Reached(){}

    @Test
    //! T8
    fun transaction_Succeeded_Before_Timeout(){}

    @Test
    fun receive_First_Transaction_Send_Start_Action_Receive_Start_Action(){}

    @Test
    fun receive_Last_Transaction_Send_Last_Action_Receive_Last_Action(){}

    @Test
    fun receive_Value_From_Sender_At_Receiver_Check(){}

    @Test
    fun send_Action_And_Extra_Receive_And_Unpack(){}

    @Test
    fun getProviderName() {}

    @Test
    fun getMActionExtras() {}

    @Test
    fun setMActionExtras() {}

    @Test
    fun getMTimeoutLength() {}

    @Test
    fun getMActionTimeout() {}

    @Test
    fun setMActionTimeout() {}

    @Test
    fun protocolReceivingData() {}

    @Test
    fun setActionsForReceiver() {}

    @Test
    fun createTimeoutTimer() {}

    @Test
    fun timerAlertSetup() {}

    @Test
    fun setAlarmTimer(){}

}