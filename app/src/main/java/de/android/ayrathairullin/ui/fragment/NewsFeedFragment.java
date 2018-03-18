package de.android.ayrathairullin.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.arellomobile.mvp.presenter.InjectPresenter;

import javax.inject.Inject;

import de.android.ayrathairullin.MyApplication;
import de.android.ayrathairullin.mvp.presenter.BaseFeedPresenter;
import de.android.ayrathairullin.mvp.presenter.NewsFeedPresenter;
import de.android.ayrathairullin.rest.api.WallApi;
import de.android.ayrathairullin.ui.activity.CreatePostActivity;
import de.android.ayrathairullin.vkclient.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFeedFragment extends BaseFeedFragment {

    @Inject
    WallApi mWallApi;

    @InjectPresenter
    NewsFeedPresenter mPresenter;


    public NewsFeedFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MyApplication.getApplicationComponent().inject(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }

    @Override
    public void onResume() {
        super.onResume();
        getBaseActivity().mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), CreatePostActivity.class);
                startActivityForResult(intent, 0);
            }
        });
    }



    @Override
    public int onCreateToolbarTitle() {
        return R.string.screen_name_news;
    }

    @Override
    protected BaseFeedPresenter onCreateFeedPresenter() {
        return mPresenter;
    }

    @Override
    public boolean needFab() {
        return true;
    }
}
