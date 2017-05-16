package com.radiant.myinvite.fragments;

import android.Manifest;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.radiant.myinvite.R;
import com.radiant.myinvite.utils.Utilities;

import java.util.Calendar;

/**
 * Created by rsvra on 08/05/2017.
 */

public class LocationFragment extends Fragment {

    private TextView MyTextView;

    public LocationFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.frag_location, container, false);
//        MyTextView = (TextView) view.findViewById(R.id.textView2);
//        Typeface mFont = Typeface.createFromAsset(getActivity().getAssets(), "fonts/myfonts.otf");
////        Typeface mFont = Typeface.createFromAsset(getAssets(), "fonts/myfonts.otf");
//        MyTextView.setTypeface(mFont);

        long callId = 1;
        long startMillSec = 0;
        long endMillSec = 0;
        Calendar beginTime = Calendar.getInstance();
        beginTime.set(2017, 6, 28, 9, 01);
        startMillSec = beginTime.getTimeInMillis();
        Calendar endTime = Calendar.getInstance();
        endTime.set(2017, 6, 28, 11, 30);
        endMillSec = endTime.getTimeInMillis();

        ContentResolver cr = getActivity().getContentResolver();
        ContentValues values = new ContentValues();
        values.put(CalendarContract.Events.DTSTART, startMillSec);
        values.put(CalendarContract.Events.DTEND, endMillSec);
        values.put(CalendarContract.Events.TITLE, "Task Details");
        values.put(CalendarContract.Events.DESCRIPTION, "Task Description");
        values.put(CalendarContract.Events.CALENDAR_ID, callId);
        values.put(CalendarContract.Events.EVENT_TIMEZONE, Calendar.getInstance()
                .getTimeZone().getID());

//        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_CALENDAR) != PackageManager.PERMISSION_GRANTED) {
//            // TODO: Consider calling
//            //    ActivityCompat#requestPermissions
//            // here to request the missing permissions, and then overriding
//            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//            //                                          int[] grantResults)
//            // to handle the case where the user grants the permission. See the documentation
//            // for ActivityCompat#requestPermissions for more details.
//            return TODO;
//        }

        if (Utilities.checkPermission(getActivity(), Manifest.permission.WRITE_CALENDAR,
                "Apps need to access Calendar", 0)) {
            Uri uri = cr.insert(CalendarContract.Events.CONTENT_URI, values);
            Long evenit;
            evenit = Long.parseLong(uri.getLastPathSegment());
            setReminder(cr, evenit, 1440);
            setReminder(cr, evenit, 60);
            setReminder(cr, evenit, 7200);
        }

        return view;
    }

    public void setReminder(ContentResolver cr, long eventID, int timeBefore) {
        try {
            ContentValues values = new ContentValues();
            values.put(CalendarContract.Reminders.MINUTES, timeBefore);
            values.put(CalendarContract.Reminders.EVENT_ID, eventID);
            values.put(CalendarContract.Reminders.METHOD, CalendarContract.Reminders.METHOD_ALERT);
            if (Utilities.checkPermission(getActivity(), Manifest.permission.WRITE_CALENDAR,
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

}
