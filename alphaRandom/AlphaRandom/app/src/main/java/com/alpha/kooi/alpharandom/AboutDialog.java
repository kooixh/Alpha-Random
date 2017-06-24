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

public class AboutDialog extends DialogFragment {

    //widgets
    private TextView version;
    private Button close;


    private String versionName = BuildConfig.VERSION_NAME;



    public AboutDialog(){
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState){

        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        final View view = inflater.inflate(R.layout.about_dialog,null);


        version = view.findViewById(R.id.version);
        close = view.findViewById(R.id.dismiss);

        version.setText("Version: "+versionName);

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AboutDialog.this.dismiss();
            }
        });



        return view;

    }
}
