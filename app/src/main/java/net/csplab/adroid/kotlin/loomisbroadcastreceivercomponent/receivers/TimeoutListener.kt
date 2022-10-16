package net.csplab.adroid.kotlin.loomisbroadcastreceivercomponent.receivers

class TimeoutContainer {
    fun interface TimeoutListener {
        //! Update Time Text, when timeout is happening
        fun updateTimer(info: String)
    }
}
