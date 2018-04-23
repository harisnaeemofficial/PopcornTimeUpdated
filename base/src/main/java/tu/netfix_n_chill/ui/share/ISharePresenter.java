package tu.netfix_n_chill.ui.share;

import tu.netfix_n_chill.mvp.IPresenter;

public interface ISharePresenter extends IPresenter<IShareView> {

    void share();

    void cancel();
}
