package net.csplab.adroid.kotlin.loomisbroadcastreceivercomponent.views;

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

import net.csplab.adroid.kotlin.loomisbroadcastreceivercomponent.receivers.BankOfBankReceiver;
import net.csplab.adroid.kotlin.loomisbroadcastreceivercomponent.receivers.PartyOneProvideReceiver;
import net.csplab.adroid.kotlin.loomisbroadcastreceivercomponent.receivers.PayProviderReceiver;
import net.csplab.adroid.kotlin.loomisbroadcastreceivercomponent.utility.UtilityActions;

public class MainActivityJava extends AppCompatActivity {
    public final String TAG = MainActivityJava.this.getClass().getSimpleName();
    // val payProvider1: PayProviderReceiver
    private ActivityMainJavaBinding bind = null;

    private BankOfBankReceiver mPayReceiver = null; // Specialised
    private Boolean mReadyToBroadCast = new Boolean(false);
    private PayProviderReceiver mTstBroadcaster = null;

    private IntentFilter mIntentFilterActions = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivityMainJavaBinding.inflate(getLayoutInflater());

        setContentView(bind.getRoot());

        try {
            mPayReceiver = new BankOfBankReceiver(UtilityActions.Util.collectActionsForProviderBankOfBank() ,"BankOfBank");
        }catch (Exception e){
            e.printStackTrace();
        }


        TextView tvInfo1 = bind.tvInfo1;
        TextView tvInfo2 = bind.tvInfo2;
        Button btStartPayProvider1 = bind.btStartPayProvider1;

        Intent intent = new Intent();
        IntentFilter itFilter = new IntentFilter();
        //itFilter.addAction();
        // ConsumerPartyPayProviderBroadcastReceiver
        //btStartPayProvider1.setOnClickListener {
            //! Get ACTION Set for Provider
            List<String> sAction = UtilityActions.Util.collectActionsForProviderBankOfBank();// UtilityActions.collectActionsForProviderBankOfBank();
            // Arbitrary number of action steps ... Ho do we put them in
            // Register ACTIONS for special PayProvider BroadcastReceiver
            mIntentFilterActions = new IntentFilter();
            mIntentFilterActions.addAction("net.csplab.adroid.kotlin.loomisbroadcastreceivercomponent.PAYID1_START");
            //for (action String: sAction) {
                //Log.d(TAG, "PayProvider2: Action Strings Read: $s Sizelist ${sAction.size} \n")
                //    addAction(s)
                //}
            //}

            mReadyToBroadCast = true;
            //! Set Broadcaster type with associated Actions
            mTstBroadcaster = new PartyOneProvideReceiver(sAction, "PartyOneProvider");
            registerReceiver(mTstBroadcaster, mIntentFilterActions);
            intent = new Intent();

            //! Load Each intent with action and "Extras" data
            for(int i=0; i < sAction.size(); ++i)  {
                intent.setAction(sAction.get(i));
                Log.d(TAG, "Intent => $intent.action :: $sAction.get(i) :: Action.size: $sAction.size() ");
                String ia = intent.getAction();
                Log.d(TAG, "Current Action: $ia");
                if (ia == sAction.get(0)) {
                    intent.putExtra("KEY1", "ID4325");
                } else if (ia == sAction.get(1)) {
                    intent.putExtra("KEY2_1", "Amount");
                    intent.putExtra("KEY2_2", "Balance");
                    intent.putExtra("KEY2_3", "Idnum");
                    intent.putExtra("KEY2_4", "SWIFT");
                    intent.putExtra("KEY2_5", "IBAN");
                } else if (ia == sAction.get(2)) {
                    intent.putExtra("KEY3", "BYE!");
                }
                //! @Chk> Could wrap this in Tinmer. Schedule to test tiime limits
                sendBroadcast(intent); // send intent to ConsumerPartyProvider, Timing
            }
            //intent.action = "net.csplab.adroid.kotlin.loomisbroadcastreceivercomponent.PAYID1_START"
        }

    // Chk@:Add same function to MainActivity
    public void addActionsToFilter(IntentFilter iFilter) {
        List<String> actionList = UtilityActions.Util.collectActionsForProviderBankOfBank();
        for (String s: actionList){
            iFilter.addAction(s);
        }
    }
}