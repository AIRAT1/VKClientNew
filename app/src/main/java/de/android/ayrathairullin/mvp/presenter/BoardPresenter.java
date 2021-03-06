package de.android.ayrathairullin.mvp.presenter;


import com.arellomobile.mvp.InjectViewState;

import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import de.android.ayrathairullin.MyApplication;
import de.android.ayrathairullin.consts.ApiConstants;
import de.android.ayrathairullin.model.Member;
import de.android.ayrathairullin.model.Topic;
import de.android.ayrathairullin.model.view.BaseViewModel;
import de.android.ayrathairullin.model.view.TopicViewModel;
import de.android.ayrathairullin.mvp.view.BaseFeedView;
import de.android.ayrathairullin.rest.api.BoardApi;
import de.android.ayrathairullin.rest.model.request.BoardGetTopicsRequestModel;
import io.reactivex.Observable;
import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

@InjectViewState
public class BoardPresenter extends BaseFeedPresenter<BaseFeedView>{

    @Inject
    BoardApi mBoardApi;

    public BoardPresenter() {
        MyApplication.getApplicationComponent().inject(this);
    }

    @Override
    public Observable<BaseViewModel> onCreateLoadDataObservable(int count, int offset) {
        return mBoardApi.getTopics(new BoardGetTopicsRequestModel(
                ApiConstants.MY_GROUP_ID, count, offset
        ).toMap())
                .flatMap(baseItemResponseFull ->
                Observable.fromIterable(baseItemResponseFull.response.getItems()))
                .doOnNext(topic -> topic.setGroupid(ApiConstants.MY_GROUP_ID))
                .doOnNext(this::saveToDb)
                .map(TopicViewModel::new);
    }

    @Override
    public Observable<BaseViewModel> onCreateRestoreDataObservable() {
        return Observable.fromCallable(getListFromrealmCallable())
                .flatMap(Observable::fromIterable)
                .map(TopicViewModel::new);
    }

    public Callable<List<Topic>> getListFromrealmCallable() {
        return () -> {
            String[] sortFields = {Member.ID};
            Sort[] sortOrder = {Sort.DESCENDING};

            Realm realm = Realm.getDefaultInstance();
            RealmResults<Topic> results = realm.where(Topic.class)
                    .equalTo("groupId", ApiConstants.MY_GROUP_ID)
                    .findAllSorted(sortFields, sortOrder);

            return realm.copyFromRealm(results);
        };
    }
}

















