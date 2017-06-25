package com.alpha.kooi.alpharandom;

import android.app.DialogFragment;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.alpha.kooi.configuration.Configuration;
import com.alpha.kooi.configuration.Session;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

/**
 * Created by Kooi on 24/06/2017.
 */

public class SaveDialog extends DialogFragment {


    //config file and list to be saved
    private Configuration config;
    private List<String> listOfOptions;
    private String userTrouble;

    //widgets
    private EditText nameOfList;
    private Button saveSession;
    private Button cancelSave;


    public static SaveDialog newInstance(Configuration config,List listOfOptions,String userTrouble){
        SaveDialog sd = new SaveDialog();
        sd.setFields(config,listOfOptions,userTrouble);
        return sd;
    }


    private void setFields(Configuration config,List listOfOptions,String userTrouble){
        this.config = config;
        this.listOfOptions = listOfOptions;
        this.userTrouble = userTrouble;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        final View view = inflater.inflate(R.layout.save_dialog,null);

        nameOfList = view.findViewById(R.id.nameOfList);
        saveSession = view.findViewById(R.id.saveSession);
        cancelSave = view.findViewById(R.id.cancelSave);


        cancelSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SaveDialog.this.dismiss();
            }
        });

        saveSession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Session s = new Session(nameOfList.getText().toString(),listOfOptions,userTrouble);
                config.addSession(s);

                try{
                    FileOutputStream fos = getActivity().openFileOutput(StartScreen.CONFIG_FILE_NAME,Context.MODE_PRIVATE);
                    ObjectOutputStream out = new ObjectOutputStream(fos);
                    out.writeObject(config);
                    out.close();
                    Log.d("Save status","Successfully saved file");

                }catch (IOException ioe){

                    Log.d("Save status","Unsuccessfully saved file");
                    ioe.printStackTrace();
                }

                SaveDialog.this.dismiss();
            }
        });
        return view;
    }
}
