package tu.netfix_n_chill.ui.details;

import android.support.annotation.Nullable;

import tu.netfix_n_chill.base.model.video.info.Episode;
import tu.netfix_n_chill.base.model.video.info.Season;
import tu.netfix_n_chill.base.model.video.info.TvShowsInfo;

public interface IDetailsTvShowView extends IDetailsView<TvShowsInfo> {

    void onSeasons(@Nullable Season[] seasons, int position);

    void onEpisodes(@Nullable Episode[] episodes, int position);
}
