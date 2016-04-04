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
public class ReposRequestParams implements Serializable {

    private String mVisibility;
    private String mAffilation;
    private String mType;
    private String mSort;
    private String mDirection;

    public ReposRequestParams() {
    }

    @JsonProperty("affilation")
    public String getAffilation() {
        return mAffilation;
    }

    public void setAffilation(String affilation) {
        mAffilation = affilation;
    }

    @JsonProperty("type")
    public String getType() {
        return mType;
    }

    public void setType(String type) {
        mType = type;
    }

    @JsonProperty("sort")
    public String getSort() {
        return mSort;
    }

    public void setSort(String sort) {
        mSort = sort;
    }

    @JsonProperty("direction")
    public String getDirection() {
        return mDirection;
    }

    public void setDirection(String direction) {
        mDirection = direction;
    }

    @JsonProperty("visibility")
    public String getVisibility() {
        return mVisibility;
    }

    public void setVisibility(String visibility) {
        mVisibility = visibility;
    }
}