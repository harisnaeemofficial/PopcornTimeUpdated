package tu.netfix_n_chill.model.content;

import java.util.List;

import tu.netfix_n_chill.base.model.video.info.VideoInfo;

public interface IContentStatus {

    boolean isLoading();

    Throwable getError();

    List<VideoInfo> getList();
}
