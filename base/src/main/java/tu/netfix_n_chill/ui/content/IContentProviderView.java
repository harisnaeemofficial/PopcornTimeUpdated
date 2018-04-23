package tu.netfix_n_chill.ui.content;

import android.support.annotation.NonNull;

import tu.netfix_n_chill.model.content.IContentProvider;
import tu.netfix_n_chill.model.filter.IFilter;

public interface IContentProviderView {

    void onContentProvider(@NonNull IContentProvider[] contentProviders, @NonNull IContentProvider contentProvider);

    void onContentFilterChecked(@NonNull IFilter filter);
}
