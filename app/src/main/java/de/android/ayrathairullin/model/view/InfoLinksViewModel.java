package de.android.ayrathairullin.model.view;


import android.view.View;
import android.widget.RelativeLayout;

import de.android.ayrathairullin.ui.view.holder.BaseViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.android.ayrathairullin.vkclient.R;

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
