package com.example.kooi.alpharandom;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StartScreen extends AppCompatActivity {


    //reference to the button
    private Button start;
    private Button about;



    PackageInfo pInfo;
    String versionName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);


        try{
            pInfo = getPackageManager().getPackageInfo(getPackageName(),0);
        }catch (PackageManager.NameNotFoundException nnfe){

        }

        versionName = pInfo.versionName;


        //reference to button
        start = (Button) findViewById(R.id.start);
        about = (Button) findViewById(R.id.about);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent initialSetup = new Intent(StartScreen.this,InitialSetup.class);
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
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
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
