package android.hipster.githubclient;

import android.hipster.githubclient.util.Preferences;

import com.google.common.base.Strings;

import javax.inject.Inject;

/**
 * Created by yurykorotin on 03/04/16.
 */
public class AuthManager {

    private final Preferences mPreferences;

    @Inject
    public AuthManager(Preferences preferences) {
        mPreferences = preferences;
    }

    public boolean isUserAuthorized() {
        return false;
    }

    public boolean isAppAuthorized() {
        return !Strings.isNullOrEmpty(mPreferences.getAuthToken());
    }

    public void authorizeApplication(String token) {
        mPreferences.setAuthToken(token);
    }
}
