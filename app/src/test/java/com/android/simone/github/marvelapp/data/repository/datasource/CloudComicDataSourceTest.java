package com.android.simone.github.marvelapp.data.repository.datasource;

import com.android.simone.github.marvelapp.data.api.entity.mapper.ComicEntityMapper;
import com.android.simone.github.marvelapp.data.api.net.MarvelApiClient;
import com.android.simone.github.marvelapp.domain.datasource.ComicDataSource;
import com.android.simone.github.marvelapp.domain.model.Comic;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import rx.Observable;
import rx.observers.TestSubscriber;

/**
 * @author Simone Bellotti
 */
public class CloudComicDataSourceTest {

    private int page;

    private String characterId;

    public CloudComicDataSourceTest() {
    }

    @Before
    public void setUp() throws Exception {
        page = 0;
        characterId = "1009220";
    }

    @Test
    public void testComicListObservableCreation() throws Exception {
        ComicDataSource dataSource = new CloudComicDataSource(new MarvelApiClient(), new ComicEntityMapper(), 20);
        TestSubscriber<List<Comic>> testSubscriber = provideTestSubscriber(dataSource);

        testSubscriber.assertNoErrors();
    }

    @Test
    public void testComicList_success() throws Exception {
        ComicDataSource dataSource = new CloudComicDataSource(new MarvelApiClient(), new ComicEntityMapper(), 20);
        TestSubscriber<List<Comic>> testSubscriber = provideTestSubscriber(dataSource);
        Assert.assertTrue(!testSubscriber.getOnNextEvents().isEmpty());
    }

    @Test
    public void testComicList_error() throws Exception {
        ComicDataSource dataSource = new CloudComicDataSource(new MarvelApiClient(null, null), new ComicEntityMapper(), 20);
        TestSubscriber<List<Comic>> testSubscriber = provideTestSubscriber(dataSource);
        testSubscriber.assertNotCompleted();
    }

    private TestSubscriber<List<Comic>> provideTestSubscriber(ComicDataSource dataSource) {
        Observable<List<Comic>> comicListObservable = dataSource.getComics(page, characterId);
        TestSubscriber<List<Comic>> testSubscriber = new TestSubscriber<>();
        comicListObservable.subscribe(testSubscriber);
        return testSubscriber;
    }
}