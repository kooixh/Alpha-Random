package com.alpha.kooi.alpharandom;

import android.app.DialogFragment;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Kooi on 29/06/2017.
 */

public class SaveAlert extends DialogFragment {


    private Button dismissAlert;


    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        final View view = inflater.inflate(R.layout.save_alert,null);

        dismissAlert = view.findViewById(R.id.closeSaveAlert);


        dismissAlert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //close the dialog and recreate the activity

                SaveAlert.this.dismiss();
            }
        });



        return view;
    }
}
