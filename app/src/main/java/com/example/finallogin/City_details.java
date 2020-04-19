package com.example.finallogin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class City_details extends AppCompatActivity  {
    List<City> lstCity;

    private RecyclerViewAdapter mRecyclerVIewAdapter;
    private ArrayList<City> mCitylist;
    int days_remain;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_details);

        Intent intent = getIntent();
        days_remain = intent.getIntExtra("Days_remaining",0);



        lstCity = new ArrayList<>();
        lstCity.add(new City("Macau","Macau, located on the southeast coast of China, is a special administrative region of the country.Being Asia's well-known gambling Mecca","Category",R.drawable.macau));
        lstCity.add(new City("Dubai","Dubai is a destination that mixes modern culture with history, adventure with world-class shopping and entertainment.","Category",R.drawable.dubai));
        lstCity.add(new City("Hong Kong","Hong Kong's a great city for an adventurous eater. Stop at a street vendor for fish balls on a stick or stinky tofu.","Category",R.drawable.hong_kong));
        lstCity.add(new City("Mauritius","Mauritius is arguably Africa’s wealthiest destination, a tropical paradise with tons to do. Port Louis, the modern capital of this 38-mile by 29-mile island, is a bustling port","Category",R.drawable.mauritius));
        lstCity.add(new City("Bali","Bali is a living postcard, an Indonesian paradise that feels like a fantasy. Soak up the sun on a stretch of fine white sand, or commune with the tropical creatures as you dive along coral ridges ","Category",R.drawable.bali01));
        lstCity.add(new City("Paris","Everyone who visits Paris for the first time probably has the same punchlist of major attractions to hit: The Louvre, Notre Dame, The Eiffel Tower, etc. ","Category",R.drawable.paris));
        lstCity.add(new City("Mumbai","Mumbai (formerly known as Bombay), India, is famous for its chaotic streets. For bargains and people-watching, outdoor bazaars top the list of attractions.","Category",R.drawable.mumbai));
        lstCity.add(new City("Rome","It’s nicknamed the Eternal City for a reason. In Rome, you can drink from a street fountain fed by an ancient aqueduct. Or see the same profile on a statue in the Capitoline Museum and the guy making your cappuccino. ","Category",R.drawable.rome));

        RecyclerView myrv = (RecyclerView) findViewById(R.id.recyclerview_id);

        RecyclerViewAdapter myAdapter = new RecyclerViewAdapter(this,lstCity,days_remain);

        myrv.setLayoutManager(new GridLayoutManager(this,2));
        myrv.setAdapter(myAdapter);





    }


}
