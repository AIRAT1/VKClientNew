package de.android.ayrathairullin.mvp.view;

import com.arellomobile.mvp.MvpView;
import de.android.ayrathairullin.model.Profile;
import de.android.ayrathairullin.ui.fragment.BaseFragment;


public interface MainView extends MvpView {
    void startSignIn();
    void signedIn();
    void showCurrentUser(Profile profile);
    void showFragmentFromDrawer(BaseFragment baseFragment);
}
