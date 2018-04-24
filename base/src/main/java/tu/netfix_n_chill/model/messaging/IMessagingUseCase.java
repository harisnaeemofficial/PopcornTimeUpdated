package tu.netfix_n_chill.model.messaging;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import tu.netfix_n_chill.model.messaging.IMessagingData;
import tu.netfix_n_chill.model.messaging.IMessagingNotificationData;


public interface IMessagingUseCase {

    interface Observer {

        void onShowMessagingDialog(@NonNull IMessagingData data);
    }

    interface NotificationObserver {

        void onShowMessagingNotification(@NonNull IMessagingNotificationData data);
    }

    IMessagingData getData();

    void subscribe(@NonNull Observer observer);

    void unsubscribe(@NonNull Observer observer);

    void subscribe(@NonNull NotificationObserver observer);

    void show(@Nullable IMessagingData data);
}
