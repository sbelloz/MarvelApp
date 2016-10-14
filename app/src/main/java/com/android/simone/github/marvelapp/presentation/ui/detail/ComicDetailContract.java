package com.android.simone.github.marvelapp.presentation.ui.detail;

import com.android.simone.github.marvelapp.presentation.ui.BasePresenter;
import com.android.simone.github.marvelapp.presentation.viewmodel.ComicViewModel;

/**
 * @author Simone Bellotti
 */

public interface ComicDetailContract {

    interface Presenter extends BasePresenter {

    }

    interface View {
        void showComic(ComicViewModel comic);
    }
}
