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
import de.android.ayrathairullin.ui.fragment.InfoContactsFragment;
import de.android.ayrathairullin.ui.view.holder.BaseViewHolder;
import de.android.ayrathairullin.vkclient.R;

public class InfoContactsViewModel extends BaseViewModel{
    @Override
    public LayoutTypes getType() {
        return LayoutTypes.InfoContacts;
    }

    @Override
    public InfoContactsViewHolder onCreateViewHolder(View view) {
        return new InfoContactsViewHolder(view);
    }

    public static class InfoContactsViewHolder extends BaseViewHolder<InfoContactsViewModel> {

        @Inject
        MyFragmentManager mFragmentManager;

        @BindView(R.id.rv_contacts)
        RelativeLayout rvContacts;

        public InfoContactsViewHolder(View itemView) {
            super(itemView);
            MyApplication.getApplicationComponent().inject(this);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void bindViewHolder(InfoContactsViewModel infoContactsViewModel) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("CLICK_LINK", "click to InfoLinksViewModel");
                    mFragmentManager.addFragment((BaseActivity) view.getContext(), new InfoContactsFragment(),
                            R.id.main_wrapper);



                }
            });
        }

        @Override
        public void unbindViewHolder() {

        }
    }
}
