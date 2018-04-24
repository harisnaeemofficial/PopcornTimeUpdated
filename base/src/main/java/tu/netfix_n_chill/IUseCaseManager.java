package tu.netfix_n_chill;

import android.support.annotation.NonNull;

import tu.netfix_n_chill.model.content.IContentUseCase;
import tu.netfix_n_chill.model.details.IDetailsUseCase;

public interface IUseCaseManager {

    @NonNull
    IContentUseCase getContentUseCase();

    @NonNull
    IDetailsUseCase getDetailsUseCase();
}
