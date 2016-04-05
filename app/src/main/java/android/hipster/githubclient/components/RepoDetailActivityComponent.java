package android.hipster.githubclient.components;

import android.hipster.githubclient.activities.LoginActivity;
import android.hipster.githubclient.activities.RepoDetailActivity;
import android.hipster.githubclient.modules.LoginActivityModule;
import android.hipster.githubclient.modules.RepoDetailActivityModule;
import android.hipster.githubclient.util.PermissionHelper;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by yurykorotin on 01/04/16.
 */
@Component(modules = RepoDetailActivityModule.class)
@Singleton
public interface RepoDetailActivityComponent {
    void inject(RepoDetailActivity detailActivity);
}
