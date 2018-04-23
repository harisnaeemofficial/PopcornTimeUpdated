package tu.netfix_n_chill.ui.settings;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import tu.netfix_n_chill.mvp.Presenter;
import tu.netfix_n_chill.mvp.ViewState;

import java.io.File;

public final class OnDownloadsCacheFolderViewState extends ViewState<ISettingsView> {

    private File downloadsCacheFolder;

    public OnDownloadsCacheFolderViewState(@NonNull Presenter<ISettingsView> presenter, @Nullable File downloadsCacheFolder) {
        super(presenter);
        this.downloadsCacheFolder = downloadsCacheFolder;
    }

    public void apply(@Nullable File downloadsCacheFolder) {
        this.downloadsCacheFolder = downloadsCacheFolder;
        apply();
    }

    @Override
    public void apply(@NonNull ISettingsView view) {
        view.onDownloadsCacheFolder(downloadsCacheFolder);
    }
}
