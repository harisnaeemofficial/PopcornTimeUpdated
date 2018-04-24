package tu.netfix_n_chill.model.config;

import android.support.annotation.NonNull;

public interface IConfigLocalRepository {

    @NonNull
    Config getConfig();

    void setConfig(@NonNull Config config);
}
