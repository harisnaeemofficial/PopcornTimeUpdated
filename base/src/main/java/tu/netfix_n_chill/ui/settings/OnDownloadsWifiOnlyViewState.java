package tu.netfix_n_chill.ui.settings;

import android.support.annotation.NonNull;

import tu.netfix_n_chill.mvp.Presenter;
import tu.netfix_n_chill.mvp.ViewState;

public final class OnDownloadsWifiOnlyViewState extends ViewState<ISettingsView> {

    private Boolean downloadsWifiOnly;

    public OnDownloadsWifiOnlyViewState(@NonNull Presenter<ISettingsView> presenter, @NonNull Boolean downloadsWifiOnly) {
        super(presenter);
        this.downloadsWifiOnly = downloadsWifiOnly;
    }

    public void apply(@NonNull Boolean downloadsWifiOnly) {
        this.downloadsWifiOnly = downloadsWifiOnly;
        apply();
    }

    @Override
    public void apply(@NonNull ISettingsView view) {
        view.onDownloadsWifiOnly(downloadsWifiOnly);
    }
}
