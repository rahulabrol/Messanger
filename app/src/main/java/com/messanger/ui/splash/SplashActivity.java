package com.messanger.ui.splash;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.messanger.R;
import com.messanger.base.BaseActivity;
import com.messanger.ui.dashboard.HomeActivity;

/**
 * Created by Rahul Abrol on 11/24/17.
 * <p>
 * Class @{@link SplashActivity} act as a Launcher screen (A starting point of an
 * Android application).It handles all the task that are used to load data like
 * access token api hit, login model and Firebase initialization.
 */

public class SplashActivity extends BaseActivity implements SplashPresenter.SplashView {

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        findViewById(R.id.tvAppName).setVisibility(View.VISIBLE);

        final SplashPresenter splashPresenter = new SplashPresenterImpl(this);
        // method called to halt the screen to show the branding of the application.
        splashPresenter.haltScreen(TIME);
    }

    @Override
    public void switchToLogin() {
        startActivity(new Intent(SplashActivity.this, HomeActivity.class));
        finish();
    }

    @Override
    public void showLoading() {
        findViewById(R.id.progressBar).setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        findViewById(R.id.progressBar).setVisibility(View.GONE);
    }

//    @Override
//    public void getFirebaseTaskResult(final Task<AuthResult> authResultTask, OnCompleteListener<AuthResult> listener) {
//        authResultTask.addOnCompleteListener(this, listener);
//    }
}
