package com.akshay.fooddelivery.ui;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.akshay.fooddelivery.R;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import java.io.IOException;
import java.util.List;

public class LocationActivity extends BaseActivity implements OnMapReadyCallback, GoogleMap.OnMyLocationButtonClickListener {

    private static final String TAG = "LocationActivity";
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    private static final int DEFAULT_ZOOM = 15;

    private EditText etCity, etLocality, etBuildingName, etPincode, etState, etLandmark, etFullName, etPhoneNumber;

    private boolean mLocationPermissionsGranted = false;

    private GoogleMap mMap;
    private FusedLocationProviderClient mFusedLocationProviderClient;
    private Location mLastKnownLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        Log.wtf(TAG, "LocationActivity: onCreate() method Called");

        etCity = (EditText) findViewById(R.id.et_address_city);
        etLocality = (EditText) findViewById(R.id.et_address_locality);
        etBuildingName = (EditText) findViewById(R.id.et_address_building);
        etPincode = (EditText) findViewById(R.id.et_address_pincode);
        etState = (EditText) findViewById(R.id.et_address_state);
        etLandmark = (EditText) findViewById(R.id.et_address_landmark);
        etFullName = (EditText) findViewById(R.id.et_address_fullname);
        etPhoneNumber = (EditText) findViewById(R.id.et_address_phonenumber);

        getLocationPermission();
    }

    private void getLocationPermission(){

        Log.d(TAG, "getLocationPermission: getting location permissions");

        String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION};

        if(ContextCompat.checkSelfPermission(this.getApplicationContext(),
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){

            mLocationPermissionsGranted = true;
            initMap();
        }else{
            ActivityCompat.requestPermissions(this,
                    permissions,
                    LOCATION_PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Log.d(TAG, "onRequestPermissionsResult: called.");

        mLocationPermissionsGranted = false;

        switch(requestCode){
            case LOCATION_PERMISSION_REQUEST_CODE:{
                // If request is cancelled, the result arrays are empty.
                if ((grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {

                    Log.d(TAG, "onRequestPermissionsResult: permission granted");
                    mLocationPermissionsGranted = true;

                    //initialize our map
                    initMap();
                } else {
                    mLocationPermissionsGranted = false;
                    Log.d(TAG, "onRequestPermissionsResult: permission failed");
                }
                return;
            }
        }
    }

    private void initMap(){
        Log.d(TAG, "initMap: initializing map");

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(LocationActivity.this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        Log.wtf(TAG, "LocationActivity: OnMapReady");
        mMap = googleMap;

        if (mLocationPermissionsGranted) {
            getDeviceLocation();

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            mMap.setMyLocationEnabled(true);

            mMap.setOnMyLocationButtonClickListener(this);
        }
    }

    private void getDeviceLocation(){
        Log.wtf(TAG, "LocationActivity: gettingDeviceLocation");

        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        try{
            if(mLocationPermissionsGranted){
                @SuppressLint("MissingPermission") Task<Location> locationResult = mFusedLocationProviderClient.getLastLocation();
                locationResult.addOnCompleteListener(new OnCompleteListener<Location>() {
                    @Override
                    public void onComplete(@NonNull Task<Location> task) {
                        if (task.isSuccessful() && task.getResult() != null) {
                            Log.wtf(TAG, "location found");
                            mLastKnownLocation = task.getResult();

                            setAddressInfo(getLatlng(mLastKnownLocation));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(getLatlng(mLastKnownLocation), DEFAULT_ZOOM));
                        } else {
                            Log.wtf(TAG, "Current Location Not Found");
                            Toast.makeText(LocationActivity.this, "Unable to get Current Location", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }catch (Exception e){
            Log.wtf(TAG, "LocationActivity: getDeviceLocation: " + e.getMessage());
        }
    }

    public void setAddressInfo(LatLng latLng) {
        Geocoder geocoder = new Geocoder(LocationActivity.this);

        try {
            List<Address> addressList = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);
            if (addressList != null && addressList.size() > 0) {
                Log.wtf(TAG, "AddresList: " + addressList);
                //String addressLine = addressList.get(0).getAddressLine(0);
                String locality = addressList.get(0).getThoroughfare();
                String city = addressList.get(0).getLocality();
                String pincode = addressList.get(0).getPostalCode();
                String state = addressList.get(0).getAdminArea();

                setAddressFields(city, locality, pincode, state);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setAddressFields(String city, String locality, String pincode, String state) {
        etCity.setText(city);
        etLocality.setText(locality);
        etPincode.setText(pincode);
        etState.setText(state);
    }

    private LatLng getLatlng(Location location){
        LatLng latLng = new LatLng(location.getLatitude(),
                location.getLongitude());
        return latLng;
    }

    public void saveAddress(View view) {
        String city = etCity.getText().toString();
        String locality = etLocality.getText().toString();
        String buildingName = etBuildingName.getText().toString();
        String pincode = etPincode.getText().toString();
        String state = etState.getText().toString();
        String name = etFullName.getText().toString();
        String phoneNumber = etPhoneNumber.getText().toString();
        int flag = 1;

        if(city.isEmpty()){
            flag=0;
            etCity.setError("Enter City");
        }

        if(locality.isEmpty()) {
            flag=0;
            etLocality.setError("Enter locality or street");
        }
        if(buildingName.isEmpty()) {
            flag=0;
            etBuildingName.setError("Cannot be Empty");
        }
        if(pincode.isEmpty()) {
            flag=0;
            etPincode.setError("Enter Pincode");
        }
        if(state.isEmpty()) {
            flag=0;
            etState.setError("Enter State");
        }
        if(name.isEmpty()) {
            flag=0;
            etFullName.setError("Enter your Full Name");
        }
        if(phoneNumber.isEmpty()) {
            flag=0;
            etPhoneNumber.setError("Provide Phone Number");
        }

        if(flag==1){
            Intent intent = new Intent(LocationActivity.this, MainActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public boolean onMyLocationButtonClick() {
        getDeviceLocation();
        return true;
    }
}
