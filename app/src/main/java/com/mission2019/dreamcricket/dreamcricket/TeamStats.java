package com.mission2019.dreamcricket.dreamcricket;

public class TeamStats {
    private String mTeamName;
    private TeamForm mTeamForm;

    public TeamStats(String teamName) {
        mTeamName = teamName;
    }

    public String getTeamName() {
        return mTeamName;
    }

    public void setTeamName(String teamName) {
        mTeamName = teamName;
    }

    public TeamForm getTeamForm() {
        return mTeamForm;
    }

    public void setTeamForm(TeamForm teamForm) {
        mTeamForm = teamForm;
    }
}
