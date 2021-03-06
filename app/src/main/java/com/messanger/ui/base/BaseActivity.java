package com.messanger.ui.base;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;

import com.messanger.utils.AppConstant;

/**
 * Created by Rahul Abrol on 11/24/17.
 * <p>
 * Class @{@link BaseActivity} used to ba act as a common
 * class which is used to extand all tha common functionality
 * which are used almost in every Activity.
 */
public abstract class BaseActivity extends AppCompatActivity implements MvpView, AppConstant, View.OnClickListener {
    @Override
    public void onClick(final View view) {

    }
    //    private ProgressDialog mProgressDialog;
//
//    @Override
//    protected void onCreate(@Nullable final Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
////        mActivityComponent = DaggerActivityComponent.builder()
////                .activityModule(new ActivityModule(this))
////                .applicationComponent(((MvpApp) getApplication()).getComponent())
////                .build();
//
//    }
//
//    @Override
//    protected void attachBaseContext(final Context newBase) {
//        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
//    }
//    @TargetApi(Build.VERSION_CODES.M)
//    public void requestPermissionsSafely(final String[] permissions, final int requestCode) {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            requestPermissions(permissions, requestCode);
//        }
//    }
//
//    @TargetApi(Build.VERSION_CODES.M)
//    public boolean hasPermission(final String permission) {
//        return Build.VERSION.SDK_INT < Build.VERSION_CODES.M ||
//                checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED;
//    }
//
//    @Override
//    public void showLoading() {
//        hideLoading();
////        mProgressDialog = CommonUtils.showLoadingDialog(this);
//    }
//
//    @Override
//    public void hideLoading() {
//        if (mProgressDialog != null && mProgressDialog.isShowing()) {
//            mProgressDialog.cancel();
//        }
//    }
//
//    private void showSnackBar(final String message) {
//        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content),
//                message, Snackbar.LENGTH_SHORT);
//        View sbView = snackbar.getView();
//        TextView textView = (TextView) sbView
//                .findViewById(android.support.design.R.id.snackbar_text);
//        textView.setTextColor(ContextCompat.getColor(this, R.color.colorAccent));
//        snackbar.show();
//    }
//
//    @Override
//    public void onError(final String message) {
//        if (message != null) {
//            showSnackBar(message);
//        } else {
//            showSnackBar(getString(R.string.app_name));
//        }
//    }
//
//    @Override
//    public void onError(@StringRes final int resId) {
//        onError(getString(resId));
//    }
//
//    @Override
//    public void showMessage(final String message) {
//        if (message != null) {
//            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
//        } else {
//            Toast.makeText(this, getString(R.string.app_name), Toast.LENGTH_SHORT).show();
//        }
//    }
//
//    @Override
//    public void showMessage(@StringRes final int resId) {
//        showMessage(getString(resId));
//    }
//
//    @Override
//    public boolean isNetworkConnected() {
//        return NetworkUtils.isNetworkConnected(getApplicationContext());
//    }
//
//    public void hideKeyboard() {
//        View view = this.getCurrentFocus();
//        if (view != null) {
//            InputMethodManager imm = (InputMethodManager)
//                    getSystemService(Context.INPUT_METHOD_SERVICE);
//            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
//        }
//    }
//
//    @Override
//    public void openActivityOnTokenExpire() {
////        startActivity(LoginActivity.getStartIntent(this));
//        finish();
//    }
//
//    @Override
//    protected void onDestroy() {
//
////        if (mUnBinder != null) {
////            mUnBinder.unbind();
////        }
//        super.onDestroy();
//    }
}
