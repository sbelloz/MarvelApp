package com.android.simone.github.marvelapp.presentation.ui.widget.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Simone Bellotti
 */

public abstract class RecyclerViewAdapter<T, VH extends RecyclerView.ViewHolder>
        extends RecyclerView.Adapter<VH>
        implements View.OnClickListener {

    protected List<T> items = new ArrayList<>();
//    protected OnItemClickListener<T> listener;

    public RecyclerViewAdapter() {
        super();
    }

    public RecyclerViewAdapter(@Nullable T[] items) {
        this(items == null ? null : new ArrayList<>(Arrays.asList(items)));
    }

    public RecyclerViewAdapter(@Nullable List<T> items) {
        this.items = items == null ? new ArrayList<T>() : items;
    }

    protected abstract VH getViewHolder(ViewGroup parent, int viewType);

    protected abstract void onBindView(VH holder, int position, T item);

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        return getViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        onBindView(holder, position, getItem(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public List<T> getItems() {
        return items;
    }

    public T getItem(int position) {
        return items.get(position);
    }

//    public void setOnItemClickListener(OnItemClickListener<T> listener) {
//        this.listener = listener;
//    }

    public void add(T item, int position) {
        items.add(position, item);
        notifyItemInserted(position);
    }

    public void add(T item) {
        items.add(item);
        notifyItemInserted(items.indexOf(item));
    }

    public void addAll(List<T> moreItems) {
        int pos = getItemCount();
        items.addAll(moreItems);
        notifyItemRangeInserted(pos, items.size());
    }

    public T remove(int position) {
        T item = items.remove(position);
        notifyItemRemoved(position);
        return item;
    }

    public T remove(T item) {
        return remove(items.indexOf(item));
    }

    public void clear() {
        items.clear();
        notifyDataSetChanged();
    }

}
