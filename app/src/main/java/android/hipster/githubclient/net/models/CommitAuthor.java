package android.hipster.githubclient.net.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by yurykorotin on 05/04/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CommitAuthor implements Serializable {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-ddTHH:mmZ")
    private Date mDate;
    private String mEmail;
    private String mName;

    @JsonProperty("date")
    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    @JsonProperty("email")
    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    @JsonProperty("name")
    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }
}
