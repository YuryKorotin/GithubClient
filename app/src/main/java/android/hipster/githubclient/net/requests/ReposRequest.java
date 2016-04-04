package android.hipster.githubclient.net.requests;

import android.hipster.githubclient.GithubClientApplication;
import android.hipster.githubclient.net.GithubApiClient;
import android.hipster.githubclient.net.GithubApiClient_;
import android.hipster.githubclient.net.models.ReposList;
import android.hipster.githubclient.util.Preferences;

import com.octo.android.robospice.request.SpiceRequest;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by yurykorotin on 03/04/16.
 */

@Singleton
public class ReposRequest extends SpiceRequest<ReposList> {
    private GithubApiClient mRestClient;

    private Preferences mPreferences;

    @Inject
    public ReposRequest(Preferences preferences, GithubApiClient githubApiClient) {
        super(ReposList.class);

        mPreferences = preferences;

        mRestClient = githubApiClient;
    }

    @Override
    public ReposList loadDataFromNetwork() throws Exception {
        //ReposRequestParams reposRequestParams = new ReposRequestParams();

        mRestClient.setHttpBasicAuth("dreadnoughtfull@yandex.ru", mPreferences.getAuthToken());

        ReposList response = mRestClient.getUserRepos();

        return response;
    }
}
