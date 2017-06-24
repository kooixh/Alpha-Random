package com.alpha.kooi.alpharandom;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.alpha.kooi.configuration.Configuration;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class StartScreen extends AppCompatActivity {


    //reference to the button
    private Button start;
    private Button about;
    private Button saveButton;


    final static String CONFIG_FILE_NAME = "config.data";
    private Configuration config;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);

        //read from config
            try{
                FileInputStream fis = openFileInput(CONFIG_FILE_NAME);
                ObjectInputStream objectInputStream = new ObjectInputStream(fis);
                config = (Configuration) objectInputStream.readObject();
                objectInputStream.close();

            }catch (IOException ioe){
                Log.d("Error","Error reading configuration");

            }catch (ClassNotFoundException cnfe){
                Log.d("Error","Error reading configuration");
            }

            //Create a new config on the first run
            if(config == null){
                config = new Configuration();
            }

        //reference to button
        start = (Button) findViewById(R.id.start);
        about = (Button) findViewById(R.id.about);
        saveButton = (Button) findViewById(R.id.saveButton);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent initialSetup = new Intent(StartScreen.this,InitialSetup.class);
                initialSetup.putExtra("config",config);
                StartScreen.this.startActivity(initialSetup);
            }
        });

        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AboutDialog ad = new AboutDialog();
                ad.show(getFragmentManager(),"About");
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(StartScreen.this,SaveScreen.class);
                i.putExtra("config",config);
                startActivity(i);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(CONFIG_FILE_NAME));
            out.writeObject(config);
            out.close();

        }catch (IOException fnfe){

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
}
