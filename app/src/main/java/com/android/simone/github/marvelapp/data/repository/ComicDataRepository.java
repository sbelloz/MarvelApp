package com.android.simone.github.marvelapp.data.repository;

import com.android.simone.github.marvelapp.domain.model.Comic;
import com.android.simone.github.marvelapp.domain.datasource.ComicDataSource;
import com.android.simone.github.marvelapp.domain.repository.ComicRepository;

import java.util.List;

/**
 * @author Simone Bellotti <simone.bellotti@immobiliare.it>
 */

public class ComicDataRepository implements ComicRepository {

    private final ComicDataSource dataSource;

    public ComicDataRepository(ComicDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Comic> getComics(int page, String characterId) {
        return dataSource.getComics(page, characterId);
    }

}
