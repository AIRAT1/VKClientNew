package de.android.ayrathairullin.rest.model.request;


import com.google.gson.annotations.SerializedName;
import com.vk.sdk.api.VKApiConst;

import java.util.Map;

import de.android.ayrathairullin.consts.ApiConstants;

public class UsersGetRequestModel extends BaseRequestModel{

    @SerializedName(VKApiConst.USER_IDS)
    String userId;

    @SerializedName(VKApiConst.FIELDS)
    String fields = ApiConstants.DEFAULT_USER_FIELDS;

    public UsersGetRequestModel(String userId) {
        this.userId = userId;
    }

    public String getUserIds() {
        return userId;
    }

    public String getFields() {
        return fields;
    }

    public void setUserIds(String userIds) {
        this.userId = userIds;
    }

    public void setFields(String fields) {
        this.fields = fields;
    }

    @Override
    public void onMapCreate(Map<String, String> map) {

        map.put(VKApiConst.USER_ID, getUserIds());
        map.put(VKApiConst.FIELDS, getFields());

    }
}
