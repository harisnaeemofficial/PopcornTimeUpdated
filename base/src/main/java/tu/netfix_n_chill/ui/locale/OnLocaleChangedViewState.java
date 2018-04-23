package tu.netfix_n_chill.ui.locale;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import tu.netfix_n_chill.mvp.Presenter;
import tu.netfix_n_chill.mvp.ViewState;

public final class OnLocaleChangedViewState extends ViewState<ILocaleView> {

    private String language;

    public OnLocaleChangedViewState(@NonNull Presenter<ILocaleView> presenter, @Nullable String language) {
        super(presenter);
        this.language = language;
    }

    public OnLocaleChangedViewState setLanguage(@Nullable String language) {
        this.language = language;
        return this;
    }

    @Override
    public void apply(@NonNull ILocaleView view) {
        view.onLocaleChanged(language);
    }
}
