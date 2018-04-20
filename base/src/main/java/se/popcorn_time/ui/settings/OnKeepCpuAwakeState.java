package se.popcorn_time.ui.settings;

import android.support.annotation.NonNull;

import se.popcorn_time.mvp.Presenter;
import se.popcorn_time.mvp.ViewState;

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
