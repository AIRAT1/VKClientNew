package com.example.user.fandroidvktest.di.component;

import com.example.user.fandroidvktest.common.manager.NetworkManager;
import com.example.user.fandroidvktest.di.module.ApplicationModule;
import com.example.user.fandroidvktest.di.module.ManagerModule;
import com.example.user.fandroidvktest.di.module.RestModule;
import com.example.user.fandroidvktest.model.view.CommentBodyViewModel;
import com.example.user.fandroidvktest.model.view.CommentFooterViewModel;
import com.example.user.fandroidvktest.model.view.TopicViewModel;
import com.example.user.fandroidvktest.mvp.presenter.BoardPresenter;
import com.example.user.fandroidvktest.mvp.presenter.CommentsPresenter;
import com.example.user.fandroidvktest.mvp.presenter.InfoPresenter;
import com.example.user.fandroidvktest.mvp.presenter.MainPresenter;
import com.example.user.fandroidvktest.mvp.presenter.MembersPresenter;
import com.example.user.fandroidvktest.mvp.presenter.NewsFeedPresenter;
import com.example.user.fandroidvktest.mvp.presenter.OpenedCommentPresenter;
import com.example.user.fandroidvktest.mvp.presenter.OpenedPostPresenter;
import com.example.user.fandroidvktest.mvp.presenter.TopicCommentsPresenter;
import com.example.user.fandroidvktest.ui.activity.BaseActivity;
import com.example.user.fandroidvktest.ui.activity.MainActivity;
import com.example.user.fandroidvktest.ui.fragment.CommentsFragment;
import com.example.user.fandroidvktest.ui.fragment.NewsFeedFragment;
import com.example.user.fandroidvktest.ui.fragment.OpenedCommentFragment;
import com.example.user.fandroidvktest.ui.fragment.OpenedPostFragment;
import com.example.user.fandroidvktest.ui.fragment.TopicCommentsFragment;
import com.example.user.fandroidvktest.ui.view.holder.NewsItemBodyHolder;
import com.example.user.fandroidvktest.ui.view.holder.NewsItemFooterHolder;
import com.example.user.fandroidvktest.ui.view.holder.attachment.ImageAttachmentHolder;
import com.example.user.fandroidvktest.ui.view.holder.attachment.VideoAttachmentHolder;

import javax.inject.Singleton;

import dagger.Component;



@Singleton
@Component(
        modules = {ApplicationModule.class, RestModule.class, ManagerModule.class})
public interface ApplicationComponent {

    //activities
    void inject(BaseActivity activity);
    void inject(MainActivity activity);

    //fragments
    void inject(NewsFeedFragment fragment);
    void inject(OpenedPostFragment fragment);
    void inject(OpenedCommentFragment fragment);
    void inject(CommentsFragment fragment);
    void inject(TopicCommentsFragment fragment);

    //holders
    void inject(NewsItemBodyHolder holder);
    void inject(NewsItemFooterHolder holder);
    void inject(ImageAttachmentHolder holder);
    void inject(VideoAttachmentHolder holder);
    void inject(CommentBodyViewModel.CommentBodyViewHolder holder);
    void inject(CommentFooterViewModel.CommentFooterHolder holder);
    void inject(TopicViewModel.TopicViewHolder holder);

    //presenters
    void inject(NewsFeedPresenter presenter);
    void inject(MainPresenter presenter);
    void inject(MembersPresenter presenter);
    void inject(BoardPresenter presenter);
    void inject(InfoPresenter presenter);
    void inject(OpenedPostPresenter presenter);
    void inject(CommentsPresenter presenter);
    void inject(OpenedCommentPresenter presenter);
    void inject(TopicCommentsPresenter presenter);

    //managers
    void inject(NetworkManager manager);


}
