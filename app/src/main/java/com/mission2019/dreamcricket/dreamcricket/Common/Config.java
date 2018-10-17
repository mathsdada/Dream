package com.mission2019.dreamcricket.dreamcricket.Common;

import java.util.ArrayList;
import java.util.Arrays;

public class Config {
//    public static final String API_BASE_URL = "https://young-tor-24482.herokuapp.com/";
    public static final String API_BASE_URL = "http://192.168.0.103:5000/";
//    public static final String API_KEY = "1234567890";

    public static final String TEAM_BATTING_FORM = "Recent Match Results";
    public static final String TEAM_BATTING_MATCHES = "Recent Match Scores";
    public static final String TEAM_BATTING_MOST_RUNS = "Most Runs";
    public static final String TEAM_BATTING_BEST_AVG = "Best Batting Average";
    public static final String TEAM_BATTING_BEST_SR = "Best Batting Strike Rate";
    public static final String TEAM_BATTING_MOST_DUCKS = "Most Ducks";
    public static final String TEAM_BATTING_MOST_50s = "Most 50s";
    public static final String TEAM_BATTING_MOST_100s = "Most 100s";
    public static final String TEAM_BATTING_MOST_4s = "Most 4s";
    public static final String TEAM_BATTING_MOST_6s = "Most 6s";
    public static final String TEAM_BATTING_HIGH_SCORES = "High Scores";

    public static final String TEAM_BOWLING_MOST_WICKETS = "Most Wickets";
    public static final String TEAM_BOWLING_BEST_ECO = "Best Bowling Economy";
    public static final String TEAM_BOWLING_BEST_SR = "Best Bowling Strike Rate";
    public static final String TEAM_BOWLING_MOST_4PLUS_WKTS = "Most 4+ wickets in Innings";
    public static final String TEAM_BOWLING_MOST_5PLUS_WKTS = "Most 5+ wickets in Innings";
    public static final String TEAM_BOWLING_MOST_MAIDENS = "Most Maiden Overs";
    public static final String TEAM_BOWLING_BEST_INN_BOWLING = "Best Bowling Figures in Innings";
    public static final String TEAM_BOWLING_MOST_RUNS_CONCEDED_INN = "Most Runs Conceded in Innings";

    public static final String TEAM_HEAD_TO_HEAD_RUNS_AGAINST_BOWLING_STYLES = "Runs Against Bowling Styles";
    public static final String TEAM_HEAD_TO_HEAD_RUNS_AGAINST_BOWLERS = "Runs Against Opp. Bowlers";

    public static final ArrayList<String> teamStatsCategories = new ArrayList<>(Arrays.asList(
             "Recent Matches",
                TEAM_BATTING_FORM , TEAM_BATTING_MATCHES,
             "Batting Records",
                TEAM_BATTING_MOST_RUNS, /*TEAM_BATTING_BEST_AVG, */TEAM_BATTING_BEST_SR,
                /*TEAM_BATTING_MOST_DUCKS, */TEAM_BATTING_MOST_50s, TEAM_BATTING_MOST_100s,
                TEAM_BATTING_MOST_4s, TEAM_BATTING_MOST_6s, TEAM_BATTING_HIGH_SCORES,
             "Bowling Records",
                TEAM_BOWLING_MOST_WICKETS, TEAM_BOWLING_BEST_ECO, /*TEAM_BOWLING_BEST_SR,*/
                TEAM_BOWLING_MOST_4PLUS_WKTS, TEAM_BOWLING_MOST_5PLUS_WKTS,
                /*TEAM_BOWLING_MOST_MAIDENS, */TEAM_BOWLING_BEST_INN_BOWLING,
                TEAM_BOWLING_MOST_RUNS_CONCEDED_INN,
             "Head to Head Records",
                TEAM_HEAD_TO_HEAD_RUNS_AGAINST_BOWLING_STYLES,
                TEAM_HEAD_TO_HEAD_RUNS_AGAINST_BOWLERS));
}
