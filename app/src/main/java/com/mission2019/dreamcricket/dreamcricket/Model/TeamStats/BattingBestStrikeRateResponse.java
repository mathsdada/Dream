package com.mission2019.dreamcricket.dreamcricket.Model.TeamStats;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mission2019.dreamcricket.dreamcricket.Model.TableRow;

import java.util.ArrayList;

public class BattingBestStrikeRateResponse {
    @SerializedName("overall")
    @Expose
    private ArrayList<BestBatStrikeRate> mMostRunsOverall;

    @SerializedName("atVenue")
    @Expose
    private ArrayList<BestBatStrikeRate> mMostRunsAtVenue;

    public ArrayList<BestBatStrikeRate> getMostRunsOverall() {
        return mMostRunsOverall;
    }

    public void setMostRunsOverall(ArrayList<BestBatStrikeRate> mostRunsOverall) {
        mMostRunsOverall = mostRunsOverall;
    }

    public ArrayList<BestBatStrikeRate> getMostRunsAtVenue() {
        return mMostRunsAtVenue;
    }

    public void setMostRunsAtVenue(ArrayList<BestBatStrikeRate> mostRunsAtVenue) {
        mMostRunsAtVenue = mostRunsAtVenue;
    }

    public ArrayList<TableRow> convertToTableRows(ArrayList<BestBatStrikeRate> bestStrikeRates) {
        ArrayList<TableRow> tableRows = new ArrayList<>();
        tableRows.add(new TableRow("Batsman", "I", "R", "B", "SR"));
        for (BestBatStrikeRate bestStrikeRate: bestStrikeRates) {
            tableRows.add(new TableRow(bestStrikeRate.getBatsman(), bestStrikeRate.getMatches(),
                    bestStrikeRate.getRuns(), bestStrikeRate.getBalls(),
                    bestStrikeRate.getStrikeRate()));
        }
        return tableRows;
    }
}
