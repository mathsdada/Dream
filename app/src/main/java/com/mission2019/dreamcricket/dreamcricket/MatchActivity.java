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
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MatchActivity extends AppCompatActivity {

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
        mViewPager.setAdapter(new SectionsPagerAdapter(getSupportFragmentManager()));

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
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
        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;
            String key = "";
            switch (position) {
                case 0: {
                    fragment = new TeamsFragment();
                    key = TeamsFragment.KEY_MATCH_DATA_TEAM_FRAGMENT;
                    break;
                }
                case 1: {
                    fragment = new PlayersFragment();
                    key = PlayersFragment.KEY_MATCH_DATA_PLAYER_FRAGMENT;
                    break;
                }
                case 2: {
                    fragment = new VenueFragment();
                    key = VenueFragment.KEY_MATCH_DATA_VENUE_FRAGMENT;
                    break;
                }
            }
            if (fragment != null) {
                Bundle bundle = new Bundle();
                bundle.putString(key, mMatchJsonObject.toString());
                fragment.setArguments(bundle);
            }
            return fragment;
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
