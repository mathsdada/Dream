package com.mission2019.dreamcricket.dreamcricket;

import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

public class MatchActivity extends AppCompatActivity {
    private static final String TAG = MatchActivity.class.getSimpleName();
    public static final String KEY_MATCH_DATA = "KEY_MATCH_DATA";
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    private JSONObject mMatchJsonObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);

        // Extract Intent Data from Source
        String intentJsonData = getIntent().getStringExtra(KEY_MATCH_DATA);
        try {
            mMatchJsonObject = new JSONObject(intentJsonData);
        } catch (JSONException e) {
            e.printStackTrace();
        }

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
        mViewPager = (ViewPager) findViewById(R.id.container);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        // Below code will disable swiping in the view pager..
        mViewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));
    }
    private void changeActionBarTitle() {
        try {
            JSONArray teamJsonArray = mMatchJsonObject.getJSONArray("match_teams");
            String teamA = teamJsonArray.getJSONObject(0).getString("team_short_name");
            String teamB = teamJsonArray.getJSONObject(1).getString("team_short_name");
            getSupportActionBar().setTitle(teamA.toUpperCase() + " vs " +
                                           teamB.toUpperCase());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {
        private ArrayList<Fragment> mFragmentList;

        SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
            Fragment fragment = null;
            mFragmentList = new ArrayList<>();

            Bundle bundle = new Bundle();
            bundle.putString(KEY_MATCH_DATA, mMatchJsonObject.toString());

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
