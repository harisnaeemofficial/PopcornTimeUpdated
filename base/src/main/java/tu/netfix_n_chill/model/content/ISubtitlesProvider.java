package tu.netfix_n_chill.model.content;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import tu.netfix_n_chill.base.model.WatchInfo;
import tu.netfix_n_chill.base.model.video.info.Episode;
import tu.netfix_n_chill.base.model.video.info.Season;
import tu.netfix_n_chill.base.model.video.info.Torrent;
import tu.netfix_n_chill.base.model.video.info.VideoInfo;
import tu.netfix_n_chill.model.subtitles.Subtitles;

public interface ISubtitlesProvider {

    @NonNull
    Observable<Map.Entry<String, List<Subtitles>>[]> getSubtitles(@NonNull VideoInfo videoInfo,
                                                                @Nullable Season season,
                                                                @Nullable Episode episode,
                                                                @Nullable Torrent torrent);

    @NonNull
    Observable<Map.Entry<String, List<Subtitles>>[]> getSubtitles(@NonNull WatchInfo watchInfo);
}
