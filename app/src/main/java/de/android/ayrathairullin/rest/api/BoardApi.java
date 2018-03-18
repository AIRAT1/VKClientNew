package de.android.ayrathairullin.rest.api;


import java.util.Map;

import de.android.ayrathairullin.model.CommentItem;
import de.android.ayrathairullin.model.Topic;
import de.android.ayrathairullin.rest.model.response.BaseItemResponse;
import de.android.ayrathairullin.rest.model.response.Full;
import de.android.ayrathairullin.rest.model.response.ItemWithSendersResponse;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface BoardApi {
    @GET(ApiMethods.BOARD_GET_TOPICS)
    Observable<Full<BaseItemResponse<Topic>>> getTopics(@QueryMap Map<String, String> map);

    @GET(ApiMethods.BOARD_GET_COMMENTS)
    Observable<Full<ItemWithSendersResponse<CommentItem>>> getComments(@QueryMap Map<String, String> map);
}
