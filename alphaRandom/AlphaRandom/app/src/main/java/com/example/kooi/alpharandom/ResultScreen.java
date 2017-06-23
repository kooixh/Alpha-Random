package com.example.kooi.alpharandom;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import random.Random;

public class ResultScreen extends AppCompatActivity {

    //random class passed
    Random<String> random;

    //List Passed
    List<String> listOfOptions;

    //widgets
    Button randomButton;
    TextView randomResult;
    Button reset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_screen);

        randomButton = (Button) findViewById(R.id.randomButton);
        randomResult = (TextView) findViewById(R.id.randomResult);
        reset = (Button) findViewById(R.id.reset);

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ResultScreen.this, StartScreen.class);
                startActivity(i);

            }
        });


        Intent i = getIntent();
        Bundle b = i.getExtras();

        //get extras
        random = (Random<String>) b.get("random");
        listOfOptions = b.getStringArrayList("listOfOptions");

        String launchedBy = b.getString("launchedBy");

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
}
