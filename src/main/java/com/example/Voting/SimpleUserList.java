package com.example.Voting;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("userList")
public class SimpleUserList implements UserList{

    List<User> userList;

    public SimpleUserList(List<User> userList) {
        this.userList = new ArrayList<User>();
    }

    @Override
    public void addUser(User user) {
        userList.add(user);
    }

    @Override
    public List<User> getUserList() {
        return this.userList;
    }
}
