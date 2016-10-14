package com.android.simone.github.marvelapp.data.repository.datasource;

import com.android.simone.github.marvelapp.data.api.ComicApiService;
import com.android.simone.github.marvelapp.data.api.entity.MarvelResponse;
import com.android.simone.github.marvelapp.data.api.entity.mapper.ComicEntityMapper;
import com.android.simone.github.marvelapp.data.api.net.MarvelApiClient;
import com.android.simone.github.marvelapp.domain.model.Comic;
import com.android.simone.github.marvelapp.domain.datasource.ComicDataSource;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

/**
 * @author Simone Bellotti <simone.bellotti@immobiliare.it>
 */

public class CloudComicDataSource implements ComicDataSource {

    private final static int LIMIT = 20;

    private final ComicEntityMapper mapper;
    private final MarvelApiClient apiClient;

    public CloudComicDataSource(MarvelApiClient apiClient, ComicEntityMapper mapper) {
        this.apiClient = apiClient;
        this.mapper = mapper;
    }

    @Override
    public List<Comic> getComics(int page, String characterId) {
        int offset = calculateOffset(page);
        Response<MarvelResponse> response = null;

        Call<MarvelResponse> call = apiClient.create(ComicApiService.class).getComics(characterId, LIMIT, offset);
        try {
           response = call.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return mapper.transformCollection(response.body().getData().getResultList());
    }

    private int calculateOffset(int page) {
        return page * LIMIT;
    }
}
