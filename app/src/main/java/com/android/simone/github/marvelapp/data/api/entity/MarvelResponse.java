package com.android.simone.github.marvelapp.data.api.entity;

import com.google.gson.annotations.SerializedName;

/**
 * @author Simone Bellotti
 */

public class MarvelResponse {

    @SerializedName("code")
    private int code;
    @SerializedName("status")
    private String status;
    @SerializedName("data")
    private DataContainer data;

    public DataContainer getData() {
        return data;
    }

    public int getCode() {
        return code;
    }

    public String getStatus() {
        return status;
    }
}
