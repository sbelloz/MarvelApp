package com.android.simone.github.marvelapp.data.api.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author Simone Bellotti
 */

public class DataContainer {

    @SerializedName("offset")
    private int offset;
    @SerializedName("limit")
    private int limit;
    @SerializedName("total")
    private int total;
    @SerializedName("count")
    private int count;
    @SerializedName("results")
    private List<ComicResponse> resultList;

    public List<ComicResponse> getResultList() {
        return resultList;
    }
}
