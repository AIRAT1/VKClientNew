package de.android.ayrathairullin.di.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import de.android.ayrathairullin.common.manager.MyFragmentManager;
import de.android.ayrathairullin.common.manager.NetworkManager;


@Module
public class ManagerModule {
    @Provides
    @Singleton
    MyFragmentManager provideMyFragmentManager() {
        return new MyFragmentManager();
    }

    @Provides
    @Singleton
    NetworkManager provideNetworkManager() {
        return new NetworkManager();
    }
}
