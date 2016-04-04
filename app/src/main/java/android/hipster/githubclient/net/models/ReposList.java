package android.hipster.githubclient.net.models;

import java.util.ArrayList;

/**
 * Created by yurykorotin on 03/04/16.
 */
public class ReposList {
    private static final long serialVersionUID = 6836514467436078182L;

    public ArrayList<RepoData> getRepos() {
        return repos;
    }

    public void setRepos(ArrayList<RepoData> repos) {
        this.repos = repos;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    private ArrayList<RepoData> repos;

    public static long getSerialversionuid() {
        return serialVersionUID;
    }
}
