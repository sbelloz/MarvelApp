package com.android.simone.github.marvelapp.presentation.ui.list;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.android.simone.github.marvelapp.R;
import com.android.simone.github.marvelapp.presentation.ui.BaseActivity;
import com.android.simone.github.marvelapp.presentation.ui.detail.ComicDetailView;
import com.android.simone.github.marvelapp.presentation.viewmodel.ComicModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ComicListActivity
        extends BaseActivity
        implements OnComicClickListener {

    public static Intent newIntent(Context context) {
        return new Intent(context, ComicListActivity.class);
    }

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.comic_list_view)
    ComicListView comicListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comic_list);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        initViews();
    }

    private void initViews() {
        comicListView.setOnComicClickListener(this);
    }

    private boolean isTwoPane() {
        return ButterKnife.findById(this, R.id.comic_detail_view) != null;
    }

    @Override
    public void onComicClick(ComicModel comic) {
        if (isTwoPane()) {
            ComicDetailView comicDetailView = ButterKnife.findById(this, R.id.comic_detail_view);
            comicDetailView.showComic(comic);
        } else {
            flowCoordinator.readComicDetail(comic);
        }
    }
}
