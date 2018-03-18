package de.android.ayrathairullin.rest.api;

import java.util.Map;

import de.android.ayrathairullin.rest.model.response.Full;
import de.android.ayrathairullin.rest.model.response.VideosResponse;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface VideoApi {
    @GET(ApiMethods.VIDEO_GET)
    Observable<Full<VideosResponse>> get(@QueryMap Map<String, String> map);
}
