package random;

import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Kooi on 22/06/2017.
 */

public interface Random<E> extends Serializable{

    E random(List<E> list);

}
