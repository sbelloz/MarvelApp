package com.android.simone.github.marvelapp.data.api.net;

import com.android.simone.github.marvelapp.data.api.entity.MarvelResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * @author Simone Bellotti
 */

public interface MarvelApiService {

    @GET("/v1/public/characters/{characterId}/comics")
    Call<MarvelResponse> getComics(@Path("characterId") String characterId,
                                   @Query("limit") int limit,
                                   @Query("offset") int offset);
}
