package tu.netfix_n_chill.ui.share;

import android.support.annotation.NonNull;

import tu.netfix_n_chill.mvp.Presenter;
import tu.netfix_n_chill.mvp.ViewState;

import tu.netfix_n_chill.model.share.IShareData;

public final class OnShowShareDataViewState extends ViewState<IShareView> {

    private IShareData shareData;

    public OnShowShareDataViewState(@NonNull Presenter<IShareView> presenter, @NonNull IShareData shareData) {
        super(presenter);
        this.shareData = shareData;
    }

    public OnShowShareDataViewState setShareData(@NonNull IShareData shareData) {
        this.shareData = shareData;
        return this;
    }

    @Override
    public void apply(@NonNull IShareView view) {
        view.onShowShareData(shareData);
    }
}
