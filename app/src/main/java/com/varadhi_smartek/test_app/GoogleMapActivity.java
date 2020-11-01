package com.varadhi_smartek.test_app;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import java.io.IOException;
import java.util.List;

public class GoogleMapActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    String imageAddress, addressLine1, addressLine2, landMark, city, state, pincode;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_map);

        Intent intent = getIntent();
        imageAddress = intent.getStringExtra("imageAddress");
        addressLine1 = intent.getStringExtra("addressLine1");
        addressLine2 = intent.getStringExtra("addressLine2");
        landMark = intent.getStringExtra("landMark");
        city = intent.getStringExtra("city");
        state = intent.getStringExtra("state");
        pincode = intent.getStringExtra("pincode");

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        String full_address = addressLine1 + ", " + addressLine2 + ", " + landMark + ", " + city + ", " + state + ", " + pincode;
        LatLng latLng = getLocationFromAddress(full_address);
        mMap.addMarker(new MarkerOptions()
                .position(latLng)
                .title(full_address));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        float zoomLevel = 10.0f;
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoomLevel));
    }

    public LatLng getLocationFromAddress(String strAddress) {
        Geocoder coder = new Geocoder(GoogleMapActivity.this);
        List<Address> address;
        LatLng p1 = null;

        try {
            // May throw an IOException
            address = coder.getFromLocationName(strAddress, 5);
            if (address == null) {
                return null;
            }

            Address location = address.get(0);
            p1 = new LatLng(location.getLatitude(), location.getLongitude() );

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return p1;
    }
}
