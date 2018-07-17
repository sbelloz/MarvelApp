package com.android.simone.github.marvelapp.presentation.di.component;

import com.android.simone.github.marvelapp.domain.executor.PostExecutionThread;
import com.android.simone.github.marvelapp.domain.executor.ThreadExecution;
import com.android.simone.github.marvelapp.domain.repository.ComicRepository;
import com.android.simone.github.marvelapp.presentation.di.module.ApplicationModule;
import com.android.simone.github.marvelapp.presentation.di.module.ContextModule;
import com.android.simone.github.marvelapp.presentation.di.module.DataModule;
import com.android.simone.github.marvelapp.presentation.ui.BaseActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author Simone Bellotti
 */

@Singleton
@Component(modules = {ApplicationModule.class, DataModule.class, ContextModule.class})
public interface ApplicationComponent {
    void inject(BaseActivity baseActivity);

    ThreadExecution threadExecution();

    PostExecutionThread postExecutionThread();

    ComicRepository comicRepository();
}
