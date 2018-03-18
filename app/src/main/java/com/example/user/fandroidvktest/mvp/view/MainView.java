package com.example.user.fandroidvktest.mvp.view;

import com.arellomobile.mvp.MvpView;
import com.example.user.fandroidvktest.model.Profile;
import com.example.user.fandroidvktest.ui.fragment.BaseFragment;


public interface MainView extends MvpView {
    void startSignIn();
    void signedIn();
    void showCurrentUser(Profile profile);
    void showFragmentFromDrawer(BaseFragment baseFragment);
}
