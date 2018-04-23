package tu.netfix_n_chill.ui.details;

import tu.netfix_n_chill.mvp.IPresenter;

import tu.netfix_n_chill.base.model.video.info.VideoInfo;

public interface IDetailsPresenter<T extends VideoInfo, V extends IDetailsView<T>> extends IPresenter<V> {
}
