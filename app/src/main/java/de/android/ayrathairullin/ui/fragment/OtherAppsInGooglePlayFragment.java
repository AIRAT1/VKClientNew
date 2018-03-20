package de.android.ayrathairullin.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import de.android.ayrathairullin.vkclient.R;

public class OtherAppsInGooglePlayFragment extends BaseFragment {

    @BindView(R.id.webview)
    WebView mWebView;

    @BindString(R.string.more_apps_in_google_play)
    String mTitle;


    public OtherAppsInGooglePlayFragment() {

    }


    @Override
    protected int getMainContentLayout() {
        return R.layout.fragment_webview;
    }

    @Override
    public int onCreateToolbarTitle() {
        return R.string.more_apps_in_google_play;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        getBaseActivity().getProgressBar().setVisibility(View.VISIBLE);

        mWebView.loadUrl(getString(R.string.google_play_link));

        mWebView.setWebViewClient(new WebViewClient() {

            public void onPageFinished(WebView view, String url) {
                // do your stuff here
                if (getBaseActivity() != null) {
                    getBaseActivity().getProgressBar().setVisibility(View.GONE);
                }
            }
        });
    }


}
