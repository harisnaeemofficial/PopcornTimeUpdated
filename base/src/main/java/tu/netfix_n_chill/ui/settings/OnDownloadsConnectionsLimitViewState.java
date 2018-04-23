package tu.netfix_n_chill.ui.settings;

import android.support.annotation.NonNull;

import tu.netfix_n_chill.mvp.Presenter;
import tu.netfix_n_chill.mvp.ViewState;

public final class OnDownloadsConnectionsLimitViewState extends ViewState<ISettingsView> {

    private Integer downloadsConnectionsLimit;

    public OnDownloadsConnectionsLimitViewState(@NonNull Presenter<ISettingsView> presenter, @NonNull Integer downloadsConnectionsLimit) {
        super(presenter);
        this.downloadsConnectionsLimit = downloadsConnectionsLimit;
    }

    public void apply(@NonNull Integer downloadsConnectionsLimit) {
        this.downloadsConnectionsLimit = downloadsConnectionsLimit;
        apply();
    }

    @Override
    public void apply(@NonNull ISettingsView view) {
        view.onDownloadsConnectionsLimit(downloadsConnectionsLimit);
    }
}
