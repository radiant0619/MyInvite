package com.radiant.myinvite.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.radiant.myinvite.R;

/**
 * Created by rsvra on 08/05/2017.
 */

public class MapFragments extends Fragment implements OnMapReadyCallback {

    static final LatLng marriagePlace = new LatLng(9.950863, 78.207583);
    static final LatLng myHome = new LatLng(9.951476, 78.205874);
    static final LatLng brideHome = new LatLng(9.429044, 77.806599);
    static final LatLng recepLoc = new LatLng(9.950533, 78.207555);
    private GoogleMap mMap;


    public MapFragments() {
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
        View view = inflater.inflate(R.layout.frag_map, container, false);
        if (mMap == null) {
            SupportMapFragment mapFrag = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
            mapFrag.getMapAsync(this);
        }

        return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        Marker hamburg = mMap.addMarker(new MarkerOptions().position(marriagePlace)
                .title("Sri Kalamegaperumal Temple")
                .snippet("Marriage Place"));

        Marker markGroomHome = mMap.addMarker(new MarkerOptions()
                .position(myHome)
                .title("Prince's Kingdom"));

        Marker markBrideHome = mMap.addMarker(new MarkerOptions()
                .position(brideHome)
                .title("Princess's Kingdom"));

        Marker recPlace = mMap.addMarker(new MarkerOptions()
                .position(recepLoc)
                .title("Sri Jeeyar Swamigal Marraige Hall")
                .snippet("Engagement & Reception"));

        // Move the camera instantly to hamburg with a zoom of 15.
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(marriagePlace, 25));

        // Zoom in, animating the camera.
        mMap.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);
//        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
//                ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            // TODO: Consider calling
//            //    ActivityCompat#requestPermissions
//            // here to request the missing permissions, and then overriding
//            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//            //                                          int[] grantResults)
//            // to handle the case where the user grants the permission. See the documentation
//            // for ActivityCompat#requestPermissions for more details.
//            return;
//        }
//        mMap.setMyLocationEnabled(true);
    }
}
