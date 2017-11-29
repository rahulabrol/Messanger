package com.messanger.ui.splash;

import android.os.Handler;

import com.messanger.database.LocalDatabaseManager;

/**
 * Created by Rahul Abrol on 11/24/17.
 * <p>
 * Class @{@link SplashPresenterImpl} implement the Splash Presenter to
 * communicate with the view of this presenter.
 * <p>
 * Interface @{@link SplashPresenter} work as a bridge between view and Presenter.
 * <p>
 * Interface @{@link SplashInteractor.OnLoginFinishedListener} work as a bridge
 * model and presenter.
 */
public class SplashPresenterImpl implements SplashPresenter, SplashInteractor.OnLoginFinishedListener {
    private SplashPresenter.SplashView view;
    private SplashInteractor splashInteractor;

    /**
     * Constructor of presenter used to create the
     * instance of the view to communicate.
     *
     * @param splashView interface to connect again with splash view.
     */
    public SplashPresenterImpl(final SplashPresenter.SplashView splashView) {
        view = splashView;
        this.splashInteractor = new SplashInteractorImpl();
    }

    @Override
    public void haltScreen(final int milliSec, final String userName, final String password,
                           final LocalDatabaseManager localDbManager) {
        // handler to hold the screen to
        // show banding of the product.
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (view != null) {
                    view.showLoading();
                }
                splashInteractor.login(localDbManager, userName,
                        password, SplashPresenterImpl.this);
            }
        }, milliSec);
    }

    @Override
    public void createUser(String email, String name, LocalDatabaseManager localDbManager) {
        splashInteractor.createUser(localDbManager, email, name, SplashPresenterImpl.this);
    }

    @Override
    public void onError(final String error) {
        if (view == null) {
            return;
        }
        view.hideLoading();
        view.showError(error);

    }

    @Override
    public void onSuccess() {
        if (view == null) {
            return;
        }
        view.hideLoading();
        view.switchToLogin();

    }
}
