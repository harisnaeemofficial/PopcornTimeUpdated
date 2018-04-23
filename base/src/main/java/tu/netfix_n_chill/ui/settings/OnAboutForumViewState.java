package tu.netfix_n_chill.ui.settings;

import android.support.annotation.NonNull;

import tu.netfix_n_chill.mvp.Presenter;
import tu.netfix_n_chill.mvp.ViewState;

public final class OnAboutForumViewState extends ViewState<ISettingsView> {

    private String forumUrl;

    public OnAboutForumViewState(@NonNull Presenter<ISettingsView> presenter, @NonNull String forumUrl) {
        super(presenter);
        this.forumUrl = forumUrl;
    }

    public void apply(@NonNull String forumUrl) {
        this.forumUrl = forumUrl;
        apply();
    }

    @Override
    public void apply(@NonNull ISettingsView view) {
        view.onAboutForum(forumUrl);
    }
}
