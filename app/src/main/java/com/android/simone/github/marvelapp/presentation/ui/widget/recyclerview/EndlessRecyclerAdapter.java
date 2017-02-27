package com.android.simone.github.marvelapp.presentation.ui.widget.recyclerview;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * @author Simone Bellotti
 */

public abstract class EndlessRecyclerAdapter<T, VH extends RecyclerView.ViewHolder>
        extends RecyclerViewAdapter<T, VH> {

    protected static final int TYPE_PROGRESS = 1000;

    private boolean isLoading = false;

    @LayoutRes
    private int progressResId;

    public EndlessRecyclerAdapter(@LayoutRes int progressResId) {
        super();
        this.progressResId = progressResId;
    }

    public EndlessRecyclerAdapter(@Nullable T[] items, @LayoutRes int progressResId) {
        super(items);
        this.progressResId = progressResId;
    }

    public EndlessRecyclerAdapter(@Nullable List<T> items, @LayoutRes int progressResId) {
        super(items);
        this.progressResId = progressResId;
    }

    public boolean isLoading() {
        return isLoading;
    }

    public void setIsLoading(boolean isLoading) {
        this.isLoading = isLoading;
        if (isLoading) {
            // TODO: 18/01/17 Cannot call this method in a scroll callback. Scroll callbacks might be run during a measure & layout pass where you cannot change the RecyclerView data.
            // todo Any method call that might change the structure of the RecyclerView or the adapter contents should be postponed to the next frame.
            notifyItemInserted(getItemCount());
        }
    }

    @Override
    public int getItemCount() {
        return isLoading() ?
                items.size() + 1
                : items.size();
    }

    @Override
    public int getItemViewType(int position) {
        return (isLoading() && position >= items.size()) ?
                TYPE_PROGRESS : super.getItemViewType(position);
    }

    public boolean isProgressView(int position) {
        return position == items.size();
    }

    @SuppressWarnings("unchecked")
    @Override
    public final VH onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh;
        if (viewType == TYPE_PROGRESS) {
            View viewProgress = LayoutInflater.from(parent.getContext()).inflate(progressResId, parent, false);
            vh = new ProgressViewHolder(viewProgress);
        } else {
            vh = getViewHolder(parent, viewType);
        }

        return (VH) vh;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public final void onBindViewHolder(VH holder, int position) {
        if (holder instanceof ProgressViewHolder) {
            /* no-op */
        } else {
            onBindView(holder, position, getItem(position));
        }
    }

    private static class ProgressViewHolder extends RecyclerView.ViewHolder {
        ProgressViewHolder(View v) {
            super(v);
        }
    }

}
