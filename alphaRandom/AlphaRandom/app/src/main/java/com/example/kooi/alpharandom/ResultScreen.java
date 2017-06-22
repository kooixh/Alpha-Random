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

        Intent i = getIntent();
        Bundle b = i.getExtras();

        random = (Random<String>) b.get("random");
        listOfOptions = b.getStringArrayList("listOfOptions");
        randomButton = (Button) findViewById(R.id.randomButton);
        randomResult = (TextView) findViewById(R.id.randomResult);
        reset = (Button) findViewById(R.id.reset);

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ResultScreen.this,StartScreen.class);
                startActivity(i);

            }
        });

        randomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                randomResult.setText(random.random(listOfOptions));
                randomResult.setVisibility(View.VISIBLE);
                randomButton.setVisibility(View.INVISIBLE);
                reset.setVisibility(View.VISIBLE);
            }
        });


        
    }
}
