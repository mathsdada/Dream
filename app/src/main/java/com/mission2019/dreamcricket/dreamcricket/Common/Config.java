package com.mission2019.dreamcricket.dreamcricket.Common;

import java.util.ArrayList;
import java.util.Arrays;

public class Config {
//    public static final String API_BASE_URL = "https://young-tor-24482.herokuapp.com/";
    public static final String API_BASE_URL = "http://192.168.0.103:5000/";
//    public static final String API_KEY = "1234567890";

    public static final ArrayList<String> teamStatsCategories = new ArrayList<>(Arrays.asList(
             "Recent Matches",
                    "Recent Match Results", "Recent Match Scores",
             "Batting Records",
                    "Most Runs",            "Best Batting Average",     "Best Batting Strike Rate",
                    "Most Ducks",           "Most 50s",                 "Most 100s",
                    "Most 4s",              "Most 6s",                  "High Scores",
                    "Performance Against Bowling Styles",
             "Bowling Records",
                    "Most Wickets",         "Best Bowling Economy",     "Best Bowling Strike Rate",
                    "Best Bowling Average", "Most 4+ wickets in Innings", "Most 5+ wickets in Innings",
                    "Most Maiden Overs",    "Best Bowling Figures in Innings",
                    "Most Runs Conceded in Innings",
             "Head to Head Records",
                    "Batsman vs Opp. Team Bowling Styles",
                    "Batsman vs Opp. Team Bowler",
                    "Bowler vs Opp. Team Batsman"));
}
