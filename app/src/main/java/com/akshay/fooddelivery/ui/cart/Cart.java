package com.akshay.fooddelivery.ui.cart;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.akshay.fooddelivery.R;
import com.akshay.fooddelivery.model.CartDetails;
import com.akshay.fooddelivery.model.CartItems;
import com.akshay.fooddelivery.model.ConfirmOrder;
import com.akshay.fooddelivery.model.MenuItems;
import com.akshay.fooddelivery.util.CartUtil;
import com.akshay.fooddelivery.util.Constants;
import com.akshay.fooddelivery.util.FirebaseUtil;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Akshay on 21-02-2018.
 */

public class Cart extends Fragment {

    private static final String TAG = "MainActivity";
    private static View view;

    private DatabaseReference cartItemsReference;
    private ValueEventListener listener;

    private RecyclerView mRecyclerCartItem;
    private CartItemRecyclerAdapter mCartItemRecyclerAdapter;

    private TextView tvTotalItemValue, tvPackagingCharges, tvGSTCharges, tvDeliveryCharges, tvTotalAmount;
    private TextView tvRestaurantName, tvRestaurantAddress;
    private Button btConfirmOrder;

    double totalItemValue, packagingCharges, gstCharges, deliveryCharges, totalAmount = 0;

    private List<MenuItems> orderedItems = new ArrayList<>();

    private CartDetails cartDetails;
    private ConstraintLayout billingLayout;

    public Cart() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //Log.wtf(TAG, "CartFragment: onCreateView");
        view = inflater.inflate(R.layout.nav_cart, container, false);

        cartItemsReference = FirebaseUtil.getCartItemsReference();

        tvTotalItemValue = (TextView) view.findViewById(R.id.tv_cart_total_item_value);
        tvPackagingCharges = (TextView) view.findViewById(R.id.tv_cart_total_packaging_charges);
        tvGSTCharges = (TextView) view.findViewById(R.id.tv_cart_total_gst_charges);
        tvDeliveryCharges = (TextView) view.findViewById(R.id.tv_cart_total_delivery_charges);
        tvTotalAmount = (TextView) view.findViewById(R.id.tv_cart_total_to_pay);

        tvRestaurantName = (TextView) view.findViewById(R.id.tv_cart_restaurant_name);
        tvRestaurantAddress = (TextView) view.findViewById(R.id.tv_cart_restaurant_locality);

        btConfirmOrder = (Button) view.findViewById(R.id.bt_confirm_order);

        billingLayout = (ConstraintLayout) view.findViewById(R.id.layout_billing);

        mRecyclerCartItem = (RecyclerView) view.findViewById(R.id.recycler_cart_items_list);

        LinearLayoutManager layoutManager =
                new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

        mRecyclerCartItem.setLayoutManager(layoutManager);

        mCartItemRecyclerAdapter = new CartItemRecyclerAdapter(CartItems.class,
                R.layout.list_cart_items,
                CartItemRecyclerAdapter.CartItemRecyclerViewHolder.class,
                cartItemsReference);

        mRecyclerCartItem.setAdapter(mCartItemRecyclerAdapter);

        FirebaseUtil.getCartDetailsReference().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if (dataSnapshot.getValue()!=null){
                    cartDetails = dataSnapshot.getValue(CartDetails.class);
                    tvRestaurantName.setText(cartDetails.getRestaurantName());
                    tvRestaurantAddress.setText(cartDetails.getRestaurantAddress());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        listener = cartItemsReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                totalItemValue = 0;
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    CartItems cartItems = snapshot.getValue(CartItems.class);

                    totalItemValue += cartItems.getItemTotalPrice();

                    MenuItems menuItems = new MenuItems(cartItems.getItemId(), "Soups", cartItems.getItemName(), cartItems.getItemQuantity(), cartItems.getItemTotalPrice());
                    orderedItems.add(menuItems);
                }

                packagingCharges = CartUtil.getPackagingCharges();
                deliveryCharges = CartUtil.getDeliveryCharges();
                gstCharges = CartUtil.getGSTCharges(totalItemValue);
                totalAmount = totalItemValue + packagingCharges + deliveryCharges + gstCharges;

                setBillingView();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        btConfirmOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String orderId = FirebaseDatabase.getInstance().getReference().push().getKey();
                ConfirmOrder confirmOrder = new ConfirmOrder(orderId, orderedItems, totalAmount, Constants.ORDER_STATUS_ONGOING);
                CartUtil.confirmOrder(confirmOrder, cartDetails, getContext());
            }
        });
        return view;
    }

    private void setBillingView() {

        tvTotalItemValue.setText("\u20B9 " + String.valueOf(totalItemValue));
        tvPackagingCharges.setText("\u20B9 " + String.valueOf(packagingCharges));
        tvGSTCharges.setText("\u20B9 " + String.format( "%.2f", gstCharges) );
        tvDeliveryCharges.setText("\u20B9 " + String.valueOf(deliveryCharges));
        tvTotalAmount.setText("\u20B9 " + String.valueOf(totalAmount));

        billingLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (listener != null){
            cartItemsReference.removeEventListener(listener);
        }
    }
}
