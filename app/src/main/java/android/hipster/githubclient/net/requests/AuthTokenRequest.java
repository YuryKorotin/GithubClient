package android.hipster.githubclient.net.requests;

import android.hipster.githubclient.net.GithubApiClient;
import android.hipster.githubclient.net.GithubApiClient_;
import android.hipster.githubclient.net.models.AccessTokenResponse;
import android.hipster.githubclient.net.models.TokenRequestParams;
import android.hipster.githubclient.util.Consts;

import com.octo.android.robospice.request.SpiceRequest;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by yurykorotin on 03/04/16.
 */

@Singleton
public class AuthTokenRequest extends SpiceRequest<AccessTokenResponse> {

    private GithubApiClient mRestClient;

    public void setUserName(String userName) {
        mUserName = userName;
    }

    public void setPassword(String password) {
        mPassword = password;
    }

    private String mUserName;
    private String mPassword;

    @Inject
    public AuthTokenRequest(GithubApiClient githubApiClient) {
        super(AccessTokenResponse.class);

        mRestClient = githubApiClient;
    }

    @Override
    public AccessTokenResponse loadDataFromNetwork() throws Exception {
        List<String> scopes = Arrays.asList(Consts.API_SCOPES);

        TokenRequestParams params = new TokenRequestParams(Consts.CLIENT_ID,
                                                           Consts.CLIENT_SECRET,
                                                           Consts.API_NOTE,
                                                           mPassword,
                                                           scopes);

        mRestClient.setHttpBasicAuth(mUserName, mPassword);

        AccessTokenResponse response = mRestClient.getAccessToken(params);

        return response;
    }
}
