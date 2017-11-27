package com.messanger.ui.splash;

/**
 * Created by Rahul Abrol on 11/27/17.
 * <p>
 * This Interface act as a bridge between the model and presentor
 * for communication.
 */

public interface SplashInteractor {

    void login(String username, String password, OnLoginFinishedListener listener);

    interface OnLoginFinishedListener {
        //void onTaskResult(Task<AuthResult> resultTask, OnCompleteListener<AuthResult> listener);

        void onError();

        void onSuccess();
    }

}
