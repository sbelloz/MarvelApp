package com.android.simone.github.marvelapp.presentation.ui.navigator;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.android.simone.github.marvelapp.presentation.ui.detail.ComicDetailActivity;
import com.android.simone.github.marvelapp.presentation.ui.list.ComicListActivity;
import com.android.simone.github.marvelapp.presentation.viewmodel.ComicViewModel;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * @author Simone Bellotti
 */
@Singleton
public class Navigator {

    @Inject
    public Navigator() {
    }

    public void finish(Activity activity) {
        activity.finish();
    }

    public void navigateToComicList(Context context) {
        Intent intent = ComicListActivity.newIntent(context);
        context.startActivity(intent);
    }

    public void navigateToComicDetail(Context context, ComicViewModel comic) {
        Intent intent = ComicDetailActivity.newIntent(context, comic);
        context.startActivity(intent);
    }
}
