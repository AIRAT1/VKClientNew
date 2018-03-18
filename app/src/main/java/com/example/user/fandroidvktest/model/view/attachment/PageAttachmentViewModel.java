package com.example.user.fandroidvktest.model.view.attachment;

import android.view.View;

import com.example.user.fandroidvktest.model.attachment.Page;
import com.example.user.fandroidvktest.model.view.BaseViewModel;
import com.example.user.fandroidvktest.ui.view.holder.attachment.PageAttachmentHolder;

public class PageAttachmentViewModel extends BaseViewModel {

    private String mTitle;
    private String mUrl;

    public PageAttachmentViewModel(Page page) {
        mUrl = page.getUrl();
        mTitle = page.getTitle();
    }

    public String getTitle() {
        return mTitle;
    }


    @Override
    public LayoutTypes getType() {
        return LayoutTypes.AttachmentPage;
    }

    @Override
    public PageAttachmentHolder onCreateViewHolder(View view) {
        return new PageAttachmentHolder(view);
    }



    public String getmUrl() {
        return mUrl;
    }
}
