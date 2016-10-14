package com.android.simone.github.marvelapp.presentation.ui.list;

import com.android.simone.github.marvelapp.presentation.ui.BasePresenter;
import com.android.simone.github.marvelapp.presentation.viewmodel.ComicViewModel;

import java.util.List;

/**
 * @author Simone Bellotti <simone.bellotti@immobiliare.it>
 */

public interface ComicListContract {

    interface Presenter extends BasePresenter {
        void onNewPage();

        void showComicDetail(ComicViewModel model);
    }

    interface View {
        void showLoading();

        void showError(String message);

        void showEmpty();

        void showComicList(List<ComicViewModel> comicList);
    }
}
