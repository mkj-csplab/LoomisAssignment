package net.csplab.adroid.kotlin.loomisbroadcastreceivercomponent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import net.csplab.adroid.kotlin.loomisbroadcastreceivercomponent.databinding.ActivityReceiverTestBinding

class ReceiverTestActivity : AppCompatActivity() {

    private var TAG = ReceiverTestActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var bind = ActivityReceiverTestBinding.inflate(layoutInflater)

        setContentView(bind.root)
        val actionBar: ActionBar? = supportActionBar
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true) // Disable the button
            actionBar.setDisplayHomeAsUpEnabled(true) // Remove the left caret
            actionBar.setDisplayShowHomeEnabled(true) // Remove the icon
        }

        var tvShowReceivedData  = bind.tvShowReceivedData
        var btOk                = bind.btOk

        btOk.setOnClickListener {
            Toast.makeText(this, "OK: Receiver" , Toast.LENGTH_LONG).show()
            tvShowReceivedData.text = "Notice!"
        }

        val extraInfo = intent.getStringExtra("KEY_NAME")
        tvShowReceivedData.text = "KeyName: " + extraInfo

        Log.d(TAG, "onCreate: ")
    }
}