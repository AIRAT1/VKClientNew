package de.android.ayrathairullin.mvp.view;

import de.android.ayrathairullin.model.view.NewsItemFooterViewModel;

public interface OpenedPostView extends BaseFeedView {
    void setFooter(NewsItemFooterViewModel viewModel);
}
