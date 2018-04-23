package tu.netfix_n_chill.ui.details;

import android.support.annotation.NonNull;

import tu.netfix_n_chill.base.model.video.info.MoviesInfo;
import tu.netfix_n_chill.model.content.IContentUseCase;
import tu.netfix_n_chill.model.details.IDetailsUseCase;

public final class DetailsMoviePresenter extends DetailsPresenter<MoviesInfo, IDetailsMovieView> implements IDetailsMoviePresenter {

    public DetailsMoviePresenter(@NonNull IContentUseCase contentUseCase, @NonNull IDetailsUseCase detailsUseCase) {
        super(contentUseCase, detailsUseCase);
    }
}
