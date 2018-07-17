package com.android.simone.github.marvelapp.presentation.ui.navigator;

import com.android.simone.github.marvelapp.presentation.viewmodel.ComicModel;

import javax.inject.Inject;

/**
 * @author Simone Bellotti <simone.bellotti@immobiliare.it>
 */


public class ComicFlowCoordinator {

    private final Navigator navigator;

    @Inject
    public ComicFlowCoordinator(Navigator navigator) {
        this.navigator = navigator;
    }


    public void start() {
        /* no-op */
    }

    public void readComicDetail(ComicModel comicModel) {
        navigator.showComicDetail(comicModel);
    }

    public void closeScreen() {
        navigator.close();
    }

}
