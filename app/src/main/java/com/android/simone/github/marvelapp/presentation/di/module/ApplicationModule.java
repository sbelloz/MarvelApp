package com.android.simone.github.marvelapp.presentation.di.module;

import com.android.simone.github.marvelapp.data.api.net.MarvelApiClient;
import com.android.simone.github.marvelapp.data.repository.ComicDataRepository;
import com.android.simone.github.marvelapp.data.repository.datasource.CloudComicDataSource;
import com.android.simone.github.marvelapp.domain.datasource.ComicDataSource;
import com.android.simone.github.marvelapp.domain.executor.JobExecutor;
import com.android.simone.github.marvelapp.domain.executor.PostExecutionThread;
import com.android.simone.github.marvelapp.domain.executor.ThreadExecution;
import com.android.simone.github.marvelapp.domain.repository.ComicRepository;
import com.android.simone.github.marvelapp.presentation.UIThread;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author Simone Bellotti
 */

@Module
public class ApplicationModule {

    public ApplicationModule() {
    }

    @Provides
    @Singleton
    ThreadExecution provideThreadExecution(JobExecutor jobExecutor) {
        return jobExecutor;
    }

    @Provides
    @Singleton
    PostExecutionThread providePostExecutionThread(UIThread uiThread) {
        return uiThread;
    }

    @Provides
    @Singleton
    ComicRepository provideComicRepository(ComicDataRepository comicDataRepository) {
        return comicDataRepository;
    }

    @Provides
    @Singleton
    ComicDataSource provideComicDataSource(CloudComicDataSource cloudComicDataSource) {
        return cloudComicDataSource;
    }

    @Provides
    @Singleton
    MarvelApiClient provideMarvelApiClient(@Named("marvel_public_api_key") String publicKey,
                                           @Named("marvel_private_api_key") String privateKey) {
        return new MarvelApiClient(publicKey, privateKey);
    }

//    @Provides
//    @Singleton
//    ComicFlowCoordinator provideComicFlowCoordinator(ComicFlowCoordinator comicFlowCoordinator) {
//        return comicFlowCoordinator;
//    }

}
