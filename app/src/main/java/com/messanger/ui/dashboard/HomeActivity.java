package com.messanger.ui.dashboard;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.messanger.R;
import com.messanger.base.BaseActivity;

/**
 * Created by Rahul Abrol on 11/24/17.
 */

public class HomeActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }
}
