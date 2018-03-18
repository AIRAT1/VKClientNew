package com.example.user.fandroidvktest.di.module;

import com.example.user.fandroidvktest.rest.RestClient;
import com.example.user.fandroidvktest.rest.api.BoardApi;
import com.example.user.fandroidvktest.rest.api.GroupsApi;
import com.example.user.fandroidvktest.rest.api.UsersApi;
import com.example.user.fandroidvktest.rest.api.VideoApi;
import com.example.user.fandroidvktest.rest.api.WallApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;



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

}






















