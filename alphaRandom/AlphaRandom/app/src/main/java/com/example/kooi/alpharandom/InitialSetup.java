package com.example.kooi.alpharandom;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.NumberPicker;
import java.lang.reflect.*;
import android.widget.EditText;

public class InitialSetup extends AppCompatActivity {


    //reference to number picker
    NumberPicker optionsPicker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial_setup);

        optionsPicker = (NumberPicker) findViewById(R.id.optionsPicker);
        optionsPicker.setMinValue(1);
        optionsPicker.setMaxValue(99);
        setNumberPickerTextColor(optionsPicker,Color.BLACK);


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

    public static boolean setNumberPickerTextColor(NumberPicker numberPicker, int color)
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
