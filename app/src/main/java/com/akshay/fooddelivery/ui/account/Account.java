package com.akshay.fooddelivery.ui.account;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.akshay.fooddelivery.R;
import com.akshay.fooddelivery.model.ConfirmOrder;
import com.akshay.fooddelivery.util.FirebaseUtil;
import com.google.firebase.database.DatabaseReference;

/**
 * Created by Akshay on 21-02-2018.
 */

public class Account extends Fragment implements OrderRecyclerAdapter.OrderListClickListener {

    private static final String TAG = "MainActivity";
    private static View view;

    private DatabaseReference orderListReference;

    private RecyclerView mRecyclerOrderList;
    private OrderRecyclerAdapter mOrderRecyclerAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //Log.wtf(TAG, "AccountFragment: onCreateView");
        view = inflater.inflate(R.layout.nav_account, container, false);

        orderListReference = FirebaseUtil.getUserOrderReference();

        mRecyclerOrderList = (RecyclerView) view.findViewById(R.id.recycler_user_order_list);

        LinearLayoutManager layoutManager =
                new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

        mRecyclerOrderList.setLayoutManager(layoutManager);

        mOrderRecyclerAdapter = new OrderRecyclerAdapter(ConfirmOrder.class,
                R.layout.list_restaurant,
                OrderRecyclerAdapter.OrderRecyclerViewHolder.class,
                orderListReference, this);

        mRecyclerOrderList.setAdapter(mOrderRecyclerAdapter);
        return view;
    }

    @Override
    public void onOrderListItemClick(int clickedItemIndex) {

    }
}
