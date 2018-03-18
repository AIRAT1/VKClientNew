package com.example.user.fandroidvktest.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.example.user.fandroidvktest.MyApplication;
import com.example.user.fandroidvktest.common.utils.VkListHelper;
import com.example.user.fandroidvktest.consts.ApiConstants;
import com.example.user.fandroidvktest.model.WallItem;
import com.example.user.fandroidvktest.model.view.BaseViewModel;
import com.example.user.fandroidvktest.model.view.NewsItemFooterViewModel;
import com.example.user.fandroidvktest.model.view.OpenedPostHeaderViewModel;
import com.example.user.fandroidvktest.model.view.OpenedPostRepostHeaderViewModel;
import com.example.user.fandroidvktest.mvp.view.OpenedPostView;
import com.example.user.fandroidvktest.rest.api.WallApi;
import com.example.user.fandroidvktest.rest.model.request.WallGetByIdRequestModel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;

@InjectViewState
public class OpenedPostPresenter extends BaseFeedPresenter<OpenedPostView> {

    private int id;

    @Inject
    WallApi mWallApi;

    public OpenedPostPresenter() {
        MyApplication.getApplicationComponent().inject(this);

    }

    @Override
    public Observable<BaseViewModel> onCreateLoadDataObservable(int count, int offset) {
        return mWallApi.getById(new WallGetByIdRequestModel(ApiConstants.MY_GROUP_ID, id).toMap())
                .flatMap(full -> Observable.fromIterable(VkListHelper.getWallList(full.response)))
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(wallItem -> {
                    NewsItemFooterViewModel newsItemFooterViewModel = new NewsItemFooterViewModel(wallItem);

                    getViewState().setFooter(newsItemFooterViewModel);
                })

                .observeOn(Schedulers.io())
                .doOnNext(this::saveToDb)
                .flatMap(wallItem -> {
                    List<BaseViewModel> list = new ArrayList<>();
                    List<BaseViewModel> forwardedList = new ArrayList<>();

                    list.add(new OpenedPostHeaderViewModel(wallItem));

                    list.addAll(VkListHelper.getAttachmentVkItems(wallItem.getAttachments()));
                    if (wallItem.haveSharedRepost()) {

                        forwardedList.add(new OpenedPostRepostHeaderViewModel(wallItem.getSharedRepost()));
                        forwardedList.addAll(VkListHelper.getAttachmentVkItems(wallItem.getSharedRepost().getAttachments()));
                    }
                    return Observable.fromIterable(list).concatWith(Observable.fromIterable(forwardedList));
                });

    }

    @Override
    public Observable<BaseViewModel> onCreateRestoreDataObservable() {
        return Observable.fromCallable(getListFromRealmCallable())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(wallItem -> {
                    NewsItemFooterViewModel newsItemFooterViewModel = new NewsItemFooterViewModel(wallItem);

                    getViewState().setFooter(newsItemFooterViewModel);
                })

                .observeOn(Schedulers.io())
                .flatMap(wallItem -> {
                    List<BaseViewModel> list = new ArrayList<>();
                    List<BaseViewModel> forwardedList = new ArrayList<>();

                    list.add(new OpenedPostHeaderViewModel(wallItem));

                    list.addAll(VkListHelper.getAttachmentVkItems(wallItem.getAttachments()));
                    if (wallItem.haveSharedRepost()) {

                        forwardedList.add(new OpenedPostRepostHeaderViewModel(wallItem.getSharedRepost()));
                        forwardedList.addAll(VkListHelper.getAttachmentVkItems(wallItem.getSharedRepost().getAttachments()));
                    }
                    return Observable.fromIterable(list).concatWith(Observable.fromIterable(forwardedList));
                });
    }






    private Callable<WallItem> getListFromRealmCallable() {
        return () -> {
            Realm realm = Realm.getDefaultInstance();
            WallItem wallItem = realm.where(WallItem.class).equalTo("id", id).findFirst();

            return realm.copyFromRealm(wallItem);
        };
    }

    public void setId(int id) {
        this.id = id;
    }
}