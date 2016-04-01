package android.hipster.githubclient;

import android.app.Application;
import android.content.Context;
import android.hipster.githubclient.components.ApplicationComponent;
import android.hipster.githubclient.components.ComponentsBuilder;

/**
 * Created by yurykorotin on 01/04/16.
 */
public class GithubClientApplication extends Application{
    private final GithubClientApplication mInstance;
    protected ApplicationComponent mApplicationComponent;

    public GithubClientApplication() {
        mInstance = this;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        configureDagger();
    }

    protected void configureDagger() {
        mApplicationComponent = ComponentsBuilder.getApplicationComponent(this);
        mApplicationComponent.inject(this);
    }

    public static GithubClientApplication get(Context context){
        return (GithubClientApplication) context.getApplicationContext();
    }
}