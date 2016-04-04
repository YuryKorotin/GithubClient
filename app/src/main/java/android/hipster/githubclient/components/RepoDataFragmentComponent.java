package android.hipster.githubclient.components;

import android.hipster.githubclient.activities.LoginActivity;
import android.hipster.githubclient.fragments.RepoDataFragment;
import android.hipster.githubclient.modules.LoginActivityModule;
import android.hipster.githubclient.modules.RepoDataFragmentModule;
import android.hipster.githubclient.util.PermissionHelper;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by yurykorotin on 01/04/16.
 */
@Component(modules = RepoDataFragmentModule.class)
@Singleton
public interface RepoDataFragmentComponent {
    void inject(RepoDataFragment fragment);
}
