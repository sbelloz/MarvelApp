package com.android.simone.github.marvelapp.presentation.di;

import com.android.simone.github.marvelapp.presentation.di.component.ApplicationComponent;
import com.android.simone.github.marvelapp.presentation.di.component.ComicComponent;
import com.android.simone.github.marvelapp.presentation.di.component.DaggerApplicationComponent;
import com.android.simone.github.marvelapp.presentation.di.component.DaggerComicComponent;
import com.android.simone.github.marvelapp.presentation.di.module.ApplicationModule;
import com.android.simone.github.marvelapp.presentation.di.module.UseCaseModule;

/**
 * @author Simone Bellotti
 */

public final class ComponentProvider {

    private static ApplicationComponent applicationComponent;
    private static ComicComponent comicComponent;

    public static ApplicationComponent provideApplicationComponent() {
        if (applicationComponent == null) {
            applicationComponent = DaggerApplicationComponent.builder()
                    .applicationModule(new ApplicationModule())
                    .build();
        }
        return applicationComponent;
    }


    public static ComicComponent provideComicComponent(ApplicationComponent applicationComponent) {
        if (comicComponent == null) {
            comicComponent = DaggerComicComponent.builder()
                    .applicationComponent(applicationComponent)
                    .useCaseModule(new UseCaseModule())
                    .build();
        }
        return comicComponent;
    }
}
