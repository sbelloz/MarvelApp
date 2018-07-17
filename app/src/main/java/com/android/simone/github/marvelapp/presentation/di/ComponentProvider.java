package com.android.simone.github.marvelapp.presentation.di;

import android.app.Activity;

import com.android.simone.github.marvelapp.presentation.di.component.ApplicationComponent;
import com.android.simone.github.marvelapp.presentation.di.component.ComicComponent;
import com.android.simone.github.marvelapp.presentation.di.component.DaggerApplicationComponent;
import com.android.simone.github.marvelapp.presentation.di.component.DaggerComicComponent;
import com.android.simone.github.marvelapp.presentation.di.module.ContextModule;

/**
 * @author Simone Bellotti
 */

public final class ComponentProvider {

    private static ApplicationComponent applicationComponent;
    private static ComicComponent comicComponent;

    public static ApplicationComponent provideApplicationComponent(Activity activity) {
//        if (applicationComponent == null) {
//            applicationComponent = DaggerApplicationComponent.builder()
//                    .contextModule(new ContextModule(activity))
//                    .build();
//        }
//        return applicationComponent;
        return DaggerApplicationComponent.builder()
                .contextModule(new ContextModule(activity))
                .build();
    }


    public static ComicComponent provideComicComponent(ApplicationComponent applicationComponent) {
//        if (comicComponent == null) {
//            comicComponent = DaggerComicComponent.builder()
//                    .applicationComponent(applicationComponent)
//                    .build();
//        }
//        return comicComponent;
        return DaggerComicComponent.builder()
                .applicationComponent(applicationComponent)
                .build();
    }
}
