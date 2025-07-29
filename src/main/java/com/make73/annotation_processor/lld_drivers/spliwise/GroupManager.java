package com.make73.annotation_processor.lld_drivers.spliwise;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupManager {
    Map<Integer, Group> groups;

    public GroupManager() {
        this.groups = new HashMap<>();
    }

    public void createGroup(String name) {
        Group group = new Group(groups.size() + 1, name);
        groups.put(group.id, group);
    }

    public Group getGroup(Integer id) {
        return groups.get(id);
    }

    public List<Group> listGroups() {
        return new ArrayList<>(groups.values());
    }

}
