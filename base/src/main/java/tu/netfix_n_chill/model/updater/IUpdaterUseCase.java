package tu.netfix_n_chill.model.updater;

import android.support.annotation.NonNull;

import io.reactivex.Observable;

public interface IUpdaterUseCase {

    @NonNull
    Observable<Update> getUpdate(@NonNull String[] urls);
}