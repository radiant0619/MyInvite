package com.radiant.myinvite.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.radiant.myinvite.R;

/**
 * Created by rsvra on 08/05/2017.
 */

public class ContactFragment extends Fragment {

    private TextView MyTextView;

    public ContactFragment() {
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
        View view = inflater.inflate(R.layout.frag_contacts, container, false);
//        MyTextView = (TextView) view.findViewById(R.id.textView2);
//        Typeface mFont = Typeface.createFromAsset(getActivity().getAssets(), "fonts/myfonts.otf");
////        Typeface mFont = Typeface.createFromAsset(getAssets(), "fonts/myfonts.otf");
//        MyTextView.setTypeface(mFont);

        return view;
    }

}
