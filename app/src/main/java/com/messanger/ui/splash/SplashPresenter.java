package com.messanger.ui.splash;

import com.messanger.data.database.LocalDatabaseManager;
import com.messanger.ui.base.MvpPresenter;

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
    /**
     * Method used to halt the screen to show banding of application and
     * check if the user exists  into the db.
     *
     * @param milliSec       number of milliseconds to halt the screen.
     * @param userName       unique user  name of the user like email,phone etc.
     * @param password       password of existed user.
     * @param localDbManager initialize the local db to save server data locally.
     */
    void haltScreen(int milliSec, String userName, String password, LocalDatabaseManager localDbManager);

    /**
     * Method used to create the new user with unique email and name.
     *
     * @param email          unique username.
     * @param password       password
     * @param localDbManager local database for locally save data.
     */
    void createUser(String email, String password, LocalDatabaseManager localDbManager);

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

        void initializeFirebase();

        // void getFirebaseTaskResult(Task<AuthResult> authResultTask, OnCompleteListener<AuthResult> listener);
    }

}
