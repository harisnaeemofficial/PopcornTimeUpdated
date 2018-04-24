package tu.netfix_n_chill.base;

import android.support.annotation.NonNull;

import tu.netfix_n_chill.model.config.IConfigUseCase;
import tu.netfix_n_chill.model.messaging.IMessagingUseCase;
import tu.netfix_n_chill.model.settings.ISettingsUseCase;

public interface IPopcornApplication {

    @NonNull
    IMessagingUseCase getMessagingUseCase();

    @NonNull
    IConfigUseCase getConfigUseCase();

    @NonNull
    ISettingsUseCase getSettingsUseCase();
}
