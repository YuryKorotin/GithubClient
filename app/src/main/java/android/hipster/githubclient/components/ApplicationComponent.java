package android.hipster.githubclient.components;

import android.hipster.githubclient.AuthManager;
import android.hipster.githubclient.GithubClientApplication;
import android.hipster.githubclient.activities.LoginActivity;
import android.hipster.githubclient.activities.MainActivity;
import android.hipster.githubclient.modules.AppModule;
import android.hipster.githubclient.util.Preferences;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by yurykorotin on 01/04/16.
 */
@Component(modules = AppModule.class)
@Singleton
public interface ApplicationComponent {
    void inject(GithubClientApplication application);
    void inject(MainActivity mainActivity);

    Preferences preferences();
    AuthManager authManager();
}
