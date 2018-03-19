package de.android.ayrathairullin.ui.fragment;


import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;

import de.android.ayrathairullin.vkclient.R;

public class MyPreferencesFragment extends PreferenceFragment {
    public MyPreferencesFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }
}
