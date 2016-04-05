package android.hipster.githubclient.services;

import android.app.Application;
import android.hipster.githubclient.net.models.CommitsList;
import android.hipster.githubclient.net.models.ReposList;

import com.octo.android.robospice.SpiceService;
import com.octo.android.robospice.persistence.CacheManager;
import com.octo.android.robospice.persistence.exception.CacheCreationException;
import com.octo.android.robospice.persistence.springandroid.json.jackson2.Jackson2ObjectPersister;

/**
 * Created by yurykorotin on 02/04/16.
 */
public class CommitsSpiceService extends SpiceService {

    @Override
    public CacheManager createCacheManager(Application application) throws CacheCreationException {
        CacheManager manager = new CacheManager();
        Jackson2ObjectPersister<CommitsList> persister = new Jackson2ObjectPersister<CommitsList>(getApplication(), CommitsList.class);
        manager.addPersister(persister);
        return manager;
    }

    @Override
    public int getThreadCount() {
        return 3;
    }
}
