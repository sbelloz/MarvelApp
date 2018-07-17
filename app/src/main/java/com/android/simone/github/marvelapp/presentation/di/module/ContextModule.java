package com.android.simone.github.marvelapp.presentation.di.module;

import android.app.Activity;

import dagger.Module;
import dagger.Provides;

/**
 * @author Simone Bellotti <simone.bellotti@immobiliare.it>
 */
@Module
public class ContextModule {

    private final Activity context;

    public ContextModule(Activity context) {
        this.context = context;
    }

    @Provides
    Activity provideContext() {
        return context;
    }
}
