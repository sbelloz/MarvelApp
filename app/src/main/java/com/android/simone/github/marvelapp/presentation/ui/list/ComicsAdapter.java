package com.android.simone.github.marvelapp.presentation.ui.list;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.simone.github.marvelapp.R;
import com.android.simone.github.marvelapp.presentation.ui.widget.adapter.EndlessRecyclerAdapter;
import com.android.simone.github.marvelapp.presentation.viewmodel.ComicViewModel;
import com.bumptech.glide.Glide;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Simone Bellotti
 */

public class ComicsAdapter extends EndlessRecyclerAdapter<ComicViewModel, ComicsAdapter.ViewHolder> {

    @Inject
    public ComicsAdapter() {
        super(R.layout.progress_item);
    }

    @Override
    protected ViewHolder getViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comic, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    protected void onBindView(ViewHolder holder, int position, ComicViewModel item) {
        holder.bindItem(item, this);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.comic_image)
        ImageView comicView;
        @BindView(R.id.title_view)
        TextView titleView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindItem(ComicViewModel comic, View.OnClickListener onClickListener) {
            itemView.setOnClickListener(onClickListener);
            itemView.setTag(comic);

            titleView.setText(comic.getTitle());
            Glide.with(comicView.getContext())
                    .load(comic.getThumbnailUrl())
//                    .centerCrop()
                    .crossFade()
                    .into(comicView);
        }
    }
}
