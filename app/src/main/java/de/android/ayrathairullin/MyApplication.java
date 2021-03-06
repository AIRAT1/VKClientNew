package de.android.ayrathairullin;

import android.app.Application;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.mikepenz.materialdrawer.util.AbstractDrawerImageLoader;
import com.mikepenz.materialdrawer.util.DrawerImageLoader;
import com.vk.sdk.VKSdk;

import de.android.ayrathairullin.di.component.ApplicationComponent;
import de.android.ayrathairullin.di.component.DaggerApplicationComponent;
import de.android.ayrathairullin.di.module.ApplicationModule;
import io.realm.Realm;
import io.realm.RealmConfiguration;


public class MyApplication extends Application {

    private static ApplicationComponent sApplicationComponent;
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();

        MyApplication.context = getApplicationContext();

        initComponent();

        VKSdk.initialize(this);

        Realm.init(this);
        RealmConfiguration realmConfiguration = new RealmConfiguration
                .Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);

        DrawerImageLoader.init(new AbstractDrawerImageLoader() {
            @Override
            public void set(ImageView imageView, Uri uri, Drawable placeholder, String tag) {
                Glide.with(imageView.getContext()).load(uri).into(imageView);
            }
        });

    }

    private void initComponent() {
        sApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this)).build();
    }

    public static ApplicationComponent getApplicationComponent() {
        return sApplicationComponent;
    }

    public static Context getContext() {
        return context;
    }
}
