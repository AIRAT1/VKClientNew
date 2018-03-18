package de.android.ayrathairullin.di.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import de.android.ayrathairullin.rest.RestClient;
import de.android.ayrathairullin.rest.api.AccountApi;
import de.android.ayrathairullin.rest.api.BoardApi;
import de.android.ayrathairullin.rest.api.GroupsApi;
import de.android.ayrathairullin.rest.api.UsersApi;
import de.android.ayrathairullin.rest.api.VideoApi;
import de.android.ayrathairullin.rest.api.WallApi;


@Module
public class RestModule {
    private RestClient mRestClient;


    public RestModule() {
        mRestClient = new RestClient();
    }


    @Provides
    @Singleton
    public RestClient provideRestClient() {
        return mRestClient;
    }

    @Provides
    @Singleton
    public WallApi provideWallApi() {
        return mRestClient.createService(WallApi.class);
    }

    @Provides
    @Singleton
    public UsersApi provideUsersApi() {
        return mRestClient.createService(UsersApi.class);
    }

    @Provides
    @Singleton
    public GroupsApi provideGroupsApi() {
        return mRestClient.createService(GroupsApi.class);
    }

    @Provides
    @Singleton
    public BoardApi provideBoardApi(){
        return mRestClient.createService(BoardApi.class);
    }

    @Provides
    @Singleton
    public VideoApi provideVideoApi() {
        return mRestClient.createService(VideoApi.class);
    }

    @Provides
    @Singleton
    public AccountApi provideAccountApi() {
        return mRestClient.createService(AccountApi.class);
    }

}






















