package com.android.simone.github.marvelapp.data.repository.datasource;

import com.android.simone.github.marvelapp.data.api.entity.ComicResponse;
import com.android.simone.github.marvelapp.data.api.entity.MarvelResponse;
import com.android.simone.github.marvelapp.data.api.entity.mapper.ComicEntityMapper;
import com.android.simone.github.marvelapp.data.api.exception.MarvelApiException;
import com.android.simone.github.marvelapp.data.api.net.MarvelApiClient;
import com.android.simone.github.marvelapp.data.api.net.MarvelApiService;
import com.android.simone.github.marvelapp.domain.datasource.ComicDataSource;
import com.android.simone.github.marvelapp.domain.model.Comic;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Response;
import rx.Observable;
import rx.Subscriber;

/**
 * @author Simone Bellotti
 */

@Singleton
public class CloudComicDataSource implements ComicDataSource {

    private final ComicEntityMapper entityMapper;
    private final MarvelApiService apiService;
    private final int comicsLimit;

    @Inject
    public CloudComicDataSource(MarvelApiClient apiClient,
                                ComicEntityMapper entityMapper,
                                 @Named("comic_per_page") int comicsLimit) {
        this.apiService = apiClient.createService(MarvelApiService.class);
        this.entityMapper = entityMapper;
        this.comicsLimit = comicsLimit;
    }

    @Override
    public Observable<List<Comic>> getComics(final int page, final String characterId) {
        return Observable.create(new Observable.OnSubscribe<List<Comic>>() {
            @Override
            public void call(Subscriber<? super List<Comic>> subscriber) {
                try {
                    int offset = calculateOffset(page);

                    Call<MarvelResponse> call = apiService.getComics(characterId, comicsLimit, offset);

                    Response<MarvelResponse> response = call.execute();

                    if (response.isSuccessful()) {
                        List<ComicResponse> comicList = response.body().getData().getResultList();
                        subscriber.onNext(entityMapper.transformCollection(comicList));
                        subscriber.onCompleted();
                    } else {
                        String status = response.body().getStatus();
                        subscriber.onError(new MarvelApiException(response.code(), status));
                    }

                } catch (Exception e) {
                    subscriber.onError(new MarvelApiException(e.getMessage(), e.getCause()));
                }
            }
        });
    }

    private int calculateOffset(int page) {
        return page * comicsLimit;
    }
}
