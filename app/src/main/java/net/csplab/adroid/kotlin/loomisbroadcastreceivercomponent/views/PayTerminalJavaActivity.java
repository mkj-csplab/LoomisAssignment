package net.csplab.adroid.kotlin.loomisbroadcastreceivercomponent.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;
import net.csplab.adroid.kotlin.loomisbroadcastreceivercomponent.databinding.ActivityPayTerminalJavaBinding;
import net.csplab.adroid.kotlin.loomisbroadcastreceivercomponent.receivers.BankOfBankReceiver;
import net.csplab.adroid.kotlin.loomisbroadcastreceivercomponent.receivers.PartyOneProviderReceiver;
import net.csplab.adroid.kotlin.loomisbroadcastreceivercomponent.receivers.PayProviderReceiver;
import net.csplab.adroid.kotlin.loomisbroadcastreceivercomponent.utility.UtilityActions;

public class PayTerminalJavaActivity extends AppCompatActivity {
    public final String TAG = PayTerminalJavaActivity.this.getClass().getSimpleName();
    // val payProvider1: PayProviderReceiver
    private ActivityPayTerminalJavaBinding bind = null;

    private BankOfBankReceiver mPayReceiver = null; // Specialised
    private boolean mReadyToBroadCast = true;
    // private PayProviderReceiver mTstBroadcaster = null;

    private IntentFilter mIntentFilterActions = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivityPayTerminalJavaBinding.inflate(getLayoutInflater());

        setContentView(bind.getRoot());


        //List<String> actionStrings = UtilityActions.Util.setupActionsForProviderBankOfBank();// UtilityActions.collectActionsForProviderBankOfBank();
        Intent intent = new Intent();
        try {
            //How to set action string
            //mPayReceiver = new BankOfBankReceiver(actionStrings ,"BankOfBank");
        }catch (Exception e){
            e.printStackTrace();
        }

        TextView tvInfo1 = bind.tvInfo1;
        TextView tvInfo2 = bind.tvInfo2;
        Button btStartPayProvider1 = bind.btStartPayProvider1;

        //IntentFilter itFilter = new IntentFilter();
        //itFilter.addAction();
        // ConsumerPartyPayProviderBroadcastReceiver
        //btStartPayProvider1.setOnClickListener {
        //! Get ACTION Set for Provider

        // Arbitrary number of action steps ... Ho do we put them in
        // Register ACTIONS for special PayProvider BroadcastReceiver
        mIntentFilterActions = new IntentFilter();

        mReadyToBroadCast = true;

        //! Set Broadcaster type with associated Actions
        registerReceiver(mPayReceiver, mIntentFilterActions);

        if (intent.getAction()== null) {
            Log.d(TAG, "PayTerminalJavaActivity:onCreate: Intent Action is null");
        } else {
            //intent.getIdentifier(); // chk: ?@
            int inttContentDescr = intent.describeContents();
            String inttAction = intent.getAction();
            Log.d(TAG, "PayTerminalJavaActivity:onCreate: Intent is NOT null: Content Description $inttContentDescr :intent action $inttAction");
        }
        //! Load Each intent with action and "Extras" data
//        for(int i=0; i < actionStrings.size(); ++i)  {
//            intent.setAction(actionStrings.get(i)); // Dont set action each time onReceive is signalled
//            Log.d(TAG, "PayTerminalJavaActivity:onCreate: Intent => $intent.action :: $sAction.get(i) :: Action.size: $sAction.size() ");
//            String ia = intent.getAction();
//            Log.d(TAG, "PayTerminalJavaActivity:onCreate: Current Action: $ia");
//            if (ia == actionStrings.get(0)) {
//                intent.putExtra("KEY1", "ID4325");
//            } else if (ia == actionStrings.get(1)) {
//                intent.putExtra("KEY2_1", "Amount");
//                intent.putExtra("KEY2_2", "Balance");
//                intent.putExtra("KEY2_3", "Idnum");
//                intent.putExtra("KEY2_4", "SWIFT");
//                intent.putExtra("KEY2_5", "IBAN");
//            } else if (ia == actionStrings.get(2)) {
//                intent.putExtra("KEY3", "BYE!");
//            }
//            //! @Chk> Could wrap this in Tinmer. Schedule to test tiime limits
//            sendBroadcast(intent); // send intent to ConsumerPartyProvider, Timing
        }
        //intent.action = "net.csplab.adroid.kotlin.loomisbroadcastreceivercomponent.PAYID1_START"

    // Chk@:Add same function to MainActivity
    public  void addActionsToFilter(IntentFilter iFilter) {
        List<String> actionList = UtilityActions.Util.setupActionsForProviderBankOfBank();
        for (String s: actionList){
            iFilter.addAction(s);
        }
    }
}