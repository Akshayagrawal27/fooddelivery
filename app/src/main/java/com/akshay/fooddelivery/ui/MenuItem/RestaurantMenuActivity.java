package com.akshay.fooddelivery.ui.MenuItem;

import com.akshay.fooddelivery.model.CartItems;
import com.akshay.fooddelivery.model.MenuCategory;
import com.akshay.fooddelivery.model.RestaurantInfo;
import com.akshay.fooddelivery.ui.BaseActivity;
import com.akshay.fooddelivery.ui.MainActivity;
import com.akshay.fooddelivery.ui.cart.CartActivity;
import com.akshay.fooddelivery.util.Constants;
import com.akshay.fooddelivery.util.FirebaseUtil;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.akshay.fooddelivery.R;
import com.akshay.fooddelivery.model.MenuItems;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class RestaurantMenuActivity extends BaseActivity implements MenuCategoryRecyclerAdapter.MenuCategoryListClickListener {

    private DatabaseReference menuCategoryListReference;

    private RecyclerView mRecyclerMenuCategoryList;
    private MenuCategoryRecyclerAdapter mMenuCategoryRecyclerAdapter;

    public RestaurantInfo restaurantInfo;

    private TextView tvRestaurantName, tvRestaurantLocality;
    private TextView tvNumberOfItems, tvTotalAmount;
    private LinearLayout tvViewCartLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_menu);

        Intent intent = this.getIntent();
        restaurantInfo = (RestaurantInfo) intent.getParcelableExtra(Constants.KEY_RESTAURANT_INFO);

        menuCategoryListReference = FirebaseUtil.getMenuCategoryListReference();

        mRecyclerMenuCategoryList = (RecyclerView) findViewById(R.id.recycler_menu_category);
        tvRestaurantName = (TextView) findViewById(R.id.tv_item_restaurant_name);
        tvRestaurantLocality = (TextView) findViewById(R.id.tv_item_restaurant_locality);

        tvNumberOfItems = (TextView) findViewById(R.id.tv_menu_number_of_items);
        tvTotalAmount = (TextView) findViewById(R.id.tv_menu_total_price);
        tvViewCartLayout = (LinearLayout) findViewById(R.id.layout_view_cart);

        tvRestaurantName.setText(restaurantInfo.getRestaurantName());
        tvRestaurantLocality.setText(restaurantInfo.getAddress());

        LinearLayoutManager layoutManager =
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        mRecyclerMenuCategoryList.setLayoutManager(layoutManager);

        mMenuCategoryRecyclerAdapter = new MenuCategoryRecyclerAdapter(MenuCategory.class,
                R.layout.list_menu_category,
                MenuCategoryRecyclerAdapter.MenuCategoryRecyclerViewHolder.class,
                menuCategoryListReference, this, this, restaurantInfo);

        mRecyclerMenuCategoryList.setAdapter(mMenuCategoryRecyclerAdapter);

        FirebaseUtil.getCartItemsReference().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                double totalAmount = 0;
                long numberOfItems = 0;

                if (dataSnapshot.getValue() != null){
                    numberOfItems = dataSnapshot.getChildrenCount();

                    for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                        CartItems cartItems = snapshot.getValue(CartItems.class);
                        totalAmount += cartItems.getItemTotalPrice();
                    }
                    tvNumberOfItems.setText(String.valueOf(numberOfItems + " Items"));
                    tvTotalAmount.setText(String.valueOf(totalAmount));
                    tvViewCartLayout.setVisibility(View.VISIBLE);
                }else{
                    tvViewCartLayout.setVisibility(View.GONE);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        tvViewCartLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), CartActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onMenuCategoryListItemClick(int clickedItemIndex) {

    }
}
