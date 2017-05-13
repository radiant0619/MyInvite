package com.radiant.myinvite.fragments;

import android.graphics.Typeface;

import java.text.SimpleDateFormat;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.radiant.myinvite.R;

import java.util.Date;

/**
 * Created by rsvra on 08/05/2017.
 */

public class HomeFragment extends Fragment {

    private TextView MyTextView;
    private TextView tvDay, tvHour, tvMinute, tvSecond, tvEvent;
    private LinearLayout linearLayout1, linearLayout2;
    private Handler handler;
    private Runnable runnable;

    public HomeFragment() {
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
        View view = inflater.inflate(R.layout.frag_home, container, false);
        MyTextView = (TextView) view.findViewById(R.id.textView2);
        Typeface mFont = Typeface.createFromAsset(getActivity().getAssets(), "fonts/myfonts.otf");
//        Typeface mFont = Typeface.createFromAsset(getAssets(), "fonts/myfonts.otf");
        MyTextView.setTypeface(mFont);

//        linearLayout1 = (LinearLayout) view.findViewById(R.id.ll1);
//        linearLayout2 = (LinearLayout) view.findViewById(R.id.ll2);
        tvDay = (TextView) view.findViewById(R.id.txtTimerDay);
        tvHour = (TextView) view.findViewById(R.id.txtTimerHour);
        tvMinute = (TextView) view.findViewById(R.id.txtTimerMinute);
        tvSecond = (TextView) view.findViewById(R.id.txtTimerSecond);
//        tvEvent = (TextView) view.findViewById(R.id.tvevent);

        countDownStart();
        return view;
    }


    // //////////////COUNT DOWN START/////////////////////////
    public void countDownStart() {
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                handler.postDelayed(this, 1000);
                try {
                    SimpleDateFormat dateFormat = new SimpleDateFormat(
                            "yyyy-MM-dd");
                    // Here Set your Event Date
                    Date eventDate = dateFormat.parse("2017-06-28");
                    Date currentDate = new Date();
                    if (!currentDate.after(eventDate)) {
                        long diff = eventDate.getTime()
                                - currentDate.getTime();
                        long days = diff / (24 * 60 * 60 * 1000);
                        diff -= days * (24 * 60 * 60 * 1000);
                        long hours = diff / (60 * 60 * 1000);
                        diff -= hours * (60 * 60 * 1000);
                        long minutes = diff / (60 * 1000);
                        diff -= minutes * (60 * 1000);
                        long seconds = diff / 1000;
                        tvDay.setText("" + String.format("%02d", days));
                        tvHour.setText("" + String.format("%02d", hours));
                        tvMinute.setText("" + String.format("%02d", minutes));
                        tvSecond.setText("" + String.format("%02d", seconds));
                    } else {
                        linearLayout1.setVisibility(View.VISIBLE);
                        linearLayout2.setVisibility(View.GONE);
//                        tvEvent.setText("Android Event Start");
                        handler.removeCallbacks(runnable);
                        // handler.removeMessages(0);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        handler.postDelayed(runnable, 0);
    }

    // //////////////COUNT DOWN END/////////////////////////

}
