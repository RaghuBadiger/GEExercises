package com.ge.exercise1;

import java.util.List;

public abstract class Group {


    private String id;
    private String name;
    
    @Override
    public String toString() {
        return "Group [id=" + id + ", name=" + name + ", users=" + users + "]";
    }


    public List<User> getUsers() {
        return users;
    }

    
    public void setUsers(List<User> users) {
        this.users = users;
    }

    private List<User> users;

    public Group(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Group() {
        
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    

   
}
