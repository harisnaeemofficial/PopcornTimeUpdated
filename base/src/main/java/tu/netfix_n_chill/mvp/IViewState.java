package tu.netfix_n_chill.mvp;

import android.support.annotation.NonNull;

public interface IViewState<T> {

    void apply(@NonNull T view);
}