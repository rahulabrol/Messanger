package com.messanger.base;

/**
 * Created by Rahul Abrol on 11/24/17.
 * <p>
 * Every presenter in the app must either implement this interface or extend BasePresenter
 * indicating the MvpView type that wants to be attached with.
 */
public interface MvpPresenter<V extends MvpView> {

    void showLoading();

    void hideLoading();

    void showError(String error);


//    void onAttach(V mvpView);
//
//    void onDetach();

//    void handleApiError(ANError error);

//    void setUserAsLoggedOut();
}