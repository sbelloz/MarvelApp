package com.android.simone.github.marvelapp.presentation;

import android.app.Application;

import com.android.simone.github.marvelapp.BuildConfig;
import com.android.simone.github.marvelapp.presentation.di.ComponentProvider;
import com.android.simone.github.marvelapp.presentation.di.component.ApplicationComponent;
import com.squareup.leakcanary.LeakCanary;

/**
 * @author Simone Bellotti
 */

public class MarvelApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }

        if (BuildConfig.DEBUG) {
            LeakCanary.install(this);
        }
    }

    public ApplicationComponent getApplicationComponent() {
        return ComponentProvider.provideApplicationComponent();
    }

}
