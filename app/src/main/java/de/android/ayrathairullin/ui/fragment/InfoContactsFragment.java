package de.android.ayrathairullin.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.arellomobile.mvp.presenter.InjectPresenter;

import butterknife.ButterKnife;
import de.android.ayrathairullin.MyApplication;
import de.android.ayrathairullin.mvp.presenter.BaseFeedPresenter;
import de.android.ayrathairullin.mvp.presenter.InfoContactsPresenter;
import de.android.ayrathairullin.vkclient.R;

public class InfoContactsFragment extends  BaseFeedFragment {

    @InjectPresenter
    InfoContactsPresenter mPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setWithEndlessList(false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MyApplication.getApplicationComponent().inject(this);
        ButterKnife.bind(this, view);
    }

    @Override
    protected BaseFeedPresenter onCreateFeedPresenter() {
        return mPresenter;
    }

    @Override
    public int onCreateToolbarTitle() {
        return R.string.title_contacts;
    }
}
