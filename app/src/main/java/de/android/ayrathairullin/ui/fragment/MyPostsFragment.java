package de.android.ayrathairullin.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;

import de.android.ayrathairullin.vkclient.R;


public class MyPostsFragment extends NewsFeedFragment {

    public MyPostsFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter.setEnableIdFiltering(true);
    }

    @Override
    public int onCreateToolbarTitle() {
        return R.string.screen_name_my_posts;
    }


}
