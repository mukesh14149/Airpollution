package com.example.mukesh.airpollution;

/**
 * Created by mukesh on 23/1/16.
 *
 * updated by Siddharth on 3/3/16.
 */


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.maps.android.PolyUtil;
import java.util.List;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity
implements OnMapClickListener, OnMapReadyCallback {

    //final int RQS_GooglePlayServices = 1;
    private GoogleMap mMap;
    //private GoogleMap googleMap;



    boolean markerClicked;
    PolygonOptions polygonOptions;
    Polygon polygon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        //  SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
        //        .findFragmentById(R.id.map);
        // mapFragment.getMapAsync(this);


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        //  SupportMapFragment mapFragment = (SupportMapFragment) this.getSupportFragmentManager()
        //        .findFragmentById(R.id.map);
        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync((OnMapReadyCallback) this);


    }

    @Override
    public void onMapReady(GoogleMap googleMap)
    {
        mMap = googleMap;
        mMap.setOnMapClickListener(this);

        ArrayList<Polygon> polygonList = new ArrayList<>();
        Polygon polygon = mMap.addPolygon(new PolygonOptions()
                        .add(new LatLng(28.555335, 76.798553), new LatLng(28.830117, 76.935883), new LatLng(28.882919, 77.080078), new LatLng(28.868489, 77.220154), new LatLng(28.714438, 77.328644), new LatLng(28.519141, 77.3698434), (new LatLng(28.399615, 77.196808)), (new LatLng(28.555335, 76.798553)))
                        .strokeColor(Color.RED)

                //    .fillColor(Color.BLUE)
                //
        );



        // ArrayList<Polygon> polygonList = new ArrayList<>();
        Polygon p1 = mMap.addPolygon(new PolygonOptions()
                .add(new LatLng(28.882919, 77.080078), new LatLng(28.752972, 77.078705), new LatLng(28.714438, 77.328644), new LatLng(28.868489, 77.220154),new LatLng(28.882919, 77.080078))

                .strokeColor(Color.RED)

                .fillColor(Color.BLUE));

        // ArrayList<Polygon> polygonList = new ArrayList<>();
        Polygon p2 = mMap.addPolygon(new PolygonOptions()
                        .add(new LatLng(28.882919, 77.080078), new LatLng(28.752972, 77.078705), new LatLng(28.830117, 76.935883), new LatLng(28.882919, 77.080078))

                        .strokeColor(Color.RED)

                //.fillColor(Color.BLUE)
        );
        Polygon p3 = mMap.addPolygon(new PolygonOptions()
                        .add(new LatLng(28.555335, 76.798553), new LatLng(28.752972, 77.078705), new LatLng(28.830117, 76.935883), new LatLng(28.555335, 76.798553))

                        .strokeColor(Color.RED)

                // .fillColor(Color.BLUE)
        );
        final LatLng SYDNEY = new LatLng(28.6139,77.2090);
        final LatLng MOUNTAIN_VIEW = new LatLng(28.6387, 77.0941);

        final LatLngBounds.Builder bounds;
        final LatLng s=new LatLng(28.643353 ,77.446747);
        bounds = new LatLngBounds.Builder();
        bounds.include(new LatLng(28.643353, 77.446747));
        bounds.include(new LatLng(28.509488, 76.823273));

        // Obtain the map from a MapFragment or MapView.

// Move the camera instantly to Sydney with a zoom of 15.
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(SYDNEY, 10));

// Zoom in, animating the camera.
        mMap.animateCamera(CameraUpdateFactory.zoomIn());

// Zoom out to zoom level 10, animating with a duration of 2 seconds.
        mMap.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);

       // mMap.setOnMapClickListener(true);

// Construct a CameraPosition focusing on Mountain View and animate the camera to that position.
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(MOUNTAIN_VIEW)      // Sets the center of the map to Mountain View
                .zoom(10)                   // Sets the zoom
                .bearing(90)                // Sets the orientation of the camera to east
                .tilt(30)                   // Sets the tilt of the camera to 30 degrees
                .build();                   // Creates a CameraPosition from the builder
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));











        mMap.setOnCameraChangeListener(new GoogleMap.OnCameraChangeListener() {

                                           @Override
                                           public void onCameraChange(CameraPosition arg0) {
                                               // Move camera.
                                               // googleMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds.build(),
                                               if (arg0.zoom >= 10) ;

                                               else if (arg0.zoom < 9) {
                                                   CameraPosition cameraPosition1 = new CameraPosition.Builder()
                                                           .target(MOUNTAIN_VIEW)      // Sets the center of the map to Mountain View
                                                           .zoom(12)                   // Sets the zoom
                                                           .bearing(90)                // Sets the orientation of the camera to east
                                                           .tilt(30)                   // Sets the tilt of the camera to 30 degrees
                                                           .build();                   // Creates a CameraPosition from the builder
                                                   mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition1));

                                               }
                                               // Remove listener to prevent position reset on camera move.
                                               //  googleMap.setOnCameraChangeListener(googleMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds.build(),12)));


                                               //googleMap.(latlngbounds);
                                           }

                                       }

        );

    }



    @Override
    public void onMapClick(LatLng point) {
        Polygon polygon = mMap.addPolygon(new PolygonOptions()
                .add(new LatLng(28.555335, 76.798553), new LatLng(28.830117, 76.935883), new LatLng(28.882919, 77.080078), new LatLng(28.868489, 77.220154), new LatLng(28.714438, 77.328644), new LatLng(28.519141, 77.3698434), (new LatLng(28.399615, 77.196808)), (new LatLng(28.555335, 76.798553))));

        Polygon p1 = mMap.addPolygon(new PolygonOptions()
                        .add(new LatLng(28.882919, 77.080078), new LatLng(28.752972, 77.078705), new LatLng(28.714438, 77.328644), new LatLng(28.868489, 77.220154),new LatLng(28.882919, 77.080078))
        );

        Polygon p2 = mMap.addPolygon(new PolygonOptions()
                        .add(new LatLng(28.882919, 77.080078), new LatLng(28.752972, 77.078705), new LatLng(28.830117, 76.935883), new LatLng(28.882919, 77.080078))
        );

        Polygon p3 = mMap.addPolygon(new PolygonOptions()
                        .add(new LatLng(28.555335, 76.798553), new LatLng(28.752972, 77.078705), new LatLng(28.830117, 76.935883), new LatLng(28.555335, 76.798553))
        );


        //CHANGE POLYGON SPECIFICATIONS
        //ADD ALL POLYGONS


        List<LatLng> points = polygon.getPoints();
        List<LatLng> points1 = p1.getPoints();
        List<LatLng> points2 = p2.getPoints();
        List<LatLng> points3 = p3.getPoints();


        if (PolyUtil.containsLocation(point, points, false)) {
            //Do SOMETHING...
        } else if (PolyUtil.containsLocation(point, points1, false)) {
            //Do SOMETHING...
        }
        if (PolyUtil.containsLocation(point, points2, false)) {
            //Do SOMETHING...
        }
        if (PolyUtil.containsLocation(point, points3, false)) {
            //Do SOMETHING...
        }


    }



}