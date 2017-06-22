package random;

import java.util.List;

/**
 * Created by Kooi on 22/06/2017.
 */

public class TrueRandom<E> implements Random<E> {


    public E random(List<E> list){

        //random a number within range 0 to size of list-1
        int index =(int) Math.floor(Math.random() * list.size());

        return list.get(index);
    }


}
