package com.messanger.database;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.messanger.utils.AppConstant.TABLE_USERS;

/**
 * Created by clicklabs on 11/24/17.
 * <p>
 * Class @{@link FirebaseManager} used to handle all
 * firebase related tasks like authentication ,
 * storage, analytics etc.
 */
public final class FirebaseManager {

    private static final String TAG = FirebaseManager.class.getSimpleName();
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private boolean isSignIn;
    private OnCompleteListener<AuthResult> listener = new OnCompleteListener<AuthResult>() {
        @Override
        public void onComplete(@NonNull Task<AuthResult> task) {
            if (task.isSuccessful()) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    //initialize firebase db here.
                    getFirebaseDatabaseInstance();
//                    PaperDb.setId(user.getUid());
                    // if true then existing user else new user.
                    if (isSignIn) {
                        Log.d(TAG, "onComplete: --------UID------->" + user.getUid());
                        getRemoteData();
                    } else {
                        Log.d(TAG, "onComplete: --------UID------->" + user.getUid());
//                    fbDatabase()
                    }
                }
            } else {
                Log.e(TAG, "onComplete: -------ERROR-->" + task.getException());
            }
        }
    };


    /**
     * private constructor cannot.
     */
    private FirebaseManager() {
    }

    /**
     * Method used to get the instance of a this class.
     *
     * @return instance of {@link FirebaseManager}.
     */
    public static FirebaseManager getInstance() {
        return new FirebaseManager();
    }

    /**
     * Method used to get remote data and update local database.
     */
    private void getRemoteData() {
        DatabaseReference ref = firebaseDatabase.getReference(TABLE_USERS);

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(final DataSnapshot dataSnapshot) {
                if (dataSnapshot != null) {
                    for (DataSnapshot data : dataSnapshot.getChildren()) {
                        if (data != null) {
//                            Log.e(TAG, CommonData.getId() + " <<------->> " + data.getKey());
                            // this is not a current loggedIn user Id then true else false.
//                            if (!CommonData.getId().equals(data.getKey())) {
//                                localHelper.addUsers(data.getValue(User.class));
//                            }
                        }
                    }
                }
            }

            @Override
            public void onCancelled(final DatabaseError databaseError) {
                databaseError.getMessage();
            }
        });
    }

    /**
     * Method used to get the instance of a firebase
     * auth to access all the auth related data of a
     * user.
     */
    public void getFirebaseAuth() {
        // @return instance of {@link FirebaseAuth}.
        firebaseAuth = FirebaseAuth.getInstance();
//        return firebaseAuth;
    }

    /**
     * Method used to get the instance of a firebase
     * Database to access all the tables.
     */
    public void getFirebaseDatabaseInstance() {
        // @return instance of {@link FirebaseAuth}.
        firebaseDatabase = FirebaseDatabase.getInstance();
//        return firebaseAuth;
    }

    /**
     * Method used to get the current user from the firebase
     * auth if exists.
     *
     * @return @{@link FirebaseUser}
     */
    public FirebaseUser getUser() {
        return firebaseAuth.getCurrentUser();
    }

    /**
     * Method used to signIn into the firebase with email and
     * password if user already exists.
     *
     * @param userName unique username of the user
     * @param password password of the entered user.
     */
    public void signIn(final String userName, final String password) {
        // @return @{@link Task<AuthResult>} to call {@link com.google.android.gms.tasks.OnCompleteListener}
        // to get the status that task is successful or not.
        isSignIn = true;
        Task<AuthResult> task = firebaseAuth.signInWithEmailAndPassword(userName, password);
        task.addOnCompleteListener(listener);
    }

    /**
     * Method used to SignUp into the firebase with email and
     * password if user not exists into the firebase database.
     *
     * @param userName unique username of the user
     * @param password password of the entered user.
     */
    public void createUser(final String userName, final String password) {
        //@return @{@link Task<AuthResult>} to call {@link com.google.android.gms.tasks.OnCompleteListener}
        // to get the status that task is successful or not.
        isSignIn = false;
        Task<AuthResult> createTask = firebaseAuth.createUserWithEmailAndPassword(userName, password);
        createTask.addOnCompleteListener(listener);
    }


}
