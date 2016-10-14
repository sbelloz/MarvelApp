package com.android.simone.github.marvelapp.presentation.ui.widget.adapter;

import android.view.View;

/**
 * @author Simone Bellotti
 */

public interface OnItemClickListener<T> {

    void onItemClick(View view, T item);
}
