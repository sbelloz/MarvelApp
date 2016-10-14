package com.android.simone.github.marvelapp.domain.datasource;

import com.android.simone.github.marvelapp.domain.model.Comic;

import java.util.List;

/**
 * @author Simone Bellotti <simone.bellotti@immobiliare.it>
 */

public interface ComicDataSource {

    List<Comic> getComics(int page, String characterId);
}
