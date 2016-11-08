package com.android.simone.github.marvelapp.data.api.entity.mapper;

import com.android.simone.github.marvelapp.data.api.entity.Date;
import com.android.simone.github.marvelapp.data.api.entity.Price;
import com.android.simone.github.marvelapp.data.api.entity.ComicResponse;
import com.android.simone.github.marvelapp.data.api.entity.Image;
import com.android.simone.github.marvelapp.domain.mapper.Mapper;
import com.android.simone.github.marvelapp.domain.model.Comic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * @author Simone Bellotti
 */

@Singleton
public class ComicEntityMapper implements Mapper<ComicResponse, Comic>  {

    public static final String PRICE_KEY = "printPrice";
    public static final String YEAR_KEY = "onsaleDate";

    @Inject
    public ComicEntityMapper() {
    }

    @Override
    public Comic transform(ComicResponse response) {
        Comic comic = new Comic();
        comic.setId(response.getId());
        comic.setTitle(response.getTitle());
        comic.setDescription(response.getDescription());
        comic.setPageCount(response.getPageCount());
        comic.setThumbnail(parseImage(response.getThumbnail()));
        comic.setYear(parseYears(response.getDates()));
        comic.setPrice(parsePrices(response.getPrices()));
        comic.setImages(parseImages(response.getImages()));
        return comic;
    }

    @Override
    public List<Comic> transformCollection(Collection<ComicResponse> fromList) {
        List<Comic> comicList = new ArrayList<>();
        for (ComicResponse response : fromList) {
            comicList.add(transform(response));
        }
        return comicList;
    }

    private String parsePrices(List<Price> priceList) {
        for (Price price : priceList) {
            if (price.getType().equals(PRICE_KEY)) {
                return parsePrice(price);
            }
        }
        return null;
    }

    private String parsePrice(Price price) {
        return "$" + price.getPrice();
    }

    private String parseYears(List<Date> dateList) {
        for (Date date : dateList) {
            if (date.getType().equals(YEAR_KEY)) {
                return parseYear(date);
            }
        }
        return null;
    }

    private String parseYear(Date date) {
        return date.getDate().substring(0, 4);
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
