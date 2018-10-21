package com.mission2019.dreamcricket.dreamcricket.Adapter;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mission2019.dreamcricket.dreamcricket.Model.TeamStats.RecentMatches.InningsScore;
import com.mission2019.dreamcricket.dreamcricket.Model.TeamStats.RecentMatches.MatchScore;
import com.mission2019.dreamcricket.dreamcricket.R;

import java.util.ArrayList;

public class MatchScoresRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<MatchScore> mMatchScores;

    public MatchScoresRecyclerViewAdapter(ArrayList<MatchScore> matchScores) {
        mMatchScores = matchScores;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MatchCardViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.team_stats_fragment_match_score_card, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((MatchCardViewHolder)holder).bindViews(mMatchScores.get(position));
    }

    @Override
    public int getItemCount() {
        return mMatchScores.size();
    }

    private class MatchCardViewHolder extends RecyclerView.ViewHolder {
        private TextView mMatchOutcomeTV;
        private TextView mInn1TeamNameTV, mInn1TeamScoreTV;
        private TextView mInn2TeamNameTV, mInn2TeamScoreTV;
        private TextView mInn3TeamNameTV, mInn3TeamScoreTV;
        private TextView mInn4TeamNameTV, mInn4TeamScoreTV;
        private TextView mMatchInfoTV;
        MatchCardViewHolder(@NonNull View itemView) {
            super(itemView);
            mMatchOutcomeTV = itemView.findViewById(R.id.match_outcome_tv);
            mInn1TeamNameTV = itemView.findViewById(R.id.innings_1_team_name_tv); mInn1TeamScoreTV = itemView.findViewById(R.id.innings_1_score_tv);
            mInn2TeamNameTV = itemView.findViewById(R.id.innings_2_team_name_tv); mInn2TeamScoreTV = itemView.findViewById(R.id.innings_2_score_tv);
            mInn3TeamNameTV = itemView.findViewById(R.id.innings_3_team_name_tv); mInn3TeamScoreTV = itemView.findViewById(R.id.innings_3_score_tv);
            mInn4TeamNameTV = itemView.findViewById(R.id.innings_4_team_name_tv); mInn4TeamScoreTV = itemView.findViewById(R.id.innings_4_score_tv);
            mMatchInfoTV = itemView.findViewById(R.id.match_info_tv);
        }
        void bindViews(MatchScore matchScore) {
            mMatchOutcomeTV.setText(matchScore.getMatchWinningText());
            switch (matchScore.getMatchOutcome()) {
                case "WIN": mMatchOutcomeTV.setTextColor(Color.parseColor("#43a047")); break;
                case "LOSS": mMatchOutcomeTV.setTextColor(Color.parseColor("#f4511e")); break;
                default:break;
            }

            String matchInfo = matchScore.getMatchDate() + " . " +
                    matchScore.getMatchVenue().split(",")[1];
            mMatchInfoTV.setText(matchInfo);

            ArrayList<InningsScore> inningsScores = matchScore.getInningsScores();
            for (int i=0; i<inningsScores.size(); i++) {
                switch (i) {
                    case 0: {
                        mInn1TeamNameTV.setText(inningsScores.get(i).getBattingTeam()); mInn1TeamNameTV.setVisibility(View.VISIBLE);
                        mInn1TeamScoreTV.setText(inningsScores.get(i).getInningsScore()); mInn1TeamScoreTV.setVisibility(View.VISIBLE);
                        break;
                    }
                    case 1: {
                        mInn2TeamNameTV.setText(inningsScores.get(i).getBattingTeam()); mInn2TeamNameTV.setVisibility(View.VISIBLE);
                        mInn2TeamScoreTV.setText(inningsScores.get(i).getInningsScore()); mInn2TeamScoreTV.setVisibility(View.VISIBLE);
                        break;
                    }
                    case 2: {
                        mInn3TeamNameTV.setText(inningsScores.get(i).getBattingTeam()); mInn3TeamNameTV.setVisibility(View.VISIBLE);
                        mInn3TeamScoreTV.setText(inningsScores.get(i).getInningsScore()); mInn3TeamScoreTV.setVisibility(View.VISIBLE);
                        break;
                    }
                    case 3: {
                        mInn4TeamNameTV.setText(inningsScores.get(i).getBattingTeam()); mInn4TeamNameTV.setVisibility(View.VISIBLE);
                        mInn4TeamScoreTV.setText(inningsScores.get(i).getInningsScore()); mInn4TeamScoreTV.setVisibility(View.VISIBLE);
                        break;
                    }
                }
            }
        }
    }
}
