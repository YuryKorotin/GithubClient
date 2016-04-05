package android.hipster.githubclient.net.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.security.acl.Owner;

/**
 * Created by yurykorotin on 02/04/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CommitData implements Serializable {
    private String mSha;
    private CommitDetail mCommit;

    @JsonProperty("sha")
    public String getSha() {
        return mSha;
    }

    public void setSha(String sha) {
        mSha = sha;
    }

    @JsonProperty("commit")
    public CommitDetail getCommit() {
        return mCommit;
    }

    public void setCommit(CommitDetail commit) {
        mCommit = commit;
    }
}