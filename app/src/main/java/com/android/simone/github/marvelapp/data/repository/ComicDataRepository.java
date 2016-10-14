package com.android.simone.github.marvelapp.data.repository;

import com.android.simone.github.marvelapp.domain.model.Comic;
import com.android.simone.github.marvelapp.domain.datasource.ComicDataSource;
import com.android.simone.github.marvelapp.domain.repository.ComicRepository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;

/**
 * @author Simone Bellotti
 */

@Singleton
public class ComicDataRepository implements ComicRepository {

    private final ComicDataSource dataSource;

    @Inject
    public ComicDataRepository(ComicDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Observable<List<Comic>> getComics(int page, String characterId) {
        return dataSource.getComics(page, characterId);
    }

}
