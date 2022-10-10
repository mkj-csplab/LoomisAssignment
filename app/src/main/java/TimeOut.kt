import android.util.Log
import net.csplab.adroid.kotlin.loomisbroadcastreceivercomponent.MainActivity
import java.util.*

class TimeOut {
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

    fun timeout(){
        mTimeOut = Timer();
        Timer().schedule(object: TimerTask() {
            override fun run() {
                //MainActivity.
            }
        },1000)
    }
}