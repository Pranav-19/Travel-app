package com.example.finallogin;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class PlanTravel extends AppCompatActivity {
SeekBar seekbar;
TextView seekbar_text;
int check1=0,check2=0;
EditText editText;
int value;
int P;

    private static final String TAG ="Planner01";
    private TextView mDisplayDate;
    private TextView nDisplayDate;
    public Button button1;
    TextView planner_date;

    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private DatePickerDialog.OnDateSetListener nDateSetListener;

    public String date;
    public String daten;

    public Date ac_date;
    public Date ac_daten;
    public long difference;
    public long differenceDates;
    Boolean seekbar_select;
    TextView textView;
    int startday, endday,startmon,startyr,endmon,endyr;


    @SuppressLint("SimpleDateFormat")
    public SimpleDateFormat dates = new SimpleDateFormat("dd/MM/yyyy");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_travel);
        seekbar = (SeekBar) findViewById(R.id.seekbar);
        seekbar_text = (TextView) findViewById(R.id.seekbar_text);


        //seekbar
        seekbar.getProgressDrawable().setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN);
        seekbar.getThumb().setColorFilter(Color.RED,PorterDuff.Mode.SRC_IN);

        //seekbar_text.setText(value);



        mDisplayDate = (TextView)   findViewById(R.id.stDate);
        nDisplayDate = (TextView)   findViewById(R.id.endDate);
        textView = (TextView) findViewById(R.id.dateDisplayer);

        planner_date =findViewById(R.id.planner_date_text1);
        button1 =findViewById(R.id.button02);


        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year =cal.get(Calendar.YEAR);
                int month =cal.get(Calendar.MONTH);
                int day =cal.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(
                        PlanTravel.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
                check1=1;

            }
        });




        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                Log.d(TAG, "onDateSet: mm/dd/yyyy  " + month + "/" + dayOfMonth + "/" + year);
                date = dayOfMonth + "/" + month + "/" + year;

                mDisplayDate.setText(date);

                startday = dayOfMonth;
                startmon = month;
                startyr = year;
                P=1;

                int total_days = startday + value;

                if(startmon==1)
                {
                    if(total_days<=31)
                    {
                        endday = total_days;
                        endmon= startmon;
                        endyr = startyr;
                    }
                    if(total_days>31 && total_days<=60)
                    {
                        endday = total_days -31;
                        endmon= startmon +1;
                        endyr = startyr;
                    }

                }

                if(startmon==7)
                {
                    if(total_days<=31)
                    {
                        endday = total_days;
                        endmon= startmon;
                        endyr = startyr;
                    }
                    if(total_days>31 && total_days<=62)
                    {
                        endday = total_days -31;
                        endmon= startmon +1;
                        endyr = startyr;
                    }
                }


                if( startmon==3 || startmon==5 ||  startmon==8 || startmon==10)
                {
                    if(total_days<=31)
                    {
                        endday = total_days;
                        endmon= startmon;
                        endyr = startyr;
                    }
                    if(total_days>31 && total_days<=61)
                    {
                        endday = total_days -31;
                        endmon= startmon +1;
                        endyr = startyr;
                    }

                }


                if(startmon == 2 )
                {
                    if(startyr%4==0)
                    {
                        if(total_days<=29)
                        {
                            endday = total_days;
                            endmon= startmon;
                            endyr = startyr;
                        }
                        if(total_days>29 && total_days<=60)
                        {

                            endday = total_days -29;
                            endmon= startmon +1;
                            endyr = startyr;
                        }
                    }
                    if(startyr%4!=0)
                    {
                        if(total_days<=28)
                        {
                            endday = total_days;
                            endmon= startmon;
                            endyr = startyr;
                        }
                        if(total_days>28 && total_days<=59)
                        {

                            endday = total_days -29;
                            endmon= startmon +1;
                            endyr = startyr;
                        }
                    }


                }
                if(startmon==4 || startmon==6 || startmon==9)
                {
                    if(total_days<=30)
                    {
                        endday = total_days;
                        endmon = startmon;
                        endyr = startyr;
                    }
                    if(total_days>30 && total_days<=61)
                    {
                        endday = total_days -30;
                        endmon= startmon +1;
                        endyr = startyr;
                    }
                }
                if(startmon ==11)
                {
                    if(total_days<=30)
                    {
                        endday = total_days;
                        endmon = startmon;
                        endyr = startyr;
                    }
                    if(total_days>30 && total_days<=61)
                    {
                        endday = total_days -30;
                        endmon= startmon +1;
                        endyr = startyr ;
                    }
                }
                if(startmon==12)
                {
                    if(total_days<=31)
                    {
                        endday = total_days;
                        endmon = startmon;
                        endyr = startyr;
                    }
                    if(total_days>31 && total_days<=61)
                    {
                        endday = total_days -31;
                        endmon= startmon +1 -12;
                        endyr = startyr +1;
                    }
                }
                daten =  endday +"/"+endmon+"/"+ endyr;
                nDisplayDate.setText(daten);



            }
        };








        seekbar.setMax(100);
        seekbar.setProgress(0);

        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {

                if(progress>0)
                {
                    seekbar_select=true;
                    check2=1;
                }
                seekbar_text.setText(String.valueOf(progress) +" Days");
                textView.setText("The Trip is of  " + progress + " days");
                if(P==1)
                {
                    changeDate(startday,startmon,startyr,progress);
                }

                value = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
//
////changing value of seekbar through edittext
//        editText.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                // Convert text to integer. Do you already use editText.setInputType(InputType.TYPE_CLASS_NUMBER), don't you?
//                Integer enteredProgress = Integer.valueOf(s.toString());
//                seekbar.setProgress(enteredProgress);
//
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {}
//        });




        ///date












        nDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year =cal.get(Calendar.YEAR);
                int month =cal.get(Calendar.MONTH);
                int day =cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        PlanTravel.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        nDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
                check2=1;

            }
        });

        nDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                month = month +1;


                Log.d(TAG, "onDateSet: mm/dd/yyyy  " + month + "/" + dayOfMonth + "/" + year);
                daten = dayOfMonth + "/" + month + "/" + year;
                setdat(date,daten);

                nDisplayDate.setText(daten);
            }

        };



        planner_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    ac_date = dates.parse(date);
                    ac_daten = dates.parse(daten);
                    difference = Math.abs(ac_daten.getTime() - ac_date.getTime());
                    differenceDates = difference / (24 * 60 * 60 * 1000);
                    value = (int) differenceDates;
                    seekbar_text.setText(String.valueOf(differenceDates) + " Days");
                    seekbar.setProgress(value);
                    String dayDifference = Long.toString(differenceDates);

                    textView.setText("The Trip is of  " + dayDifference + " days");


                } catch (Exception exception) {
//                    Toast.makeText(this, "Unable to find difference", Toast.LENGTH_SHORT).show();
                }


                }

        });


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (check1 == 1 && check2 == 1) {
                    Intent intent = new Intent(PlanTravel.this, PlanTravel_2.class);

                    intent.putExtra("Days", value);
                    startActivity(intent);
                }
                else if(check1!=1 && check2==1)
                {
                    Toast.makeText(PlanTravel.this, "Please select the starting date", Toast.LENGTH_LONG).show();
                }
                else if(check1==1 && check2!=1)
                {
                    Toast.makeText(PlanTravel.this, "Please select the ending date or use the SeekBar", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(PlanTravel.this, "Please select the dates", Toast.LENGTH_LONG).show();

                }
            }

        });

    }
    public void changeDate(int startday1,int startmon1,int startyr1,int value1)
    {
        int total_days = startday1 + value1;

        if(startmon1==1)
        {
            if(total_days<=31)
            {
                endday = total_days;
                endmon= startmon1;
                endyr = startyr1;
            }
            if(total_days>31 && total_days<=60)
            {
                endday = total_days -31;
                endmon= startmon1 +1;
                endyr = startyr1;
            }

        }

        if(startmon1==7)
        {
            if(total_days<=31)
            {
                endday = total_days;
                endmon= startmon1;
                endyr = startyr1;
            }
            if(total_days>31 && total_days<=62)
            {
                endday = total_days -31;
                endmon= startmon1 +1;
                endyr = startyr1;
            }
        }


        if( startmon1==3 || startmon1==5 ||  startmon1==8 || startmon1==10)
        {
            if(total_days<=31)
            {
                endday = total_days;
                endmon= startmon1;
                endyr = startyr1;
            }
            if(total_days>31 && total_days<=61)
            {
                endday = total_days -31;
                endmon= startmon1 +1;
                endyr = startyr1;
            }

        }


        if(startmon1 == 2 )
        {
            if(startyr1%4==0)
            {
                if(total_days<=29)
                {
                    endday = total_days;
                    endmon= startmon1;
                    endyr = startyr1;
                }
                if(total_days>29 && total_days<=60)
                {

                    endday = total_days -29;
                    endmon= startmon1 +1;
                    endyr = startyr1;
                }
            }
            if(startyr1%4!=0)
            {
                if(total_days<=28)
                {
                    endday = total_days;
                    endmon= startmon1;
                    endyr = startyr1;
                }
                if(total_days>28 && total_days<=59)
                {

                    endday = total_days -29;
                    endmon= startmon1 +1;
                    endyr = startyr1;
                }
            }


        }
        if(startmon1==4 || startmon1==6 || startmon1==9)
        {
            if(total_days<=30)
            {
                endday = total_days;
                endmon = startmon1;
                endyr = startyr1;
            }
            if(total_days>30 && total_days<=61)
            {
                endday = total_days -30;
                endmon= startmon1 +1;
                endyr = startyr1;
            }
        }
        if(startmon1 ==11)
        {
            if(total_days<=30)
            {
                endday = total_days;
                endmon = startmon1;
                endyr = startyr1;
            }
            if(total_days>30 && total_days<=61)
            {
                endday = total_days -30;
                endmon= startmon1 +1;
                endyr = startyr1 ;
            }
        }
        if(startmon==12)
        {
            if(total_days<=31)
            {
                endday = total_days;
                endmon = startmon1;
                endyr = startyr1;
            }
            if(total_days>31 && total_days<=61)
            {
                endday = total_days -31;
                endmon= startmon1 +1 -12;
                endyr = startyr1 +1;
            }
        }
      String   daten1 =  endday +"/"+endmon+"/"+ endyr;
        nDisplayDate.setText(daten1);

    }


    public void setdat (String ds , String de){

        try {
           Date ac_date1 = dates.parse(ds);
           Date ac_daten1 = dates.parse(de);
            difference = Math.abs(ac_daten1.getTime() - ac_date1.getTime());
            differenceDates = difference / (24 * 60 * 60 * 1000);
            value = (int)differenceDates;
            seekbar_text.setText(String.valueOf(differenceDates) + " Days");
            seekbar.setProgress(value);
            String dayDifference = Long.toString(differenceDates);

            textView.setText("The Trip is of  " + dayDifference + " days");


        } catch (Exception exception) {
//                    Toast.makeText(this, "Unable to find difference", Toast.LENGTH_SHORT).show();
        }

    }


}
