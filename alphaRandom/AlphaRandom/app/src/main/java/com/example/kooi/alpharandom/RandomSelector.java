package com.example.kooi.alpharandom;

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

import java.util.ArrayList;
import java.util.List;

import random.EliminationRandom;
import random.Random;
import random.TrueRandom;

/**
 * Created by Kooi on 22/06/2017.
 */

public class RandomSelector extends DialogFragment {


    //Random class
    Random random;

    //List passed
    private List<String> listOfOptions;

    Button trueRandom;
    Button elimRandom;
    Button cancel;


    public RandomSelector(){

    }

    public static RandomSelector newInstance(List list){
        RandomSelector rs = new RandomSelector();
        rs.setList(list);

        return rs;

    }

    private void setList(List list){
        listOfOptions = list;
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

        //user picked true random
        trueRandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                random = new TrueRandom();
                Intent i = new Intent(getActivity(),ResultScreen.class);
                i.putExtra("random",random);
                i.putStringArrayListExtra("listOfOptions",(ArrayList<String>) listOfOptions);
                //to check which activity launched the result screen
                i.putExtra("launchedBy","selector");
                startActivity(i);
            }
        });

        //user picked elimination random
        elimRandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                random = new EliminationRandom();
                Intent i = new Intent(getActivity(),EliminationScreen.class);
                i.putExtra("random",random);
                i.putStringArrayListExtra("listOfOptions",(ArrayList<String>) listOfOptions);
                startActivity(i);
            }
        });

        return view;

    }
}
