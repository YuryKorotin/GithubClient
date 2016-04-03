package android.hipster.githubclient.components;

import android.hipster.githubclient.GithubClientApplication;
import android.hipster.githubclient.activities.LoginActivity;
import android.hipster.githubclient.modules.AppModule;
import android.hipster.githubclient.modules.LoginActivityModule;
import android.hipster.githubclient.util.PermissionHelper;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by yurykorotin on 01/04/16.
 */
@Component(modules = LoginActivityModule.class)
@Singleton
public interface LoginActivityComponent {
    void inject(LoginActivity loginActivity);

    PermissionHelper permissionHelper();
}
