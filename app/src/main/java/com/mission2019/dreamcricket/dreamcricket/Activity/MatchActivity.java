package com.mission2019.dreamcricket.dreamcricket.Activity;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mission2019.dreamcricket.dreamcricket.Custom.CustomViewPager;
import com.mission2019.dreamcricket.dreamcricket.Fragment.PlayersFragment;
import com.mission2019.dreamcricket.dreamcricket.Model.Schedule.ScheduleMatch;
import com.mission2019.dreamcricket.dreamcricket.R;
import com.mission2019.dreamcricket.dreamcricket.Fragment.TeamsFragment;
import com.mission2019.dreamcricket.dreamcricket.Fragment.VenueFragment;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MatchActivity extends AppCompatActivity {
    private static final String TAG = MatchActivity.class.getSimpleName();
    public static final String KEY_MATCH_DATA = "KEY_MATCH_DATA";
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private CustomViewPager mViewPager;
    private ScheduleMatch mMatch;

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
        setupViewPager();
    }

    private void setupActionBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        changeActionBarTitle();
    }

    private void setupViewPager() {
        mViewPager = findViewById(R.id.container);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));
        mViewPager.setPagingEnabled(false);
    }
    private void changeActionBarTitle() {
        String teamA = mMatch.getTeams().get(0).getShortName();
        String teamB = mMatch.getTeams().get(1).getShortName();
        getSupportActionBar().setTitle(teamA.toUpperCase() + " vs " +
                                       teamB.toUpperCase());
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {
        private ArrayList<Fragment> mFragmentList;

        SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
            Fragment fragment = null;
            mFragmentList = new ArrayList<>();

            Bundle bundle = new Bundle();
            Gson gson = new Gson();
            bundle.putString(KEY_MATCH_DATA, gson.toJson(mMatch));

            fragment = new TeamsFragment();
            fragment.setArguments(bundle);
            mFragmentList.add(0, fragment);

            fragment = new PlayersFragment();
            fragment.setArguments(bundle);
            mFragmentList.add(1, fragment);

            fragment = new VenueFragment();
            fragment.setArguments(bundle);
            mFragmentList.add(2, fragment);
        }


        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0: return "Team";
                case 1: return "Player";
                case 2: return "Venue";
            }
            return "";
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_match, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
