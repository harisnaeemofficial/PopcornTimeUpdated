package se.popcorn_time.base;

import android.support.annotation.NonNull;

import se.popcorn_time.model.config.IConfigUseCase;
import se.popcorn_time.model.messaging.IMessagingUseCase;
import se.popcorn_time.model.settings.ISettingsUseCase;

public interface IPopcornApplication {

    @NonNull
    IMessagingUseCase getMessagingUseCase();

    @NonNull
    IConfigUseCase getConfigUseCase();

    @NonNull
    ISettingsUseCase getSettingsUseCase();
}
