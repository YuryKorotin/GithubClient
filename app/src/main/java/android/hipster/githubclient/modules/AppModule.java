package android.hipster.githubclient.modules;

import android.content.Context;
import android.hipster.githubclient.GithubClientApplication;
import android.hipster.githubclient.net.GithubApiClient;
import android.hipster.githubclient.net.requests.AuthTokenRequest;

import dagger.Module;
import dagger.Provides;

/**
 * Created by yurykorotin on 01/04/16.
 */
@Module
public class AppModule {

    private final Context mContext;
    private final GithubClientApplication mApplication;

    public AppModule() {
        mApplication = GithubClientApplication.get();
        mContext = mApplication.getApplicationContext();
    }

    @Provides
    public GithubClientApplication provideApplication() {
        return mApplication;
    }

    @Provides
    public Context provideContext() {
        return mContext;
    }
}
