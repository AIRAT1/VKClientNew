package de.android.ayrathairullin.di.component;

import javax.inject.Singleton;

import dagger.Component;
import de.android.ayrathairullin.common.manager.NetworkManager;
import de.android.ayrathairullin.di.module.ApplicationModule;
import de.android.ayrathairullin.di.module.ManagerModule;
import de.android.ayrathairullin.di.module.RestModule;
import de.android.ayrathairullin.model.view.CommentBodyViewModel;
import de.android.ayrathairullin.model.view.CommentFooterViewModel;
import de.android.ayrathairullin.model.view.InfoContactsViewModel;
import de.android.ayrathairullin.model.view.InfoLinksViewModel;
import de.android.ayrathairullin.model.view.TopicViewModel;
import de.android.ayrathairullin.mvp.presenter.BoardPresenter;
import de.android.ayrathairullin.mvp.presenter.CommentsPresenter;
import de.android.ayrathairullin.mvp.presenter.InfoContactsPresenter;
import de.android.ayrathairullin.mvp.presenter.InfoLinksPresenter;
import de.android.ayrathairullin.mvp.presenter.InfoPresenter;
import de.android.ayrathairullin.mvp.presenter.MainPresenter;
import de.android.ayrathairullin.mvp.presenter.MembersPresenter;
import de.android.ayrathairullin.mvp.presenter.NewsFeedPresenter;
import de.android.ayrathairullin.mvp.presenter.OpenedCommentPresenter;
import de.android.ayrathairullin.mvp.presenter.OpenedPostPresenter;
import de.android.ayrathairullin.mvp.presenter.TopicCommentsPresenter;
import de.android.ayrathairullin.ui.activity.BaseActivity;
import de.android.ayrathairullin.ui.activity.MainActivity;
import de.android.ayrathairullin.ui.activity.OpenedPostFromPushActivity;
import de.android.ayrathairullin.ui.fragment.CommentsFragment;
import de.android.ayrathairullin.ui.fragment.InfoContactsFragment;
import de.android.ayrathairullin.ui.fragment.InfoLinksFragment;
import de.android.ayrathairullin.ui.fragment.NewsFeedFragment;
import de.android.ayrathairullin.ui.fragment.OpenedCommentFragment;
import de.android.ayrathairullin.ui.fragment.OpenedPostFragment;
import de.android.ayrathairullin.ui.fragment.TopicCommentsFragment;
import de.android.ayrathairullin.ui.view.holder.NewsItemBodyHolder;
import de.android.ayrathairullin.ui.view.holder.NewsItemFooterHolder;
import de.android.ayrathairullin.ui.view.holder.attachment.ImageAttachmentHolder;
import de.android.ayrathairullin.ui.view.holder.attachment.VideoAttachmentHolder;


@Singleton
@Component(
        modules = {ApplicationModule.class, RestModule.class, ManagerModule.class})
public interface ApplicationComponent {

    //activities
    void inject(BaseActivity activity);
    void inject(MainActivity activity);
    void inject(OpenedPostFromPushActivity activity);

    //fragments
    void inject(NewsFeedFragment fragment);
    void inject(OpenedPostFragment fragment);
    void inject(OpenedCommentFragment fragment);
    void inject(CommentsFragment fragment);
    void inject(TopicCommentsFragment fragment);
    void inject(InfoLinksFragment fragment);
    void inject(InfoContactsFragment fragment);

    //holders
    void inject(NewsItemBodyHolder holder);
    void inject(NewsItemFooterHolder holder);
    void inject(ImageAttachmentHolder holder);
    void inject(VideoAttachmentHolder holder);
    void inject(CommentBodyViewModel.CommentBodyViewHolder holder);
    void inject(CommentFooterViewModel.CommentFooterHolder holder);
    void inject(TopicViewModel.TopicViewHolder holder);
    void inject(InfoLinksViewModel.InfoLinkViewHolder holder);
    void inject(InfoContactsViewModel.InfoContactsViewHolder holder);

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
    void inject(InfoLinksPresenter presenter);
    void inject(InfoContactsPresenter presenter);

    //managers
    void inject(NetworkManager manager);
}
