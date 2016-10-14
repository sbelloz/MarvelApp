package com.android.simone.github.marvelapp.domain.repository;

import com.android.simone.github.marvelapp.domain.model.Comic;

import java.util.List;

/**
 * @author Simone Bellotti
 */

public interface ComicRepository {


    List<Comic> getComics(String characterId);
}
