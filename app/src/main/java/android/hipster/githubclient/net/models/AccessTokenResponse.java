package android.hipster.githubclient.net.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by yurykorotin on 02/04/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccessTokenResponse implements Serializable {
    private long mId;
    private String mUrl;
    private String mToken;
    private String mHashedToken;
    private String mUpdatedAt;
    private String mCreatedAt;
    private String mFingerPrint;
    private String mNote;

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

    @JsonProperty("token")
    public String getToken() {
        return mToken;
    }

    public void setToken(String token) {
        mToken = token;
    }

    @JsonProperty("hashed_token")
    public String getHashedToken() {
        return mHashedToken;
    }

    public void setHashedToken(String hashedToken) {
        mHashedToken = hashedToken;
    }

    @JsonProperty("updated_at")
    public String getUpdatedAt() {
        return mUpdatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        mUpdatedAt = updatedAt;
    }

    @JsonProperty("created_at")
    public String getCreatedAt() {
        return mCreatedAt;
    }

    public void setCreatedAt(String createdAt) {
        mCreatedAt = createdAt;
    }

    @JsonProperty("fingerprint")
    public String getFingerPrint() {
        return mFingerPrint;
    }

    public void setFingerPrint(String fingerPrint) {
        mFingerPrint = fingerPrint;
    }

    @JsonProperty("note")
    public String getNote() {
        return mNote;
    }

    public void setNote(String note) {
        mNote = note;
    }
}