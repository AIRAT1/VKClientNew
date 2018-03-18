package com.example.user.fandroidvktest.ui.view.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.user.fandroidvktest.model.view.BaseViewModel;

/**
 * Created by user on 15.08.2017.
 */

public abstract class BaseViewHolder<Item extends BaseViewModel> extends RecyclerView.ViewHolder {

    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void bindViewHolder(Item item);

    public abstract void unbindViewHolder();
}
