package com.example.kooi.alpharandom;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class OptionsScreen extends AppCompatActivity {

    //fields from last activity
    private int totalOptions;
    private String userTrouble;

    ScrollView optionScroll;
    LinearLayout optionsLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options_screen);

        //intent that started activity
        Intent i = getIntent();
        //get the extras (total number of options)
        Bundle b = i.getExtras();
        totalOptions = b.getInt("totalOptions");
        userTrouble = b.getString("userTrouble");

        //reference to scroll view
        optionScroll = (ScrollView) findViewById(R.id.optionScroll);
        optionsLayout = (LinearLayout) findViewById(R.id.optionsLayout);


        //add a editText view for each options
        for(int j =0;j<totalOptions;j++){
            EditText et = new EditText(this);
            et.setText("Option #"+(j+1));
            et.setSingleLine(true);
            optionsLayout.addView(et);
        }


    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
