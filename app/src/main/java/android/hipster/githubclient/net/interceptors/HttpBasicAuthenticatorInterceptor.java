package android.hipster.githubclient.net.interceptors;

import org.androidannotations.rest.spring.annotations.Header;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

/**
 * Created by yurykorotin on 02/04/16.
 */
public class HttpBasicAuthenticatorInterceptor implements ClientHttpRequestInterceptor {
    @Override
    public ClientHttpResponse intercept(HttpRequest request,
                                        byte[] body,
                                        ClientHttpRequestExecution execution) throws IOException {

        request.getHeaders().set("User-Agent", "GithubViewer");
        request.getHeaders().set("Accept", "application/vnd.github.v3.json");

        return execution.execute(request, body);
    }
}
