package com.android.simone.github.marvelapp.data.api.entity;

import com.google.gson.annotations.SerializedName;

/**
 * @author Simone Bellotti
 */

public class Price {

    @SerializedName("type")
    private String type;
    @SerializedName("price")
    private float price;

    public String getType() {
        return type;
    }

    public float getPrice() {
        return price;
    }
}
