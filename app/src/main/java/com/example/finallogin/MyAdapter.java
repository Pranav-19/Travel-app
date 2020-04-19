package com.example.finallogin;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    Context context;
    List<Model> models;

    public MyAdapter(Context context, List<Model> models) {
        this.context=context;
        this.models=models;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View view=layoutInflater.inflate(R.layout.my_row,parent,false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.name.setText(models.get(position).getName());
        holder.rating.setText(models.get(position).getRating());
        ImageView temp=models.get(position).getImageView();
        temp.invalidate();
        BitmapDrawable drawable = (BitmapDrawable) temp.getDrawable();
        Bitmap bitmap = drawable.getBitmap();
        holder.imageView.setImageBitmap(bitmap);
        holder.mapLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String lat=models.get(position).getLat();
                String lng=models.get(position).getLng();
                StringBuilder uri=new StringBuilder("geo:");
                uri.append(lat+",");
                uri.append(lng+"?q=");
                uri.append(Uri.encode(models.get(position).getName()));
                Uri gmmIntentUri = Uri.parse(String.valueOf(uri));
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                context.startActivity(mapIntent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView name,rating,mapLink;
        CircleImageView imageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name=(TextView) itemView.findViewById(R.id.name);
            rating=(TextView) itemView.findViewById(R.id.rating);
            imageView=(CircleImageView) itemView.findViewById(R.id.image);
            mapLink=(TextView) itemView.findViewById(R.id.mapLink);
        }


    }
}
