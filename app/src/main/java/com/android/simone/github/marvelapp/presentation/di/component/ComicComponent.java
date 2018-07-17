package com.android.simone.github.marvelapp.presentation.di.component;

import com.android.simone.github.marvelapp.presentation.di.module.DataModule;
import com.android.simone.github.marvelapp.presentation.di.module.UseCaseModule;
import com.android.simone.github.marvelapp.presentation.di.scope.ActivityScope;
import com.android.simone.github.marvelapp.presentation.ui.list.ComicListView;

import dagger.Component;

/**
 * @author Simone Bellotti
 */
@ActivityScope
@Component(dependencies = ApplicationComponent.class,
        modules = {DataModule.class, UseCaseModule.class})
public interface ComicComponent {

    void inject(ComicListView comicListView);
}
