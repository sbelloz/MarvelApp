package com.android.simone.github.marvelapp.data.api.entity;

import com.google.gson.annotations.SerializedName;

/**
 * @author Simone Bellotti <simone.bellotti@immobiliare.it>
 */

public class Image {

    @SerializedName("path")
    private String path;
    @SerializedName("extension")
    private String extension;

    public String getPath() {
        return path;
    }

    public String getExtension() {
        return extension;
    }


}
