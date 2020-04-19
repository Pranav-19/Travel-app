package com.example.finallogin;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RestaurantsActivity extends AppCompatActivity {
    private Button getIndian;
    private Button getContinental;
    private Button getChinese;
    private Button getAmerican;
    private String cityName;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurants);

        getIndian=(Button) findViewById(R.id.getIndian);
        getContinental=(Button) findViewById(R.id.getContinental);
        getChinese=(Button) findViewById(R.id.getChinese);
        getAmerican=(Button) findViewById(R.id.getAmerican);
        textView=(TextView) findViewById(R.id.textView);

        cityName=getIntent().getExtras().getString("City");

        getIndian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("Indian");
                Object dataTransfer1[] = new Object[1];
                GetNearbyPlacesData getNearbyPlacesData1 = new GetNearbyPlacesData(RestaurantsActivity.this);


//               String tourist_attraction = "tourist_attraction";
                String url = getUrlRestaurant(cityName,"Indian");
                dataTransfer1[0] = url;
                getNearbyPlacesData1.execute(dataTransfer1);
                Toast.makeText(RestaurantsActivity.this, "Showing Hotels", Toast.LENGTH_SHORT).show();
            }
        });
        getChinese.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("Chinese");
                Object dataTransfer1[] = new Object[1];
                GetNearbyPlacesData getNearbyPlacesData1 = new GetNearbyPlacesData(RestaurantsActivity.this);

                String url = getUrlRestaurant(cityName,"Chinese");
                dataTransfer1[0] = url;
                getNearbyPlacesData1.execute(dataTransfer1);
                Toast.makeText(RestaurantsActivity.this, "Showing Hotels", Toast.LENGTH_SHORT).show();

            }
        });

        getContinental.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("Continental");
                Object dataTransfer1[] = new Object[1];
                GetNearbyPlacesData getNearbyPlacesData1 = new GetNearbyPlacesData(RestaurantsActivity.this);

                String url = getUrlRestaurant(cityName,"Continental");
                dataTransfer1[0] = url;
                getNearbyPlacesData1.execute(dataTransfer1);
                Toast.makeText(RestaurantsActivity.this, "Showing Hotels", Toast.LENGTH_SHORT).show();

            }
        });

        getAmerican.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("American");
                Object dataTransfer1[] = new Object[1];
                GetNearbyPlacesData getNearbyPlacesData1 = new GetNearbyPlacesData(RestaurantsActivity.this);

                String url = getUrlRestaurant(cityName,"American");
                dataTransfer1[0] = url;
                getNearbyPlacesData1.execute(dataTransfer1);
                Toast.makeText(RestaurantsActivity.this, "Showing Hotels", Toast.LENGTH_SHORT).show();

            }
        });



    }
    private String getUrlRestaurant(String cityName,String cuisine)
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
        googlePlaceUrl.append(cuisine+"cuisine+");
        googlePlaceUrl.append("restaurants");
        googlePlaceUrl.append("&language=en");
        googlePlaceUrl.append("&key="+"API_KEY");

        Log.d("RestaurantsActivity", "url = "+googlePlaceUrl.toString());

        return googlePlaceUrl.toString();
    }
}
