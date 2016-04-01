package android.hipster.githubclient.components;

import android.content.Context;
import android.hipster.githubclient.GithubClientApplication;
import android.hipster.githubclient.modules.AppModule;

/**
 * Created by yurykorotin on 01/04/16.
 */
public class ComponentsBuilder {
    private static ApplicationComponent sApplicationComponent;

    public static ApplicationComponent getApplicationComponent(Context context) {
        if(sApplicationComponent == null) {
            sApplicationComponent = DaggerApplicationComponent.builder().appModule(new AppModule()).build();
        }
        return sApplicationComponent;
    }
}
