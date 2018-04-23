package tu.netfix_n_chill.ui.settings;

import android.support.annotation.NonNull;

import tu.netfix_n_chill.mvp.Presenter;
import tu.netfix_n_chill.mvp.ViewState;

public final class OnSubtitlesFontSizeViewState extends ViewState<ISettingsView> {

    private Float subtitlesFontSize;

    public OnSubtitlesFontSizeViewState(@NonNull Presenter<ISettingsView> presenter, @NonNull Float subtitlesFontSize) {
        super(presenter);
        this.subtitlesFontSize = subtitlesFontSize;
    }

    public void apply(@NonNull Float subtitlesFontSize) {
        this.subtitlesFontSize = subtitlesFontSize;
        apply();
    }

    @Override
    public void apply(@NonNull ISettingsView view) {
        view.onSubtitlesFontSize(subtitlesFontSize);
    }
}
