package com.android.simone.github.marvelapp.presentation.ui.detail;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.design.widget.CollapsingToolbarLayout;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.simone.github.marvelapp.R;
import com.android.simone.github.marvelapp.presentation.Utils;
import com.android.simone.github.marvelapp.presentation.viewmodel.ComicModel;
import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author Simone Bellotti
 */

public class ComicDetailView
        extends FrameLayout
        implements ComicDetailContract.View {

    Unbinder unbinder;

    @BindView(R.id.title_view)
    TextView titleView;
    @BindView(R.id.price_view)
    TextView priceView;
    @BindView(R.id.comic_image)
    ImageView comicImage;
    @BindView(R.id.year_view)
    TextView yearView;
    @BindView(R.id.description_view)
    TextView descriptionView;
    @BindView(R.id.root_container)
    ViewGroup rootContainer;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbarLayout;

    public ComicDetailView(Context context) {
        super(context);
        init(context);
    }

    public ComicDetailView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ComicDetailView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ComicDetailView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (unbinder != null) {
            unbinder.unbind();
        }
    }

    @Override
    public void showComic(final ComicModel comic) {
        showContainer(true);

        if (collapsingToolbarLayout != null) {
            collapsingToolbarLayout.setTitle(comic.getTitle());
        }

        titleView.setText(comic.getTitle());
        yearView.setText(comic.getYear());
        descriptionView.setText(comic.getDescription());
        priceView.setText(comic.getPrice());

        Glide.with(getContext())
                .load(getRandomImage(comic))
                .centerCrop()
                .into(comicImage);
    }

    private void init(Context context) {
        inflate(context, R.layout.comic_detail_view, this);
        unbinder = ButterKnife.bind(this);

        showContainer(false);
//        collapsingToolbarLayout = ButterKnife.findById(this, R.id.collapsing_toolbar);
    }

    private void showContainer(boolean show) {
        rootContainer.setVisibility(show ? VISIBLE : GONE);
    }

    private String getRandomImage(ComicModel comic) {
        String imageUrl;
        if (comic.getImageList() != null && !comic.getImageList().isEmpty()) {
            int index = Utils.getRandomInt(0, comic.getImageList().size() - 1);
            imageUrl = comic.getImageList().get(index);
        } else {
            imageUrl = comic.getThumbnailUrl();
        }
        return imageUrl;
    }

}
