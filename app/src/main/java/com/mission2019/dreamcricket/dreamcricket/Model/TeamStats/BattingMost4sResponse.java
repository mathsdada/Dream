package com.mission2019.dreamcricket.dreamcricket.Model.TeamStats;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mission2019.dreamcricket.dreamcricket.Model.TableRow;

import java.util.ArrayList;

public class BattingMost4sResponse {
    @SerializedName("overall")
    @Expose
    private ArrayList<BattingMost4s> mMostRunsOverall;

    @SerializedName("atVenue")
    @Expose
    private ArrayList<BattingMost4s> mMostRunsAtVenue;

    public ArrayList<BattingMost4s> getOverallStats() {
        return mMostRunsOverall;
    }

    public void setMostRunsOverall(ArrayList<BattingMost4s> mostRunsOverall) {
        mMostRunsOverall = mostRunsOverall;
    }

    public ArrayList<BattingMost4s> getAtVenueStats() {
        return mMostRunsAtVenue;
    }

    public void setMostRunsAtVenue(ArrayList<BattingMost4s> mostRunsAtVenue) {
        mMostRunsAtVenue = mostRunsAtVenue;
    }

    public ArrayList<TableRow> convertToTableRows(ArrayList<BattingMost4s> most4s) {
        ArrayList<TableRow> tableRows = new ArrayList<>();
        tableRows.add(new TableRow("Batsman", "I", "R", "B", "4s"));
        for (BattingMost4s battingMost4s: most4s) {
            tableRows.add(new TableRow(battingMost4s.getBatsman(), battingMost4s.getMatches(),
                    battingMost4s.getRuns(), battingMost4s.getBalls(), battingMost4s.getFours()));
        }
        return tableRows;
    }
}
