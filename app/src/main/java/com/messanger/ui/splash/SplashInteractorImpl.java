package com.messanger.ui.splash;

import com.google.firebase.auth.FirebaseUser;
import com.messanger.database.FirebaseManager;
import com.messanger.database.LocalDatabaseManager;

/**
 * Created by Rahul Abrol on 11/27/17.
 * <p>
 * class @{@link SplashInteractorImpl} act as a model in MVP
 * architecture. we know model directly listen to presenter so
 * for communication b/w model and presenter Splash interactor is
 * used.
 */
public class SplashInteractorImpl implements SplashInteractor {

    private final FirebaseManager firebaseManager;

    /**
     * constructor of model class.
     */
    SplashInteractorImpl() {
        firebaseManager = FirebaseManager.getInstance();
    }

    @Override
    public void login(final LocalDatabaseManager localDbManager, final String username, final String password, final OnLoginFinishedListener listener) {
        if (firebaseManager != null) {
            // Initialize the firebase authorization.
            firebaseManager.getFirebaseAuth();
            // Check the user is exists.
            FirebaseUser user = firebaseManager.getUser();
            // Initialize local database here to save all the users locally if exists.
            firebaseManager.setLocalDb(localDbManager);
            //initialize firebase db here.
            firebaseManager.getFirebaseDatabaseInstance();

            if (user != null) {
                String userId = user.getUid();
                //set userId in paperDB
                //CommonData.setId(userId);
                //get data from remote database here.
                firebaseManager.getRemoteData(listener);
            } else {
                // signIn if user already exists in firebase db.
                firebaseManager.signIn(username, password, listener);

            }
        }
    }

    @Override
    public void createUser(LocalDatabaseManager localDbManager, String email, String name, OnLoginFinishedListener listener) {

    }
}
