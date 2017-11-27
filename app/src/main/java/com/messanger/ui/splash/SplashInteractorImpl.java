package com.messanger.ui.splash;

import com.google.firebase.auth.FirebaseUser;
import com.messanger.database.FirebaseManager;

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
//    private FirebaseAuth auth;

    /**
     * constructor of model class.
     */
    SplashInteractorImpl() {
        firebaseManager = FirebaseManager.getInstance();
    }

    @Override
    public void login(String username, String password, OnLoginFinishedListener listener) {
        if (firebaseManager != null) {
            // initialize the firebase authorization.
            firebaseManager.getFirebaseAuth();
            // Check the user is exists.
            FirebaseUser user = firebaseManager.getUser();
            if (user != null) {
                String userId = user.getUid();
                //set userId in paperDB
                //CommonData.setId(userId);
                //get data from remote database here.

            } else {
                // signIn if user already exists in firebase db.
                firebaseManager.signIn(username, password);
            }


        }
    }
}
