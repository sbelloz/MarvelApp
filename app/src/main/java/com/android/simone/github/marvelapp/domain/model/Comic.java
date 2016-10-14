package com.android.simone.github.marvelapp.domain.model;

import java.util.List;

/**
 * @author Simone Bellotti
 */

public class Comic {

    private String id;
    private String title;
    private String description;
    private String pageCount;
    private String thumbnail;
    private String year;
    private String price;
    private List<String> images;

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

    public String getPageCount() {
        return pageCount;
    }

    public void setPageCount(String pageCount) {
        this.pageCount = pageCount;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public List<String> getImages() {
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
    public String toString() {
        return "Comic{" +
                "year='" + year + '\'' +
                ", price='" + price + '\'' +
                ", pageCount='" + pageCount + '\'' +
                ", title='" + title + '\'' +
                ", images=" + images +
                '}';
    }
}
