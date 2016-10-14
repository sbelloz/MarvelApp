package com.android.simone.github.marvelapp.presentation.ui.list;

import android.util.Log;

import com.android.simone.github.marvelapp.domain.interactor.UseCase;
import com.android.simone.github.marvelapp.domain.model.Comic;
import com.android.simone.github.marvelapp.presentation.Constants;
import com.android.simone.github.marvelapp.presentation.di.scope.ActivityScope;
import com.android.simone.github.marvelapp.presentation.mapper.ComicViewModelMapper;
import com.android.simone.github.marvelapp.presentation.viewmodel.ComicViewModel;

import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;

import static com.bumptech.glide.gifdecoder.GifHeaderParser.TAG;

/**
 * @author Simone Bellotti
 */
@ActivityScope
public class ComicListPresenter implements ComicListContract.Presenter {

    private int page = 0;
    private final String characterId = Constants.CAPTAIN_AMERICA_ID;

    private final UseCase getComicsUseCase;
    private final ComicViewModelMapper viewModelMapper;

    private ComicListContract.View comicListView;

    @Inject
    public ComicListPresenter(UseCase useCase,
                              ComicViewModelMapper viewModelMapper) {
        this.getComicsUseCase = useCase;
        this.viewModelMapper = viewModelMapper;
    }

    public void bindView(ComicListContract.View view) {
        comicListView = view;
    }

    @Override
    public void start() {
        comicListView.hideEmpty();
        comicListView.hideRetry();
        comicListView.showLoading();
        getComicList(page);
    }

    @Override
    public void destroy() {
        releaseRefs();
    }

    @Override
    public void loadNewPage() {
        Log.d("ComicListPresenter", "loadNewPage: " + page);
        getComicList(++page);
    }

    @Override
    public void retry() {
        comicListView.hideRetry();
        comicListView.showLoading();
        getComicList(page);
    }

    @Override
    public void onComicClicked(ComicViewModel model) {
        comicListView.showComicDetail(model);
    }

    private void getComicList(int page) {
        getComicsUseCase.execute(new ComicListSubscriber(), page, characterId);
    }

    private void releaseRefs() {
        getComicsUseCase.unsubscribe();
        comicListView = null;
        page = 0;
    }

    private final class ComicListSubscriber extends Subscriber<List<Comic>> {

        @Override
        public void onCompleted() {
            comicListView.hideLoading();
        }

        @Override
        public void onError(Throwable e) {
            Log.e(TAG, "onError: " + e);
            comicListView.hideLoading();
            comicListView.showRetry(); //TODO show a clear message for user
        }

        @Override
        public void onNext(List<Comic> comicList) {
            comicListView.showComicList(viewModelMapper.transformCollection(comicList));
        }

    }
}
