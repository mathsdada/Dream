package com.mission2019.dreamcricket.dreamcricket;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ScheduleRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<Object> mScheduleDataSet;
    private static final int ITEM_TYPE_SERIES = 0;
    private static final int ITEM_TYPE_MATCH = 1;

    public ScheduleRecyclerViewAdapter(ArrayList<Object> scheduleDataSet) {
        this.mScheduleDataSet = scheduleDataSet;
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
            ((MatchViewHolder)holder).bindViews((JSONObject) mScheduleDataSet.get(position));
        }
    }
    
    @Override
    public int getItemViewType(int position) {
        if(mScheduleDataSet.get(position) instanceof String) {
            return ITEM_TYPE_SERIES;
        } else if (mScheduleDataSet.get(position) instanceof JSONObject) {
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

    private class MatchViewHolder extends RecyclerView.ViewHolder {
        private TextView mTeamATextView, mTeamBTextView;
        private TextView mVenueTextView, mTimeTextView;
        MatchViewHolder(View matchView) {
            super(matchView);
            mTeamATextView = matchView.findViewById(R.id.team_a_tv);
            mTeamBTextView = matchView.findViewById(R.id.team_b_tv);
            mVenueTextView = matchView.findViewById(R.id.title_venue_tv);
            mTimeTextView = matchView.findViewById(R.id.time_tv);
        }
        void bindViews(JSONObject matchJsonObject) {
            try {
                mVenueTextView.setText(matchJsonObject.getString("match_venue"));
                mTimeTextView.setText(matchJsonObject.getString("match_time"));

                JSONArray teamJsonArray = matchJsonObject.getJSONArray("match_teams");
                JSONObject teamAJsonObject = teamJsonArray.getJSONObject(0);
                JSONObject teamBJsonObject = teamJsonArray.getJSONObject(1);
                mTeamATextView.setText(teamAJsonObject.getString("team_name"));
                mTeamBTextView.setText(teamBJsonObject.getString("team_name"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
