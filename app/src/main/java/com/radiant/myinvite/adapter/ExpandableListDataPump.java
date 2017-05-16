package com.radiant.myinvite.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpandableListDataPump {
//    http://www.journaldev.com/9942/android-expandablelistview-example-tutorial
    public static HashMap<String, List<String>> getData() {
        HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();

        List<String> fruits = new ArrayList<String>();
        fruits.add("Gooseberry");
        fruits.add("Pomegranate");
        fruits.add("Guava");
        fruits.add("Jamun");

        List<String> flowers = new ArrayList<String>();
        flowers.add("Magilam");
        flowers.add("Sarakondrai");
        flowers.add("Mayflower");

        List<String> shadows = new ArrayList<String>();
        shadows.add("Vembu");
        shadows.add("Punkan");


        List<String> timbers = new ArrayList<String>();
        timbers.add("Teak Wood");
        timbers.add("White Teak");
        timbers.add("Malai Vembu");
        timbers.add("Thandrikkai");

        expandableListDetail.put("Shadow Tree Plants", shadows);
        expandableListDetail.put("Timber Tree Plants", timbers);
        expandableListDetail.put("Flower Tree Plants", flowers);
        expandableListDetail.put("Fruit Tree Plants", fruits);
        return expandableListDetail;
    }
}
