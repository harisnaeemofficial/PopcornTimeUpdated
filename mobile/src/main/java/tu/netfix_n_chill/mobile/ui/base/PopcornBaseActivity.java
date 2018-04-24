package tu.netfix_n_chill.mobile.ui.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import tu.netfix_n_chill.mvp.IViewRouter;

import tu.netfix_n_chill.mobile.PopcornApplication;
import tu.netfix_n_chill.mobile.R;
import tu.netfix_n_chill.mobile.ui.UpdateDialog;
import tu.netfix_n_chill.mobile.ui.locale.LocaleActivity;
import tu.netfix_n_chill.model.messaging.IMessagingUseCase;
import tu.netfix_n_chill.model.share.IShareUseCase;
import tu.netfix_n_chill.model.updater.Update;
import tu.netfix_n_chill.ui.updater.IUpdateView;

public abstract class PopcornBaseActivity extends LocaleActivity implements IViewRouter {

    private Toolbar toolbar;
    private TextView title;
    private FrameLayout content;
    private ImageView logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        title = (TextView) toolbar.findViewById(R.id.title);
        content = (FrameLayout) findViewById(R.id.content);
        logo = (ImageView) findViewById(R.id.logo);

        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        title.setVisibility(View.GONE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (getApplication() instanceof PopcornApplication) {
            ((PopcornApplication) getApplication()).setActiveViewRouter(PopcornBaseActivity.this);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (getApplication() instanceof PopcornApplication) {
            ((PopcornApplication) getApplication()).setActiveViewRouter(null);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public Toolbar getPopcornToolbar() {
        return toolbar;
    }

    public TextView getPopcornTitle() {
        return title;
    }

    public ImageView getPopcornLogoView() {
        return logo;
    }

    public View setPopcornContentView(@LayoutRes int resource) {
        return setPopcornContentView(LayoutInflater.from(PopcornBaseActivity.this).inflate(resource, null, false));
    }

    public View setPopcornContentView(@NonNull View view) {
        content.addView(view);
        return view;
    }

    @NonNull
    public IShareUseCase getShareUseCase() {
        return ((PopcornApplication) getApplication()).getShareUseCase();
    }

    @NonNull
    public IMessagingUseCase getFirebaseMessagingCase() {
        return ((PopcornApplication) getApplication()).getMessagingUseCase();
    }

    @Override
    public boolean onShowView(@NonNull Class<?> view, Object... args) {
        if (IUpdateView.class == view) {
            return UpdateDialog.show(getSupportFragmentManager(), "update_dialog", (Update) args[0]);
        } else {
            return ((IViewRouter) getApplication()).onShowView(view, args);
        }
    }
}