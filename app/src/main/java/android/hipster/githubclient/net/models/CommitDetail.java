package android.hipster.githubclient.net.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by yurykorotin on 05/04/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CommitDetail implements Serializable{
    private String mShortMessage;
    private CommitAuthor mAuthor;

    @JsonProperty("message")
    public String getShortMessage() {
        return mShortMessage;
    }

    public void setShortMessage(String shortMessage) {
        mShortMessage = shortMessage;
    }

    @JsonProperty("author")
    public CommitAuthor getAuthor() {
        return mAuthor;
    }

    public void setAuthor(CommitAuthor author) {
        mAuthor = author;
    }
}
