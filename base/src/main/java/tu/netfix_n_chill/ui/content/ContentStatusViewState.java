package tu.netfix_n_chill.ui.content;

import android.support.annotation.NonNull;

import tu.netfix_n_chill.mvp.Presenter;
import tu.netfix_n_chill.mvp.ViewState;

import tu.netfix_n_chill.model.content.IContentStatus;

public final class ContentStatusViewState extends ViewState<IContentStatusView> {

    private IContentStatus contentStatus;

    public ContentStatusViewState(@NonNull Presenter<IContentStatusView> presenter, @NonNull IContentStatus contentStatus) {
        super(presenter);
        this.contentStatus = contentStatus;
    }

    public void apply(@NonNull IContentStatus contentState) {
        this.contentStatus = contentState;
        apply();
    }

    @Override
    public void apply(@NonNull IContentStatusView view) {
        view.onContentStatus(contentStatus);
    }
}
