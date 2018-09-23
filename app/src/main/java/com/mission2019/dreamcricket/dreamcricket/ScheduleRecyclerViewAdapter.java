package com.mission2019.dreamcricket.dreamcricket;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mission2019.dreamcricket.dreamcricket.Schedule.Match;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ScheduleRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<Object> mScheduleDataSet;
    private static final int ITEM_TYPE_SERIES = 0;
    private static final int ITEM_TYPE_MATCH = 1;
    private MatchCardItemClickListener mListener;

    ScheduleRecyclerViewAdapter(ArrayList<Object> scheduleDataSet, MatchCardItemClickListener listener) {
        this.mScheduleDataSet = scheduleDataSet;
        mListener = listener;
    }

    public interface MatchCardItemClickListener {
        void onMatchCardItemClick(int pos);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == ITEM_TYPE_SERIES) {
            View seriesView = LayoutInflater.from(parent.getContext()).inflate(R.layout.series_card_item, parent, false);
            return new SeriesViewHolder(seriesView);
        } else if (viewType == ITEM_TYPE_MATCH) {
            View matchView = LayoutInflater.from(parent.getContext()).inflate(R.layout.match_card_item, parent, false);
            return new MatchViewHolder(matchView);
        } else {
            return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        if (viewType == ITEM_TYPE_SERIES) {
            ((SeriesViewHolder)holder).bindViews((String) mScheduleDataSet.get(position));
        } else if (viewType == ITEM_TYPE_MATCH) {
            ((MatchViewHolder)holder).bindViews((Match) mScheduleDataSet.get(position));
        }
    }
    
    @Override
    public int getItemViewType(int position) {
        if(mScheduleDataSet.get(position) instanceof String) {
            return ITEM_TYPE_SERIES;
        } else if (mScheduleDataSet.get(position) instanceof Match) {
            return ITEM_TYPE_MATCH;
        }
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return mScheduleDataSet.size();
    }

    private class SeriesViewHolder extends RecyclerView.ViewHolder {
        private TextView mSeriesTitleTextView;

        SeriesViewHolder(View seriesView) {
            super(seriesView);
            mSeriesTitleTextView = seriesView.findViewById(R.id.series_title_tv);
        }

        void bindViews(String seriesTitle) {
            mSeriesTitleTextView.setText(seriesTitle);
        }

    }

    private class MatchViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final String TAG = MatchViewHolder.class.getSimpleName();
        private TextView mTeamATextView, mTeamBTextView;
        private TextView mTitleTextView, mTimeTextView;
        MatchViewHolder(View matchView) {
            super(matchView);
            mTeamATextView = matchView.findViewById(R.id.team_a_tv);
            mTeamBTextView = matchView.findViewById(R.id.team_b_tv);
            mTitleTextView = matchView.findViewById(R.id.match_title_tv);
            mTimeTextView = matchView.findViewById(R.id.match_time_tv);
            matchView.setOnClickListener(this);
        }
        void bindViews(Match match) {
            String[] venue = match.getVenue().split(",");
            String[] title = match.getTitle().split(",", 2);
            String match_title = title[title.length-1] + " . " + venue[venue.length-1];
            mTitleTextView.setText(match_title);
            mTimeTextView.setText(Utility.convertEpochTime(match.getTime()));
            mTeamATextView.setText(match.getTeams().get(0).getShortName());
            mTeamBTextView.setText(match.getTeams().get(1).getShortName());
        }

        @Override
        public void onClick(View v) {
            mListener.onMatchCardItemClick(getAdapterPosition());
        }
    }
}
