package com.mission2019.dreamcricket.dreamcricket.Model.TeamStats.Batting;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mission2019.dreamcricket.dreamcricket.Model.TableRow;

import java.util.ArrayList;

public class BattingBestStrikeRateResponse {
    @SerializedName("overall")
    @Expose
    private ArrayList<BattingBestStrikeRate> mMostRunsOverall;

    @SerializedName("atVenue")
    @Expose
    private ArrayList<BattingBestStrikeRate> mMostRunsAtVenue;

    public ArrayList<BattingBestStrikeRate> getMostRunsOverall() {
        return mMostRunsOverall;
    }

    public void setMostRunsOverall(ArrayList<BattingBestStrikeRate> mostRunsOverall) {
        mMostRunsOverall = mostRunsOverall;
    }

    public ArrayList<BattingBestStrikeRate> getMostRunsAtVenue() {
        return mMostRunsAtVenue;
    }

    public void setMostRunsAtVenue(ArrayList<BattingBestStrikeRate> mostRunsAtVenue) {
        mMostRunsAtVenue = mostRunsAtVenue;
    }

    public ArrayList<TableRow> convertToTableRows(ArrayList<BattingBestStrikeRate> bestStrikeRates) {
        ArrayList<TableRow> tableRows = new ArrayList<>();
        tableRows.add(new TableRow("Batsman", "I", "R", "B", "SR"));
        for (BattingBestStrikeRate bestStrikeRate: bestStrikeRates) {
            tableRows.add(new TableRow(bestStrikeRate.getBatsman(), bestStrikeRate.getMatches(),
                    bestStrikeRate.getRuns(), bestStrikeRate.getBalls(),
                    bestStrikeRate.getStrikeRate()));
        }
        return tableRows;
    }
}
