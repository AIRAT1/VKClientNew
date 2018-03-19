package de.android.ayrathairullin.model.view;


import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.android.ayrathairullin.MyApplication;
import de.android.ayrathairullin.common.manager.MyFragmentManager;
import de.android.ayrathairullin.ui.activity.BaseActivity;
import de.android.ayrathairullin.ui.fragment.InfoLinksFragment;
import de.android.ayrathairullin.ui.view.holder.BaseViewHolder;
import de.android.ayrathairullin.vkclient.R;

public class InfoLinksViewModel extends BaseViewModel{



    @Override
    public LayoutTypes getType() {
        return LayoutTypes.InfoLinks;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(View view) {
        return new InfoLinkViewHolder(view);
    }

    public static class InfoLinkViewHolder extends BaseViewHolder<InfoLinksViewModel> {

        @Inject
        MyFragmentManager mFragmentManager;

        @BindView(R.id.rv_links)
        RelativeLayout rvLinks;

        public InfoLinkViewHolder(View itemView) {
            super(itemView);
            MyApplication.getApplicationComponent().inject(this);
            ButterKnife.bind(this, itemView);


        }

        @Override
        public void bindViewHolder(InfoLinksViewModel infoLinksViewModel) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("CLICK_LINK", "click to InfoLinksViewModel");
                    mFragmentManager.addFragment((BaseActivity) view.getContext(), new InfoLinksFragment(),
                            R.id.main_wrapper);



                }
            });

        }

        @Override
        public void unbindViewHolder() {

        }
    }
}
