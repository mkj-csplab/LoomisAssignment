package net.csplab.adroid.kotlin.loomisbroadcastreceivercomponent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import net.csplab.adroid.kotlin.loomisbroadcastreceivercomponent.databinding.ActivityReceiverTestBinding

class ReceiverTestActivity : AppCompatActivity() {

    private var TAG = ReceiverTestActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var bind = ActivityReceiverTestBinding.inflate(layoutInflater)

        setContentView(bind.root)

        var tvShowReceivedData  = bind.tvShowReceivedData
        var btOk                = bind.btOk

        btOk.setOnClickListener {
            Toast.makeText(this, "OK: Receiver" , Toast.LENGTH_LONG).show()
            tvShowReceivedData.text = "Notice!"
        }

        Log.d(TAG, "onCreate: ")

    }
}