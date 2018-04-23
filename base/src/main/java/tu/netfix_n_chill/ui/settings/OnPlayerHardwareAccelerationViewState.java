package tu.netfix_n_chill.ui.settings;

import android.support.annotation.NonNull;

import tu.netfix_n_chill.mvp.Presenter;
import tu.netfix_n_chill.mvp.ViewState;

public final class OnPlayerHardwareAccelerationViewState extends ViewState<ISettingsView> {

    private Integer playerHardwareAcceleration;

    public OnPlayerHardwareAccelerationViewState(@NonNull Presenter<ISettingsView> presenter, @NonNull Integer playerHardwareAcceleration) {
        super(presenter);
        this.playerHardwareAcceleration = playerHardwareAcceleration;
    }

    public void apply(@NonNull Integer playerHardwareAcceleration) {
        this.playerHardwareAcceleration = playerHardwareAcceleration;
        apply();
    }

    @Override
    public void apply(@NonNull ISettingsView view) {
        view.onPlayerHardwareAcceleration(playerHardwareAcceleration);
    }
}
