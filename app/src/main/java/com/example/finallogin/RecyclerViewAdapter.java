package com.example.finallogin;

import android.app.Activity;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {


    private Context mContext;
    private List<City> mData;
    Dialog myDialog;
    String nameeeeee;
    int Remaining;





    public RecyclerViewAdapter(Context mContext, List<City> mData, int Days_remaining) {
        this.mContext = mContext;
        this.mData = mData;
        this.Remaining = Days_remaining;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.cardview_items_city,parent,false);


        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        holder.country_name.setText(mData.get(position).getTitle());
        holder.img_city_thumbnail.setImageResource(mData.get(position).getThumbnail());
        myDialog = new Dialog(mContext);


        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override


            public void onClick(View v) {
                ShowPopup(position,Remaining);
            }
        });


        //set on click here
        // set the descr here;
        // do this using intent.putExtra("Description",mData.get(position).getDescription());
        //mContext.startActivity(intent)



        //hint-- recieve and set data by creating new Class which will be used in intent

    }
    public void ShowPopup( final int p, int D)
    {




        TextView textclose, city_name,city_desc,select;
        String title,description;
        Button addbtn, subbtn;
        TextView totaldays;
        int day_c =0;
        ImageView imageView;
        Button button_fav;

        TextView DREM;


        myDialog.setContentView(R.layout.custompopup);
        textclose = (TextView) myDialog.findViewById(R.id.text_close_popup);
        select = (TextView) myDialog.findViewById(R.id.select_popup);
        addbtn = (Button) myDialog.findViewById(R.id.add_days);
        subbtn = (Button) myDialog.findViewById(R.id.minus_days);
        DREM = (TextView) myDialog.findViewById(R.id.days_popup);
        totaldays = (TextView) myDialog.findViewById(R.id.days_display);

        city_name = (TextView) myDialog.findViewById(R.id.text_city_popup);
        city_desc = (TextView) myDialog.findViewById(R.id.text_desc_popup);
        imageView = (ImageView) myDialog.findViewById(R.id.img_popup);
        totaldays.setText(String.valueOf(day_c));
        title = mData.get(p).getTitle();
        city_name.setText(title);
        city_desc.setText(mData.get(p).getDescription());
        imageView.setImageResource(mData.get(p).getThumbnail());
        DREM.setText(String.valueOf(D));


        TextView explore_further;
        explore_further=(TextView) myDialog.findViewById(R.id.explore_popup);
        explore_further.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mContext,PlacesAround.class);
                intent.putExtra("cityName",title);
                mContext.startActivity(intent);
            }
        });


        textclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });


        addbtn.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View v) {


                int c = Integer.parseInt(totaldays.getText().toString());
                  int  srf = Integer.parseInt(DREM.getText().toString());
                c = c + 1;
                srf = srf-1;

                if (srf >= 0) {
                    totaldays.setText(String.valueOf(c));
                    DREM.setText(String.valueOf(srf));
                } else {
                    Toast.makeText(myDialog.getContext(), " 0 days remaining for the trip", Toast.LENGTH_LONG).show();
                }

            }
        });

        subbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int c= Integer.parseInt(totaldays.getText().toString());
                int  srf = Integer.parseInt(DREM.getText().toString());
                c = c-1;
                srf = srf +1;
                if(c<0)
                {
                    Toast.makeText(myDialog.getContext(),"Days cant be negative",Toast.LENGTH_LONG).show();
                }
                else {
                    totaldays.setText(String.valueOf(c));
                    DREM.setText(String.valueOf(srf));
                }
            }
        });



        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//
                int c = Integer.parseInt(totaldays.getText().toString());
                if (c == 0) {
                    Toast.makeText(myDialog.getContext(), "Please add the number of days", Toast.LENGTH_LONG).show();

                } else {


                    nameeeeee = mData.get(p).getTitle();
//
//
//
                    Intent intent = new Intent("custom-message");
////                //            intent.putExtra("quantity",Integer.parseInt(quantity.getText().toString()));
                    intent.putExtra("cityname", nameeeeee);
                    intent.putExtra("Days_selected",c);
                    LocalBroadcastManager.getInstance(mContext).sendBroadcast(intent);
//
//
//
//                Intent intent1 = new Intent(myDialog.getContext(),PlanTravel_2.class);
//////
//////
//                mContext.startActivity(intent);
//
//
//
//                //works above method
//                //text.setText(nameeeeee);
                    mContext.startActivity(new Intent(mContext, PlanTravel_2.class));
////                Intent intent = ((Activity)mContext).getIntent();
////                intent.putExtra("City",city_name.getText().toString());
////                ((Activity) mContext).setResult(((Activity )mContext).RESULT_OK,
////                        intent);
////                ((Activity) mContext).finish();

                }
            }
        });

        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public  class MyViewHolder extends RecyclerView.ViewHolder{

        TextView country_name;
        ImageView img_city_thumbnail;
        CardView cardView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            country_name =(TextView) itemView.findViewById(R.id.city_title_id);
            img_city_thumbnail = (ImageView) itemView.findViewById(R.id.city_img_id);
            cardView = (CardView) itemView.findViewById(R.id.cardview_city);





        }
    }
//    public void addText( String city_n)
//    {
//        TextView newtext;
//        LinearLayout linearLayout = myDialog.findViewById(R.id.layout_planner);
//        newtext = new TextView(mContext);
//        newtext.setText(city_n);
//        newtext.setWidth(160);
//
//        newtext.setHeight(80);
//
//
//        linearLayout.addView(newtext);
//
//    }

}
