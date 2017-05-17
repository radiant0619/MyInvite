package com.radiant.myinvite.fragments;

import android.graphics.Typeface;
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

        TextView MyTextView = (TextView) view.findViewById(R.id.myname);
        TextView txtTit2 = (TextView) view.findViewById(R.id.tit1);
        TextView txtTit1 = (TextView) view.findViewById(R.id.mytit);
        TextView txtfrnd = (TextView) view.findViewById(R.id.frnd);
        TextView txtEmail = (TextView) view.findViewById(R.id.email1);

        Typeface mFont = Typeface.createFromAsset(getActivity().getAssets(), "fonts/myfonts.otf");
        Typeface mFont1 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/amersn.ttf");
        Typeface mFont2 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/saloon_extth.ttf");
        Typeface mFont3 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/tuscan_narrow.ttf");

        MyTextView.setTypeface(mFont);
        txtTit1.setTypeface(mFont3);
        txtTit2.setTypeface(mFont3);
        txtfrnd.setTypeface(mFont1);
        txtEmail.setTypeface(mFont2);

        return view;
    }

}
