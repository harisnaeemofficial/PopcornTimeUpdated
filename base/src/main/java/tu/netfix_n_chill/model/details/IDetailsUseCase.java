package tu.netfix_n_chill.model.details;

import android.support.annotation.NonNull;

import java.util.List;
import java.util.Map;

import tu.netfix_n_chill.base.model.video.info.Episode;
import tu.netfix_n_chill.base.model.video.info.Season;
import tu.netfix_n_chill.base.model.video.info.Torrent;
import tu.netfix_n_chill.base.model.video.info.VideoInfo;
import tu.netfix_n_chill.model.ObservableChoiceProperty;
import tu.netfix_n_chill.model.ObservableProperty;
import tu.netfix_n_chill.model.subtitles.Subtitles;

public interface IDetailsUseCase {

    @NonNull
    ObservableProperty<VideoInfo> getVideoInfoProperty();

    @NonNull
    ObservableChoiceProperty<Season> getSeasonChoiceProperty();

    @NonNull
    ObservableChoiceProperty<Episode> getEpisodeChoiceProperty();

    @NonNull
    ObservableChoiceProperty<Map.Entry<String, List<Torrent>>> getDubbingChoiceProperty();

    @NonNull
    ObservableChoiceProperty<Torrent> getTorrentChoiceProperty();

    @NonNull
    ObservableChoiceProperty<Map.Entry<String, List<Subtitles>>> getLangSubtitlesChoiceProperty();

    @NonNull
    ObservableChoiceProperty<Subtitles> getSubtitlesChoiceProperty();

    @NonNull
    ObservableProperty<Subtitles> getCustomSubtitlesProperty();
}
