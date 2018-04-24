package tu.netfix_n_chill.mobile.ui;

import android.support.annotation.NonNull;

import tu.netfix_n_chill.model.updater.Update;
import tu.netfix_n_chill.ui.updater.IUpdateView;

public abstract class UpdateActivity extends LocaleActivity {

    @Override
    public boolean onShowView(@NonNull Class<?> view, Object... args) {
        if (IUpdateView.class == view) {
            return UpdateDialog.show(getSupportFragmentManager(), "update_dialog", (Update) args[0]);
        }
        return super.onShowView(view, args);
    }
}
