package com.android.simone.github.marvelapp.data.repository.datasource;

import com.android.simone.github.marvelapp.data.api.entity.mapper.ComicEntityMapper;
import com.android.simone.github.marvelapp.data.api.net.MarvelApiClient;
import com.android.simone.github.marvelapp.domain.datasource.ComicDataSource;
import com.android.simone.github.marvelapp.domain.model.Comic;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import rx.Observable;
import rx.observers.TestSubscriber;

import static junit.framework.Assert.*;

/**
 * @author Simone Bellotti
 */
public class CloudComicDataSourceTest {


    TestSubscriber<List<Comic>> testSubscriber = new TestSubscriber<>();

    int page;
    String characterId;
    String publicKey;
    String privateKey;


    public CloudComicDataSourceTest() {
    }

    @Before
    public void setUp() throws Exception {
        page = 0;
        characterId = "1009220";
        publicKey = "6a7ed890b4b941a925202a5630d5b162";
        privateKey = "0f1d0fdf46a0bf32f962b0b9997233c0395cdf8e";
    }

    @Test
    public void testComicListObservableCreation() throws Exception {
        ComicDataSource dataSource = new CloudComicDataSource(new MarvelApiClient(publicKey, privateKey), new ComicEntityMapper(), 20);
        TestSubscriber<List<Comic>> testSubscriber = provideTestSubscriber(dataSource);

        testSubscriber.assertNoErrors();
    }

    @Test
    public void getComicList_withSuccess() throws Exception {
        ComicDataSource dataSource = new CloudComicDataSource(new MarvelApiClient(publicKey, privateKey), new ComicEntityMapper(), 20);
        TestSubscriber<List<Comic>> testSubscriber = provideTestSubscriber(dataSource);
        assertTrue(!testSubscriber.getOnNextEvents().isEmpty());
    }

    @Test
    public void testComicList_error() throws Exception {
        ComicDataSource dataSource = new CloudComicDataSource(new MarvelApiClient(null, null), new ComicEntityMapper(), 20);
        TestSubscriber<List<Comic>> testSubscriber = provideTestSubscriber(dataSource);
        testSubscriber.assertNotCompleted();
    }

    private TestSubscriber<List<Comic>> provideTestSubscriber(ComicDataSource dataSource) {
        Observable<List<Comic>> comicListObservable = dataSource.getComics(page, characterId);
        comicListObservable.subscribe(testSubscriber);
        return testSubscriber;
    }
}