package com.example.finallogin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import android.animation.ArgbEvaluator;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.shrikanthravi.customnavigationdrawer2.data.MenuItem;
import com.shrikanthravi.customnavigationdrawer2.widget.SNavigationDrawer;


import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class home extends AppCompatActivity {
//Button signout;
//TextView Name,id;
//    String uid,name;

    //Grid System

    ViewPager viewPager;

    Adapter grid_adapter;

    List<Models_Home> models;
    Integer[] colors = null;
    ArgbEvaluator argbEvaluator = new ArgbEvaluator();

    //grid system

    GoogleSignInClient mGoogleSignInClient;



    // slider
    private List<Slide> lstSlide;
    private ViewPager slidepager;
    Uri Uri1,Uri2,Uri3,Uri4;



    //fragment
    SNavigationDrawer sNavigationDrawer;
    Class fragmentClass;
    public static Fragment fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);




        //slider
        slidepager=findViewById(R.id.slider_pager);

        Uri1 = Uri.parse("https://www.youtube.com/watch?v=AQ6GmpMu5L8");
        Uri2 = Uri.parse("https://www.youtube.com/watch?v=ka-ZgwCXKho");
        Uri3 = Uri.parse("https://www.youtube.com/watch?v=DEJx0CYrDHk");
        Uri4 = Uri.parse("https://www.youtube.com/watch?v=1PCLoWjjIkg");


        lstSlide= new ArrayList<>();
        lstSlide.add(new Slide(R.drawable.paris,"Paris",Uri1));
        lstSlide.add(new Slide(R.drawable.venice,"Venice",Uri2));
        lstSlide.add(new Slide(R.drawable.rome,"Rome",Uri3));
        lstSlide.add(new Slide(R.drawable.mumbai,"Mumbai",Uri4));

        SliderPagerAdapter adapter = new SliderPagerAdapter(this,lstSlide);
        slidepager.setAdapter(adapter);










        GoogleSignInOptions gso =new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient=GoogleSignIn.getClient(this,gso);
//Name=findViewById(R.id.textView);
//id=findViewById(R.id.textView2);
//signout=findViewById(R.id.sign_out);
//signout.setOnClickListener(new View.OnClickListener() {
//    @Override
//    public void onClick(View v) {
//        switch (v.getId()){
//            case R.id.sign_out:
//                signOut();
//                break;
//        }
//    }
//});



//

        //fragment
        sNavigationDrawer = findViewById(R.id.navigationDrawer);

        List<MenuItem> menuItems=new ArrayList<>();
        menuItems.add(new MenuItem("Profile",R.drawable.prof));
        menuItems.add(new MenuItem("Favorite",R.drawable.fav));
        menuItems.add(new MenuItem("Log out",R.drawable.log_out));


        sNavigationDrawer.setMenuItemList(menuItems);

        sNavigationDrawer.setOnMenuItemClickListener(new SNavigationDrawer.OnMenuItemClickListener() {
            @Override
            public void onMenuItemClicked(int position) {
                System.out.println("Position "+position);

                switch (position){
                    case 0:{
                        Startprofile();
//                        fragmentClass = profile.class;
                        break;
                    }
                    case 1:{
                        Startfavorite();
                        //javaClass=MainActivity.class;

                        break;
                    }
                    case 2:{
                                                  signOut();
                            break;
                    }
//                    case 3:{
//                        fragmentClass = MusicFragment.class;
//                        break;
//                    }

                }
            }
        });



        sNavigationDrawer.setDrawerListener(new SNavigationDrawer.DrawerListener() {
            @Override
            public void onDrawerOpening() {
//                try {
//                    fragment=(Fragment)fragmentClass.newInstance();
//                } catch (IllegalAccessException e) {
//                    e.printStackTrace();
//                } catch (InstantiationException e) {
//                    e.printStackTrace();
//                }
//
//                if(fragment!=null)
//                {
//                    FragmentManager fragmentManager=getSupportFragmentManager();
//                    fragmentManager.beginTransaction().setCustomAnimations(android.R.animator.fade_in,android.R.animator.fade_out).replace(R.id.frameLayout,fragment).commit();
//
//                }
            }

            @Override
            public void onDrawerClosing() {
                System.out.println("Drawer closed");

                try {
                    fragment = (Fragment) fragmentClass.newInstance();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (fragment != null) {
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out).replace(R.id.frameLayout, fragment).commit();

                }
            }

            @Override
            public void onDrawerOpened() {

            }

            @Override
            public void onDrawerClosed() {

            }

            @Override
            public void onDrawerStateChanged(int newState) {
                System.out.println("State "+newState);
            }
        });






        GoogleSignInAccount acc= GoogleSignIn.getLastSignedInAccount(this);
        if (acc != null) {
            // Name, email address, and profile photo Url
            //name = acc.getDisplayName();
            String email = acc.getEmail();
            Uri photoUrl = acc.getPhotoUrl();



            // The user's ID, unique to the Firebase project. Do NOT use this value to
            // authenticate with your backend server, if you have one. Use
            // FirebaseUser.getIdToken() instead.
           // uid = acc.getId();


        }


//        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//        if (user != null) {
//            // Name, email address, and profile photo Url
//             name = user.getDisplayName();
//            String email = user.getEmail();
//            Uri photoUrl = user.getPhotoUrl();
//
//            // Check if user's email is verified
//            boolean emailVerified = user.isEmailVerified();
//
//            // The user's ID, unique to the Firebase project. Do NOT use this value to
//            // authenticate with your backend server, if you have one. Use
//            // FirebaseUser.getIdToken() instead.
//             uid = user.getUid();
//
//
//        }
//       Name.setText(name);
//        id.setText(uid);


        //Grid system

       // models = new ArrayList<>();

        models = new ArrayList<>();
        models.add(new Models_Home(R.drawable.travel_planner_1,"Plan Travel"));
        models.add(new Models_Home(R.drawable.places_around_2,"Places Around"));
        models.add(new Models_Home(R.drawable.explore,"Explore"));

        grid_adapter=new Adapter(models,this);
        viewPager = findViewById(R.id.viewPager_menu);
        viewPager.setAdapter(grid_adapter);
        viewPager.setPadding(130,0,130,0);

        Integer[] colors_temp = {
                getResources().getColor(R.color.color1),
                getResources().getColor(R.color.color2),
                getResources().getColor(R.color.color3)
        };

        colors= colors_temp;

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position <(grid_adapter.getCount()-1) && position<(colors.length-1))
                {

                    viewPager.setBackgroundColor((Integer)argbEvaluator.evaluate(
                            positionOffset,
                            colors[position],
                            colors[position+1]
                            )
                    );
                }else{
                    viewPager.setBackgroundColor(colors[colors.length - 1]);
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });




    }
    private void signOut(){
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(home.this,"Signed out successfully",Toast.LENGTH_LONG).show();
                        finish();
                    }
                });
    }

    public void Startprofile()
    {
        Intent intent= new Intent(this,profile.class);
        startActivity(intent);
    }

    public void Startfavorite()
    {
        Intent intent= new Intent(this,favorite.class);
        startActivity(intent);
    }
}
