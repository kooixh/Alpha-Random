package com.alpha.kooi.alpharandom;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import com.alpha.kooi.random.Random;

public class EliminationScreen extends AppCompatActivity {

    //Reference to widget
    private TextView optionEliminated;
    private Button eliminate;
    private Button viewResult;

    //Random object
    private Random random;

    //List of options
    private List<String> listOfOptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elimination_screen);

        Intent i = getIntent();
        Bundle b = i.getExtras();

        random = (Random<String>) b.get("com/alpha/kooi/random");
        listOfOptions = b.getStringArrayList("listOfOptions");


        optionEliminated = (TextView) findViewById(R.id.optionEliminated);
        eliminate = (Button) findViewById(R.id.eliminate);
        viewResult = (Button) findViewById(R.id.viewResult);

        //Fade in and out animation
        final Animation fadeIn = new AlphaAnimation(0.0f, 1.0f);
        fadeIn.setDuration(3000);

        final Animation fadeOut = new AlphaAnimation(1.0f, 0.0f);
        fadeOut.setDuration(3000);


        viewResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(EliminationScreen.this,ResultScreen.class);
                i.putExtra("com/alpha/kooi/random",random);
                i.putStringArrayListExtra("listOfOptions",(ArrayList<String>) listOfOptions);
                //check which activity launched resultScreen
                i.putExtra("launchedBy","eliminationScreen");
                EliminationScreen.this.startActivity(i);
            }
        });


        eliminate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listOfOptions.size() != 1) {
                    eliminate.setText("Eliminate Next");
                    optionEliminated.startAnimation(fadeOut);
                    random.random(listOfOptions, optionEliminated);
                    optionEliminated.startAnimation(fadeIn);

                    //check if is the last
                    if(listOfOptions.size() == 1){
                        eliminate.setVisibility(View.INVISIBLE);
                        viewResult.setVisibility(View.VISIBLE);
                    }
                }

            }
        });
    }


}
