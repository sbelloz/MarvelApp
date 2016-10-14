package com.android.simone.github.marvelapp.data.repository.datasource;

import com.android.simone.github.marvelapp.data.api.entity.mapper.ComicEntityMapper;
import com.android.simone.github.marvelapp.data.api.net.MarvelApiClient;
import com.android.simone.github.marvelapp.domain.model.Comic;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * @author Simone Bellotti
 */
public class CloudComicDataSourceTest {

    CloudComicDataSource dataSource;
    int page;
    String characterId;

    public CloudComicDataSourceTest() {
    }

    @Before
    public void setUp() throws Exception {
        dataSource = new CloudComicDataSource(new MarvelApiClient(), new ComicEntityMapper());
        page = 0;
        characterId = "1009220";
    }

    @Test
    public void getComics() throws Exception {
        List<Comic> comicList = dataSource.getComics(page, characterId);
        System.out.println(comicList);
        assertTrue(!comicList.isEmpty());
    }

}