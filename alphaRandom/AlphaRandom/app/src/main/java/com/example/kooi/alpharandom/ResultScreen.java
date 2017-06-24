package com.example.kooi.alpharandom;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.kooi.configuration.Configuration;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

import random.Random;

public class ResultScreen extends AppCompatActivity {

    //random class passed
    private Random<String> random;

    //List Passed
    private List<String> listOfOptions;

    //Config file passed
    private Configuration config;

    //widgets
    private Button randomButton;
    private TextView randomResult;
    private Button reset;
    private Button saveThis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_screen);

        randomButton = (Button) findViewById(R.id.randomButton);
        randomResult = (TextView) findViewById(R.id.randomResult);
        reset = (Button) findViewById(R.id.reset);
        saveThis = (Button) findViewById(R.id.saveThis);

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ResultScreen.this, StartScreen.class);
                startActivity(i);

            }
        });


        saveThis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SaveDialog sd = SaveDialog.newInstance(config,listOfOptions);
                sd.show(getFragmentManager(),"Save Session");
            }
        });


        Intent i = getIntent();
        Bundle b = i.getExtras();

        //get extras
        random = (Random<String>) b.get("random");
        listOfOptions = b.getStringArrayList("listOfOptions");
        config = (Configuration) b.get("config");

        String launchedBy = b.getString("launchedBy");

        if(config == null){
            Log.d("Check null","Config is null");
        }else {
            Log.d("Check null","COnfig is not null");
        }

        //if activity is launched by selector dialog
        if(launchedBy.equals("selector")) {

            randomButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    random.random(listOfOptions, randomResult);
                    randomResult.setVisibility(View.VISIBLE);
                    randomButton.setVisibility(View.INVISIBLE);
                    reset.setVisibility(View.VISIBLE);
                }
            });
        }//if is launched by EliminationScreen
        else if(launchedBy.equals("eliminationScreen")){
            randomButton.setVisibility(View.INVISIBLE);
            randomResult.setVisibility(View.VISIBLE);
            randomResult.setText(listOfOptions.get(0));
            reset.setVisibility(View.VISIBLE);



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
