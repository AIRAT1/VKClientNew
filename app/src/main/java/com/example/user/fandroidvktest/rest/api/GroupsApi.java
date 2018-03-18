package com.example.user.fandroidvktest.rest.api;


import com.example.user.fandroidvktest.model.Group;
import com.example.user.fandroidvktest.model.Member;
import com.example.user.fandroidvktest.rest.model.response.BaseItemResponse;
import com.example.user.fandroidvktest.rest.model.response.Full;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface GroupsApi {

    @GET(ApiMethods.GROUPS_GET_MEMBERS)
    Observable<Full<BaseItemResponse<Member>>> getMembers(@QueryMap Map<String, String> map);

    @GET(ApiMethods.GROUPS_GET_BY_ID)
    Observable<Full<List<Group>>> getById(@QueryMap Map<String, String> map);
}
