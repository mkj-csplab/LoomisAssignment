package net.csplab.adroid.kotlin.loomisbroadcastreceivercomponent.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import net.csplab.adroid.kotlin.loomisbroadcastreceivercomponent.databinding.ActivityPayTerminalJavaBinding;
import net.csplab.adroid.kotlin.loomisbroadcastreceivercomponent.receivers.PartyOneReceiver;
import net.csplab.adroid.kotlin.loomisbroadcastreceivercomponent.utility.UtilityActions;

public class PayTerminalJavaActivity extends AppCompatActivity {
    public final String TAG = PayTerminalJavaActivity.this.getClass().getSimpleName();
    // val payProvider1: PayProviderReceiver
    private ActivityPayTerminalJavaBinding bind = null;

    private PartyOneReceiver mPartyOneReceiver = null; // Specialised
    private boolean mReadyToBroadCast = true;
    // private PayProviderReceiver mTstBroadcaster = null;

    private IntentFilter mIntentFilterActionsPartyOne = null;
    //private IntentFilter mIntentFilterActionsBankOfBank = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivityPayTerminalJavaBinding.inflate(getLayoutInflater());

        setContentView(bind.getRoot());

        String providerName = "";
        List<String> actionStrings = UtilityActions.setupActionsForProviderPartyOne();// UtilityActions.collectActionsForProvider..();
        Intent intent = new Intent();
        try {
            //How to set action string
            mPartyOneReceiver = new PartyOneReceiver(
                    providerName,
                    UtilityActions.ActionSets.INSTANCE.getActionsExtraPartyOne(),
                    15000L);
        } catch (Exception e) {
            e.printStackTrace();
        }

        mIntentFilterActionsPartyOne = new IntentFilter();//prepareProvider(providerName, intent, sActions) //! CHK: Timeoutlength L

        mReadyToBroadCast = true;
        //! Set Broadcaster type with associated Actions
        //mTstBroadcaster = PartyOneProviderReceiver(providerName, UtilityActions.ActionSets.actionsExtraPartyOne, 15000L)

        TextView tvInfo1 = bind.tvInfo1;
        TextView tvInfo2 = bind.tvInfo2;
        Button btStartPartyOneProvider = bind.btStartPartyOneProvider;

        btStartPartyOneProvider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //! Get ACTION Set for Provider

                // Arbitrary number of action steps ... Ho do we put them in
                // Register ACTIONS for special PayProvider BroadcastReceiver
                mIntentFilterActionsPartyOne = new IntentFilter();

                addActionsToFilter(mIntentFilterActionsPartyOne);

                mReadyToBroadCast = true;

                //! Set Broadcaster type with associated Actions
                registerReceiver(mPartyOneReceiver, mIntentFilterActionsPartyOne);

                if (intent.getAction() == null) {
                    Log.d(TAG, "PayTerminalJavaActivity:onCreate: Intent Action is null");
                } else {
                    //intent.getIdentifier(); // chk: ?@
                    int inttContentDescr = intent.describeContents();
                    String inttAction = intent.getAction();
                    Log.d(TAG, "PayTerminalJavaActivity:onCreate: Intent is NOT null: Content Description $inttContentDescr :intent action $inttAction");
                }
                //! Ready ACtion and data extreas to send broadcast:
                // Load Each intent with action and "Extras" data
                for (int i = 0; i < actionStrings.size(); ++i) {
                    intent.setAction(actionStrings.get(i)); // Dont set action each time onReceive is signalled
                    Log.d(TAG, "PayTerminalJavaActivity:onCreate: Intent => $intent.action :: $sAction.get(i) :: Action.size: $sAction.size() ");
                    String ia = intent.getAction();
                    Log.d(TAG, "PayTerminalJavaActivity:onCreate: Current Action: $ia");
                    if (ia == actionStrings.get(0)) {
                        intent.putExtra("KEY1", "ID4325");
                    } else if (ia == actionStrings.get(1)) {
                        intent.putExtra("KEY2_1", "Amount");
                        intent.putExtra("KEY2_2", "Balance");
                        intent.putExtra("KEY2_3", "Idnum");
                        intent.putExtra("KEY2_4", "IBAN");
                    } else if (ia == actionStrings.get(2)) {
                        intent.putExtra("KEY5", "BYE!");
                    }
                    //! @Chk> Could wrap this in Timer. Schedule to test time limits
                    sendOrderedBroadcast(intent, null); // send intent to PartyOneProvider, Timing
                }
            }
        });
    }

    // Chk@:Add same function to MainActivity
    public void addActionsToFilter(IntentFilter iFilter) {
        List<String> actionList = UtilityActions.setupActionsForProviderPartyOne();
        for (String s : actionList) {
            iFilter.addAction(s);
        }
    }
}