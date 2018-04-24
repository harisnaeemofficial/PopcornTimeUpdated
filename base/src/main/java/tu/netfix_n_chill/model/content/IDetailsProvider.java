package tu.netfix_n_chill.model.content;

import android.support.annotation.NonNull;

import io.reactivex.Observable;
import tu.netfix_n_chill.base.model.video.info.VideoInfo;

public interface IDetailsProvider {

    <T extends VideoInfo> boolean isDetailsExists(T videoInfo);

    @NonNull
    Observable<? extends VideoInfo> getDetails(VideoInfo videoInfo);
}
