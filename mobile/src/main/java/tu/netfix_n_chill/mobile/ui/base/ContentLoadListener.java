package tu.netfix_n_chill.mobile.ui.base;

public interface ContentLoadListener {

    public void showLoading();

    public void showError();

    public void retryLoad();
}