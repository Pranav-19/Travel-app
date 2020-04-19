package com.example.finallogin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.model.TypeFilter;
import com.google.android.libraries.places.api.net.FetchPlaceRequest;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class PlacesAround extends AppCompatActivity {
    boolean mLocationPermissionGranted=false;
    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION=1234;
    private Location mLastKnownLocation;
    private static final String TAG = "MapsActivity";
    private FusedLocationProviderClient mFusedLocationProviderClient;

    private Button getRestaurants;
    private Button getTouristPlaces;
    private Button getHotels;

    String cityName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_places_around);


        Intent intent=getIntent();
        if(intent.getStringExtra("cityName").equals(""))
        {
            mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

            getLocationPermission();

        }
        else
        {
            cityName=intent.getStringExtra("cityName");
            Toast.makeText(PlacesAround.this,"City set: "+cityName,Toast.LENGTH_SHORT).show();
        }

        Places.initialize(getApplicationContext(), "API_KEY");

        // Create a new Places client instance
        final PlacesClient placesClient = Places.createClient(this);


        // Initialize the AutocompleteSupportFragment.
        AutocompleteSupportFragment autocompleteFragment = (AutocompleteSupportFragment)
                getSupportFragmentManager().findFragmentById(R.id.autocomplete_fragment);

        // Specify the types of place data to return.
        autocompleteFragment.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.NAME));
//                autocompleteFragment.setCountry("IN");
//                autocompleteFragment.setTypeFilter(TypeFilter.ESTABLISHMENT);
        autocompleteFragment.setTypeFilter(TypeFilter.CITIES);


        // Set up a PlaceSelectionListener to handle the response.
        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                // TODO: Get info about the selected place.
                Log.i(TAG, "Place: " + place.getName() + ", " + place.getId());


                List<Place.Field> placeFields = Arrays.asList(Place.Field.ID, Place.Field.NAME,Place.Field.ADDRESS,Place.Field.LAT_LNG);
                FetchPlaceRequest request = FetchPlaceRequest.newInstance(place.getId(), placeFields);

                placesClient.fetchPlace(request).addOnSuccessListener((response) -> {
                    Place place1 = response.getPlace();
                    LatLng latLng1=place1.getLatLng();
                    mLastKnownLocation.setLatitude(latLng1.latitude);
                    mLastKnownLocation.setLongitude(latLng1.longitude);

                    Geocoder gcd = new Geocoder(PlacesAround.this, Locale.getDefault());
                    List<Address> addresses = null;
                    try {
                        addresses = gcd.getFromLocation(mLastKnownLocation.getLatitude(), mLastKnownLocation.getLongitude(), 1);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (addresses.size() > 0) {
                        cityName=addresses.get(0).getLocality();
                        Toast.makeText(PlacesAround.this,"City set: "+cityName,Toast.LENGTH_SHORT).show();

                    }
                    else {
                        // do your stuff
                    }


                    Toast.makeText(PlacesAround.this,"Location set",Toast.LENGTH_SHORT).show();


                }).addOnFailureListener((exception) -> {
                    if (exception instanceof ApiException) {
                        // Handle the error.
                    }
                });

            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
                Log.i(TAG, "An error occurred: " + status);
            }
        });




        getRestaurants=(Button) findViewById(R.id.getRestaurants);

        getRestaurants.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Log.d(TAG,"onClick called");
//                Object dataTransfer[] = new Object[1];
//                GetNearbyPlacesData getNearbyPlacesData = new GetNearbyPlacesData(MapsActivity.this);
//
//                String restaurant = "restaurant";
//                String url = getUrl(mLastKnownLocation.getLatitude(), mLastKnownLocation.getLongitude(), restaurant);
//                dataTransfer[0] = url;
//                getNearbyPlacesData.execute(dataTransfer);
//                Toast.makeText(MapsActivity.this, "Showing Restaurants", Toast.LENGTH_SHORT).show();

                Intent intent=new Intent(PlacesAround.this,RestaurantsActivity.class);
                intent.putExtra("City",cityName);
                startActivity(intent);
            }
        });

        getTouristPlaces=(Button) findViewById(R.id.getTouristPlaces);
        getTouristPlaces.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"onClick called");
                Object dataTransfer1[] = new Object[1];
                GetNearbyPlacesData getNearbyPlacesData1 = new GetNearbyPlacesData(PlacesAround.this);


//               String tourist_attraction = "tourist_attraction";
                String url = getUrlTourist(cityName);
                dataTransfer1[0] = url;
                getNearbyPlacesData1.execute(dataTransfer1);
                Toast.makeText(PlacesAround.this, "Showing Tourist attractions", Toast.LENGTH_SHORT).show();

            }
        });
        getHotels=(Button) findViewById(R.id.getHotels);
        getHotels.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(PlacesAround.this,HotelsActivity.class);
                intent.putExtra("City",cityName);
                startActivity(intent);
            }
        });






    }




