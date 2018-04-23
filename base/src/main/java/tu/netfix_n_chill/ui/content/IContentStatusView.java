package tu.netfix_n_chill.ui.content;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import tu.netfix_n_chill.model.content.IContentStatus;

public interface IContentStatusView {

    void onKeywords(@Nullable String keywords);

    void onContentStatus(@NonNull IContentStatus contentStatus);
}
