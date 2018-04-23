package tu.netfix_n_chill.ui.content;

import android.support.annotation.NonNull;

import tu.netfix_n_chill.mvp.Presenter;
import tu.netfix_n_chill.mvp.ViewState;

import tu.netfix_n_chill.model.content.IContentProvider;

public final class ContentProviderViewState extends ViewState<IContentProviderView> {

    private final IContentProvider[] contentProviders;
    private IContentProvider contentProvider;

    public ContentProviderViewState(@NonNull Presenter<IContentProviderView> presenter,
                                    @NonNull IContentProvider[] contentProviders,
                                    @NonNull IContentProvider contentProvider) {
        super(presenter);
        this.contentProviders = contentProviders;
        this.contentProvider = contentProvider;
    }

    public void apply(@NonNull IContentProvider contentProvider) {
        this.contentProvider = contentProvider;
        apply();
    }

    @Override
    public void apply(@NonNull IContentProviderView view) {
        view.onContentProvider(contentProviders, contentProvider);
    }
}
