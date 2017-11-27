package com.messanger.ui.splash;

import com.messanger.base.MvpPresenter;

/**
 * Created by Rahul Abrol on 11/24/17.
 * <p>
 * It defines the methods that are used for the views to get the data and
 * send to the presenters.
 * <p>
 * Interface used to hold method for communication from
 * view to presenter.
 */

public interface SplashPresenter {

    void haltScreen(int milliSec);

    /**
     * Interface used to holds all the methods
     * for  communication between presenter and view.
     * <p>
     * Holds all the methods that are used in
     * splash view to get data and communication
     * with firebase.
     */
    interface SplashView extends MvpPresenter {
        void switchToLogin();

        // void getFirebaseTaskResult(Task<AuthResult> authResultTask, OnCompleteListener<AuthResult> listener);
    }

}
