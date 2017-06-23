package random;

import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by Kooi on 22/06/2017.
 */

public class TrueRandom<E> implements Random<E> {


    public void random(List<E> list, TextView tv){

        //random a number within range 0 to size of list-1
        int index =(int) Math.floor(Math.random() * list.size());
        tv.setText(list.get(index).toString());
    }


}
