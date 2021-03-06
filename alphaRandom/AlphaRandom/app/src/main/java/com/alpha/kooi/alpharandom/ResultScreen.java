package com.alpha.kooi.alpharandom;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.TextView;

import com.alpha.kooi.configuration.Configuration;

import java.util.List;

import com.alpha.kooi.random.Random;

public class ResultScreen extends AppCompatActivity {

    //com.alpha.kooi.random class passed
    private Random<String> random;

    //List Passed
    private List<String> listOfOptions;

    private String userTrouble;

    //Config file passed
    private Configuration config;

    //widgets
    private Button randomButton;
    private TextView randomResult;
    private TextView theUserTrouble;
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
        theUserTrouble = (TextView) findViewById(R.id.theUserTrouble);

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
        random = (Random<String>) b.get("com/alpha/kooi/random");
        listOfOptions = b.getStringArrayList("listOfOptions");
        config = (Configuration) b.get("config");
        userTrouble = b.getString("userTrouble");


        theUserTrouble.setText(userTrouble + " is:");

        String launchedBy = b.getString("launchedBy");

        //Fade in and out animation
        final Animation fadeIn = new AlphaAnimation(0.0f, 1.0f);
        fadeIn.setDuration(3000);

        final Animation fadeOut = new AlphaAnimation(1.0f, 0.0f);
        fadeOut.setDuration(3000);

        //if activity is launched by selector dialog
        if(launchedBy.equals("selector")) {

            randomButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    //animation
                    randomResult.startAnimation(fadeOut);
                    random.random(listOfOptions, randomResult);
                    randomResult.startAnimation(fadeIn);


                    randomResult.setVisibility(View.VISIBLE);
                    randomButton.setVisibility(View.INVISIBLE);
                    reset.setVisibility(View.VISIBLE);
                }
            });



            saveThis.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    SaveDialog sd = SaveDialog.newInstance(config,listOfOptions,userTrouble);
                    sd.show(getFragmentManager(),"Save Session");
                }
            });
        }//if is launched by EliminationScreen
        else if(launchedBy.equals("eliminationScreen")){



            randomButton.setVisibility(View.INVISIBLE);
            randomResult.setVisibility(View.VISIBLE);

            //animation
            randomResult.startAnimation(fadeOut);
            randomResult.setText(listOfOptions.get(0));
            randomResult.startAnimation(fadeIn);



            reset.setVisibility(View.VISIBLE);


            final List<String> listOfOptionsOriginal = b.getStringArrayList("listOfOptionsOriginal");


            saveThis.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    SaveDialog sd = SaveDialog.newInstance(config,listOfOptionsOriginal,userTrouble);
                    sd.show(getFragmentManager(),"Save Session");
                }
            });




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
