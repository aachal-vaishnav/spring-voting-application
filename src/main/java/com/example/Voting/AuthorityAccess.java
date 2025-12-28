package com.example.Voting;


import org.springframework.stereotype.Component;

@Component("authority")
public class AuthorityAccess implements CounterAuthority{

    private UserList userList;
    @Override
    public void setUserList(UserList userList) {
        this.userList = userList;
    }

    @Override
    public UserList getUserList() {
        return this.userList;
    }
}
