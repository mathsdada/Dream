package com.mission2019.dreamcricket.dreamcricket.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mission2019.dreamcricket.dreamcricket.Model.Schedule.ScheduleMatch;
import com.mission2019.dreamcricket.dreamcricket.R;
import com.mission2019.dreamcricket.dreamcricket.Common.Utility;

import java.util.ArrayList;

public class ScheduleRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<ScheduleMatch> mScheduleDataSet;
    private MatchCardItemClickListener mListener;

    public ScheduleRecyclerViewAdapter(ArrayList<ScheduleMatch> scheduleDataSet, MatchCardItemClickListener listener) {
        this.mScheduleDataSet = scheduleDataSet;
        mListener = listener;
    }

    public interface MatchCardItemClickListener {
        void onMatchCardItemClick(int pos);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View matchView = LayoutInflater.from(parent.getContext()).inflate(R.layout.schedule_activity_match_card, parent, false);
        return new MatchViewHolder(matchView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((MatchViewHolder)holder).bindViews(mScheduleDataSet.get(position));
    }
    
    @Override
    public int getItemCount() {
        return mScheduleDataSet.size();
    }

    private class MatchViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final String TAG = MatchViewHolder.class.getSimpleName();
        private TextView mTeamATextView, mTeamBTextView;
        private TextView mTitleTextView, mTimeTextView, mSeriesTextView;
        MatchViewHolder(View matchView) {
            super(matchView);
            mTeamATextView = matchView.findViewById(R.id.team_one_tv);
            mTeamBTextView = matchView.findViewById(R.id.team_two_tv);
            mTitleTextView = matchView.findViewById(R.id.match_title_tv);
            mTimeTextView = matchView.findViewById(R.id.match_time_tv);
            mSeriesTextView = matchView.findViewById(R.id.series_title_tv);
            matchView.setOnClickListener(this);
        }
        void bindViews(ScheduleMatch match) {
            String[] venue = match.getVenue().split(",");
            String[] title = match.getTitle().split(",", 2);
            String match_title = match.getFormat() + " . " +
                    title[title.length-1] + " . " + venue[venue.length-1];
            mTitleTextView.setText(match_title);
            mTimeTextView.setText(Utility.convertEpochTime(match.getTime()));
            mTeamATextView.setText(match.getTeams().get(0).getName());
            mTeamBTextView.setText(match.getTeams().get(1).getName());
            mSeriesTextView.setText(match.getSeriesTitle());
        }

        @Override
        public void onClick(View v) {
            mListener.onMatchCardItemClick(getAdapterPosition());
        }
    }
}
