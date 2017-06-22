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
    List<String> listOfOptions;

    Button trueRandom;
    Button elimRandom;
    Button cancel;


    public RandomSelector(List listOfOptions){
        this.listOfOptions = listOfOptions;
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
                openActivity();
            }
        });

        //user picked elimination random
        elimRandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                random = new EliminationRandom();
                openActivity();
            }
        });

        return view;

    }

    //method to open new Activity
    private void openActivity(){
        Intent i = new Intent(getActivity(),ResultScreen.class);
        i.putExtra("random",random);
        i.putStringArrayListExtra("listOfOptions",(ArrayList<String>) listOfOptions);
        startActivity(i);
    }
}