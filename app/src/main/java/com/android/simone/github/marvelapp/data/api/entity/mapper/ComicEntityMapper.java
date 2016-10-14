package com.android.simone.github.marvelapp.data.api.entity.mapper;

import com.android.simone.github.marvelapp.data.api.entity.ComicResponse;
import com.android.simone.github.marvelapp.data.api.entity.Image;
import com.android.simone.github.marvelapp.domain.model.Comic;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Simone Bellotti <simone.bellotti@immobiliare.it>
 */

public class ComicEntityMapper {

    public Comic transform(ComicResponse response) {
        Comic comic = new Comic();
        comic.setId(response.getId());
        comic.setTitle(response.getTitle());
        comic.setDescription(response.getDescription());
        comic.setPageCount(response.getPageCount());
        comic.setThumbnail(parseImage(response.getThumbnail()));
        comic.setYear("DUMMY");//TODO
        comic.setImages(parseImages(response.getImages()));
        return comic;
    }

    public List<Comic> transformCollection(List<ComicResponse> responseList) {
        List<Comic> comicList = new ArrayList<>();
        for (ComicResponse response : responseList) {
            comicList.add(transform(response));
        }
        return comicList;
    }

    private String parseImage(Image image) {
        return image.getPath() + "." + image.getExtension();
    }

    private List<String> parseImages(List<Image> imageList) {
        List<String> images = new ArrayList<>();
        for (Image image : imageList) {
            images.add(parseImage(image));
        }
        return images;
    }
}
