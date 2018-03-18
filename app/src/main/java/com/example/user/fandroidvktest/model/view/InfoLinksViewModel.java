package com.example.user.fandroidvktest.model.view;


import android.view.View;
import android.widget.RelativeLayout;

import com.example.user.fandroidvktest.R;
import com.example.user.fandroidvktest.ui.view.holder.BaseViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InfoLinksViewModel extends BaseViewModel{

    @Override
    public LayoutTypes getType() {
        return LayoutTypes.InfoLinks;
    }

    @Override
    public InfoLinkViewHolder onCreateViewHolder(View view) {
        return new InfoLinkViewHolder(view);
    }

    static class InfoLinkViewHolder extends BaseViewHolder<InfoLinksViewModel> {

        @BindView(R.id.rv_links)
        RelativeLayout rvLinks;

        public InfoLinkViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void bindViewHolder(InfoLinksViewModel infoLinksViewModel) {

        }

        @Override
        public void unbindViewHolder() {

        }
    }
}
