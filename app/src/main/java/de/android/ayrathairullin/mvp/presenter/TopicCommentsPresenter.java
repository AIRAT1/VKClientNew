package de.android.ayrathairullin.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import de.android.ayrathairullin.MyApplication;
import de.android.ayrathairullin.common.utils.VkListHelper;
import de.android.ayrathairullin.model.CommentItem;
import de.android.ayrathairullin.model.Place;
import de.android.ayrathairullin.model.view.BaseViewModel;
import de.android.ayrathairullin.model.view.CommentBodyViewModel;
import de.android.ayrathairullin.model.view.CommentFooterViewModel;
import de.android.ayrathairullin.model.view.CommentHeaderViewModel;
import de.android.ayrathairullin.mvp.view.BaseFeedView;
import de.android.ayrathairullin.rest.api.BoardApi;
import de.android.ayrathairullin.rest.model.request.BoardGetCommentsRequestModel;
import io.reactivex.Observable;
import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

@InjectViewState
public class TopicCommentsPresenter extends BaseFeedPresenter<BaseFeedView> {
    private Place mPlace;

    @Inject
    BoardApi mBoardApi;


    public TopicCommentsPresenter() {
        MyApplication.getApplicationComponent().inject(this);
    }

    @Override
    public Observable<BaseViewModel> onCreateLoadDataObservable(int count, int offset) {
        return mBoardApi.getComments(new BoardGetCommentsRequestModel(
                Integer.parseInt(mPlace.getOwnerId()), Integer.parseInt(mPlace.getPostId()), offset).toMap())
                .flatMap(full -> Observable.fromIterable(VkListHelper.getCommentsList(full.response, true)))
                .doOnNext(commentItem -> commentItem.setPlace(mPlace))
                .doOnNext(this::saveToDb)
                .flatMap(commentItem -> Observable.fromIterable(parsePojoModel(commentItem)));
    }

    @Override
    public Observable<BaseViewModel> onCreateRestoreDataObservable() {
        return Observable.fromCallable(getListFromRealmCallable())
                .flatMap(Observable::fromIterable)
                .filter(commentItem -> commentItem.getPlace().equals(this.mPlace) && commentItem.isFromTopic)
                .flatMap(commentItem -> Observable.fromIterable(parsePojoModel(commentItem)));
    }


    private List<BaseViewModel> parsePojoModel(CommentItem commentItem) {
        List<BaseViewModel> baseItems = new ArrayList<>();
        baseItems.add(new CommentHeaderViewModel(commentItem));
        baseItems.add(new CommentBodyViewModel(commentItem));
        baseItems.add(new CommentFooterViewModel(commentItem));
        return baseItems;
    }


    public Callable<List<CommentItem>> getListFromRealmCallable() {
        return () -> {
            String[] sortFields = {"id"};
            Sort[] sortOrder = {Sort.ASCENDING};

            Realm realm = Realm.getDefaultInstance();
            RealmResults<CommentItem> results = realm.where(CommentItem.class)
                    .findAllSorted(sortFields, sortOrder);
            return realm.copyFromRealm(results);
        };
    }


    public void setPlace(Place place) {
        this.mPlace = place;
    }
}