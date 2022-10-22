package net.csplab.adroid.kotlin.loomisbroadcastreceivercomponent.utility

import android.content.IntentFilter
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

//! testing utility function, considered as part of setup of receivers
@RunWith(JUnit4::class)
class UtilityActionsTest {
    lateinit var tProviderActionsPartyOne: List<String>
    lateinit var tProviderActionsBankOfBank: List<String>

    @Before
    fun setUp() {

        tProviderActionsPartyOne = UtilityActions.setupActionsForProviderPartyOne()
        tProviderActionsBankOfBank = UtilityActions.setupActionsForProviderBankOfBank()

        val aepo = UtilityActions.ActionSets.actionsExtraPartyOne
        println("aepo: $aepo")
    }

    @Test
    //@SmallTest
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun setupActionsForProviderPartyOne() {
        tProviderActionsPartyOne
    }

    @Test
    fun setupActionsForProviderBankOfBank() {
    }

    @Test
    fun `test provider actions added to list of ActionExtra objects for Bank Of Bank`() {
        var pa = tProviderActionsPartyOne
        assertEquals( 4,pa.size)
    }

    @Test
    fun `test provider actions added to list of ActionExtra objects for Party One`() {
        var pa = tProviderActionsBankOfBank
        assertEquals( 5,pa.size)
    }

    //! Analysed tests: Chk: Check for integration type tests in the list
    //! AND CHECK, Divide between tests for receiver and test for utility
    //===========

    //! descr: Test create utility set: Unit Test
    //createActions
    fun  `create actions for providers receiver`(){

    }

    //!  Create a set of extras to be broadcast ()
    //createExtrasData()
    fun `create extras data prepared for broadcast`(){

    }

    //! prepareProvider()
    fun `prepare provider with actions and extras data`(){

    }

    //! @Integration test? UNit and integration test for the flow from send prepare to receive set@
    fun setActionsForReceiver(intenFilter: IntentFilter){

    }

    //! chk: initTransaction if actually the button Click to start the broadcaster.
    //! Was that the inteded prupose, ie @@ "cardDetected"
    fun initTransaction(){

    }
    //=======
}