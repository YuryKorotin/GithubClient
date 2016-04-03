package android.hipster.githubclient.net.requests;

import android.hipster.githubclient.GithubClientApplication;
import android.hipster.githubclient.net.GithubApiClient;
import android.hipster.githubclient.net.models.AccessTokenResponse;
import android.hipster.githubclient.net.models.TokenRequestParams;
import android.hipster.githubclient.util.Consts;
import android.util.Base64;

import com.octo.android.robospice.request.SpiceRequest;

import org.androidannotations.annotations.EBean;
import org.androidannotations.rest.spring.annotations.RestService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by yurykorotin on 03/04/16.
 */

@EBean
public class AuthTokenRequest extends SpiceRequest<AccessTokenResponse> {
    @RestService
    GithubApiClient restClient;

    public void setUserName(String userName) {
        mUserName = userName;
    }

    public void setPassword(String password) {
        mPassword = password;
    }

    private String mUserName;
    private String mPassword;

    public AuthTokenRequest() {
        super(AccessTokenResponse.class);
    }

    @Override
    public AccessTokenResponse loadDataFromNetwork() throws Exception {
        String fingerPrint = "test_fingerprint";

        List<String> scopes = Arrays.asList(Consts.API_SCOPES);

        TokenRequestParams params = new TokenRequestParams(Consts.CLIENT_ID,
                                                           Consts.CLIENT_SECRET,
                                                           Consts.API_NOTE,
                                                           fingerPrint,
                                                           scopes);

        //AccessTokenResponse response = restClient.getAccessToken(Consts.CLIENT_ID, params);

        restClient.setHttpBasicAuth(mUserName, mPassword);

        AccessTokenResponse response = restClient.getAccessToken(params);

        return response;
    }
}
