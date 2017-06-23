package random;

import android.os.Parcelable;
import android.widget.TextView;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Kooi on 22/06/2017.
 */

public interface Random<E> extends Serializable{

    void random(List<E> list, TextView tv);

}
