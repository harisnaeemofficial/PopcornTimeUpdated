package tu.netfix_n_chill.ui.content;

import android.support.annotation.NonNull;

import tu.netfix_n_chill.mvp.IPresenter;

import tu.netfix_n_chill.model.content.IContentProvider;

public interface IContentProviderPresenter extends IPresenter<IContentProviderView> {

    void setContentProvider(@NonNull IContentProvider contentProvider);
}
