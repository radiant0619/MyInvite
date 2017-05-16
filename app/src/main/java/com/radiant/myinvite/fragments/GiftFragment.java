package com.radiant.myinvite.fragments;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.radiant.myinvite.R;
import com.radiant.myinvite.adapter.ExapandableAdapter;
import com.radiant.myinvite.adapter.ExpandableListDataPump;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by rsvra on 08/05/2017.
 */

public class GiftFragment extends Fragment {

    private TextView txtviewUs;
    private TextView txtViewYou;
    private TextView txtVwUs;
    private TextView txtVwYou;
    ExpandableListView expandableListView;
    ExpandableListAdapter expandableListAdapter;
    List<String> expandableListTitle;
    HashMap<String, List<String>> expandableListDetail;

    public GiftFragment() {
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
        View view = inflater.inflate(R.layout.frag_gifts, container, false);
        Typeface mFont = Typeface.createFromAsset(getActivity().getAssets(), "fonts/myfonts.otf");
        Typeface mFont1 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/amersn.ttf");
        Typeface mFont2 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/saloon_extth.ttf");
        Typeface mFont3 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/tuscan_narrow.ttf");
//        Typeface mFont4 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/woodcut.TTF");

        txtviewUs = (TextView) view.findViewById(R.id.txt1);
        txtviewUs.setTypeface(mFont3);

        txtViewYou = (TextView) view.findViewById(R.id.txt4);
        txtViewYou.setTypeface(mFont3);

        txtVwUs = (TextView) view.findViewById(R.id.txt2);
        txtVwUs.setTypeface(mFont2);

        txtVwYou = (TextView) view.findViewById(R.id.txt3);
        txtVwYou.setTypeface(mFont3);


        expandableListView = (ExpandableListView) view.findViewById(R.id.listexpands);
        expandableListDetail = ExpandableListDataPump.getData();
        expandableListTitle = new ArrayList<String>(expandableListDetail.keySet());
        expandableListAdapter = new ExapandableAdapter(getActivity(), expandableListTitle, expandableListDetail);
        expandableListView.setAdapter(expandableListAdapter);
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
//                Toast.makeText(getActivity().getApplicationContext(),
//                        expandableListTitle.get(groupPosition) + " List Expanded.",
//                        Toast.LENGTH_SHORT).show();
            }
        });

        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
//                Toast.makeText(getActivity().getApplicationContext(),
//                        expandableListTitle.get(groupPosition) + " List Collapsed.",
//                        Toast.LENGTH_SHORT).show();
            }
        });

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
//                Toast.makeText(getActivity().getApplicationContext(),
//                        expandableListTitle.get(groupPosition) + " -> " + expandableListDetail.get(
//                                expandableListTitle.get(groupPosition)).get(childPosition), Toast.LENGTH_SHORT
//                ).show();
                return false;
            }
        });


        return view;
    }

}
