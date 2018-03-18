package de.android.ayrathairullin.mvp.view;

        import com.arellomobile.mvp.MvpView;
        import de.android.ayrathairullin.model.WallItem;
        import de.android.ayrathairullin.model.view.counter.LikeCounterViewModel;

public interface PostFooterView extends MvpView {
    void like(LikeCounterViewModel likes);

    void openComments(WallItem wallItem);
}
