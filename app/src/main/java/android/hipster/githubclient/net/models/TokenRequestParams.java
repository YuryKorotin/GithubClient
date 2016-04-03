package android.hipster.githubclient.net.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yurykorotin on 02/04/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TokenRequestParams implements Serializable {

    private String mNote;
    private String mClientId;
    private String mClientSecret;
    private String mFingerprint;
    private List<String> mScopes;

    public TokenRequestParams(String clientId, String clientSecret, String note, String fingerprint, List<String> scopes) {
        mClientId = clientId;
        mClientSecret = clientSecret;
        mNote = note;
        mFingerprint = fingerprint;
        mScopes = scopes;
    }

    @JsonProperty("note")
    public String getNote() {
        return mNote;
    }

    public void setNote(String note) {
        mNote = note;
    }

    @JsonProperty("client_id")
    public String getClientId() {
        return mClientId;
    }

    public void setClientId(String clientId) {
        mClientId = clientId;
    }

    @JsonProperty("client_secret")
    public String getClientSecret() {
        return mClientSecret;
    }

    public void setClientSecret(String clientSecret) {
        mClientSecret = clientSecret;
    }

    @JsonProperty("fingerprint")
    public String getFingerprint() {
        return mFingerprint;
    }

    public void setFingerprint(String fingerprint) {
        this.mFingerprint = fingerprint;
    }

    @JsonProperty("scopes")
    public List<String> getScopes() {
        return mScopes;
    }

    public void setScopes(ArrayList<String> scopes) {
        mScopes = scopes;
    }
}