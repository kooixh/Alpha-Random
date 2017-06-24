package com.alpha.kooi.random;

import android.widget.TextView;

import java.util.List;

/**
 * Created by Kooi on 22/06/2017.
 */

public class TrueRandom<E> implements Random<E> {


    public void random(List<E> list, TextView tv){

        //com.alpha.kooi.random a number within range 0 to size of list-1
        int index =(int) Math.floor(Math.random() * list.size());
        tv.setText(list.get(index).toString());
    }


}
