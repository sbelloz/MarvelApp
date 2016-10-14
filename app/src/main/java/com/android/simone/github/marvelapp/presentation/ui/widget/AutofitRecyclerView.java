package com.android.simone.github.marvelapp.presentation.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.android.simone.github.marvelapp.R;

/**
 * @author Simone Bellotti
 */

public class AutofitRecyclerView extends RecyclerView {

    public static final int DEFAULT_GRID_SPAN = 1;

    private GridLayoutManager mLayoutManager;
    private int mColumnWidth = -1;

    public AutofitRecyclerView(Context context) {
        super(context);
        init(context, null);
    }

    public AutofitRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public AutofitRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        int spanCount = DEFAULT_GRID_SPAN;
        if (attrs != null) {

            int[] attrsArray = {
                    android.R.attr.columnWidth
            };

            TypedArray array = context.obtainStyledAttributes(attrs, attrsArray);
            mColumnWidth = array.getDimensionPixelSize(0, -1);
            array.recycle();

            TypedArray a = context.obtainStyledAttributes(
                    attrs,
                    R.styleable.AutofitRecyclerView);
            spanCount = a.getInt(R.styleable.AutofitRecyclerView_gridSpan, DEFAULT_GRID_SPAN);
            a.recycle();
        }

        mLayoutManager = new GridLayoutManager(getContext(), spanCount);
        setLayoutManager(mLayoutManager);
    }

    @Override
    protected void onMeasure(int widthSpec, int heightSpec) {
        super.onMeasure(widthSpec, heightSpec);
        if (mColumnWidth > 0) {
            int spanCount = Math.max(1, getMeasuredWidth() / mColumnWidth);
            mLayoutManager.setSpanCount(spanCount);
        }
    }
}