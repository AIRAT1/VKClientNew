package de.android.ayrathairullin.mvp.presenter;


import com.arellomobile.mvp.InjectViewState;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import de.android.ayrathairullin.MyApplication;
import de.android.ayrathairullin.consts.ApiConstants;
import de.android.ayrathairullin.model.Group;
import de.android.ayrathairullin.model.view.BaseViewModel;
import de.android.ayrathairullin.model.view.InfoContactsViewModel;
import de.android.ayrathairullin.model.view.InfoLinksViewModel;
import de.android.ayrathairullin.model.view.InfoStatusViewModel;
import de.android.ayrathairullin.mvp.view.BaseFeedView;
import de.android.ayrathairullin.rest.api.GroupsApi;
import de.android.ayrathairullin.rest.model.request.GroupsGetByIdRequestModel;
import io.reactivex.Observable;
import io.realm.Realm;

@InjectViewState
public class InfoPresenter extends BaseFeedPresenter<BaseFeedView>{

    @Inject
    GroupsApi mGroupApi;

    public InfoPresenter() {
        MyApplication.getApplicationComponent().inject(this);
    }

    @Override
    public Observable<BaseViewModel> onCreateLoadDataObservable(int count, int offset) {
        return mGroupApi.getById(new GroupsGetByIdRequestModel(ApiConstants.MY_GROUP_ID).toMap())
                .flatMap(listFull -> Observable.fromIterable(listFull.response))
                .doOnNext(this::saveToDb)
                .flatMap(group -> Observable.fromIterable(parsePojoModel(group)));
    }

    @Override
    public Observable<BaseViewModel> onCreateRestoreDataObservable() {
        return Observable.fromCallable(getListFromRealmCallable())
                .flatMap(group -> Observable.fromIterable(parsePojoModel(group)));
    }

    private List<BaseViewModel> parsePojoModel(Group group) {
        List<BaseViewModel> items = new ArrayList<>();
        items.add(new InfoStatusViewModel(group));
        items.add(new InfoContactsViewModel());
        items.add(new InfoLinksViewModel());

        return items;
    }

    public Callable<Group> getListFromRealmCallable() {
        return () -> {
            Realm realm = Realm.getDefaultInstance();
            Group result = realm.where(Group.class)
                    .equalTo("id", Math.abs(ApiConstants.MY_GROUP_ID))
                    .findFirst();
            return realm.copyFromRealm(result);
        };
    }
}



















