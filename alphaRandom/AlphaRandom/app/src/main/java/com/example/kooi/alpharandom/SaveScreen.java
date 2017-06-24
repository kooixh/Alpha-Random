package com.example.kooi.alpharandom;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class SaveScreen extends AppCompatActivity {



    private ListView listOfSaves;
    private ArrayAdapter<String> arrayAdapter;
    private List<String> testList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_screen);

        testList.add("1");
        testList.add("2");
        testList.add("3");

        listOfSaves = (ListView) findViewById(R.id.listOfSaves);
        arrayAdapter = new ArrayAdapter<String>(this,R.layout.save_row,testList);


        for(String s:testList){

            listOfSaves.setAdapter(arrayAdapter);
        }
    }
}
