package com.mission2019.dreamcricket.dreamcricket.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mission2019.dreamcricket.dreamcricket.Model.Schedule.ScheduleMatch;
import com.mission2019.dreamcricket.dreamcricket.Activity.MatchActivity;
import com.mission2019.dreamcricket.dreamcricket.R;
import java.lang.reflect.Type;

public class TeamsFragment extends Fragment {
    private static final String TAG = TeamsFragment.class.getSimpleName();
    private ScheduleMatch mScheduleMatch;

    public TeamsFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String matchData = getArguments().getString(MatchActivity.KEY_MATCH_DATA);

        Gson gson = new Gson();
        Type type = new TypeToken<ScheduleMatch>() {}.getType();
        mScheduleMatch = gson.fromJson(matchData, type);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_team_stats, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView mTeamATitleTextView = view.findViewById(R.id.team_a_title_tv);
        TextView mTeamBTitleTextView = view.findViewById(R.id.team_b_title_tv);

        mTeamATitleTextView.setText(mScheduleMatch.getTeams().get(0).getName());
        mTeamBTitleTextView.setText(mScheduleMatch.getTeams().get(1).getName());

        mTeamATitleTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        mTeamBTitleTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
