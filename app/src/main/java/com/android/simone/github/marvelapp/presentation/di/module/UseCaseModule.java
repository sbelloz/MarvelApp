package com.android.simone.github.marvelapp.presentation.di.module;

import com.android.simone.github.marvelapp.domain.interactor.BaseUseCase;
import com.android.simone.github.marvelapp.domain.interactor.GetComicsUseCase;

import dagger.Module;

/**
 * @author Simone Bellotti
 */

@Module
public class UseCaseModule {

    public UseCaseModule() {
    }

    BaseUseCase provideGetComicListUseCase(GetComicsUseCase useCase) {
        return useCase;
    }
}
