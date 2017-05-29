package com.radiant.myinvite;

import android.*;
import android.Manifest;
import android.app.ActivityManager;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.provider.SyncStateContract;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.TaskStackBuilder;
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
import android.widget.TextView;
import android.widget.Toast;

import com.github.amlcurran.showcaseview.ShowcaseView;
import com.github.amlcurran.showcaseview.SimpleShowcaseEventListener;
import com.github.amlcurran.showcaseview.targets.ViewTarget;
import com.radiant.myinvite.fragments.ContactFragment;
import com.radiant.myinvite.fragments.CoupleFragment;
import com.radiant.myinvite.fragments.GiftFragment;
import com.radiant.myinvite.fragments.HomeFragment;
import com.radiant.myinvite.fragments.LocationFragment;
import com.radiant.myinvite.fragments.MapFragments;
import com.radiant.myinvite.fragments.WedFragment;
import com.radiant.myinvite.service.BackgroundSoundService;
import com.radiant.myinvite.utils.Utilities;

import java.security.Permission;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class HomeInviteActivity extends AppCompatActivity implements ActivityCompat.OnRequestPermissionsResultCallback {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private boolean isMute = false;
    private boolean isEventAdded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_home_invite);
        setContentView(R.layout.app_bar_home_invite);
//        toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        if (isFirstTime()) {
            new ShowcaseView.Builder(this)
                    .withMaterialShowcase()
                    .setContentTitle("Page Navigation")
                    .setContentText("Swipe Left/Right or Click any item.")
                    .setStyle(R.style.CustomShowcaseTheme2)
                    .setTarget(new ViewTarget(R.id.tabs, this))
                    .hideOnTouchOutside()
                    .build();
        }
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setOffscreenPageLimit(5);
        setupViewPager(viewPager);
        viewPager.setPageTransformer(false, new ViewPager.PageTransformer() {
            @Override
            public void transformPage(View page, float position) {
                page.setRotationY(position * -30);
            }
        });

        tabLayout = (TabLayout) findViewById(R.id.tabs);

        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();

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
                    case 4:
                        imgView.setImageResource(R.drawable.m_gift_s);
                        break;
                    case 5:
                        imgView.setImageResource(R.drawable.m_con_s);
                        break;
//                    case 6:
//                        imgView.setImageResource(R.drawable.m_con_s);
//                        break;
                }
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
                    case 4:
                        imgView.setImageResource(R.drawable.m_gift);
                        break;
                    case 5:
                        imgView.setImageResource(R.drawable.m_con);
                        break;
//                    case 6:
//                        imgView.setImageResource(R.drawable.m_con);
//                        break;
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
//                getCalender();
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                if (isMute) {
                    ((FloatingActionButton) view).setImageResource(R.mipmap.unmute);
                    startService(new Intent(HomeInviteActivity.this, BackgroundSoundService.class));
                    isMute = false;
                } else {
                    ((FloatingActionButton) view).setImageResource(R.mipmap.mute);
                    isMute = true;
                    if (isServiceRunning()) {
                        stopService(new Intent(HomeInviteActivity.this, BackgroundSoundService.class));
                    }
                }
            }
        });
        if (!isEventAdded)
            setEvent();

