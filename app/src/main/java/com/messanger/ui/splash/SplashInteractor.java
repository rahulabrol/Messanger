package com.messanger.ui.splash;

import com.messanger.data.database.LocalDatabaseManager;
import com.messanger.ui.base.OnResponseListener;

/**
 * Created by Rahul Abrol on 11/27/17.
 * <p>
 * This Interface act as a bridge between the model and presentor
 * for communication.
 */

public interface SplashInteractor {

    void login(LocalDatabaseManager localDbManager, String username, String password, OnLoginFinishedListener listener);

    void createUser(LocalDatabaseManager localDbManager, String email, String password, OnLoginFinishedListener listener);

    boolean checkUserExist();

    interface OnLoginFinishedListener extends OnResponseListener {
        //void onTaskResult(Task<AuthResult> resultTask, OnCompleteListener<AuthResult> listener);
        void initializeFirebase();
    }

}
