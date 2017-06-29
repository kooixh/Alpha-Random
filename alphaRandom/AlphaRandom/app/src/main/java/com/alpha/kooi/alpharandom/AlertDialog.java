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
import android.widget.TextView;

/**
 * Created by Kooi on 24/06/2017.
 */

public class AlertDialog extends DialogFragment {


    private String message;


    private Button dismissAlert;
    private TextView alertMessage;

    public static AlertDialog newInstance(String message){
        AlertDialog ad = new AlertDialog();
        ad.setMessage(message);
        return ad;

    }

    private void setMessage(String message){
        this.message = message;
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        final View view = inflater.inflate(R.layout.alert_dialog,null);

        dismissAlert = view.findViewById(R.id.dismissAlert);
        alertMessage = view.findViewById(R.id.alertMessage);

        alertMessage.setText(message);


        dismissAlert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //close the dialog and recreate the activity

                AlertDialog.this.dismiss();
            }
        });



        return view;
    }
}
