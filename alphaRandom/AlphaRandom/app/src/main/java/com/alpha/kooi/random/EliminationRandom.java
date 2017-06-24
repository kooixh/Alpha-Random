package com.alpha.kooi.random;

import android.widget.TextView;

import java.util.List;

/**
 * Created by Kooi on 22/06/2017.
 */

public class EliminationRandom<E> implements Random<E> {



    public void random(List<E> list,TextView tv){

        //get a com.alpha.kooi.random index
        int index =(int) Math.floor(Math.random() * list.size());
        tv.setText(list.get(index).toString()); //set the text
        //remove the item at the index
        list.remove(index);

    }
}
