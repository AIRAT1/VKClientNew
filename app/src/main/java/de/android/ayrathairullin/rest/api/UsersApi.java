package de.android.ayrathairullin.rest.api;


import java.util.List;
import java.util.Map;

import de.android.ayrathairullin.model.Profile;
import de.android.ayrathairullin.rest.model.response.Full;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface UsersApi {

    @GET(ApiMethods.USERS_GET)
    Observable<Full<List<Profile>>> get(@QueryMap Map<String, String> map);
}
