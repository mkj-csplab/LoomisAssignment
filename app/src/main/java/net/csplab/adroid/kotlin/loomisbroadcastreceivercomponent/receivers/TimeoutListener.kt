package net.csplab.adroid.kotlin.loomisbroadcastreceivercomponent.receivers

fun interface TimeoutListener {
    //! Update Time Text, when timeout is happening
    fun updateTimer(info: String)
}