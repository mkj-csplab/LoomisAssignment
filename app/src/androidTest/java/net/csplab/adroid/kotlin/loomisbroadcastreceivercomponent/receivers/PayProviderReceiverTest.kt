package net.csplab.adroid.kotlin.loomisbroadcastreceivercomponent.receivers

import android.content.Context
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import androidx.test.platform.app.InstrumentationRegistry
import net.csplab.adroid.kotlin.loomisbroadcastreceivercomponent.utility.UtilityActions
import net.csplab.adroid.kotlin.loomisbroadcastreceivercomponent.views.PayTerminalActivity
import net.csplab.adroid.kotlin.loomisbroadcastreceivercomponent.views.PayTerminalJavaActivity
import org.junit.Assert
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

//import org.mockito.junit.MockitoJUnit.rule


//! Integration Tests
//https://developer.android.com/training/testing/instrumented-tests


@SmallTest
@RunWith(AndroidJUnit4::class)
//! T8
class PayProviderReceiverTest {

    //private lateinit var UtilityActions.ActionSets

    @Rule
    public val activityRule = ActivityScenarioRule(PayTerminalActivity::class.java)

    @Before
    fun setUp() {
        // Setup for instrumentation test
        launchActivity<PayTerminalJavaActivity>()

        val appContext: Context = InstrumentationRegistry.getInstrumentation().targetContext
        Assert.assertEquals("in.curioustools.aad_x_testing2", appContext.getPackageName())
        println("useAppContext : Test Ran")
    }

    @Test
    fun activityPerformClick() {
        activityRule.scenario.onActivity {
            //
        } //bt_start_party_one_provider
        //activityRule.scenario.onActivity {
        //} // Optionally, access the activi
        //val scenario = activityRule.getScenario()
        //scenario.moveToState(Lifecycle.State.CREATED)
        //scenario.
    }

    @Test //! T7
    //Start JAva Activity -> click onm broadcaster test
    fun checkJavaActivitySetup(){
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
    fun getProviderName() {

    }

    @Test
    fun getMActionExtras() {
    }

    @Test
    fun setMActionExtras() {
    }

    @Test
    fun getMTimeoutLength() {
    }

    @Test
    fun getMActionTimeout() {
    }

    @Test
    fun setMActionTimeout() {
    }

    @Test
    fun getAlarmMgr() {
    }

//    @Test
//    fun setAlarmMgr() {
//    }
//
//    @Test
//    fun getAlarmIntent() {
//    }

//    @Test
//    fun setAlarmIntent() {
//    }

    @Test
    fun onReceive() {
    }

    @Test
    fun protocolReceivingData() {
    }

    @Test
    fun setActionsForReceiver() {
    }

    @Test
    fun createTimeoutTimer() {
    }

    @Test
    fun timerAlertSetup() {
    }

    @Test
    fun setAlarmTimer() {
    }

    //========================================================================

    @Test
    fun doTransactionSendActionsForTransactionsReceiveAllTransactionActions(){}

    @Test
    fun transactionFailedActionNotReceivedBeforeTimeout(){}

    @Test
    fun transactionSuccededCompletedAllActionsBeforeTimeout(){}

    @Test
    //! TX1
    fun timeoutReached(){}

    @Test
    //! T8
    fun transactionSucceededBeforeTimeout(){

    }

    @Test
    fun receiveFirstTransactionSendStartActionReceiveStartAction(){}

    @Test
    fun receiveLastTransactionSendLastActionReceiveLastAction(){}

    @Test
    fun receiveValueFromSenderAtReceiverCheck(){}

    @Test
    fun sendActionAndExtraReceiveAndUnpack(){}

}