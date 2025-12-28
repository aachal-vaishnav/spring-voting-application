package com.example.Voting;


import org.springframework.stereotype.Component;

@Component("democratic")
public class Democratic implements PoliticalParty{

    private String partyName = "Democratic";
    @Override
    public String getPartyName() {
        return this.partyName;
    }
}