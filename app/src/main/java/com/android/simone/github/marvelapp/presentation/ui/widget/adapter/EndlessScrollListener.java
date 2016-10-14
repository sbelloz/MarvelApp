package com.android.simone.github.marvelapp.presentation.ui.widget.adapter;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

/**
 * @author Simone Bellotti
 */

public abstract class EndlessScrollListener extends RecyclerView.OnScrollListener {

    private boolean isLoading;
    private int availableItemCount;
    private int limitItemCount;
    private RecyclerView.LayoutManager layoutManager;

    public EndlessScrollListener(RecyclerView.LayoutManager layoutManager, int limitItemCount) {
        this.layoutManager = layoutManager;
        this.limitItemCount = limitItemCount;
    }

    public abstract void onLoadMore();

    public void setAvailableItemCount(int availableItemCount) {
        this.availableItemCount = availableItemCount;
    }



    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        if (availableItemCount == limitItemCount) {
            if (isLoading) {
                isLoading = false;
            } else {
                // so means we have other item to load
                // check if we've reached the end of the list
                int lastVisibleItemPosition = 0;
                int itemCount = layoutManager.getItemCount();

                if (layoutManager instanceof StaggeredGridLayoutManager) {
                    int[] lastVisibleItemPositions = ((StaggeredGridLayoutManager) layoutManager).findLastVisibleItemPositions(null);
                    // get maximum element within the list
                    lastVisibleItemPosition = getLastVisibleItem(lastVisibleItemPositions);
                } else if (layoutManager instanceof LinearLayoutManager) {
                    lastVisibleItemPosition = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
                } else if (layoutManager instanceof GridLayoutManager) {
                    lastVisibleItemPosition = ((GridLayoutManager) layoutManager).findLastVisibleItemPosition();
                }

                if (reachedEndOfTheList(itemCount, lastVisibleItemPosition)) {
                    onLoadMore();
                    availableItemCount = 0;
                    isLoading = true;
                }
            }
        }

    }


    private boolean reachedEndOfTheList(int itemCount, int lastVisibleItem) {
        return itemCount == lastVisibleItem + 1;
    }

    private int getLastVisibleItem(int[] lastVisibleItemPositions) {
        int maxSize = 0;
        for (int i = 0; i < lastVisibleItemPositions.length; i++) {
            if (i == 0) {
                maxSize = lastVisibleItemPositions[i];
            } else if (lastVisibleItemPositions[i] > maxSize) {
                maxSize = lastVisibleItemPositions[i];
            }
        }
        return maxSize;
    }

}
