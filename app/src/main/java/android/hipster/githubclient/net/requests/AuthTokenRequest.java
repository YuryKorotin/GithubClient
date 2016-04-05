package android.hipster.githubclient.net.requests;

import android.hipster.githubclient.net.GithubApiClient;
import android.hipster.githubclient.net.GithubApiClient_;
import android.hipster.githubclient.net.models.AccessTokenResponse;
import android.hipster.githubclient.net.models.TokenRequestParams;
import android.hipster.githubclient.util.Consts;

import com.octo.android.robospice.request.SpiceRequest;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by yurykorotin on 03/04/16.
 */

@Singleton
public class AuthTokenRequest extends SpiceRequest<AccessTokenResponse> {

    private static final int RANDOM_MAX_LENGTH = 5;
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

        String fingerPrint = mPassword + random();

        TokenRequestParams params = new TokenRequestParams(Consts.CLIENT_ID,
                                                           Consts.CLIENT_SECRET,
                                                           Consts.API_NOTE,
                                                           fingerPrint,
                                                           scopes);

        mRestClient.setHttpBasicAuth(mUserName, mPassword);

        AccessTokenResponse response = mRestClient.updateAccessToken(Consts.CLIENT_ID, params);

        return response;
    }

    public static String random() {
        Random generator = new Random();
        StringBuilder randomStringBuilder = new StringBuilder();
        int randomLength = generator.nextInt(RANDOM_MAX_LENGTH);
        char tempChar;
        for (int i = 0; i < randomLength; i++){
            tempChar = (char) (generator.nextInt(96) + 32);
            randomStringBuilder.append(tempChar);
        }
        return randomStringBuilder.toString();
    }

}
