package android.hipster.githubclient.modules;

import android.hipster.githubclient.services.AuthSpiceService;
import android.hipster.githubclient.services.CommitsSpiceService;

import com.octo.android.robospice.SpiceManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by yurykorotin on 01/04/16.
 */
@Module(includes = AppModule.class)
@Singleton
public class RepoDetailActivityModule {
    @Provides
    @Singleton
    public SpiceManager provideSpiceManager() {
        return new SpiceManager(CommitsSpiceService.class);
    }
}
