package com.example.kooi.alpharandom;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

import random.Random;

public class ResultScreen extends AppCompatActivity {

    //random class passed
    Random<String> random;

    //List Passed
    List<String> listOfOptions;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_screen);

        Intent i = getIntent();
        Bundle b = i.getExtras();

        random = (Random<String>) b.get("random");
        listOfOptions = b.getStringArrayList("listOfOptions");
        
    }
}
