package com.radiant.myinvite.fragments;

import android.Manifest;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.pm.PackageManager;
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

        long callId = 3;
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
        values.put(CalendarContract.Events.EVENT_TIMEZONE, "India/Kolkatta");

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
        }

        return view;
    }

}
