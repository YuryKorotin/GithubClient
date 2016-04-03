package android.hipster.githubclient.components;

import android.content.Context;
import android.hipster.githubclient.GithubClientApplication;
import android.hipster.githubclient.activities.LoginActivity;
import android.hipster.githubclient.modules.AppModule;
import android.hipster.githubclient.modules.LoginActivityModule;
import android.util.Log;

/**
 * Created by yurykorotin on 01/04/16.
 */
public class ComponentsBuilder {
    private static ApplicationComponent sApplicationComponent;
    private static LoginActivityComponent sLoginActivityComponent;

    public static ApplicationComponent getApplicationComponent(Context context) {
        if(sApplicationComponent == null) {
            sApplicationComponent = DaggerApplicationComponent.builder().appModule(new AppModule()).build();
        }
        return sApplicationComponent;
    }

    public static LoginActivityComponent getLoginActivityComponent(Context context) {
        if(sLoginActivityComponent == null) {
            sLoginActivityComponent = DaggerLoginActivityComponent.builder().loginActivityModule(new LoginActivityModule()).appModule(new AppModule()).build();
        }
        return sLoginActivityComponent;
    }
}
