package tu.netfix_n_chill.ui.settings;

import android.support.annotation.NonNull;

import tu.netfix_n_chill.mvp.Presenter;
import tu.netfix_n_chill.mvp.ViewState;

public final class OnAboutSiteViewState extends ViewState<ISettingsView> {

    private String siteUrl;

    public OnAboutSiteViewState(@NonNull Presenter<ISettingsView> presenter, @NonNull String siteUrl) {
        super(presenter);
        this.siteUrl = siteUrl;
    }

    public void apply(@NonNull String siteUrl) {
        this.siteUrl = siteUrl;
        apply();
    }

    @Override
    public void apply(@NonNull ISettingsView view) {
        view.onAboutSite(siteUrl);
    }
}
