package com.example.finallogin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.core.content.res.ResourcesCompat;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Itenary extends AppCompatActivity {
    String city_names[];
    int count, city_count,cccc;
    int [] frequency;
    TextView itn,a,b,c,d;
    Animation frombottom;
    String begin, dest;
    private Bitmap bitmap;
    int days_count_display=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itenary);
        frombottom= AnimationUtils.loadAnimation(this,R.anim.frombottom);
            Intent mIntent = getIntent();

        city_names = mIntent.getStringArrayExtra("CITYNAMES");
        count = mIntent.getIntExtra("COUNT", 0);
        city_count = mIntent.getIntExtra("CITYCOUNT", 0);
        cccc = mIntent.getIntExtra("Number_Diff_Cities",0);
        frequency = mIntent.getIntArrayExtra("City_frequency");
        begin = mIntent.getStringExtra("Begin");
        dest = mIntent.getStringExtra("Destination");


//      //  a.setText(city_names[0]);
//        b.setText(city_names[1]);
//        c.setText(""+ count);
//        d.setText("" +city_count);

        if(city_names==null)
        {
            Toast.makeText(Itenary.this,"count is 0",Toast.LENGTH_LONG).show();
        }
        addAirport(begin);
        for (int i = 1; i <= cccc; i++) {

                    addText(city_names[i],days_count_display,frequency[i]);
                    days_count_display = days_count_display+ frequency[i];

        }
        //itn.setText("hello");
        adddestAir(count,dest);
    }

    public void addText(String city_N,int c,int freq) {

        for(int j=0;j<freq;j++) {
            LinearLayout linearLayout = findViewById(R.id.itenary_layout);


            Typeface typeface = ResourcesCompat.getFont(this, R.font.share_tech_mono);


            TextView newDay = new TextView(this);

            LinearLayout.LayoutParams paramsDAY = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            paramsDAY.setMargins(0, 10, 0, 0);
            newDay.setLayoutParams(paramsDAY);

            newDay.setText("DAY " + c);

            newDay.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            newDay.setTextSize(20);
            //newDay.setBackgroundResource(R.drawable.img_itenary);
            newDay.setTextColor(Color.WHITE);
            newDay.setTypeface(typeface);
            linearLayout.addView(newDay);
            c=c+1;


            TextView newtext = new TextView(this);
            newtext.setText(city_N);


            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(0, 10, 0, 0);
            newtext.setLayoutParams(params);
            newtext.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            newtext.setTextSize(40);
            newtext.setTextColor(Color.BLACK);

            newtext.setTypeface(typeface);
            linearLayout.addView(newtext);


            ImageView imageView = new ImageView(this);
            imageView.setImageResource(R.drawable.arrow);
            LinearLayout.LayoutParams params3 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params3.setMargins(0, 0, 0, 0);

            newtext.setLayoutParams(params3);
            linearLayout.addView(imageView);

        }
    }
    public void addAirport(String b)
    {
        LinearLayout linearLayout = findViewById(R.id.itenary_layout);



        Typeface typeface = ResourcesCompat.getFont(this, R.font.share_tech_mono);



        TextView newDay = new TextView(this);

        LinearLayout.LayoutParams paramsDAY = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        paramsDAY.setMargins(0, 50, 0, 0);
        newDay.setLayoutParams(paramsDAY);
        newDay.setText("DAY 0");
        newDay.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        newDay.setTextSize(20);
        //newDay.setBackgroundResource(R.drawable.img_itenary);
        newDay.setTextColor(Color.WHITE);
        newDay.setTypeface(typeface);
        linearLayout.addView(newDay);




        TextView newtext = new TextView(this);

        if(b!=null)
        {
            newtext.setText(b);
        }
        else {
           newtext.setText("Starting_Airport");
        }


        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 10, 0, 0);
        newtext.setLayoutParams(params);
        newtext.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        newtext.setTextSize(40);
        newtext.setTextColor(Color.BLACK);

        newtext.setTypeface(typeface);
        linearLayout.addView(newtext);








        ImageView imageView = new ImageView(this);
        imageView.setImageResource(R.drawable.arrow);
        LinearLayout.LayoutParams params3 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params3.setMargins(0, 30, 0, 0);

        imageView.setLayoutParams(params3);
        linearLayout.addView(imageView);



    }
    public void adddestAir(int co, String d)
    {
        LinearLayout linearLayout = findViewById(R.id.itenary_layout);



        Typeface typeface = ResourcesCompat.getFont(this, R.font.share_tech_mono);



        TextView newDay = new TextView(this);

        LinearLayout.LayoutParams paramsDAY = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        paramsDAY.setMargins(0, 10, 0, 0);
        newDay.setLayoutParams(paramsDAY);
        co=co+1;
        newDay.setText("DAY "+co);
        newDay.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        newDay.setTextSize(20);
        //newDay.setBackgroundResource(R.drawable.img_itenary);
        newDay.setTextColor(Color.WHITE);
        newDay.setTypeface(typeface);
        linearLayout.addView(newDay);




        TextView newtext = new TextView(this);
        if(d!=null)
        {
            newtext.setText(d);
        }
        else {
            newtext.setText("Ending_Airport");
        }


        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 10, 0, 0);
        newtext.setLayoutParams(params);
        newtext.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        newtext.setTextSize(40);
        newtext.setTextColor(Color.BLACK);

        newtext.setTypeface(typeface);
        linearLayout.addView(newtext);


        ImageView imageView = new ImageView(this);
        imageView.setImageResource(R.drawable.happy_journey);
        LinearLayout.LayoutParams params3 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params3.setMargins(0, 30, 0, 0);

        imageView.setLayoutParams(params3);
        linearLayout.addView(imageView);
        imageView.startAnimation(frombottom);



        Button btn = new Button(this);
        btn.setText("Pdf");
        btn.setHeight(30);
        btn.setWidth(50);
        LinearLayout.LayoutParams params4 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params4.setMargins(0, 10, 0, 0);
        btn.setLayoutParams(params4);
        linearLayout.addView(btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("size"," "+linearLayout.getWidth() +"  "+linearLayout.getWidth());
              //  linearLayout.setBackgroundColor(Color.parseColor("#4b79a1"));
                linearLayout.setBackgroundResource(R.drawable.itenary_bg);
                linearLayout.removeView(btn);
                bitmap = loadBitmapFromView(linearLayout, linearLayout.getWidth(), linearLayout.getHeight());
                createPdf();

            }
        });





    }



    public static Bitmap loadBitmapFromView(View v, int width, int height) {
        Bitmap b = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(b);
        v.draw(c);

        return b;
    }
    private void createPdf(){
        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        //  Display display = wm.getDefaultDisplay();
        DisplayMetrics displaymetrics = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        float hight = displaymetrics.heightPixels ;
        float width = displaymetrics.widthPixels ;

        int convertHighet = (int) hight, convertWidth = (int) width;

//        Resources mResources = getResources();
//        Bitmap bitmap = BitmapFactory.decodeResource(mResources, R.drawable.screenshot);

        PdfDocument document = new PdfDocument();
        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(convertWidth, convertHighet, 1).create();
        PdfDocument.Page page = document.startPage(pageInfo);

        Canvas canvas = page.getCanvas();

        Paint paint = new Paint();
        canvas.drawPaint(paint);

        bitmap = Bitmap.createScaledBitmap(bitmap, convertWidth, convertHighet, true);


        paint.setColor(Color.WHITE);
        canvas.drawBitmap(bitmap, 0, 0 , null);
        document.finishPage(page);

        // write the document content
        String targetPdf = "/sdcard/Itenary.pdf";
        File filePath;
        filePath = new File(targetPdf);
        try {
            document.writeTo(new FileOutputStream(filePath));

        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Something wrong: " + e.toString(), Toast.LENGTH_LONG).show();
        }

        // close the document
        document.close();
        Toast.makeText(this, "PDF with name -  Itenary  is created and stored in phone storage!!!", Toast.LENGTH_SHORT).show();

        openGeneratedPDF();

    }

    private void openGeneratedPDF(){
        File file = new File("/sdcard/Itenary.pdf");
        if (file.exists())
        {
            Intent intent=new Intent(Intent.ACTION_VIEW);
            Uri uri = FileProvider.getUriForFile(Itenary.this, BuildConfig.APPLICATION_ID + ".provider",file);

            intent.setDataAndType(uri, "application/pdf");
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

            intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            try
            {
                startActivity(intent);
            }
            catch(ActivityNotFoundException e)
            {
                Toast.makeText(this, "No Application available to view pdf", Toast.LENGTH_LONG).show();
            }
        }
    }
}
