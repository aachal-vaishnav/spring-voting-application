package com.example.Voting;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("authority")
public class AuthorityAccess implements CounterAuthority{

    @Autowired
    private UserList userList;//injecting user dependency

//    @Override
//    public void setUserList(UserList userList) {
//        this.userList = userList;
//    }


    @Override
    public UserList getUserList() {
        return this.userList;
    }
}
