package tu.netfix_n_chill.ui.share;

import android.support.annotation.NonNull;

import tu.netfix_n_chill.model.share.IShareData;

public interface IShareView {

    void onShowShareData(@NonNull IShareData shareData);
}
