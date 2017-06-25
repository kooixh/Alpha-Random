package com.alpha.kooi.alpharandom;

import android.app.DialogFragment;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.alpha.kooi.configuration.Configuration;

import java.util.ArrayList;
import java.util.List;

import com.alpha.kooi.random.EliminationRandom;
import com.alpha.kooi.random.Random;
import com.alpha.kooi.random.TrueRandom;

/**
 * Created by Kooi on 22/06/2017.
 */

public class RandomSelector extends DialogFragment {


    //Random class
    private Random random;

    //List passed
    private List<String> listOfOptions;
    private Configuration config;


    private String userTrouble;

    private Button trueRandom;
    private Button elimRandom;
    private Button cancel;


    public RandomSelector(){

    }

    public static RandomSelector newInstance(List list,Configuration config,String userTrouble){
        RandomSelector rs = new RandomSelector();
        rs.setFields(config,list,userTrouble);
        return rs;

    }

    private void setFields(Configuration config,List list,String userTrouble){
        this.config = config;
        this.userTrouble = userTrouble;
        this.listOfOptions = list;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        final View view = inflater.inflate(R.layout.random_selector,null);

        //instantiate button
        trueRandom = view.findViewById(R.id.trueRandom);
        elimRandom = view.findViewById(R.id.elimRandom);
        cancel = view.findViewById(R.id.cancel);
        
        //close the dialog
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RandomSelector.this.dismiss();
            }
        });

        //user picked true com.alpha.kooi.random
        trueRandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                random = new TrueRandom();
                Intent i = new Intent(getActivity(),ResultScreen.class);
                i.putExtra("com/alpha/kooi/random",random);
                i.putStringArrayListExtra("listOfOptions",(ArrayList<String>) listOfOptions);
                i.putExtra("config",config);
                i.putExtra("userTrouble",userTrouble);
                //to check which activity launched the result screen
                i.putExtra("launchedBy","selector");
                startActivity(i);
            }
        });

        //user picked elimination com.alpha.kooi.random
        elimRandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                random = new EliminationRandom();
                Intent i = new Intent(getActivity(),EliminationScreen.class);
                i.putExtra("com/alpha/kooi/random",random);
                i.putExtra("config",config);
                i.putExtra("userTrouble",userTrouble);
                i.putStringArrayListExtra("listOfOptions",(ArrayList<String>) listOfOptions);
                startActivity(i);
            }
        });

        return view;

    }
}
