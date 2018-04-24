package tu.netfix_n_chill.mobile.model.content;

import android.support.annotation.NonNull;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.functions.Function;
import tu.netfix_n_chill.base.model.video.info.Season;
import tu.netfix_n_chill.base.model.video.info.TvShowsInfo;
import tu.netfix_n_chill.base.model.video.info.VideoInfo;
import tu.netfix_n_chill.model.content.IDetailsProvider;
import tu.netfix_n_chill.model.filter.IFilter;

public final class TvShowsSeasonsProvider implements IDetailsProvider {

    private final ContentRepository repository;
    private final IFilter qualityFilter;

    public TvShowsSeasonsProvider(@NonNull ContentRepository repository, @NonNull IFilter qualityFilter) {
        this.repository = repository;
        this.qualityFilter = qualityFilter;
    }

    @Override
    public <T extends VideoInfo> boolean isDetailsExists(T videoInfo) {
        return false;
    }

    @NonNull
    @Override
    public Observable<? extends VideoInfo> getDetails(VideoInfo videoInfo) {
        if (videoInfo instanceof TvShowsInfo) {
            return repository.getSeasons(videoInfo.getImdb(), qualityFilter).map(new SeasonRxMapper((TvShowsInfo) videoInfo));
        }
        return Observable.error(new IllegalArgumentException("Wrong video info type"));
    }

    private static final class SeasonRxMapper implements Function<ArrayList<Season>, TvShowsInfo> {

        private final TvShowsInfo info;

        private SeasonRxMapper(@NonNull TvShowsInfo info) {
            this.info = info;
        }

        @Override
        public TvShowsInfo apply(@io.reactivex.annotations.NonNull ArrayList<Season> seasons) throws Exception {
            info.setSeasons(seasons);
            return info;
        }
    }
}
