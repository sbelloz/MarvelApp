package com.android.simone.github.marvelapp.domain.interactor;

import com.android.simone.github.marvelapp.domain.executor.PostExecutionThread;
import com.android.simone.github.marvelapp.domain.executor.ThreadExecution;
import com.android.simone.github.marvelapp.domain.repository.ComicRepository;

import java.util.Arrays;

import javax.inject.Inject;

import rx.Observable;

/**
 * @author Simone Bellotti
 */

public class GetComicsUseCase extends UseCase<Object> {

    private ComicRepository comicRepository;

    @Inject
    public GetComicsUseCase(ComicRepository comicRepository,
                            ThreadExecution threadExecution,
                            PostExecutionThread postExecutionThread) {
        super(threadExecution, postExecutionThread);
        this.comicRepository = comicRepository;
    }

    @Override
    protected Observable buildObservable(Object... params) {
        if (params != null && params.length == 2) {
            return comicRepository.getComics((Integer) params[0], (String) params[1]);
        } else {
            throw new UnsupportedOperationException("Must provide mandatory params: " + Arrays.toString(params));
        }
    }
}
