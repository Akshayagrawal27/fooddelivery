package com.akshay.fooddelivery.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.akshay.fooddelivery.R;
import com.akshay.fooddelivery.model.RestaurantInfo;
import com.akshay.fooddelivery.ui.MenuItem.RestaurantMenuActivity;
import com.akshay.fooddelivery.util.Constants;
import com.akshay.fooddelivery.util.FirebaseUtil;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.firebase.database.DatabaseReference;

/**
 * Created by Akshay on 21-05-2018.
 */

public class Home extends Fragment implements HomeRecyclerAdapter.RestaurantListClickListener {

    private static View view;

    private DatabaseReference databaseReference;

    ShimmerFrameLayout mShimmerFrameLayout;

    private RecyclerView mRecyclerRestaurantList;
    private HomeRecyclerAdapter mHomeRecyclerAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.nav_home, container, false);
        //mShimmerFrameLayout = (ShimmerFrameLayout) view.findViewById(R.id.shimmer_container);
        databaseReference = FirebaseUtil.getRestaurantInfoReference();

        mRecyclerRestaurantList = (RecyclerView) view.findViewById(R.id.recycler_restaurants);
        LinearLayoutManager layoutManager =
                new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

        mRecyclerRestaurantList.setLayoutManager(layoutManager);

        mHomeRecyclerAdapter = new HomeRecyclerAdapter(RestaurantInfo.class,
                R.layout.list_restaurant,
                HomeRecyclerAdapter.HomeRecyclerViewHolder.class,
                databaseReference, this, view, mShimmerFrameLayout);

        mRecyclerRestaurantList.setAdapter(mHomeRecyclerAdapter);

        mRecyclerRestaurantList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });

        return view;
    }

    @Override
    public void onRestaurantListItemClick(int clickedItemIndex) {

        RestaurantInfo restaurantInfo = mHomeRecyclerAdapter.getItem(clickedItemIndex);
        Intent intent = new Intent(getContext(), RestaurantMenuActivity.class);
        intent.putExtra(Constants.KEY_RESTAURANT_INFO, restaurantInfo);
        startActivity(intent);
    }

    @Override
    public void onResume() {
        super.onResume();
        //mShimmerFrameLayout.startShimmerAnimation();
    }
}
