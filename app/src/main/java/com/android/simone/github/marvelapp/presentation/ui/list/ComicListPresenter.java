package com.android.simone.github.marvelapp.presentation.ui.list;

import com.android.simone.github.marvelapp.data.repository.ComicDataRepository;
import com.android.simone.github.marvelapp.data.api.entity.mapper.ComicEntityMapper;
import com.android.simone.github.marvelapp.data.api.net.MarvelApiClient;
import com.android.simone.github.marvelapp.data.repository.datasource.CloudComicDataSource;
import com.android.simone.github.marvelapp.domain.interactor.GetComicsUseCase;

/**
 * @author Simone Bellotti <simone.bellotti@immobiliare.it>
 */

public class ComicListPresenter implements ComicListContract.Presenter {

    public static final String CHARACTER_ID = "1009220";

    private int page;
    private final GetComicsUseCase useCase;

    public ComicListPresenter(GetComicsUseCase useCase) {
        //TODO INJECT
        this.useCase = new GetComicsUseCase(
                new ComicDataRepository(
                        new CloudComicDataSource(
                                new MarvelApiClient(),
                                new ComicEntityMapper())),
                CHARACTER_ID);
        this.page = 0;
    }

    @Override
    public void start() {
        useCase.execute(page);
    }

    @Override
    public void destroy() {

    }


    @Override
    public void onNewPage() {
        useCase.execute(++page);
    }
}
