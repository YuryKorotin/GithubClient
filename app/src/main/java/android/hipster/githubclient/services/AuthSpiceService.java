package android.hipster.githubclient.services;

import android.app.Application;

import com.octo.android.robospice.SpiceService;
import com.octo.android.robospice.persistence.CacheManager;
import com.octo.android.robospice.persistence.exception.CacheCreationException;

/**
 * Created by yurykorotin on 02/04/16.
 */
public class AuthSpiceService extends SpiceService {

    @Override
    public CacheManager createCacheManager(Application application) throws CacheCreationException {
        CacheManager manager = new CacheManager();
        //Jackson2ObjectPersister<FacebookPage> persister = new Jackson2ObjectPersister<FacebookPage>(getApplication(), FacebookPage.class);
        //mmanager.addPersister(persister);
        return manager;
    }
}
