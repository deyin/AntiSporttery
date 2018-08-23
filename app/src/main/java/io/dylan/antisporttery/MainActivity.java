package io.dylan.antisporttery;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashMap;
import java.util.Map;

import static android.view.View.VISIBLE;

public class MainActivity extends AppCompatActivity implements SportteryFragment.OnSportterySelectedListener {

    private ViewPager mViewPager;
    private ViewPagerAdapter mFragmentPagerAdapter;
    private BottomNavigationView mBottomNavigationView;
    private int lastSelectedMenuItemPos = -1;

    private Map<String, Sporttery> selectedSporttery = new HashMap<>();

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_sporttery:
                case R.id.navigation_dashboard:
                case R.id.navigation_notifications:
                    mViewPager.setCurrentItem(item.getOrder());
                    return true;
            }
            return false;
        }
    };

    private ViewPager.OnPageChangeListener mOnPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            if(lastSelectedMenuItemPos > -1) {
                mBottomNavigationView.getMenu().getItem(lastSelectedMenuItemPos).setChecked(false);
            } else {
                mBottomNavigationView.getMenu().getItem(0).setChecked(false);
            }
            mBottomNavigationView.getMenu().getItem(position).setChecked(false);
            lastSelectedMenuItemPos = position;
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBottomNavigationView= (BottomNavigationView) findViewById(R.id.navigation);
        mBottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        mFragmentPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        mFragmentPagerAdapter.addFragment(SportteryFragment.newInstance(0, "Matches"));
        mViewPager.setAdapter(mFragmentPagerAdapter);
        mViewPager.addOnPageChangeListener(mOnPageChangeListener);
    }

    public void shownBottomNavigation(boolean flag) {
        mBottomNavigationView.setVisibility(flag ? VISIBLE : View.GONE);
    }

    @Override
    public boolean onSportterySelected(Sporttery sporttery, int id) {
        mBottomNavigationView.setVisibility(VISIBLE);
        selectedSporttery.put(sporttery.getOdds().getId(), sporttery);
        switch (id) {
            case R.id.tv_win:
                sporttery.getOdds().getHad().setWinSelected(!sporttery.getOdds().getHad().isWinSelected());
                return sporttery.getOdds().getHad().isWinSelected();
            case R.id.tv_draw:
                sporttery.getOdds().getHad().setDrawSelected(!sporttery.getOdds().getHad().isDrawSelected());
                return sporttery.getOdds().getHad().isDrawSelected();
            case R.id.tv_lose:
                sporttery.getOdds().getHad().setLoseSelected(!sporttery.getOdds().getHad().isLoseSelected());
                return sporttery.getOdds().getHad().isLoseSelected();
            case R.id.tv_spread_win:
                sporttery.getOdds().getHhad().setWinSelected(!sporttery.getOdds().getHhad().isWinSelected());
                return sporttery.getOdds().getHhad().isWinSelected();
            case R.id.tv_spread_draw:
                sporttery.getOdds().getHhad().setDrawSelected(!sporttery.getOdds().getHhad().isDrawSelected());
                return sporttery.getOdds().getHhad().isDrawSelected();
            case R.id.tv_spread_lose:
                sporttery.getOdds().getHhad().setLoseSelected(!sporttery.getOdds().getHhad().isLoseSelected());
                return sporttery.getOdds().getHhad().isLoseSelected();
            default:
                throw new IllegalArgumentException("Unkown view id: " + id);
        }
    }
}
