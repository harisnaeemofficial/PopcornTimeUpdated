package tu.netfix_n_chill.model.messaging;

import android.os.Parcelable;

public interface IMessagingData {

    interface Action extends Parcelable {

        String name();
    }
}
