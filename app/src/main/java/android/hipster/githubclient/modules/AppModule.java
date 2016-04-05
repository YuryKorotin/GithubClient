package android.hipster.githubclient.modules;

import android.content.Context;
import android.graphics.Bitmap;
import android.hipster.githubclient.GithubClientApplication;
import android.hipster.githubclient.net.GithubApiClient;
import android.hipster.githubclient.net.GithubApiClient_;

import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import javax.inject.Singleton;

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


    @Provides
    @Singleton
    public GithubApiClient provideGithubApiClient(Context context) {
        return new GithubApiClient_(context);
    }

    @Provides
    @Singleton
    public ImageLoaderConfiguration provideImageLoaderConfiguration(GithubClientApplication application) {
        ImageLoaderConfiguration.Builder config = new ImageLoaderConfiguration.Builder(application)
                .threadPoolSize(2)
                .threadPriority(Thread.MAX_PRIORITY)
                .denyCacheImageMultipleSizesInMemory()
                .tasksProcessingOrder(QueueProcessingType.FIFO)
                .memoryCacheSizePercentage(13)
                .diskCacheFileCount(100)
                .diskCacheFileNameGenerator(new HashCodeFileNameGenerator())
                .defaultDisplayImageOptions(DisplayImageOptions.createSimple());
        return config.build();
    }

    @Provides
    @Singleton
    public ImageLoader provideImageLoader(ImageLoaderConfiguration configuration) {
        ImageLoader loader = ImageLoader.getInstance();

        if(!loader.isInited()) {
            loader.init(configuration);
        }

        return loader;
    }

    @Provides
    public DisplayImageOptions provideDisplayOptions() {
        return new DisplayImageOptions.Builder()
                .resetViewBeforeLoading(false)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .considerExifParams(true)
                .imageScaleType(ImageScaleType.EXACTLY)
                .bitmapConfig(Bitmap.Config.RGB_565).build();
    }
}
