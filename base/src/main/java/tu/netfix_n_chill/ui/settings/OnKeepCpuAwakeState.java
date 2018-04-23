package tu.netfix_n_chill.ui.settings;

import android.support.annotation.NonNull;

import tu.netfix_n_chill.mvp.Presenter;
import tu.netfix_n_chill.mvp.ViewState;

public final class OnKeepCpuAwakeState extends ViewState<ISettingsView> {

    private Boolean keepCpuAwake;

    public OnKeepCpuAwakeState(@NonNull Presenter<ISettingsView> presenter, @NonNull Boolean keepCpuAwake) {
        super(presenter);
        this.keepCpuAwake = keepCpuAwake;
    }

    public void apply(@NonNull Boolean downloadsClearCacheFolder) {
        this.keepCpuAwake = downloadsClearCacheFolder;
        apply();
    }

    @Override
    public void apply(@NonNull ISettingsView view) {
        view.onKeepCPUawake(keepCpuAwake);

    }
}
