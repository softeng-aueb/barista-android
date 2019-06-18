package com.example.stsisko.helloworldgradle.activities;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.stsisko.helloworldgradle.R;
import com.example.stsisko.helloworldgradle.sights.SightManager;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.Collection;

public class GeolocationTestActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    private LinearLayout linearLayout;
    private ArrayList<View> textViews;

    LocationManager locationManager;

    LocationListener locationListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_geolocation_test);

        this.linearLayout = (LinearLayout)findViewById(R.id.map_list);
        this.textViews = new ArrayList<>();
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // User gave permissions
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 10, this.locationListener);
            }
        }
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        /**
         * USER LOCATION LOGIC
         */

        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                Log.i("Location", location.toString());

                LatLng currentLocation = new LatLng(location.getLatitude(), location.getLongitude());
                mMap.clear();


                Collection<String> nearby = SightManager.findNearbySights(currentLocation,500);
                clearViewList();
                nearby.forEach((s)->{
                    appendTextView(s);
                    LatLng currentSight = SightManager.sights.get(s);
                    mMap.addMarker(new MarkerOptions().position(currentSight).title(s).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));
                });
                mMap.addMarker(new MarkerOptions().position(currentLocation).title("Your Location"));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation,16));


            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };

        if(Build.VERSION.SDK_INT < 23 ){
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,10,locationListener);

        }
        // for > 23 SDK must ask for permissions
        else{
            //REQUEST PERMISSIONS iF NO GRANTED
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);

            }
            else{ // permission allready granted
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,10,locationListener);

                Location lastKnownLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                LatLng location = new LatLng(lastKnownLocation.getLatitude(), lastKnownLocation.getLongitude());
                mMap.clear();
                mMap.addMarker(new MarkerOptions()
                        .position(location).title("Marker in AUEB")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location,18));;
            }
        }


    }

    private void clearViewList() {
        for(View  v : this.textViews){
            this.linearLayout.removeView(v);
        }
        this.textViews.clear();
    }


    private void appendTextView(String value) {

        TextView valueTV = new TextView(this);
        this.textViews.add(valueTV);

        valueTV.setText(value);
        valueTV.setId(this.textViews.size());
        valueTV.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.FILL_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));

        this.textViews.add(valueTV);
        this.linearLayout.addView(valueTV);

    }
}
