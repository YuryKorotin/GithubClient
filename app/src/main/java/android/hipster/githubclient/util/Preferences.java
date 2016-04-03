package android.hipster.githubclient.util;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by yurykorotin on 03/04/16.
 */
@Singleton
public class Preferences {

    private static final String SHARED_PREFERENCES_NAME = "github_client_preferences";
    private static final String KEY_AUTH_TOKEN = "auth_token";

    private final SharedPreferences mPrefs;

    @Inject
    public Preferences(Context context) {
        mPrefs = context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    public String getAuthToken() {
        return mPrefs.getString(KEY_AUTH_TOKEN, "");
    }

    public void setAuthToken(String token) {
        mPrefs.edit().putString(KEY_AUTH_TOKEN, token).apply();
    }
}
