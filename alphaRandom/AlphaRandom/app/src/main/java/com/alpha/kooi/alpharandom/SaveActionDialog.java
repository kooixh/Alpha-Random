package com.alpha.kooi.alpharandom;

import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.alpha.kooi.configuration.Configuration;
import com.alpha.kooi.configuration.Session;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

/**
 * Created by Kooi on 24/06/2017.
 */

public class SaveActionDialog extends DialogFragment {

    private Configuration config;
    private Session sessionReferenced;



    private Button useButton;
    private Button deleteSaveButton;
    private Button dismissSaveAction;
    private TextView sessionName;



    public static SaveActionDialog newInstance(Configuration config,Session sessionReferenced){

        SaveActionDialog sad = new SaveActionDialog();
        sad.setFields(config,sessionReferenced);
        return sad;
    }


    private void setFields(Configuration config,Session sessionReferenced){
        //this.listOfOptions = listOfOptions;
        this.config = config;
        this.sessionReferenced = sessionReferenced;

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        final View view = inflater.inflate(R.layout.save_action_dialog,null);


        useButton = view.findViewById(R.id.useButton);
        deleteSaveButton = view.findViewById(R.id.deleteSaveButton);
        dismissSaveAction = view.findViewById(R.id.dismissSaveAction);
        sessionName = view.findViewById(R.id.sessionName);


        sessionName.setText(sessionReferenced.getName());


        useButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RandomSelector rs = RandomSelector.newInstance(sessionReferenced.getLisOfOptions(),config);
                rs.show(getFragmentManager(),"Random Selector");
            }
        });

        deleteSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               config.getListOfSessions().remove(sessionReferenced);
                try{
                    FileOutputStream fos = getActivity().openFileOutput(StartScreen.CONFIG_FILE_NAME, Context.MODE_PRIVATE);
                    ObjectOutputStream out = new ObjectOutputStream(fos);
                    out.writeObject(config);
                    out.close();
                    Log.d("Save status","Successfully saved file");
                }catch (IOException ioe){

                    Log.d("Save status","Unsuccessfully saved file");
                    ioe.printStackTrace();
                }

                //Dismiss the dialog
                SaveActionDialog.this.dismiss();

                //start a new activity
                Intent i = new Intent(getActivity(),StartScreen.class);
                startActivity(i);

            }
        });

        dismissSaveAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SaveActionDialog.this.dismiss();
            }
        });



        return view;
    }
}
