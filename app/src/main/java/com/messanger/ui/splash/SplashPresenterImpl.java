package com.messanger.ui.splash;

import android.os.Handler;

import com.messanger.data.database.LocalDatabaseManager;

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
        new Handler().postDelayed(() -> {
            if (view != null) {
                view.showLoading();
            }
            if (splashInteractor.checkUserExist()) {
                splashInteractor.login(localDbManager, userName, password, SplashPresenterImpl.this);
            } else{

            }
        }, milliSec);
    }

    @Override
    public void createUser(String email, String name, String password, LocalDatabaseManager localDbManager) {
        splashInteractor.createUser(localDbManager, email, name, password, SplashPresenterImpl.this);
    }

    @Override
    public void initializeFirebase() {
        view.initializeFirebase();
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
    public void onSuccess(Object response) {
        if (view == null) {
            return;
        }
        view.hideLoading();
        view.switchToLogin();
    }
}
