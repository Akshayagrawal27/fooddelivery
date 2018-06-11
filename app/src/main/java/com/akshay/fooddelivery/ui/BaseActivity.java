package com.akshay.fooddelivery.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.akshay.fooddelivery.ui.login.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by Akshay on 20-02-2018.
 */

public class BaseActivity extends AppCompatActivity {

    private static final String TAG = "PhoneAuthActivity";
    public FirebaseAuth.AuthStateListener authListener;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.wtf(TAG, "BaseActivity: onCreate() method Called");

        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {
                    startActivity(new Intent(BaseActivity.this, LoginActivity.class));
                    finish();
                }
            }
        };

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.wtf(TAG, "BaseActivity: onStart() method Called");
        /*if (!(this instanceof LoginActivity)){
            FirebaseAuth.getInstance().addAuthStateListener(authListener);
        }*/
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.wtf(TAG, "BaseActivity: onStop() method Called");
        /*if (authListener != null) {
            FirebaseAuth.getInstance().removeAuthStateListener(authListener);
        }*/
    }
}
