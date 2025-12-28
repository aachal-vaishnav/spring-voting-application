package com.example.Voting;

public interface User {
    void setUserName(String userName);
    String getUserName();

    void setPoliticalParty(PoliticalParty party);
    PoliticalParty getParty();
}
