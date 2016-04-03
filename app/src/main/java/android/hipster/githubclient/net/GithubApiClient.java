package android.hipster.githubclient.net;

import android.hipster.githubclient.net.interceptors.HttpBasicAuthenticatorInterceptor;
import android.hipster.githubclient.net.models.AccessTokenResponse;
import android.hipster.githubclient.net.models.TokenRequestParams;

import org.androidannotations.rest.spring.annotations.Body;
import org.androidannotations.rest.spring.annotations.Get;
import org.androidannotations.rest.spring.annotations.Header;
import org.androidannotations.rest.spring.annotations.Path;
import org.androidannotations.rest.spring.annotations.Post;
import org.androidannotations.rest.spring.annotations.Put;
import org.androidannotations.rest.spring.annotations.RequiresAuthentication;
import org.androidannotations.rest.spring.annotations.Rest;
import org.androidannotations.rest.spring.api.RestClientHeaders;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

/**
 * Created by yurykorotin on 02/04/16.
 */
@Rest(rootUrl = "https://api.github.com",
        converters = { MappingJackson2HttpMessageConverter.class},
        interceptors = { HttpBasicAuthenticatorInterceptor.class })
public interface GithubApiClient extends RestClientHeaders {
    //@Get("/users/whatever?client_id={cliendId}&client_secret={clientSecret}")
    //AccessTokenResponse getAccessToken(@Path String clientId, @Path String clientSecret);

    @Post("/authorizations")
    @RequiresAuthentication
    AccessTokenResponse getAccessToken(@Body TokenRequestParams params);

    @Put("/authorizations/clients/{clientId}")
    @RequiresAuthentication
    AccessTokenResponse updateAccessToken(@Path String clientId, @Body TokenRequestParams params);

    void setHttpBasicAuth(String username, String password);
}
