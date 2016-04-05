package android.hipster.githubclient.net.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by yurykorotin on 02/04/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RepoData implements Serializable {
    private long mId;
    private String mUrl;
    private String mName;
    private String mFullName;
    private String mDescription;
    private long mForksCount;
    private long mWatchersCount;
    private long mStarGazersCount;
    private OwnerData mOwner;

    @JsonProperty("description")
    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    @JsonProperty("owner")
    public OwnerData getOwner() {
        return mOwner;
    }

    public void setOwner(OwnerData owner) {
        mOwner = owner;
    }
    @JsonProperty("id")
    public long getId() {
        return mId;
    }

    public void setId(long id) {
        mId = id;
    }

    @JsonProperty("url")
    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }

    @JsonProperty("name")
    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    @JsonProperty("full_name")
    public String getFullName() {
        return mFullName;
    }

    public void setFullName(String fullName) {
        mFullName = fullName;
    }

    @JsonProperty("forks_count")
    public long getForksCount() {
        return mForksCount;
    }

    public void setForksCount(long forksCount) {
        mForksCount = forksCount;
    }

    @JsonProperty("watchers_count")
    public long getWatchersCount() {
        return mWatchersCount;
    }

    public void setWatchersCount(long watchersCount) {
        mWatchersCount = watchersCount;
    }

    @JsonProperty("stargazers_count")
    public long getStarGazersCount() {
        return mStarGazersCount;
    }

    public void setStarGazersCount(long starGazersCount) {
        mStarGazersCount = starGazersCount;
    }
}