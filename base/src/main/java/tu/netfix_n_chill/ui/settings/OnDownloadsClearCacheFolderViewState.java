package tu.netfix_n_chill.ui.settings;

import android.support.annotation.NonNull;

import tu.netfix_n_chill.mvp.Presenter;
import tu.netfix_n_chill.mvp.ViewState;

public final class OnDownloadsClearCacheFolderViewState extends ViewState<ISettingsView> {

    private Boolean downloadsClearCacheFolder;

    public OnDownloadsClearCacheFolderViewState(@NonNull Presenter<ISettingsView> presenter, @NonNull Boolean downloadsClearCacheFolder) {
        super(presenter);
        this.downloadsClearCacheFolder = downloadsClearCacheFolder;
    }

    public void apply(@NonNull Boolean downloadsClearCacheFolder) {
        this.downloadsClearCacheFolder = downloadsClearCacheFolder;
        apply();
    }

    @Override
    public void apply(@NonNull ISettingsView view) {
        view.onDownloadsClearCacheFolder(downloadsClearCacheFolder);
    }
}
