package com.example.kooi.alpharandom;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import random.*;

import java.util.ArrayList;
import java.util.List;

public class OptionsScreen extends AppCompatActivity {

    //fields from last activity
    private int totalOptions;
    private String userTrouble;

    //List
    List<String> listOfOptions = new ArrayList<String>();


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
            et.setId(j);
            et.setText("Option #"+(j+1));
            et.setSingleLine(true);
            optionsLayout.addView(et);
        }

        //Create a new button after all options entered
        Button nextButton = new Button(this);
        nextButton.setText("Proceed");
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                for(int j =0;j<totalOptions;j++){
                    //get the editText
                    EditText et = (EditText) findViewById(j);
                    //get the value of the editText
                    String s = et.getText().toString();
                    //add to the list
                    listOfOptions.add(s);
                }

                RandomSelector rs = RandomSelector.newInstance(listOfOptions);
                rs.show(getFragmentManager(),"Choose your Random");

            }
        });


        optionsLayout.addView(nextButton);


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