//    private String getUrl(double latitude , double longitude , String nearbyPlace)
//    {
//
//        StringBuilder googlePlaceUrl = new StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?");
//        googlePlaceUrl.append("location="+latitude+","+longitude);
//        googlePlaceUrl.append("&radius="+3000);
//        googlePlaceUrl.append("&type="+nearbyPlace);
//        googlePlaceUrl.append("&sensor=true");
//        googlePlaceUrl.append("&key="+"API_KEY");
//
//        Log.d("MapsActivity", "url = "+googlePlaceUrl.toString());
//
//        return googlePlaceUrl.toString();
//    }

    private String getUrlTourist(String cityName)
    {
        StringBuilder googlePlaceUrl = new StringBuilder("https://maps.googleapis.com/maps/api/place/textsearch/json?");
        String[] words;

        words = cityName.split("\\s+");
        for (int i = 0; i < words.length; i++) {
            words[i] = words[i].replaceAll("[^\\w]", "");
        }

        googlePlaceUrl.append("query=");
        for(int i=0;i<words.length;i++)
        {
            googlePlaceUrl.append(words[i]+"+");
        }
        googlePlaceUrl.append("point+of+interest");
        googlePlaceUrl.append("&language=en");
        googlePlaceUrl.append("&key="+"API_KEY");

        Log.d("MapsActivity", "url = "+googlePlaceUrl.toString());

        return googlePlaceUrl.toString();
    }

//    private String getUrlHotel(String cityName)
//    {
//        StringBuilder googlePlaceUrl = new StringBuilder("https://maps.googleapis.com/maps/api/place/textsearch/json?");
//        String[] words;
//
//        words = cityName.split("\\s+");
//        for (int i = 0; i < words.length; i++) {
//            words[i] = words[i].replaceAll("[^\\w]", "");
//        }
//
//        googlePlaceUrl.append("query=");
//        for(int i=0;i<words.length;i++)
//        {
//            googlePlaceUrl.append(words[i]+"+");
//        }
//        googlePlaceUrl.append("hotels");
//        googlePlaceUrl.append("&language=en");
//        googlePlaceUrl.append("&key="+"API_KEY");
//
//        Log.d("MapsActivity", "url = "+googlePlaceUrl.toString());
//
//        return googlePlaceUrl.toString();
//    }




    private void getLocationPermission() {
        Log.d(TAG, "getLocationPermissionCalled");

        /*
         * Request location permission, so that we can get the location of the
         * device. The result of the permission request is handled by a callback,
         * onRequestPermissionsResult.
         */
        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mLocationPermissionGranted = true;
            getDeviceLocation();
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{ACCESS_FINE_LOCATION},
                    PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        mLocationPermissionGranted = false;
        switch (requestCode) {
            case PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mLocationPermissionGranted = true;
                    getDeviceLocation();
                    Log.d(TAG,"Permission granted");
                }
            }
        }
    }

    private void getDeviceLocation() {
        /*
         * Get the best and most recent location of the device, which may be null in rare
         * cases when a location is not available.
         */
        try {
            if (mLocationPermissionGranted) {
                Task locationResult = mFusedLocationProviderClient.getLastLocation();
                locationResult.addOnCompleteListener(this, new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if (task.isSuccessful()) {
                            // Set the map's camera position to the current location of the device.
                            mLastKnownLocation = (Location) task.getResult();

                            Geocoder gcd = new Geocoder(PlacesAround.this, Locale.getDefault());
                            List<Address> addresses = null;
                            try {
                                if (mLastKnownLocation!=null)
                                {
                                    addresses = gcd.getFromLocation(mLastKnownLocation.getLatitude(), mLastKnownLocation.getLongitude(), 1);
                                }
                                else{
                                    Toast.makeText(PlacesAround.this,"Can't access your location.Try again",Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(PlacesAround.this,Home_normal.class);
                                    startActivity(intent);
                                }

                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            if (addresses.size() > 0) {
                                cityName=addresses.get(0).getLocality();
                                Toast.makeText(PlacesAround.this,"City set: "+cityName,Toast.LENGTH_LONG).show();

                            }
                            else {
                                // do your stuff
                            }

                            Log.d(TAG,"Fetched location successfully");

                            //moveCamera(new LatLng(mLastKnownLocation.getLatitude(),mLastKnownLocation.getLongitude()),DEFAULT_ZOOM,"My location");
                        } else {
                            Log.d(TAG, "Current location is null. Using defaults.");
                            Log.e(TAG, "Exception: %s", task.getException());
//                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mDefaultLocation, DEFAULT_ZOOM));
//                            mMap.getUiSettings().setMyLocationButtonEnabled(false);
                        }
                    }
                });
            }
        } catch(SecurityException e)  {
            Log.e("Exception: %s", e.getMessage());
        }
    }


}
