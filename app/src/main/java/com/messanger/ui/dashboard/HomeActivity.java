package com.messanger.ui.dashboard;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.messanger.R;
import com.messanger.base.BaseActivity;

/**
 * Created by Rahul Abrol on 11/24/17.
 * Class @{@link HomeActivity} used to hold three fragments are:-
 * <p>
 * i) @{@link RecentFragments} used to holds the recent chat od users.
 * ii) @{@link CallsFragment} used to show the list of dialed calls.
 * iii) @{@link FriendsFragents} used to show list of all the friends.
 */

public class HomeActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }
}
