package de.android.ayrathairullin.rest.api;

import java.util.Map;

import de.android.ayrathairullin.model.CommentItem;
import de.android.ayrathairullin.rest.model.response.Full;
import de.android.ayrathairullin.rest.model.response.GetWallByIdResponse;
import de.android.ayrathairullin.rest.model.response.GetWallResponse;
import de.android.ayrathairullin.rest.model.response.ItemWithSendersResponse;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;



public interface WallApi {
    @GET(ApiMethods.WALL_GET)
    Observable<GetWallResponse> get(@QueryMap Map<String, String> map);

    @GET(ApiMethods.WALL_GET_BY_ID)
    Observable<GetWallByIdResponse> getById(@QueryMap Map<String, String> map);

    @GET(ApiMethods.WALL_GET_COMMENTS)
    Observable<Full<ItemWithSendersResponse<CommentItem>>> getComments(@QueryMap Map<String, String> map);

}
