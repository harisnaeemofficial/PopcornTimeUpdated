package tu.netfix_n_chill.ui.content;

import android.support.annotation.Nullable;

import tu.netfix_n_chill.mvp.IPresenter;

public interface IContentStatusPresenter extends IPresenter<IContentStatusView> {

    void setKeywords(@Nullable String keywords);

    void getContent(boolean reset);
}
