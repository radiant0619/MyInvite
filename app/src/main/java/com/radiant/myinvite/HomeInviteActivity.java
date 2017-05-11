package com.radiant.myinvite;

import android.app.ActivityManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.radiant.myinvite.fragments.CoupleFragment;
import com.radiant.myinvite.fragments.HomeFragment;
import com.radiant.myinvite.fragments.LocationFragment;
import com.radiant.myinvite.fragments.WedFragment;
import com.radiant.myinvite.service.BackgroundSoundService;

import java.util.ArrayList;
import java.util.List;

public class HomeInviteActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_invite);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();

        tabLayout.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                Log.d("Test Focus", "tested");
            }
        });
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                ImageView imgView = (ImageView) tab.getCustomView();
                switch (tab.getPosition()) {
                    case 0:
                        imgView.setImageResource(R.drawable.m_home_s);
                        break;
                    case 1:
                        imgView.setImageResource(R.drawable.m_wed_s);
                        break;
                    case 2:
                        imgView.setImageResource(R.drawable.m_abt_s);
                        break;
                    case 3:
                        imgView.setImageResource(R.drawable.m_loc_s);
                        break;
                }
//                Toast.makeText(getApplicationContext(), String.valueOf(tab.getPosition()), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                ImageView imgView = (ImageView) tab.getCustomView();
                switch (tab.getPosition()) {
                    case 0:
                        imgView.setImageResource(R.drawable.m_home);
                        break;
                    case 1:
                        imgView.setImageResource(R.drawable.m_wed);
                        break;
                    case 2:
                        imgView.setImageResource(R.drawable.m_abt);
                        break;
                    case 3:
                        imgView.setImageResource(R.drawable.m_loc);
                        break;
                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                Log.d("Test Reselect", "tested 3");
            }
        });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();

                if (isServiceRunning()) {
                    ((FloatingActionButton) view).setImageResource(R.mipmap.mute);
                    stopService(new Intent(HomeInviteActivity.this, BackgroundSoundService.class));
                } else {
                    ((FloatingActionButton) view).setImageResource(R.mipmap.unmute);
                    startService(new Intent(HomeInviteActivity.this, BackgroundSoundService.class));
                }
            }
        });
//        fab.setVisibility(View.INVISIBLE);
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
//                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        drawer.setDrawerListener(toggle);
//        toggle.syncState();
//
//        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
//        navigationView.setNavigationItemSelectedListener(this);

//        BackgroundSoundService.MY_SERVICE


    }

    private void setupTabIcons() {

        ImageView tabOne = (ImageView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabOne.setImageResource(R.drawable.m_home_s);
        tabLayout.getTabAt(0).setCustomView(tabOne);

        ImageView tabTwo = (ImageView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabTwo.setImageResource(R.drawable.m_wed);
        tabLayout.getTabAt(1).setCustomView(tabTwo);

        ImageView tabThree = (ImageView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabThree.setImageResource(R.drawable.m_abt);
        tabLayout.getTabAt(2).setCustomView(tabThree);

        ImageView tabFour = (ImageView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabFour.setImageResource(R.drawable.m_loc);
        tabLayout.getTabAt(3).setCustomView(tabThree);
    }

    private boolean isServiceRunning() {
        ActivityManager manager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            Log.d("service Name", service.service.getClassName());
            if ("com.radiant.myinvite.service.BackgroundSoundService".equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
//        Intent svc = new Intent(this, BackgroundSoundService.class);
//        startService(svc);
    }

    @Override
    protected void onPause() {
        super.onPause();
//        stopService(new Intent(this, BackgroundSoundService.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_invite, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new HomeFragment(), "Home");
        adapter.addFrag(new WedFragment(), "Wedding Details");
        adapter.addFrag(new CoupleFragment(), "Bride & Groom");
        adapter.addFrag(new LocationFragment(), "Location");
//        adapter.addFrag(new OneFragment(), "FIVE");
//        adapter.addFrag(new OneFragment(), "SIX");
//        adapter.addFrag(new OneFragment(), "SEVEN");
//        adapter.addFrag(new OneFragment(), "EIGHT");
//        adapter.addFrag(new OneFragment(), "NINE");
//        adapter.addFrag(new OneFragment(), "TEN");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }

    }


}
