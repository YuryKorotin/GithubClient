package android.hipster.githubclient.net.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

/**
 * Created by yurykorotin on 03/04/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CommitsList extends ArrayList<CommitData>{
    public CommitsList() {

    }
}
