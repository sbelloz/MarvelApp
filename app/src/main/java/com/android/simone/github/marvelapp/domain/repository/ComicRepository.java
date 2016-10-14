package com.android.simone.github.marvelapp.domain.repository;

import com.android.simone.github.marvelapp.domain.model.Comic;

import java.util.List;

import rx.Observable;

/**
 * @author Simone Bellotti
 */

public interface ComicRepository {

    Observable<List<Comic>> getComics(int page, String characterId);
}
