package net.csplab.adroid.kotlin.loomisbroadcastreceivercomponent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import net.csplab.adroid.kotlin.loomisbroadcastreceivercomponent.databinding.ActivityMainJavaBinding;

public class MainActivityJava extends AppCompatActivity {

    private ActivityMainJavaBinding bind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivityMainJavaBinding.inflate(getLayoutInflater());

        setContentView(bind.getRoot());

        TextView tvTxt = bind.textView2;
        Button bt = bind.button;


    }
}