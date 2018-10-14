package com.mission2019.dreamcricket.dreamcricket.Model.TeamStats;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TeamForm {
    @SerializedName("outcome")
    @Expose
    private String mOutcome;

    @SerializedName("opp_team")
    @Expose
    private String mOppTeam;

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
