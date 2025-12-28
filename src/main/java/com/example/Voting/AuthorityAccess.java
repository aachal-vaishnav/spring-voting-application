package com.example.Voting;


import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("authority")
public class AuthorityAccess implements CounterAuthority{

    @PostConstruct
    public void inti(){
        System.out.println("DB Connection successful");
    }
    @PreDestroy
    public void destroy(){
        System.out.println("Voting has been closed");
    }
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
