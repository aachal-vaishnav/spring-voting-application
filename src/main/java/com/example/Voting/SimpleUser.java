package com.example.Voting;


import org.springframework.stereotype.Component;

@Component("user")
public class SimpleUser implements User{

    private String userName ;
    private PoliticalParty party; //political party inject into the user
    @Override
    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String getUserName() {
        return this.userName;
    }

    @Override
    public void setPoliticalParty(PoliticalParty party) {
        this.party= party;
    }

    @Override
    public PoliticalParty getParty() {
        return this.party;
    }
}
