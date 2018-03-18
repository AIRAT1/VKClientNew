package de.android.ayrathairullin.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import de.android.ayrathairullin.MyApplication;
import de.android.ayrathairullin.common.utils.VkListHelper;
import de.android.ayrathairullin.model.CommentItem;
import de.android.ayrathairullin.model.view.BaseViewModel;
import de.android.ayrathairullin.model.view.CommentFooterViewModel;
import de.android.ayrathairullin.model.view.OpenedPostHeaderViewModel;
import de.android.ayrathairullin.mvp.view.BaseFeedView;
import io.reactivex.Observable;
import io.realm.Realm;

@InjectViewState
public class OpenedCommentPresenter extends BaseFeedPresenter<BaseFeedView> {

    int id;

    public OpenedCommentPresenter() {
        MyApplication.getApplicationComponent().inject(this);

    }

    @Override
    public Observable<BaseViewModel> onCreateLoadDataObservable(int count, int offset) {
        return createObservable();

    }

    @Override
    public Observable<BaseViewModel> onCreateRestoreDataObservable() {
        return createObservable();
    }


    private Observable<BaseViewModel> createObservable() {
        return Observable.fromCallable(getListFromRealmCallable())
                .retry(2)
                .flatMap(commentItem -> {
                    List<BaseViewModel> list = new ArrayList<>();
                    List<BaseViewModel> forwardedList = new ArrayList<>();

                    list.add(new OpenedPostHeaderViewModel(commentItem));

                    list.addAll(VkListHelper.getAttachmentVkItems(commentItem.getAttachments()));

                    list.add(new CommentFooterViewModel(commentItem));

                    return Observable.fromIterable(list).concatWith(Observable.fromIterable(forwardedList));
                });
    }



    public Callable<CommentItem> getListFromRealmCallable() {
        return () -> {
            Realm realm = Realm.getDefaultInstance();
            CommentItem commentItem = realm.where(CommentItem.class).equalTo("id", id).findFirst();

            return realm.copyFromRealm(commentItem);
        };
    }

    public void setId(int id) {
        this.id = id;
    }
}

