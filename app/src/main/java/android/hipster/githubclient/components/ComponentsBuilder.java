package android.hipster.githubclient.components;

import android.content.Context;
import android.hipster.githubclient.GithubClientApplication;
import android.hipster.githubclient.activities.LoginActivity;
import android.hipster.githubclient.modules.AppModule;
import android.hipster.githubclient.modules.LoginActivityModule;
import android.hipster.githubclient.modules.RepoDataFragmentModule;
import android.hipster.githubclient.modules.RepoDetailActivityModule;
import android.util.Log;

/**
 * Created by yurykorotin on 01/04/16.
 */
public class ComponentsBuilder {
    //TODO: Remove boilerplate code

    private static ApplicationComponent sApplicationComponent;
    private static LoginActivityComponent sLoginActivityComponent;
    private static RepoDataFragmentComponent sRepoDataFragmentComponent;
    private static RepoDetailActivityComponent sRepoDetailActivityComponent;
    private static AppModule sAppModule;

    public static ApplicationComponent getApplicationComponent() {
        if(sAppModule == null) {
            sAppModule = new AppModule();
        }
        if(sApplicationComponent == null) {
            sApplicationComponent = DaggerApplicationComponent.builder().appModule(sAppModule).build();
        }
        return sApplicationComponent;
    }

    public static LoginActivityComponent getLoginActivityComponent() {
        if(sAppModule == null) {
            sAppModule = new AppModule();
        }
        if(sLoginActivityComponent == null) {
            sLoginActivityComponent = DaggerLoginActivityComponent.builder().loginActivityModule(new LoginActivityModule())
                                                                            .appModule(sAppModule).build();
        }
        return sLoginActivityComponent;
    }

    public static RepoDataFragmentComponent getReposDataFragmentComponent() {
        if(sAppModule == null) {
            sAppModule = new AppModule();
        }
        if(sRepoDataFragmentComponent == null) {
            sRepoDataFragmentComponent = DaggerRepoDataFragmentComponent.builder().repoDataFragmentModule(new RepoDataFragmentModule())
                                                                                  .appModule(sAppModule).build();
        }
        return sRepoDataFragmentComponent;
    }

    public static RepoDetailActivityComponent getRepoDetailActivityComponent() {
        if(sAppModule == null) {
            sAppModule = new AppModule();
        }
        if(sRepoDetailActivityComponent == null) {
            sRepoDetailActivityComponent = DaggerRepoDetailActivityComponent.builder().repoDetailActivityModule(new RepoDetailActivityModule())
                    .appModule(sAppModule).build();
        }
        return sRepoDetailActivityComponent;
    }
}
