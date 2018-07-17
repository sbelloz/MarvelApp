package com.android.simone.github.marvelapp.presentation.ui.list;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.android.simone.github.marvelapp.R;
import com.android.simone.github.marvelapp.presentation.di.ComponentProvider;
import com.android.simone.github.marvelapp.presentation.ui.widget.recyclerview.EndlessScrollListener;
import com.android.simone.github.marvelapp.presentation.viewmodel.ComicModel;
import com.pnikosis.materialishprogress.ProgressWheel;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author Simone Bellotti
 */

public class ComicListView
        extends FrameLayout
        implements ComicListContract.View {

    @Inject
    @Named("comic_per_page")
    int comicPerPage;

    private Unbinder unbinder;

    @BindView(R.id.recycler_view)
    RecyclerView comicRecyclerView;
    @BindView(R.id.progress_wheel)
    ProgressWheel progressWheel;
    @BindView(R.id.empty_view)
    TextView emptyView;
    @BindView(R.id.btn_retry)
    Button btnRetry;

    @Inject
    ComicListPresenter presenter;
    @Inject
    ComicAdapter comicsAdapter;

    OnComicClickListener onComicClickListener;
    EndlessScrollListener endlessScrollListener;
    GridLayoutManager layoutManager;

    public ComicListView(Context context) {
        super(context);
        init(context);
    }

    public ComicListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ComicListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ComicListView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        initInjector();
        initRecyclerView();
        initRetryBtn();
        initAdapter();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        initPresenter();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (unbinder != null) {
            unbinder.unbind();
        }
        presenter.destroy();
    }

    @Override
    public void showLoading() {
        progressWheel.setVisibility(VISIBLE);
        comicRecyclerView.setVisibility(GONE);
    }

    @Override
    public void hideLoading() {
        progressWheel.setVisibility(GONE);
        comicRecyclerView.setVisibility(VISIBLE);
    }

    @Override
    public void showRetry() {
        showErrorView();
    }

    @Override
    public void hideRetry() {
        hideRetryView();
    }

    @Override
    public void showEmpty() {
        showEmptyView();
    }

    @Override
    public void hideEmpty() {
        hideEmptyView();
    }

    @Override
    public void showComicList(List<ComicModel> comicList) {
        endlessScrollListener.setAvailableItemCount(comicList.size());
        comicsAdapter.addAll(comicList);
        comicsAdapter.setIsLoading(false);
    }

    @Override
    public void showComicDetail(ComicModel comic) {
        if (onComicClickListener != null) {
            onComicClickListener.onComicClick(comic);
        }
    }


    public void setOnComicClickListener(OnComicClickListener onComicClickListener) {
        this.onComicClickListener = onComicClickListener;
    }

    private void init(Context context) {
        inflate(context, R.layout.comic_list_view, this);
        unbinder = ButterKnife.bind(this);
    }

    private void initInjector() {
        ComponentProvider.provideComicComponent(ComponentProvider.provideApplicationComponent((Activity)getContext()))
                .inject(this);
    }

    private void initPresenter() {
        presenter.bindView(this);
        presenter.start();
    }

    private void initRecyclerView() {
        layoutManager = new GridLayoutManager(getContext(), getResources().getInteger(R.integer.grid_span));

        comicRecyclerView.setLayoutManager(layoutManager);
        comicRecyclerView.setHasFixedSize(true);
        comicRecyclerView.setItemAnimator(new DefaultItemAnimator());
        initEndlessScrollListener(layoutManager, comicPerPage);
    }

    private void initAdapter() {
        comicsAdapter.setOnItemClickListener((view, item) -> presenter.onComicClicked(item));
        comicRecyclerView.setAdapter(comicsAdapter);

        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return comicsAdapter.isProgressView(position) ? layoutManager.getSpanCount() : 1;
            }
        });
    }

    private void initEndlessScrollListener(RecyclerView.LayoutManager layoutManager, int comicPerPage) {
        endlessScrollListener = new EndlessScrollListener(layoutManager, comicPerPage) {
            @Override
            public void onLoadMore() {
                comicsAdapter.setIsLoading(true);
                presenter.loadNewPage();
            }
        };
        comicRecyclerView.addOnScrollListener(endlessScrollListener);
    }

    private void initRetryBtn() {
        btnRetry.setOnClickListener(v -> presenter.retry());
    }

    private void showEmptyView() {
        comicRecyclerView.setVisibility(GONE);
        btnRetry.setVisibility(GONE);
        emptyView.setVisibility(VISIBLE);
        emptyView.setText(getContext().getString(R.string.no_results));
    }

    private void showErrorView() {
        comicRecyclerView.setVisibility(GONE);
        emptyView.setVisibility(VISIBLE);
        btnRetry.setVisibility(VISIBLE);
        emptyView.setText(getContext().getString(R.string.ops_something_went_wrong_retry));
    }

    private void hideEmptyView() {
        emptyView.setVisibility(GONE);
    }

    private void hideRetryView() {
        emptyView.setVisibility(GONE);
        btnRetry.setVisibility(GONE);
    }

    private void showRecyclerView() {
        comicRecyclerView.setVisibility(VISIBLE);
    }
}
