package android.hipster.githubclient.modules;

import android.hipster.githubclient.net.requests.AuthTokenRequest;
import android.hipster.githubclient.services.AuthSpiceService;

import com.octo.android.robospice.SpiceManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by yurykorotin on 01/04/16.
 */
@Module(includes = AppModule.class)
@Singleton
public class LoginActivityModule {
    @Provides
    @Singleton
    public SpiceManager provideSpiceManager() {
        return new SpiceManager(AuthSpiceService.class);
    }

    @Provides
    @Singleton
    public AuthTokenRequest provideAuthTokenRequest() {
        return new AuthTokenRequest();
    }
}
