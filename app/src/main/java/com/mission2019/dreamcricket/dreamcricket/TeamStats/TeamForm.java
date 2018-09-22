package com.mission2019.dreamcricket.dreamcricket.TeamStats;

import java.util.ArrayList;

public class TeamForm {
    private ArrayList<TeamMatchOutcome> mTeamForm;

    public TeamForm() {
        mTeamForm = new ArrayList<>();
    }

    public ArrayList<TeamMatchOutcome> getTeamForm() {
        return mTeamForm;
    }

    public void setTeamMatchOutcome(TeamMatchOutcome outcome) {
        mTeamForm.add(outcome);
    }
}
