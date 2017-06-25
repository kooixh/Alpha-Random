package com.alpha.kooi.alpharandom;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.NumberPicker;
import java.lang.reflect.*;
import android.widget.EditText;

import com.alpha.kooi.configuration.Configuration;

public class InitialSetup extends AppCompatActivity {


    //reference to widgets
    private NumberPicker optionsPicker;
    private FloatingActionButton next;
    private EditText trouble;

    //fields
    private String userTrouble;
    private int totalOptions;
    private Configuration config;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial_setup);

        optionsPicker = (NumberPicker) findViewById(R.id.optionsPicker);
        next = (FloatingActionButton) findViewById(R.id.next);
        trouble = (EditText) findViewById(R.id.trouble);

        Intent i = getIntent();
        Bundle b = i.getExtras();

        config = (Configuration) b.get("config");




        optionsPicker.setMinValue(2);
        optionsPicker.setMaxValue(99);
        setNumberPickerTextColor(optionsPicker,Color.BLACK);


        //Action Listener to detect EditText changes and show button if not empty
        trouble.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.toString().trim().length()!=0){
                    next.show();
                }else{
                    next.hide();
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {


            }
        });

        //Action Listener for floating button
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userTrouble = trouble.getText().toString();
                totalOptions = optionsPicker.getValue();

                Intent i = new Intent(InitialSetup.this,OptionsScreen.class);
                i.putExtra("totalOptions",totalOptions);
                i.putExtra("userTrouble",userTrouble);
                i.putExtra("config",config);

                InitialSetup.this.startActivity(i);
            }
        });


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    private static boolean setNumberPickerTextColor(NumberPicker numberPicker, int color)
    {
        final int count = numberPicker.getChildCount();
        for(int i = 0; i < count; i++){
            View child = numberPicker.getChildAt(i);
            if(child instanceof EditText){
                try{
                    Field selectorWheelPaintField = numberPicker.getClass()
                            .getDeclaredField("mSelectorWheelPaint");
                    selectorWheelPaintField.setAccessible(true);
                    ((Paint)selectorWheelPaintField.get(numberPicker)).setColor(color);
                    ((EditText)child).setTextColor(color);
                    numberPicker.invalidate();
                    return true;
                }
                catch(NoSuchFieldException e){
                    Log.w("setNumberTextColor", e);
                }
                catch(IllegalAccessException e){
                    Log.w("", e);
                }
                catch(IllegalArgumentException e){
                    Log.w("", e);
                }
            }
        }
        return false;
    }
}
