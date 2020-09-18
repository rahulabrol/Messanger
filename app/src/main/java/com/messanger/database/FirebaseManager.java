package com.messanger.database;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.messanger.data.database.LocalDatabaseManager;
import com.messanger.datamodel.User;
import com.messanger.ui.base.OnResponseListener;

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
    private static FirebaseManager instance;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private LocalDatabaseManager localDbManager;
//    private SplashInteractor.OnLoginFinishedListener notifyListner;
//    private OnCompleteListener<AuthResult> listener = new OnCompleteListener<AuthResult>() {
//        @Override
//        public void onComplete(@NonNull final Task<AuthResult> task) {
//            if (task.isSuccessful()) {
//                FirebaseUser user = firebaseAuth.getCurrentUser();
//                if (user != null) {
////                    PaperDb.setId(user.getUid());
//                    // if true then existing user else new user.
//                    if (isSignIn) {
//                        getRemoteData();
//                    } else {
////                    fbDatabase()
//                        if (notifyListner != null) {
//                            notifyListner.onSuccess();
//                        }
//                    }
//                }
//            } else {
//                Timber.e("onComplete: -------ERROR-->%s", task.getException().getMessage());
//                if (notifyListner != null) {
//                    notifyListner.onError(task.getException().getMessage());
//                }
//            }
//        }
//    };

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
        if (instance == null) {
            synchronized (FirebaseManager.class) {
                if (instance == null) {
                    instance = new FirebaseManager();
                }
            }
        }
        return instance;
    }

    /**
     * Method used to get remote data and update local database.
     *
     * @param listener used for send status back.
     */
    public void getRemoteData(final OnResponseListener listener) {
        getRemoteData();
    }

    /**
     * Method used to get the reference of firebase
     * remote table and set data into the local db.
     */
    private void getRemoteData() {
        checkIfFirebaseDatabaseInitialized();
        DatabaseReference ref = firebaseDatabase.getReference(TABLE_USERS);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull final DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    if (data != null) {
                        Log.e(TAG, data.getValue() + " <<------->> " + data.getKey());
                        // this is not a current loggedIn user Id then true else false.
//                            if (!CommonData.getId().equals(data.getKey()) && localDbManager != null) {
                        localDbManager.addUsers(data.getValue(User.class));
//                            }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull final DatabaseError databaseError) {
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
    }

    /**
     * Method used to get the instance of a firebase
     * Database to access all the tables.
     */
    public void getFirebaseDatabaseInstance() {
        firebaseDatabase = FirebaseDatabase.getInstance();
    }

    /**
     * Method used to get the current user from the firebase
     * auth if exists.
     *
     * @return @{@link FirebaseUser}
     */
    public FirebaseUser getUser() {
        checkIfFirebaseAuthInitialized();
        return firebaseAuth.getCurrentUser();
    }

    /**
     * Method used to check if firebase auth is initialized or not
     * if not throw exception.
     *
     * @throws IllegalArgumentException if auth is null.
     */
    private void checkIfFirebaseAuthInitialized() {
        if (firebaseAuth == null) {
            throw new IllegalArgumentException("FirebaseAuth instance cannot be null. Call" +
                    "FirebaseAuth.getInstance()");
        }
    }

    /**
     * Method used to check if firebase auth is initialized or not
     * if not throw exception.
     *
     * @throws IllegalArgumentException if auth is null.
     */
    private void checkIfFirebaseDatabaseInitialized() {
        if (firebaseDatabase == null) {
            throw new IllegalArgumentException("FirebaseDatabase instance cannot be null. Call" +
                    "FirebaseDatabase.getInstance()");
        }
    }

    /**
     * Method used to signIn into the firebase with email and
     * password if user already exists.
     *
     * @param userName         unique username of the user
     * @param password         password of the entered user.
     * @param responseListener listener to send status back to presenter.
     */
    public void signIn(final String userName, final String password,
                       final OnResponseListener responseListener) {
        // @return @{@link Task<AuthResult>} to call {@link com.google.android.gms.tasks.OnCompleteListener}
        // to get the status that task is successful or not.
        checkIfFirebaseAuthInitialized();
        Task<AuthResult> task = firebaseAuth.signInWithEmailAndPassword(userName, password);
        task.addOnSuccessListener(authResult -> {
            responseListener.onSuccess(authResult.getUser());
        }).addOnFailureListener(e -> {
            responseListener.onError(e.getMessage());
        });
    }

    /**
     * Method used to SignUp into the firebase with email and
     * password if user not exists into the firebase database.
     *
     * @param userName unique username of the user
     * @param password password of the entered user.
     */
    public void createUser(final String userName, final String password,
                           final OnResponseListener responseListener) {
        //@return @{@link Task<AuthResult>} to call {@link com.google.android.gms.tasks.OnCompleteListener}
        // to get the status that task is successful or not.
        checkIfFirebaseAuthInitialized();
        firebaseAuth.createUserWithEmailAndPassword(userName, password)
                .addOnSuccessListener(authResult -> {
                    responseListener.onSuccess(authResult.getUser());
                }).addOnFailureListener(e -> {
            responseListener.onError(e.getMessage());
        });
    }

    /**
     * Set Local database refrence here to store firebase data here.
     *
     * @param localDbManager instance of local db.
     */
    public void setLocalDb(final LocalDatabaseManager localDbManager) {
        this.localDbManager = localDbManager;
    }
}
