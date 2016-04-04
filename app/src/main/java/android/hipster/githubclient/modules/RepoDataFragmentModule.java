package android.hipster.githubclient.modules;

import android.content.Context;
import android.hipster.githubclient.net.GithubApiClient;
import android.hipster.githubclient.net.GithubApiClient_;
import android.hipster.githubclient.net.requests.AuthTokenRequest;
import android.hipster.githubclient.net.requests.ReposRequest;
import android.hipster.githubclient.services.AuthSpiceService;
import android.hipster.githubclient.services.ReposSpiceService;
import android.hipster.githubclient.util.Preferences;

import com.octo.android.robospice.SpiceManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by yurykorotin on 01/04/16.
 */
@Module(includes = AppModule.class)
@Singleton
public class RepoDataFragmentModule {
    @Provides
    @Singleton
    public SpiceManager provideSpiceManager() {
        return new SpiceManager(ReposSpiceService.class);
    }
}
