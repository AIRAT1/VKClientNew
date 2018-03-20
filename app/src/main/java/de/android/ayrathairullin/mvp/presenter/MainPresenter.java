package de.android.ayrathairullin.mvp.presenter;

import android.content.Context;
import android.content.Intent;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.concurrent.Callable;

import javax.inject.Inject;

import de.android.ayrathairullin.CurrentUser;
import de.android.ayrathairullin.MyApplication;
import de.android.ayrathairullin.common.manager.MyFragmentManager;
import de.android.ayrathairullin.common.manager.NetworkManager;
import de.android.ayrathairullin.model.Profile;
import de.android.ayrathairullin.mvp.view.MainView;
import de.android.ayrathairullin.rest.api.UsersApi;
import de.android.ayrathairullin.rest.model.request.UsersGetRequestModel;
import de.android.ayrathairullin.ui.activity.SettingActivity;
import de.android.ayrathairullin.ui.fragment.BaseFragment;
import de.android.ayrathairullin.ui.fragment.BoardFragment;
import de.android.ayrathairullin.ui.fragment.InfoFragment;
import de.android.ayrathairullin.ui.fragment.MembersFragment;
import de.android.ayrathairullin.ui.fragment.MyPostsFragment;
import de.android.ayrathairullin.ui.fragment.NewsFeedFragment;
import de.android.ayrathairullin.ui.fragment.OtherAppsInGooglePlayFragment;
import de.android.ayrathairullin.vkclient.R;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;
import io.realm.RealmObject;


@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> {

    @Inject
    MyFragmentManager myFragmentManager;

    @Inject
    UsersApi mUserApi;

    @Inject
    NetworkManager mNetworkManager;

    public void checkAuth() {
        if (!CurrentUser.isAuthorized()) {
            getViewState().startSignIn();
        } else {
            getCurrentUser();
            getViewState().signedIn();
        }
    }

    public MainPresenter() {
        MyApplication.getApplicationComponent().inject(this);
    }

    public Observable<Profile> getProfileFromNetwork() {
        return mUserApi.get(new UsersGetRequestModel(CurrentUser.getId()).toMap())
                .flatMap(listFull -> Observable.fromIterable(listFull.response))
                .doOnNext(this::saveToDb);
    }

    private Observable<Profile> getProfileFromDb() {
        return Observable.fromCallable(getListFromRealmCallable());
    }

    public void saveToDb(RealmObject item) {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(realm1 -> realm1.copyToRealmOrUpdate(item));
    }

    public Callable<Profile> getListFromRealmCallable() {
        return () -> {
            Realm realm = Realm.getDefaultInstance();
            Profile realmResults = realm.where(Profile.class)
                    .equalTo("id", Integer.parseInt(CurrentUser.getId()))
                    .findFirst();
            return realm.copyFromRealm(realmResults);
        };
    }

    private void getCurrentUser() {
        mNetworkManager.getNetworkObservable()
                .flatMap(aBoolean ->  {
                    if (!CurrentUser.isAuthorized()) {
                        getViewState().startSignIn();
                    }
                    return aBoolean
                            ? getProfileFromNetwork()
                            : getProfileFromDb();
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(profile -> {
                    getViewState().showCurrentUser(profile);
                }, error -> {
                    error.printStackTrace();
                });
    }

    public void drawerItemClick(int id){
        BaseFragment fragment = null;

        switch (id) {
            case 1:
                fragment = new NewsFeedFragment();
                break;
            case 2:
                fragment = new MyPostsFragment();
                break;
            case 3:
                getViewState().startActivityFromDrawer(SettingActivity.class);
                return;
            case 4:
                fragment = new MembersFragment();
                break;
            case 5:
                fragment = new BoardFragment();
                break;
            case 6:
                fragment = new InfoFragment();
                break;
            case 7:
                fragment = new OtherAppsInGooglePlayFragment();
                break;
            case 8:
                Context context = MyApplication.getContext();
                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String title = context.getString(R.string.app_name);
                String body = context.getString(R.string.share_body) + context.getString(R.string.share_url);
                sharingIntent.putExtra(Intent.EXTRA_SUBJECT, title);
                sharingIntent.putExtra(Intent.EXTRA_TEXT, body);
                context.startActivity(Intent.createChooser(sharingIntent, context.getString(R.string.share_via)));
                break;
        }

        if (fragment != null && !myFragmentManager.isAlreadyContains(fragment)) {
            getViewState().showFragmentFromDrawer(fragment);
        }
    }
}
























