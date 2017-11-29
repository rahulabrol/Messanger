package com.messanger.ui.splash;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.messanger.R;
import com.messanger.base.BaseActivity;
import com.messanger.database.LocalDatabaseManager;
import com.messanger.ui.dashboard.HomeActivity;

/**
 * Created by Rahul Abrol on 11/24/17.
 * <p>
 * Class @{@link SplashActivity} act as a Launcher screen (A starting point of an
 * Android application).It handles all the task that are used to load data like
 * access token api hit, login model and Firebase initialization.
 */
public class SplashActivity extends BaseActivity implements SplashPresenter.SplashView {

    private TextView tvSignUp;
    private SplashPresenter splashPresenter;
    private LocalDatabaseManager localDbManager;

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        findViewById(R.id.tvAppName).setVisibility(View.VISIBLE);
        tvSignUp = findViewById(R.id.tvSignUp);
        tvSignUp.setOnClickListener(this);

        localDbManager = LocalDatabaseManager.getInstance(this);

        splashPresenter = new SplashPresenterImpl(this);
        // method called to halt the screen to show the branding of the application.
        splashPresenter.haltScreen(TIME, "rahulabro211@gmail.com", "123456789", localDbManager);
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

    @Override
    public void showError(final String error) {
        tvSignUp.setVisibility(View.VISIBLE);
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {
        splashPresenter.createUser("rahulabrol2211@gmail.com", "Rahul Abrol", localDbManager);
    }
}
