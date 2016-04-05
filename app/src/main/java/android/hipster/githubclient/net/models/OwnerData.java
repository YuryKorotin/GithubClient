package android.hipster.githubclient.net.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by yurykorotin on 02/04/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class OwnerData implements Serializable {
    private long mId;
    private String mAvatarUrl;
    private String mLogin;

    @JsonProperty("avatar_url")
    public String getAvatarUrl() {
        return mAvatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        mAvatarUrl = avatarUrl;
    }

    @JsonProperty("login")
    public String getLogin() {
        return mLogin;
    }

    public void setLogin(String login) {
        mLogin = login;
    }

    @JsonProperty("id")
    public long getId() {
        return mId;
    }

    public void setId(long id) {
        mId = id;
    }

}