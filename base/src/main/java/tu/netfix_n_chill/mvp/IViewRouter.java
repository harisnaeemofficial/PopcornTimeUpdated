package tu.netfix_n_chill.mvp;

import android.support.annotation.NonNull;

public interface IViewRouter {

    boolean onShowView(@NonNull Class<?> view, Object... args);
}
