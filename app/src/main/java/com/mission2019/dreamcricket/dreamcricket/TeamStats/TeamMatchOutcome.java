package com.mission2019.dreamcricket.dreamcricket.TeamStats;

public class TeamMatchOutcome {
    private String mOutcome;
    private String mOppTeam;

    public TeamMatchOutcome(String outcome, String oppTeam) {
        mOutcome = outcome;
        mOppTeam = oppTeam;
    }

    public String getOutcome() {
        return mOutcome;
    }

    public void setOutcome(String outcome) {
        mOutcome = outcome;
    }

    public String getOppTeam() {
        return mOppTeam;
    }

    public void setOppTeam(String oppTeam) {
        mOppTeam = oppTeam;
    }
}