//      test  pwd: Rsv@A017 Alias: Radiant pwd: marees96
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
        tabLayout.getTabAt(3).setCustomView(tabFour);

        ImageView tabFive = (ImageView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabFive.setImageResource(R.drawable.m_gift);
        tabLayout.getTabAt(4).setCustomView(tabFive);

        ImageView tabSix = (ImageView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabSix.setImageResource(R.drawable.m_con);
        tabLayout.getTabAt(5).setCustomView(tabSix);

//        ImageView tabSeven = (ImageView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
//        tabSeven.setImageResource(R.drawable.m_con);
//        tabLayout.getTabAt(6).setCustomView(tabSeven);
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

    private boolean isFirstTime() {
        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        boolean ranBefore = preferences.getBoolean("RanBefore", false);
        if (!ranBefore) {
            // first time
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("RanBefore", true);
            editor.commit();
        }
        return !ranBefore;
    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!isMute) {
            Intent svc = new Intent(this, BackgroundSoundService.class);
            startService(svc);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (!isMute)
            stopService(new Intent(this, BackgroundSoundService.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.home_invite, menu);
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


    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new HomeFragment(), "Home");
        adapter.addFrag(new WedFragment(), "Wedding Details");
        adapter.addFrag(new CoupleFragment(), "Bride & Groom");
        adapter.addFrag(new LocationFragment(), "Location");
//        adapter.addFrag(new MapFragments(), "Map");
        adapter.addFrag(new GiftFragment(), "Gift");
        adapter.addFrag(new ContactFragment(), "Contact");
//        adapter.addFrag(new OneFragment(), "EIGHT");
//        adapter.addFrag(new OneFragment(), "NINE");
//        adapter.addFrag(new OneFragment(), "TEN");
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Log.i("Permission Call back", "Success");
//        switch (requestCode) {
//            case 0: {
//                // If request is cancelled, the result arrays are empty.
//                if (grantResults.length > 0
//                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//
//                    // permission was granted, yay! Do the
//                    // contacts-related task you need to do.
//
//                } else {
//
//                    // permission denied, boo! Disable the
//                    // functionality that depends on this permission.
//                }
//                return;
//
//            }
//        }
    }

    private void setEvent() {
        long callId = 1;
        long startMillSec = 0;
        long endMillSec = 0;
        Calendar beginTime = Calendar.getInstance();
        beginTime.set(2017, 6, 28, 9, 01);
        startMillSec = beginTime.getTimeInMillis();
        Calendar endTime = Calendar.getInstance();
        endTime.set(2017, 6, 28, 11, 30);
        endMillSec = endTime.getTimeInMillis();

        ContentResolver cr = this.getContentResolver();
        ContentValues values = new ContentValues();
        values.put(CalendarContract.Events.DTSTART, startMillSec);
        values.put(CalendarContract.Events.DTEND, endMillSec);
        values.put(CalendarContract.Events.TITLE, "Sakthivel's Wedding");
        values.put(CalendarContract.Events.DESCRIPTION, "Wedding ceremony of Mr.Sakthivel & Ms.Marreswari @ Thirumohur");
        values.put(CalendarContract.Events.CALENDAR_ID, callId);
        values.put(CalendarContract.Events.EVENT_TIMEZONE, Calendar.getInstance()
                .getTimeZone().getID());

        if (Utilities.checkPermission(this, android.Manifest.permission.WRITE_CALENDAR,
                "Apps need to access Calendar", 0)) {
            Uri uri = cr.insert(CalendarContract.Events.CONTENT_URI, values);
            Long evenit;
            evenit = Long.parseLong(uri.getLastPathSegment());
            setReminder(cr, evenit, 1440);
            setReminder(cr, evenit, 60);
            setReminder(cr, evenit, 7200);
        }

    }

    public void setReminder(ContentResolver cr, long eventID, int timeBefore) {
        try {
            ContentValues values = new ContentValues();
            values.put(CalendarContract.Reminders.MINUTES, timeBefore);
            values.put(CalendarContract.Reminders.EVENT_ID, eventID);
            values.put(CalendarContract.Reminders.METHOD, CalendarContract.Reminders.METHOD_ALERT);
            if (Utilities.checkPermission(this, Manifest.permission.WRITE_CALENDAR,
                    "Apps need to access Calendar", 0)) {
                Uri uri = cr.insert(CalendarContract.Reminders.CONTENT_URI, values);
                Cursor c = CalendarContract.Reminders.query(cr, eventID,
                        new String[]{CalendarContract.Reminders.MINUTES});
                if (c.moveToFirst()) {
                    System.out.println("calendar"
                            + c.getInt(c.getColumnIndex(CalendarContract.Reminders.MINUTES)));
                }
                c.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getCalender() {
        Log.d("Calenders List", "cliecked");
        Cursor cur = null;
        ContentResolver cr = getContentResolver();
        Uri uri = CalendarContract.Calendars.CONTENT_URI;
        String selection = "((" + CalendarContract.Calendars.ACCOUNT_NAME + " = ?) AND ("
                + CalendarContract.Calendars.ACCOUNT_TYPE + " = ?) AND ("
                + CalendarContract.Calendars.OWNER_ACCOUNT + " = ?))";
        Log.d("Calenders List", "Have rights");
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CALENDAR) != PackageManager.PERMISSION_GRANTED) {
            cur = cr.query(uri, new String[]{CalendarContract.Calendars._ID,
                            CalendarContract.Calendars.ACCOUNT_NAME,
                            CalendarContract.Calendars.CALENDAR_DISPLAY_NAME,
                            CalendarContract.Calendars.OWNER_ACCOUNT}
                    , selection, null, null);
            Log.d("Calenders List", "Have rights");
            Log.d("Calenders List", String.valueOf(cur.getCount()));

            while (cur.moveToNext()) {
                Log.d("Calenders List", "Have data");
                long calID = 0;
                String displayName = null;
                String accountName = null;
                String ownerName = null;

                // Get the field values
                calID = cur.getLong(0);
                displayName = cur.getString(1);
                accountName = cur.getString(2);
                ownerName = cur.getString(3);
                Log.d("Calenders List", String.valueOf(calID) + "-" + displayName + "-" + accountName + "-" + ownerName);

            }
        }

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
