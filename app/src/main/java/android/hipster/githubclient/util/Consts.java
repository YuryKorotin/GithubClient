package android.hipster.githubclient.util;

import android.hipster.githubclient.net.models.ReposList;

import com.octo.android.robospice.request.listener.RequestListener;

/**
 * Created by yurykorotin on 02/04/16.
 */
public class Consts {
    public final static String AUTH_TOKEN_CACHE_KEY = "auth token";

    public static final String REPOS_CACHE_KEY = "repos";

    public final static String CLIENT_ID = "18f8e72079c2a9b52be9";

    public final static String CLIENT_SECRET = "f57bd7fd072ae56e17246bdbfa4a13127258825b";

    public final static String API_NOTE = "Github Android Client";

    public final static String[] API_SCOPES = {"public_repo", "notifications", "user"};
}
