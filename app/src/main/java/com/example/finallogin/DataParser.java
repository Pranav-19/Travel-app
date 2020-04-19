package com.example.finallogin;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class DataParser {

    private HashMap<String, String> getPlace(JSONObject googlePlaceJson) throws JSONException
    {
        HashMap<String, String> googlePlaceMap = new HashMap<>();
        String placeName = "--NA--";
        String vicinity= "--NA--";
        String latitude= "";
        String longitude="";
        String rating="";
        String height="",width="",photo_reference="";
        Log.d("DataParser","jsonobject ="+googlePlaceJson.toString());


        try {
            if (!googlePlaceJson.isNull("name")) {
                placeName = googlePlaceJson.getString("name");
            }

            if (!googlePlaceJson.isNull("vicinity")) {
                vicinity = googlePlaceJson.getString("vicinity");
            }

            latitude = googlePlaceJson.getJSONObject("geometry").getJSONObject("location").getString("lat");
            longitude = googlePlaceJson.getJSONObject("geometry").getJSONObject("location").getString("lng");
            if(googlePlaceJson.has("rating"))
                rating = googlePlaceJson.getString("rating");

            try{
                JSONArray photos = googlePlaceJson.getJSONArray("photos");
                height=photos.getJSONObject(0).getString("height");
                width= photos.getJSONObject(0).getString("width");
                photo_reference=photos.getJSONObject(0).getString("photo_reference");
            }catch (JSONException e){
                e.printStackTrace();


                // here write your code to get value from Json object which is not getJSONArray.
            }




            googlePlaceMap.put("rating",rating);

            googlePlaceMap.put("height",height);
            googlePlaceMap.put("width",width);
            googlePlaceMap.put("photo_reference",photo_reference);

            googlePlaceMap.put("place_name", placeName);
            googlePlaceMap.put("vicinity", vicinity);
            googlePlaceMap.put("lat", latitude);
            googlePlaceMap.put("lng", longitude);


        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        return googlePlaceMap;

    }
    private List<HashMap<String, String>>getPlaces(JSONArray jsonArray)
    {
        int count = jsonArray.length();
        List<HashMap<String, String>> placelist = new ArrayList<>();
        HashMap<String, String> placeMap = null;

        for(int i = 0; i<count;i++)
        {
            try {
                placeMap = getPlace((JSONObject) jsonArray.get(i));
                //if(!placeMap.get("rating").equals(null) && !placeMap.get("name").equals(null))
                placelist.add(placeMap);
            } catch (JSONException e) {
                e.printStackTrace();
                continue;
            }

        }
        return placelist;
    }

    public List<HashMap<String, String>> parse(String jsonData)
    {
        JSONArray jsonArray = null;
        JSONObject jsonObject;

        Log.d("json data", jsonData);

        try {
            jsonObject = new JSONObject(jsonData);
            jsonArray = jsonObject.getJSONArray("results");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return getPlaces(jsonArray);
    }
}