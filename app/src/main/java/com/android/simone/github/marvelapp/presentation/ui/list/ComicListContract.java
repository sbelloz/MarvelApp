package com.android.simone.github.marvelapp.presentation.ui.list;

import com.android.simone.github.marvelapp.presentation.ui.BasePresenter;
import com.android.simone.github.marvelapp.presentation.viewmodel.ComicModel;

import java.util.List;

/**
 * @author Simone Bellotti
 */

public interface ComicListContract {

    interface Presenter extends BasePresenter {
        void loadNewPage();

        void retry();

        void onComicClicked(ComicModel model);
    }

    interface View {
        void showLoading();

        void hideLoading();

        void showEmpty();

        void hideEmpty();

        void showRetry();

        void hideRetry();

        void showComicList(List<ComicModel> comicList);

        void showComicDetail(ComicModel model);
    }
}
