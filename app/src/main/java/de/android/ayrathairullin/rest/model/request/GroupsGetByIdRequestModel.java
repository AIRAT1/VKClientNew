package de.android.ayrathairullin.rest.model.request;


import com.google.gson.annotations.SerializedName;
import com.vk.sdk.api.VKApiConst;

import java.util.Map;

import de.android.ayrathairullin.consts.ApiConstants;

public class GroupsGetByIdRequestModel extends BaseRequestModel{

    @SerializedName(VKApiConst.GROUP_ID)
    int groupid;

    @SerializedName(VKApiConst.FIELDS)
    String fields = ApiConstants.DEFAULT_GROUP_FIELDS;

    public GroupsGetByIdRequestModel(int groupid) {
        this.groupid = Math.abs(groupid);
    }

    public int getGroupid() {
        return groupid;
    }

    public String getFields() {
        return fields;
    }

    public void setGroupid(int groupid) {
        this.groupid = Math.abs(groupid);
    }

    public void setFields(String fields) {
        this.fields = fields;
    }

    @Override
    public void onMapCreate(Map<String, String> map) {
        map.put(VKApiConst.GROUP_ID, String.valueOf(getGroupid()));
        map.put(VKApiConst.FIELDS, String.valueOf(getFields()));

    }
}















