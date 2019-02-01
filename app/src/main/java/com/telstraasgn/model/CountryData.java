package com.telstraasgn.model;

import com.google.gson.annotations.SerializedName;
import com.telstraasgn.utils.Constants;

public class CountryData {

    @SerializedName(Constants.API_TITLE)
    private String title;
    @SerializedName(Constants.API_DESC)
    private String description;
    @SerializedName(Constants.API_IMAGE)
    private String imageHref;

    public CountryData(String title, String description, String imageHref) {
        this.title = title;
        this.description = description;
        this.imageHref = imageHref;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageHref() {
        return imageHref;
    }

    public void setImageHref(String imageHref) {
        this.imageHref = imageHref;
    }
}
