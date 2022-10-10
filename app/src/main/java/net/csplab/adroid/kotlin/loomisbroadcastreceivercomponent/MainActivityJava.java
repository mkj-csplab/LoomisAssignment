package net.csplab.adroid.kotlin.loomisbroadcastreceivercomponent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import net.csplab.adroid.kotlin.loomisbroadcastreceivercomponent.databinding.ActivityMainJavaBinding;

import java.util.List;

import receivers.BankOfBankReceiver;
import utility.UtilityActions;

public class MainActivityJava extends AppCompatActivity {
    public final String TAG = MainActivityJava.this.getClass().getSimpleName();
    // val payProvider1: PayProviderReceiver
    private ActivityMainJavaBinding bind = null;
    private BankOfBankReceiver payReceiver = null; // Specialised

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivityMainJavaBinding.inflate(getLayoutInflater());

        setContentView(bind.getRoot());

        payReceiver = new BankOfBankReceiver(UtilityActions.Util.collectActionsForProviderBankOfBank(""));

        TextView tvTxt = bind.textView2;
        Button bt = bind.button;

        Intent intent = new Intent();
        IntentFilter itFilter = new IntentFilter();
        //itFilter.addAction();
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "MainActivity: onClick:");
                registerReceiver(payReceiver,itFilter);
                //sendBroadcast();
            }
        });
    }

    // Chk@:Add same function to MainActivity
    public void addActionsToFilter(IntentFilter iFilter) {
        List<String> actionList = UtilityActions.Util.collectActionsForProviderBankOfBank("");
        for (String s: actionList){
            iFilter.addAction(s);
        }
    }
}