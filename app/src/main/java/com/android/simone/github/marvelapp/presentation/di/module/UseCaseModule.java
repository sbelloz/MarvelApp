package com.android.simone.github.marvelapp.presentation.di.module;

import com.android.simone.github.marvelapp.domain.interactor.UseCase;
import com.android.simone.github.marvelapp.domain.interactor.GetComicsUseCase;
import com.android.simone.github.marvelapp.presentation.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * @author Simone Bellotti
 */

@Module
public class UseCaseModule {

    public UseCaseModule() {
    }

    @Provides
    @ActivityScope
    UseCase provideGetComicListUseCase(GetComicsUseCase useCase) {
        return useCase;
    }
}
