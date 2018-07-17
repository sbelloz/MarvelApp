package com.android.simone.github.marvelapp.presentation.ui.navigator;

import android.app.Activity;
import android.content.Intent;

import com.android.simone.github.marvelapp.presentation.ui.detail.ComicDetailActivity;
import com.android.simone.github.marvelapp.presentation.ui.list.ComicListActivity;
import com.android.simone.github.marvelapp.presentation.viewmodel.ComicModel;

import javax.inject.Inject;

/**
 * @author Simone Bellotti <simone.bellotti@immobiliare.it>
 */

public class Navigator {

    private Activity activity;

    @Inject
    public Navigator(Activity activity) {
        this.activity = activity;
    }

    public void close() {
        activity.finish();
    }

    public void showComicList() {
        Intent intent = ComicListActivity.newIntent(activity);
        activity.startActivity(intent);
    }

    public void showComicDetail(ComicModel comicModel) {
        Intent intent = ComicDetailActivity.newIntent(activity, comicModel);
        activity.startActivity(intent);
    }

}
