package tu.netfix_n_chill.model.messaging;

public interface IMessagingNotificationData extends IMessagingData {

    String getTitle();

    String getMessage();

    Action getAction();
}
