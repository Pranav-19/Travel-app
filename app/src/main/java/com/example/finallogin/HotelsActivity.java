package com.example.finallogin;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class HotelsActivity extends AppCompatActivity {
    private Button get3star;
    private Button get4star;
    private Button get5star;
//    public MyDBHandler dbHandler;
    private String cityName;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotels);
        get3star=(Button) findViewById(R.id.get3star);
        get4star=(Button) findViewById(R.id.get4star);
        get5star=(Button) findViewById(R.id.get5star);
        textView=(TextView) findViewById(R.id.textView);

//        if(savedInstanceState==null)
//        {
//            dbHandler=new MyDBHandler(HotelsActivity.this,null,null,1);
//        }
        cityName=getIntent().getExtras().getString("City");

        get3star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("3 STAR HOTELS");
                Object dataTransfer1[] = new Object[1];
                GetNearbyPlacesData getNearbyPlacesData1 = new GetNearbyPlacesData(HotelsActivity.this);


//               String tourist_attraction = "tourist_attraction";
                String url = getUrlHotel(cityName,3);
                dataTransfer1[0] = url;
                getNearbyPlacesData1.execute(dataTransfer1);
                Toast.makeText(HotelsActivity.this, "Showing Hotels", Toast.LENGTH_SHORT).show();
            }
        });
        get4star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("4 STAR HOTELS");
                Object dataTransfer1[] = new Object[1];
                GetNearbyPlacesData getNearbyPlacesData1 = new GetNearbyPlacesData(HotelsActivity.this);

                String url = getUrlHotel(cityName,4);
                dataTransfer1[0] = url;
                getNearbyPlacesData1.execute(dataTransfer1);
                Toast.makeText(HotelsActivity.this, "Showing Hotels", Toast.LENGTH_SHORT).show();

            }
        });

        get5star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("5 STAR HOTELS");
                Object dataTransfer1[] = new Object[1];
                GetNearbyPlacesData getNearbyPlacesData1 = new GetNearbyPlacesData(HotelsActivity.this);

                String url = getUrlHotel(cityName,5);
                dataTransfer1[0] = url;
                getNearbyPlacesData1.execute(dataTransfer1);
                Toast.makeText(HotelsActivity.this, "Showing Hotels", Toast.LENGTH_SHORT).show();

            }
        });


//        ListView listView=(ListView) findViewById(R.id.listView);
//        listView.setVisibility(View.INVISIBLE);
    }
    private String getUrlHotel(String cityName,int rating)
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
        googlePlaceUrl.append(String.valueOf(rating)+"star+");
        googlePlaceUrl.append("hotels");
        googlePlaceUrl.append("&language=en");
        googlePlaceUrl.append("&key="+"API_KEY");

        Log.d("MapsActivity", "url = "+googlePlaceUrl.toString());

        return googlePlaceUrl.toString();
    }


//    @Override
//    protected void onSaveInstanceState(@NonNull Bundle outState) {
//        outState.putString("message","This is my message");
//        super.onSaveInstanceState(outState);
//    }
}
