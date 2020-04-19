package com.example.finallogin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Profile_normal extends AppCompatActivity {

    Button signout;
    TextView Name,id;
    String uid,name;
    String email;
    ImageView prof;
    Uri photoUrl;
    GoogleSignInClient mGoogleSignInClient;
    FirebaseUser currentUser;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_normal);

        signout=findViewById(R.id.prof_signout_normal);
        Name =findViewById(R.id.prof_name_normal);
        prof=findViewById(R.id.prof_img_normal);
        id=findViewById(R.id.prof_mail_normal);
        mAuth= FirebaseAuth.getInstance();
        currentUser=mAuth.getCurrentUser();



        Glide.with(this).load(currentUser.getPhotoUrl()).into(prof);
        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (v.getId()){
                    case R.id.prof_signout_normal:
                        signOut();
                        break;
                }

            }
        });

                       FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // Name, email address, and profile photo Url
             name = user.getDisplayName();
             email = user.getEmail();
             photoUrl = user.getPhotoUrl();

            // Check if user's email is verified
            boolean emailVerified = user.isEmailVerified();

            // The user's ID, unique to the Firebase project. Do NOT use this value to
            // authenticate with your backend server, if you have one. Use
            // FirebaseUser.getIdToken() instead.
             uid = user.getUid();


            Name.setText(name);
            id.setText(email);

            Glide.with(this).load(user.getPhotoUrl()).into(prof);
        }


    }
    private void signOut(){

        FirebaseAuth.getInstance()
              .signOut();

       Toast.makeText(Profile_normal.this, "Signed out successfully", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this,Login.class);
        startActivity(intent);


    }

}
