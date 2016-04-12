package android.hipster.githubclient.activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.pm.PackageManager;
import android.hipster.githubclient.AuthManager;
import android.hipster.githubclient.components.ComponentsBuilder;
import android.hipster.githubclient.net.models.AccessTokenResponse;
import android.hipster.githubclient.net.requests.AuthTokenRequest;
import android.hipster.githubclient.util.Consts;
import android.hipster.githubclient.util.PermissionHelper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import android.hipster.githubclient.R;

import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.persistence.DurationInMillis;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.EditorAction;
import org.androidannotations.annotations.ViewById;

import javax.inject.Inject;

import in.anshul.libray.PasswordEditText;

import static android.Manifest.permission.INSTALL_SHORTCUT;
import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
@EActivity(R.layout.activity_login)
public class LoginActivity extends AppCompatActivity implements RequestListener<AccessTokenResponse> {

    @Inject
    PermissionHelper mHelper;

    @Inject
    SpiceManager mSpiceManager;

    @Inject
    AuthManager mAuthManager;

    @Inject
    AuthTokenRequest mAuthTokenRequest;

    @ViewById(R.id.email)
    AutoCompleteTextView mEmailView;

    @ViewById(R.id.password)
    PasswordEditText mPasswordView;

    @ViewById(R.id.login_form)
    View mProgressView;

    @ViewById(R.id.login_progress)
    View mLoginFormView;

    @Click(R.id.email_sign_in_button)
    void onLogin() {
        attemptLogin();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ComponentsBuilder.getLoginActivityComponent().inject(this);

        if(mAuthManager.isAppAuthorized()) {
            finish();

            MainActivity_.intent(this).start();
        }
    }

    @EditorAction(R.id.password)
    void onPasswordEdit(TextView textView, int id, KeyEvent keyEvent) {
        if (id == R.id.login || id == EditorInfo.IME_NULL) {
            attemptLogin();
        }
    }

    @AfterViews
    void syncViews() {
        /*if(mHelper.mayRequestContacts(this)) {
            requestPermissions(PermissionHelper.REQUEST_READ_CONTACTS);
        }*/
    }

    private void attemptLogin() {
        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);

        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            showProgress(true);
        }

        mAuthTokenRequest.setUserName(email);
        mAuthTokenRequest.setPassword(password);
        mSpiceManager.execute(mAuthTokenRequest, Consts.AUTH_TOKEN_CACHE_KEY, DurationInMillis.ALWAYS_RETURNED, this);
    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }


    @Override
    protected void onStop() {
        if(mSpiceManager.isStarted()) {
            mSpiceManager.shouldStop();
        }
        super.onStop();
    }

    @Override
    protected void onStart() {
        if(!mSpiceManager.isStarted()) {
            mSpiceManager.start(this);
        }
        super.onStart();
    }

    @Override
    public void onRequestFailure(SpiceException spiceException) {
        Snackbar.make(mLoginFormView, spiceException.getLocalizedMessage(), Snackbar.LENGTH_LONG)
                .setAction(getString(R.string.ok), null).show();
    }

    @Override
    public void onRequestSuccess(AccessTokenResponse accessTokenResponse) {
        mAuthManager.authorizeApplication(accessTokenResponse.getToken());

        finish();

        MainActivity_.intent(this).start();
    }
}

