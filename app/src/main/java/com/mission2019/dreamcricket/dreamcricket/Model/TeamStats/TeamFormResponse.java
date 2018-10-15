package com.mission2019.dreamcricket.dreamcricket.Model.TeamStats;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mission2019.dreamcricket.dreamcricket.Model.TableRow;

import java.util.ArrayList;

public class TeamFormResponse {
    @SerializedName("overall")
    @Expose
    private ArrayList<TeamForm> mTeamFormOverall;

    @SerializedName("atVenue")
    @Expose
    private ArrayList<TeamForm> mTeamFormAtVenue;

    public ArrayList<TeamForm> getOverallStats() {
        return mTeamFormOverall;
    }

    public void setTeamFormOverall(ArrayList<TeamForm> teamFormOverall) {
        mTeamFormOverall = teamFormOverall;
    }

    public ArrayList<TeamForm> getAtVenueStats() {
        return mTeamFormAtVenue;
    }

    public void setTeamFormAtVenue(ArrayList<TeamForm> teamFormAtVenue) {
        mTeamFormAtVenue = teamFormAtVenue;
    }

    public ArrayList<TableRow> convertToTableRows(ArrayList<TeamForm> teamForm) {
        ArrayList<TableRow> tableRows = new ArrayList<>();
        tableRows.add(new TableRow("Outcome", "Opp.Team"));
        for (TeamForm form: teamForm) {
            tableRows.add(new TableRow(form.getOutcome(), form.getOppTeam()));
        }
        return tableRows;
    }
}
