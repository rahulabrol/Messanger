package com.messanger.ui.splash;

import android.os.Handler;

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
    public void haltScreen(int milliSec) {
        // handler to hold the screen to
        // show banding of the product.
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (view != null) {
                    view.showLoading();
                }
                splashInteractor.login("rahulabrol2211@gmail.com",
                        "123456789", SplashPresenterImpl.this);
            }
        }, milliSec);
    }

    //    @Override
//    public void onTaskResult(final Task<AuthResult> resultTask, OnCompleteListener<AuthResult> listener) {
//        if (view != null) {
//            view.getFirebaseTaskResult(resultTask, listener);
//        }
//    }

    @Override
    public void onError() {

    }

    @Override
    public void onSuccess() {

    }
}
