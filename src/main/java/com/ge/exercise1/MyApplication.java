package com.ge.exercise1;

import java.util.Collection;
import java.util.NoSuchElementException;

public class MyApplication extends Application {

    private Collection<User>  users;
    private Collection<Group> groups;

    public MyApplication(String id, String name) {
        super(id, name);
    }

    @Override
    public Collection<User> getUsers() {
        return users;
    }

    @Override
    public User getUser(String userId) {

        // users.stream().forEach(user -> System.out.println(user.getId() +"--------------frm getUser"));

        try {
            return users.stream().filter(user -> userId.equals(user.getId())).findAny().get();
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    @Override
    public Collection<Group> getGroups() {
        return groups;
    }

    @Override
    public Group getGroup(String groupId) {
        try {
            return groups.stream().filter(group -> groupId.equals(group.getId())).findAny().get();
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    public void setGroups(Collection<Group> groups) {
        this.groups = groups;
    }

    public void setUsers(Collection<User> users) {
        this.users = users;
    }

    public int getGroupSize() {
        return groups.size();
    }

    public int getUserSize() {
        return users.size();
    }

    @Override
    public String toString() {
        return "MyApplication id =" + super.getId() + ",name=" + super.getName() + ", [users=" + users + ", groups=" + groups + "]";
    }

}
