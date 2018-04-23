package tu.netfix_n_chill.ui.settings;

import android.support.annotation.NonNull;

import tu.netfix_n_chill.mvp.Presenter;
import tu.netfix_n_chill.mvp.ViewState;

public final class OnLanguageViewState extends ViewState<ISettingsView> {

    private String language;

    public OnLanguageViewState(@NonNull Presenter<ISettingsView> presenter, @NonNull String language) {
        super(presenter);
        this.language = language;
    }

    public void onLanguage(@NonNull String language) {
        this.language = language;
        apply();
    }

    @Override
    public void apply(@NonNull ISettingsView view) {
        view.onLanguage(language);
    }
}