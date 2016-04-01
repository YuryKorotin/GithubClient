package android.hipster.githubclient.components;

import android.hipster.githubclient.GithubClientApplication;
import android.hipster.githubclient.modules.AppModule;

import dagger.Component;

/**
 * Created by yurykorotin on 01/04/16.
 */
@Component(modules = AppModule.class)
public interface ApplicationComponent {
    void inject(GithubClientApplication application);
}
