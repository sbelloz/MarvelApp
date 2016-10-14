package com.android.simone.github.marvelapp.presentation.viewmodel;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * @author Simone Bellotti
 */

public class ComicViewModel implements Parcelable {

    private String id;
    private String title;
    private String description;
    private String thumbnailUrl;
    private String year;
    private List<String> images;
    private String price;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public List<String> getImageList() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.title);
        dest.writeString(this.description);
        dest.writeString(this.thumbnailUrl);
        dest.writeString(this.year);
        dest.writeStringList(this.images);
        dest.writeString(this.price);
    }

    public ComicViewModel() {
    }

    protected ComicViewModel(Parcel in) {
        this.id = in.readString();
        this.title = in.readString();
        this.description = in.readString();
        this.thumbnailUrl = in.readString();
        this.year = in.readString();
        this.images = in.createStringArrayList();
        this.price = in.readString();
    }

    public static final Parcelable.Creator<ComicViewModel> CREATOR = new Parcelable.Creator<ComicViewModel>() {
        @Override
        public ComicViewModel createFromParcel(Parcel source) {
            return new ComicViewModel(source);
        }

        @Override
        public ComicViewModel[] newArray(int size) {
            return new ComicViewModel[size];
        }
    };
}
