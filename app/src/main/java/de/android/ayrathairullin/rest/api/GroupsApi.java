package de.android.ayrathairullin.rest.api;


import java.util.List;
import java.util.Map;

import de.android.ayrathairullin.model.Group;
import de.android.ayrathairullin.model.Member;
import de.android.ayrathairullin.rest.model.response.BaseItemResponse;
import de.android.ayrathairullin.rest.model.response.Full;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface GroupsApi {

    @GET(ApiMethods.GROUPS_GET_MEMBERS)
    Observable<Full<BaseItemResponse<Member>>> getMembers(@QueryMap Map<String, String> map);

    @GET(ApiMethods.GROUPS_GET_BY_ID)
    Observable<Full<List<Group>>> getById(@QueryMap Map<String, String> map);
}
