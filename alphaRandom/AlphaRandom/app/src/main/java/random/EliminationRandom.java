package random;

import java.util.List;

/**
 * Created by Kooi on 22/06/2017.
 */

public class EliminationRandom<E> implements Random<E> {


    public E random(List<E> list){


        //while there are more than one element
        while(list.size() != 1){
            int index =(int) Math.floor(Math.random() * list.size()-1);
            list.remove(index);

        }

        //return the remaining element
        return list.get(0);
    }
}
