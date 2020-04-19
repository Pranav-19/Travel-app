package com.example.finallogin;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.common.api.ApiException;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.PhotoMetadata;
import com.google.android.libraries.places.api.net.FetchPhotoRequest;
import com.google.android.libraries.places.api.net.PlacesClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


class GetNearbyPlacesData extends AsyncTask<Object, String, String>{

    private String googlePlacesData;
    String url;
    Context context;
    Activity activity;
    PlacesClient placesClient;
    RecyclerView recyclerView;

    public GetNearbyPlacesData(Context context)
    {
        this.context=context;
        activity=(Activity) context;

    }

    @Override
    protected String doInBackground(Object... objects){

        url = (String)objects[0];

        DownloadURL downloadURL = new DownloadURL();
        try {
            googlePlacesData = downloadURL.readUrl(url);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return googlePlacesData;
    }

    @Override
    protected void onPostExecute(String s){

        List<HashMap<String, String>> nearbyPlaceList;
        DataParser parser = new DataParser();
        nearbyPlaceList = parser.parse(s);
        Log.d("nearbyplacesdata","called parse method");
        placesClient = Places.createClient(context);
        showNearbyPlaces(nearbyPlaceList);
    }

    private void showNearbyPlaces(List<HashMap<String, String>> nearbyPlaceList)
    {
        List<Model> models;
        models=new ArrayList<>();
        String[] names=new String[nearbyPlaceList.size()];
        double[] ratings=new double[nearbyPlaceList.size()];
        boolean[] favorites=new boolean[nearbyPlaceList.size()];
        for(int i = 0; i < nearbyPlaceList.size(); i++)
        {

            HashMap<String, String> googlePlace = nearbyPlaceList.get(i);
            if(googlePlace!=null)
            {
                String placeName = googlePlace.get("place_name");
                String vicinity = googlePlace.get("vicinity");
                double rating;
                ImageView imageView=new ImageView(context);
                imageView.setImageResource(R.drawable.image_not_found);
                if(googlePlace.get("rating").equals(""))
                {
                    rating=0.0;
                }
                else
                {
                    rating=Double.parseDouble(googlePlace.get("rating"));
                }
                String lat=googlePlace.get("lat");
                String lng=googlePlace.get("lng");

                String height=googlePlace.get("height");
                String width=googlePlace.get("width");
                String photo_reference=googlePlace.get("photo_reference");

                names[i]=placeName;
                ratings[i]=rating;
//                if(isFavorite(names[i]))
//                    favorites[i]=true;
//                else
//                     favorites[i]=false;
                if(!photo_reference.equals(""))
                {
                    PhotoMetadata.Builder photoMetadata_builder= PhotoMetadata.builder(photo_reference);
                    photoMetadata_builder.setHeight(Integer.parseInt(height));
                    photoMetadata_builder.setWidth(Integer.parseInt(width));
                    PhotoMetadata photoMetadata=photoMetadata_builder.build();

                    FetchPhotoRequest photoRequest = FetchPhotoRequest.builder(photoMetadata)
                            .setMaxWidth(500) // Optional.
                            .setMaxHeight(300) // Optional.
                            .build();

                    placesClient.fetchPhoto(photoRequest).addOnSuccessListener((fetchPhotoResponse) -> {
                        Bitmap bitmap = fetchPhotoResponse.getBitmap();
                        imageView.setImageBitmap(bitmap);
                    }).addOnFailureListener((exception) -> {
                        if (exception instanceof ApiException) {
                            ApiException apiException = (ApiException) exception;
                            int statusCode = apiException.getStatusCode();
                            // Handle error with given status code.
                            Log.e("GetNearbyPlacesData", "Place not found: " + exception.getMessage());
                        }
                    });
                }


                models.add(new Model(imageView,placeName,String.valueOf(rating),lat,lng));



                Log.d("GetNearbyPlacesData","Name: "+placeName+" rating: "+rating+"isFavorite: "+favorites[i]);
            }



        }

        recyclerView=activity.findViewById(R.id.recyclerView);

        MyAdapter myAdapter=new MyAdapter(context,models);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
    }



}


