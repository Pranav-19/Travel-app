package com.example.finallogin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.load.resource.bitmap.CenterCrop;

import java.util.ArrayList;
import java.util.Optional;

public class PlanTravel_2 extends AppCompatActivity  {
Button add_button,itenary;
int days=0,day_rem=0;

String citynames[];
int citynames_count=0;
int Days_selected;
int[] array_day;
    int c_count=0; // keeps the count of how many different cities are selected
int city_id; //keeps track of the city selected and it sr.no is its id;
   int [] city_numbers; // keeps the track of how many times each city is selected;
String begin,dest;
int check1=0, check2=0;

TextView newtext,new_city,day,last_day,days_remain;
String city_retrieved;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_travel_2);
                               itenary = (Button)findViewById(R.id.itenary);
                               itenary.setVisibility(View.INVISIBLE);

////////retrieving data












        Intent mIntent = getIntent();
         days = mIntent.getIntExtra("Days",0);
            array_day = new  int[days];
        citynames = new String[days+2];
        city_numbers = new int[days+2];

//        try {
//            days = Integer.parseInt(val);
//        } catch(NumberFormatException nfe) {
//            System.out.println("Could not parse " + nfe);
//        }
            days_remain = (TextView) findViewById(R.id.days_remaining);








        //retrieving data
//        if (savedInstanceState == null) {
//            Bundle extras = getIntent().getExtras();
//            if(extras == null) {
//                city_retrieved= "new city";
//                new_city.setText(city_retrieved);
//            } else {
//                city_retrieved= extras.getString("city_n");
//               // addText(city_retrieved);
//                new_city.setText(city_retrieved);
//                //new_city.setText(city_retrieved);
//            }
//        } else {
//            city_retrieved= (String) savedInstanceState.getSerializable("city_n");
//            //new_city.setText(city_retrieved);
//            new_city.setText(city_retrieved);
//           // addText(city_retrieved);
//        }




        //

        // Register to receive messages.
        // We are registering an observer (mMessageReceiver) to receive Intents
        // with actions named "custom-message".
        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver,
                new IntentFilter("custom-message"));




        last_day = (TextView) findViewById(R.id.last_day);
        int d = days +1;
        last_day.setText("Day "+d);
        day_rem=days;
        days_remain.setText("Days remaining : "+day_rem);


        add_button=(Button) findViewById(R.id.add_btn_plan);

//spinner 1
        Spinner staticSpinner = (Spinner) findViewById(R.id.static_spinner);

        // Create an ArrayAdapter using the string array and a default spinner
        ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter
                .createFromResource(this, R.array.Airport_array,
                        android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        staticAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        staticSpinner.setAdapter(staticAdapter);

        String[] items = new String[] { "Chai Latte", "Green Tea", "Black Tea" };

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, items);


        staticSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                begin = parent.getItemAtPosition(position).toString();
                if(position>0)
                {
                    check1=1;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




        ///Spinner 2



        Spinner staticSpinner2 = (Spinner) findViewById(R.id.static_spinner2);

        // Create an ArrayAdapter using the string array and a default spinner
        ArrayAdapter<CharSequence> staticAdapter2 = ArrayAdapter
                .createFromResource(this, R.array.Airport_array2,
                        android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        staticAdapter2
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        staticSpinner2.setAdapter(staticAdapter2);

        String[] items1 = new String[] { "Chai Latte", "Green Tea", "Black Tea" };

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, items1);

staticSpinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        dest = parent.getItemAtPosition(position).toString();
        if(position>0)
        {
            check2=1;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
});




        //


        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                citynames_count++;
                c_count++;

                Intent intent = new Intent(PlanTravel_2.this,City_details.class);
                int i =day_rem;
                intent.putExtra("Days_remaining", i);


                startActivity(intent);



            }
        });










    }

//
    public  BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String city_n = intent.getStringExtra("cityname");
            Days_selected = intent.getIntExtra("Days_selected",Days_selected);
            addText(city_n,citynames_count,Days_selected,c_count,city_numbers);
            citynames_count = citynames_count +Days_selected -1;
            day_rem=day_rem-Days_selected;
             days_remain.setText("Days remaining : "+day_rem);
             citynames[c_count]= city_n;
             if(day_rem==0)
            {

                add_button.setVisibility(View.GONE);
                itenary.setVisibility(View.VISIBLE);
                itenary.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(check1==1 && check2==1) {
                            Intent intent = new Intent(PlanTravel_2.this, Itenary.class);

                            if (days != 0 && citynames != null && citynames_count != 0) {

                                intent.putExtra("CITYNAMES", citynames);
                                intent.putExtra("COUNT", days);
                                intent.putExtra("CITYCOUNT", citynames_count);
                                intent.putExtra("Number_Diff_Cities", c_count);
                                intent.putExtra("City_frequency", city_numbers);
                                intent.putExtra("Begin", begin);
                                intent.putExtra("Destination", dest);
                                startActivity(intent);
                            } else {
                                Toast.makeText(PlanTravel_2.this, "error", Toast.LENGTH_LONG).show();
                            }
                        }
                        else if(check1==1 && check2!=1)
                        {
                            Toast.makeText(PlanTravel_2.this,"Please select Ending City",Toast.LENGTH_LONG).show();
                        }
                        else if(check1!=1 && check2==1)
                        {
                            Toast.makeText(PlanTravel_2.this,"Please select Starting City",Toast.LENGTH_LONG).show();
                        }
                        else if(check1!=1 && check2!=1)
                        {
                            Toast.makeText(PlanTravel_2.this,"Please select Starting City & Ending City",Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }

        }
    };
    public void addText(String city_N,int c,int selected_d,int cccc,int[] CN)
    {
        CN [cccc]=selected_d;

        for(int i=0;i<selected_d;i++) {
            LinearLayout linearLayout = findViewById(R.id.layout_planner);
            newtext = new TextView(this);
            newtext.setText(city_N);
            newtext.setWidth(200);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(0, 10, 0, 0);
            newtext.setLayoutParams(params);
            newtext.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            newtext.setTextSize(22);
            newtext.setBackgroundResource(R.drawable.planner_2_city_bg);
            newtext.setHeight(53);



            linearLayout.addView(newtext);


//        newtext.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int idd = newtext.getId();
//                switch (idd)
//                {
//
//                }
//                Toast.makeText(PlanTravel_2.this,idd+" is selected",Toast.LENGTH_LONG).show();
//            }
//        });


            LinearLayout l = findViewById(R.id.layout_planner_day);
            TextView dayt = new TextView(this);


            dayt.setText("DAY " + c);

            dayt.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            dayt.setWidth(100);
            dayt.setBackgroundResource(R.drawable.day_edittext);
            dayt.setHeight(53);
            LinearLayout.LayoutParams paramsw = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            paramsw.setMargins(0, 10, 0, 0);
            newtext.setLayoutParams(paramsw);


            l.addView(dayt);
            c=c+1;

        }

    }




}
