package com.mission2019.dreamcricket.dreamcricket.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mission2019.dreamcricket.dreamcricket.Common.Utility;
import com.mission2019.dreamcricket.dreamcricket.Custom.CustomViewPager;
import com.mission2019.dreamcricket.dreamcricket.Fragment.PlayersFragment;
import com.mission2019.dreamcricket.dreamcricket.Model.Schedule.ScheduleMatch;
import com.mission2019.dreamcricket.dreamcricket.Model.Schedule.ScheduleTeam;
import com.mission2019.dreamcricket.dreamcricket.R;
import com.mission2019.dreamcricket.dreamcricket.Fragment.TeamsFragment;
import com.mission2019.dreamcricket.dreamcricket.Fragment.VenueFragment;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MatchActivity extends AppCompatActivity {
    private static final String TAG = MatchActivity.class.getSimpleName();
    public static final String KEY_MATCH_DATA = "KEY_MATCH_DATA";
    private ScheduleMatch mMatch;
    private String mStatsType = null;
    private String mCurrentTeam = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);

        Intent intent = getIntent();
        String data = intent.getStringExtra(KEY_MATCH_DATA);
        Gson gson = new Gson();
        Type type = new TypeToken<ScheduleMatch>() {}.getType();
        mMatch = gson.fromJson(data, type);
        setupActionBar();
    }
    private void setupActionBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        /* Change Action Bar Title */
        String teamA = mMatch.getTeams().get(0).getShortName();
        String teamB = mMatch.getTeams().get(1).getShortName();
        getSupportActionBar().setTitle(teamA.toUpperCase() + " vs " +
                teamB.toUpperCase());
        /* setup Spinners */
        setUpStatsSelectionSpinner();
        setUpTeamSelectionSpinner(mMatch.getTeams().get(0).getShortName(),
                mMatch.getTeams().get(1).getShortName());
    }
    private void setUpTeamSelectionSpinner(String firstTeam, final String secondTeam) {
        String[] teams = {firstTeam, secondTeam};
        final Spinner spinner = findViewById(R.id.team_spinner);
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(MatchActivity.this,
                R.layout.stats_spinner_item,
                teams);
        spinnerAdapter.setDropDownViewResource(R.layout.stats_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedTeam = spinner.getSelectedItem().toString();
                if ((mCurrentTeam == null) || (!mCurrentTeam.equals(selectedTeam))) {
                    mCurrentTeam = selectedTeam;
                    updateUI(mStatsType, mCurrentTeam);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    private void setUpStatsSelectionSpinner() {
        String[] statTypes = {Utility.TEAM_STATS, Utility.PLAYER_STATS, Utility.VENUE_STATS};
        final Spinner spinner = findViewById(R.id.stats_type_spinner);
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(MatchActivity.this,
                R.layout.stats_spinner_item,
                statTypes);
        spinnerAdapter.setDropDownViewResource(R.layout.stats_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);
        if (mStatsType != null) {
            int spinnerPosition = spinnerAdapter.getPosition(mStatsType);
            spinner.setSelection(spinnerPosition);
            mStatsType = null;
        }
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedType = spinner.getSelectedItem().toString();
                if ((mStatsType == null) || (!mStatsType.equals(selectedType))) {
                    mStatsType = selectedType;
                    updateUI(mStatsType, mCurrentTeam);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    private void updateUI(String statsType, String team) {
        if (statsType == null || team == null) return;

        ScheduleTeam targetTeam, oppTeam;
        Bundle bundle = new Bundle();
        Gson gson = new Gson();

        if (team.equals(mMatch.getTeams().get(0).getName())) {
            targetTeam = mMatch.getTeams().get(0);
            oppTeam = mMatch.getTeams().get(1);
        } else {
            oppTeam = mMatch.getTeams().get(0);
            targetTeam = mMatch.getTeams().get(1);
        }
        bundle.putString(TeamsFragment.KEY_TARGET_TEAM, gson.toJson(targetTeam));
        bundle.putString(TeamsFragment.KEY_OPP_TEAM, gson.toJson(oppTeam));
        bundle.putString(TeamsFragment.KEY_FORMAT, mMatch.getFormat());
        bundle.putString(TeamsFragment.KEY_VENUE, mMatch.getVenue());
        switch (statsType) {
            case Utility.TEAM_STATS: {
                TeamsFragment fragment = new TeamsFragment();
                fragment.setArguments(bundle);
                getSupportFragmentManager().
                        beginTransaction().
                        replace(R.id.container, fragment).
                        commit();
                break;
            }
            case Utility.PLAYER_STATS: {
                PlayersFragment fragment = new PlayersFragment();
                fragment.setArguments(bundle);
                getSupportFragmentManager().
                        beginTransaction().
                        replace(R.id.container, fragment).
                        commit();
                break;
            }
            case Utility.VENUE_STATS: {
                break;
            }
        }
    }
}
