package com.example.finallogin;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

public class Adapter_normal extends PagerAdapter {
        private List<Models_Home_normal> models;
        private LayoutInflater layoutInflater;
        private Context context;

        public Adapter_normal(List<Models_Home_normal> models, Context context) {
            this.models = models;
            this.context = context;
        }




    @Override
        public int getCount() {
            return models.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view.equals(object);
        }
        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, final int position) {
            layoutInflater = LayoutInflater.from(context);
            View view= layoutInflater.inflate(R.layout.item,container,false);

            ImageView imageView;
            final TextView title;



            imageView = view.findViewById(R.id.image_menu_1);
            title = view.findViewById(R.id.title_menu_1);
            title.setText(models.get(position).getTitle());

            imageView.setImageResource(models.get(position).getImage());
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(models.get(position).getTitle()=="Explore")
                    {
                        startmenu1();
                    }
                    if(models.get(position).getTitle()=="Places Around")
                    {
                        startmenu2();
                    }
                    if(models.get(position).getTitle()=="Plan Travel")
                    {
                        startmenu3();
                    }


                }
            });




            container.addView(view,0);

            return view;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View)object);
        }

        public void startmenu1(){
            Intent intent =new Intent(context,Explore.class);
            context.startActivity(intent);
        }
        public void startmenu2(){
            Intent intent =new Intent(context,PlacesAround.class);
            intent.putExtra("cityName","");
            context.startActivity(intent);
        }

        public void startmenu3(){
            Intent intent =new Intent(context,PlanTravel.class);
            context.startActivity(intent);
        }

    }

