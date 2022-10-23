package net.csplab.adroid.kotlin.loomisbroadcastreceivercomponent.utility

import android.content.IntentFilter
//import android.util.Log
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

    //! T1
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
    //! T2
    fun `test provider actions added to list of ActionExtra objects for Party One`() {
        var pa = tProviderActionsBankOfBank
        //assertEquals( 5,pa.size)
        assertEquals( 3,pa.size)
    }

    //! Analysed tests: Chk: Check for integration type tests in the list
    //! AND CHECK, Divide between tests for receiver and test for utility
    //===========

    //! descr: Test create utility set: Unit Test
    //createActions
    @Test
    //! TD1
    fun  `create actions for providers receiver`(){
        //! Create action
        var actionSet = UtilityActions.ActionSets.actionsExtraPartyOne
        val actionSetSize = actionSet.size
        UtilityActions.prepareAddCustomAction(actionSet, "TEST_ACTION", "PartyOne")
        assertEquals(actionSetSize+1, actionSet.size)
        //Log.d(":TEST", actionSet[actionSet.size-2].action)
        println(":TEST ${actionSet[actionSet.size-2].action}")

        assert(actionSet[actionSet.size-2].action.contains("TEST_ACTION"))
    }

    //!  Create a set of extras to be broadcast ()
    //createExtrasData()
    //! T3
    fun `create extras data prepared for broadcast`(){
        //! create a dataset key,value:
    }

    //! prepareProvider()
    //! T4
    fun `prepare provider with actions and extras data`(){

    }

    //! @Integration test? UNit and integration test for the flow from send prepare to receive set@
    fun setActionsForReceiver(intenFilter: IntentFilter){
    }

    //! chk: initTransaction if actually the button Click to start the broadcaster.
    //! Was that the inteded prupose, ie @@ "cardDetected"
    fun initTransaction(){

    }
}