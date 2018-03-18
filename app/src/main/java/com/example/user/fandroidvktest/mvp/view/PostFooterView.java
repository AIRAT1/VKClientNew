package com.example.user.fandroidvktest.mvp.view;

        import com.arellomobile.mvp.MvpView;
        import com.example.user.fandroidvktest.model.WallItem;
        import com.example.user.fandroidvktest.model.view.counter.LikeCounterViewModel;

public interface PostFooterView extends MvpView {
    void like(LikeCounterViewModel likes);

    void openComments(WallItem wallItem);
}
