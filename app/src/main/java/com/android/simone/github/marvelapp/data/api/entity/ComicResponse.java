package com.android.simone.github.marvelapp.data.api.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author Simone Bellotti <simone.bellotti@immobiliare.it>
 */

public class ComicResponse {

    @SerializedName("id")
    private String id;
    @SerializedName("title")
    private String title;
    @SerializedName("variantDescription")
    private String variantDescription;
    @SerializedName("description")
    private String description;
    @SerializedName("pageCount")
    private String pageCount;
    @SerializedName("dates")
    private List<Date> dates;
    @SerializedName("thumbnail")
    private Image thumbnail;
    @SerializedName("images")
    private List<Image> images;

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getVariantDescription() {
        return variantDescription;
    }

    public String getDescription() {
        return description;
    }

    public String getPageCount() {
        return pageCount;
    }

    public Image getThumbnail() {
        return thumbnail;
    }

    public List<Image> getImages() {
        return images;
    }
}
