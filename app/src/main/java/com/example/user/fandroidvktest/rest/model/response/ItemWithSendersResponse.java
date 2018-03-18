package com.example.user.fandroidvktest.rest.model.response;


import com.example.user.fandroidvktest.model.Group;
import com.example.user.fandroidvktest.model.Owner;
import com.example.user.fandroidvktest.model.Profile;

import java.util.ArrayList;
import java.util.List;

public class ItemWithSendersResponse<T> extends BaseItemResponse<T> {
    private List<Profile> profiles = new ArrayList<>();
    private List<Group> groups = new ArrayList<>();

    private List<Profile> getProfiles() {
        return profiles;
    }

    private List<Group> getGroups() {
        return groups;
    }

    private List<Owner> getAllSenders() {
        List<Owner> all = new ArrayList<>();
        all.addAll(getProfiles());
        all.addAll(getGroups());
        return all;
    }

    public Owner getSender(int id) {
        for (Owner owner : getAllSenders()) {
            if (owner.getId() == Math.abs(id)) {
                return owner;
            }
        }
        return null;
    }
}
