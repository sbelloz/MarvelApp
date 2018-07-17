package com.android.simone.github.marvelapp.domain.interactor;

import com.android.simone.github.marvelapp.domain.executor.PostExecutionThread;
import com.android.simone.github.marvelapp.domain.executor.ThreadExecution;
import com.android.simone.github.marvelapp.domain.model.Comic;
import com.android.simone.github.marvelapp.domain.repository.ComicRepository;

import java.util.List;

import javax.inject.Inject;

import dagger.internal.Preconditions;
import rx.Observable;

/**
 * @author Simone Bellotti
 */

public class GetComicsUseCase
        extends UseCase<List<Comic>, GetComicsUseCase.Params> {

    private ComicRepository comicRepository;

    @Inject
    public GetComicsUseCase(ComicRepository comicRepository,
                            ThreadExecution threadExecution,
                            PostExecutionThread postExecutionThread) {
        super(threadExecution, postExecutionThread);
        this.comicRepository = comicRepository;
    }

    @Override
    protected Observable<List<Comic>> buildObservable(Params params) {
        Preconditions.checkNotNull(params);
        return comicRepository.getComics(params.page, params.characterId);
    }

    public static class Params {

        private final int page;
        private final String characterId;

        public Params(int page, String characterId) {
            this.page = page;
            this.characterId = characterId;
        }

        public static Params of(int page, String characterId) {
            return new Params(page, characterId);
        }
    }
}
