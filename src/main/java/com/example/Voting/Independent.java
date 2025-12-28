package com.example.Voting;


import org.springframework.stereotype.Component;

@Component("independent")
public class Independent implements PoliticalParty{

    private String partyName = "Independent";
    @Override
    public String getPartyName() {
        return this.partyName;
    }
}
