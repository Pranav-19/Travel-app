package com.example.finallogin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class profile extends AppCompatActivity {
    Button signout;
    TextView Name,id;
    String uid,name;
    ImageView prof;
    GoogleSignInClient mGoogleSignInClient;
    FirebaseUser currentUser;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        signout=findViewById(R.id.prof_signout);
        Name =findViewById(R.id.prof_name);
        prof=findViewById(R.id.prof_img);
        id=findViewById(R.id.prof_mail);
        mAuth= FirebaseAuth.getInstance();
        currentUser=mAuth.getCurrentUser();

        GoogleSignInOptions gso =new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient= GoogleSignIn.getClient(this,gso);

        Name.setText(currentUser.getDisplayName());
        id.setText(currentUser.getEmail());


        Glide.with(this).load(currentUser.getPhotoUrl()).into(prof);
        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.prof_signout:
                        signOut();
                        break;
                }
            }
        });

    }
    private void signOut(){
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(profile.this,"Signed out successfully",Toast.LENGTH_LONG).show();
                        finish();
                    }
                });
    }
}
