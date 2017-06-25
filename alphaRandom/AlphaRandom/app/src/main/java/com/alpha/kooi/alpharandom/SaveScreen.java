package com.alpha.kooi.alpharandom;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.alpha.kooi.configuration.Configuration;
import com.alpha.kooi.configuration.Session;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

public class SaveScreen extends AppCompatActivity {


    private Configuration config;


    private ListView listOfSaves;
    private ArrayAdapter<Session> arrayAdapter;
    private List<Session> listOfSavedSession;

    private Button clearAll;
    private Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_screen);

        clearAll = (Button) findViewById(R.id.clearAll);
        back = (Button) findViewById(R.id.back);

        clearAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //clear the config file
                config.clear();

                //write file
                try{
                    FileOutputStream fos = openFileOutput(StartScreen.CONFIG_FILE_NAME, Context.MODE_PRIVATE);
                    ObjectOutputStream out = new ObjectOutputStream(fos);
                    out.writeObject(config);
                    out.close();
                    Log.d("Save status","Successfully saved file");

                }catch (IOException ioe){

                    Log.d("Save status","Unsuccessfully saved file");
                    ioe.printStackTrace();
                }


                Intent i = new Intent(SaveScreen.this,StartScreen.class);
                startActivity(i);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SaveScreen.this,StartScreen.class);
                startActivity(i);

            }
        });

        Intent i = getIntent();
        Bundle b = i.getExtras();

        config =(Configuration) b.get("config");



        listOfSaves = (ListView) findViewById(R.id.listOfSaves);
        listOfSavedSession = config.getListOfSessions();



        arrayAdapter = new ArrayAdapter<Session>(this,R.layout.save_row,config.getListOfSessions());

        listOfSaves.setAdapter(arrayAdapter);




        listOfSaves.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                SaveActionDialog sad = SaveActionDialog.newInstance(config, (Session) listOfSaves.getItemAtPosition(i),arrayAdapter);
                sad.show(getFragmentManager(), "Save Action Dialog");

                listOfSaves.refreshDrawableState();


            }
        });


    }
}
