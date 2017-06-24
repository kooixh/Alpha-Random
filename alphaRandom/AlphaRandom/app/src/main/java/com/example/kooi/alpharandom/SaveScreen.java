package com.example.kooi.alpharandom;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.kooi.configuration.Configuration;
import com.example.kooi.configuration.Session;

import java.util.ArrayList;
import java.util.List;

public class SaveScreen extends AppCompatActivity {


    private Configuration config;


    private ListView listOfSaves;
    private ArrayAdapter<Session> arrayAdapter;
    private List<Session> listOfSavedSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_screen);

        Intent i = getIntent();
        Bundle b = i.getExtras();

        config =(Configuration) b.get("config");



        listOfSaves = (ListView) findViewById(R.id.listOfSaves);
        listOfSavedSession = config.getListOfSessions();
        arrayAdapter = new ArrayAdapter<Session>(this,R.layout.save_row,listOfSavedSession);

        listOfSaves.setAdapter(arrayAdapter);


    }
}
