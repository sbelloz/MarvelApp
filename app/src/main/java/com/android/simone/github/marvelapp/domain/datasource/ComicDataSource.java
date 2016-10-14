package com.android.simone.github.marvelapp.domain.datasource;

import com.android.simone.github.marvelapp.domain.model.Comic;

import java.util.List;

import rx.Observable;

/**
 * @author Simone Bellotti
 */

public interface ComicDataSource {

    Observable<List<Comic>> getComics(int page, String characterId);
}
