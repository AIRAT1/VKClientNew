package com.example.user.fandroidvktest.rest.api;


import com.example.user.fandroidvktest.model.CommentItem;
import com.example.user.fandroidvktest.model.Topic;
import com.example.user.fandroidvktest.rest.model.response.BaseItemResponse;
import com.example.user.fandroidvktest.rest.model.response.Full;
import com.example.user.fandroidvktest.rest.model.response.ItemWithSendersResponse;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface BoardApi {
    @GET(ApiMethods.BOARD_GET_TOPICS)
    Observable<Full<BaseItemResponse<Topic>>> getTopics(@QueryMap Map<String, String> map);

    @GET(ApiMethods.BOARD_GET_COMMENTS)
    Observable<Full<ItemWithSendersResponse<CommentItem>>> getComments(@QueryMap Map<String, String> map);
}
