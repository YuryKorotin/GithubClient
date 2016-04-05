package android.hipster.githubclient.net.requests;

import android.hipster.githubclient.net.GithubApiClient;
import android.hipster.githubclient.net.models.CommitsList;
import android.hipster.githubclient.net.models.RepoData;
import android.hipster.githubclient.net.models.ReposList;
import android.hipster.githubclient.util.Preferences;

import com.octo.android.robospice.request.SpiceRequest;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by yurykorotin on 03/04/16.
 */

@Singleton
public class CommitsRequest extends SpiceRequest<CommitsList> {
    private GithubApiClient mRestClient;

    private Preferences mPreferences;

    private RepoData mRepoData;

    @Inject
    public CommitsRequest(Preferences preferences, GithubApiClient githubApiClient) {
        super(CommitsList.class);

        mPreferences = preferences;

        mRestClient = githubApiClient;
    }

    @Override
    public CommitsList loadDataFromNetwork() throws Exception {
        //TODO: Add caching to realm of commit

        mRestClient.setBearerAuth(mPreferences.getAuthToken());

        CommitsList response = mRestClient.getCommits(mRepoData.getOwner().getLogin(), mRepoData.getName());

        return response;
    }

    public void setRepoData(RepoData repoData) {
        mRepoData = repoData;
    }
}
