package com.android.simone.github.marvelapp.presentation.ui.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.android.simone.github.marvelapp.R;
import com.android.simone.github.marvelapp.presentation.ui.BaseActivity;
import com.android.simone.github.marvelapp.presentation.viewmodel.ComicModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author Simone Bellotti
 */

public class ComicDetailActivity extends BaseActivity {

    private static final String TAG = ComicDetailActivity.class.getSimpleName();
    public static final String EXTRA_COMIC = "comic";

    public static Intent newIntent(Context context, ComicModel comic) {
        return new Intent(context, ComicDetailActivity.class)
                .putExtra(EXTRA_COMIC, comic);
    }

    Unbinder unbinder;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.comic_detail_view)
    ComicDetailView comicDetailView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comic_detail);
        unbinder = ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        setDisplayHomeAsUpEnabled(true);

        ComicModel comic = getComicFromIntent(getIntent());
        if (comic != null) {
            comicDetailView.showComic(comic);
        } else {
            Log.e(TAG, "You have passed a null comic");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder != null) {
            unbinder.unbind();
        }
    }

    private ComicModel getComicFromIntent(Intent intent) {
        return intent.getParcelableExtra(EXTRA_COMIC);
    }
}
