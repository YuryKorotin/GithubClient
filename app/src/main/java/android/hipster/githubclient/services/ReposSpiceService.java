package android.hipster.githubclient.services;

import android.app.Application;
import android.hipster.githubclient.net.models.AccessTokenResponse;
import android.hipster.githubclient.net.models.ReposList;

import com.octo.android.robospice.SpiceService;
import com.octo.android.robospice.persistence.CacheManager;
import com.octo.android.robospice.persistence.exception.CacheCreationException;
import com.octo.android.robospice.persistence.springandroid.json.jackson2.Jackson2ObjectPersister;

/**
 * Created by yurykorotin on 02/04/16.
 */
public class ReposSpiceService extends SpiceService {

    @Override
    public CacheManager createCacheManager(Application application) throws CacheCreationException {
        CacheManager manager = new CacheManager();
        Jackson2ObjectPersister<ReposList> persister = new Jackson2ObjectPersister<ReposList>(getApplication(), ReposList.class);
        manager.addPersister(persister);
        return manager;
    }

    @Override
    public int getThreadCount() {
        return 3;
    }
}
