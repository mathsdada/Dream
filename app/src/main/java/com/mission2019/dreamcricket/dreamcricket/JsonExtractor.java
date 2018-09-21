package com.mission2019.dreamcricket.dreamcricket;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonExtractor {
    public static TeamStats extractTeamStatsFromJSON(JSONObject teamStatsResponseJSON) {
        TeamStats teamStats = null;
        try {
            String teamName = teamStatsResponseJSON.getString("team_name");
            teamStats = new TeamStats(teamName);

            JSONObject teamStatsJSON =  teamStatsResponseJSON.getJSONObject("team_stats");

            JSONArray teamFormDataJSONArray = teamStatsJSON.getJSONArray("form");
            teamStats.setTeamForm(JsonExtractorInternal.extractTeamForm(teamFormDataJSONArray));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return teamStats;
    }

    public static class JsonExtractorInternal {
        static TeamForm extractTeamForm(JSONArray teamFormDataJSONArray) throws JSONException {
            TeamForm teamForm = new TeamForm();
            for (int matchIndex = 0; matchIndex < teamFormDataJSONArray.length(); matchIndex++) {
                JSONObject teamMatchOutcomeJSON = teamFormDataJSONArray.getJSONObject(matchIndex);
                String outcome = teamMatchOutcomeJSON.getString("outcome");
                String oppTeam = teamMatchOutcomeJSON.getString("opp_team");
                teamForm.setTeamMatchOutcome(new TeamMatchOutcome(outcome, oppTeam));
            }
            return teamForm;
        }
    }
}
