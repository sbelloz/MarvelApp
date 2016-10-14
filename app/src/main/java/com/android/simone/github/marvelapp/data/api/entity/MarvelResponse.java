package com.android.simone.github.marvelapp.data.api.entity;

import com.google.gson.annotations.SerializedName;

/**
 * @author Simone Bellotti <simone.bellotti@immobiliare.it>
 */

public class MarvelResponse {

    @SerializedName("code")
    private int code;
    @SerializedName("status")
    private String status;
    @SerializedName("data")
    private DataApiContainer data;

    public DataApiContainer getData() {
        return data;
    }
}
