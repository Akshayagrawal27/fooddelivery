package com.akshay.fooddelivery.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;

import com.akshay.fooddelivery.R;
import com.akshay.fooddelivery.ui.account.Account;
import com.akshay.fooddelivery.ui.cart.Cart;
import com.akshay.fooddelivery.ui.explore.Explore;
import com.akshay.fooddelivery.ui.home.Home;

public class MainActivity extends BaseActivity {

    private static final String TAG = "MainActivity";

    private ActionBar toolbar;
    private Fragment fragment;
    private FragmentManager fragmentManager;
    private BottomNavigationView navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = getSupportActionBar();
        toolbar.hide();

        fragment = new Home();
        //Launch Fragment for the First Time
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.container, fragment).addToBackStack(null).commit();

        //mTextMessage = (TextView) findViewById(R.id.message);
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    fragment = new Home();
                    break;
                case R.id.navigation_explore:
                    fragment = new Explore();
                    break;
                case R.id.navigation_cart:
                    fragment = new Cart();
                    break;
                case R.id.navigation_account:
                    fragment = new Account();
                    break;
            }
            final FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.container, fragment).commit();
            return true;
        }
    };

    @Override
    public void onBackPressed() {
        if(fragment instanceof Home){
            finish();
        }else{
            navigation.setSelectedItemId(R.id.navigation_home);
        }
    }
}
