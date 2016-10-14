package com.android.simone.github.marvelapp.presentation.ui.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.android.simone.github.marvelapp.R;
import com.android.simone.github.marvelapp.presentation.ui.BaseActivity;
import com.android.simone.github.marvelapp.presentation.viewmodel.ComicViewModel;

import butterknife.BindView;

/**
 * @author Simone Bellotti <simone.bellotti@immobiliare.it>
 */

public class ComicDetailActivity extends BaseActivity {

    public static final String EXTRA_COMIC = "comic";

    public static Intent newIntent(Context context, ComicViewModel comic) {
        return new Intent(context, ComicDetailActivity.class)
                .putExtra(EXTRA_COMIC, comic);
    }

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comic_detail);
        setSupportActionBar(toolbar);

        setDisplayHomeAsUpEnabled(true);
    }
}
