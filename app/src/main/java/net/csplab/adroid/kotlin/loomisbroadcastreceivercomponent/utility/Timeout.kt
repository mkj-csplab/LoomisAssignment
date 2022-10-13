package net.csplab.adroid.kotlin.loomisbroadcastreceivercomponent.utility

import java.util.*

class Timeout {
    lateinit var mTimeOut: Timer

//    myTimer = new Timer();
//        myTimer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                // If you want to modify a view in your Activity
//                MyActivity.this.runOnUiThread(new Runnable()
//                public void run(){
//                    tv.append("Hello World");
//                }
//       );
//            }
//        }, 1000, 1000); // initial delay 1 second, interval 1 second

    fun timeout(timeout: Long){
        mTimeOut = Timer();
        Timer().schedule(object: TimerTask() {
            override fun run() {
                //MainActivity.
            }
        },timeout)
    }


}