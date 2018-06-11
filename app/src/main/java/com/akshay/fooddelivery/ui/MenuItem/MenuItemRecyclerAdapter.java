package com.akshay.fooddelivery.ui.MenuItem;

import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.akshay.fooddelivery.R;
import com.akshay.fooddelivery.model.MenuItems;
import com.akshay.fooddelivery.model.RestaurantInfo;
import com.akshay.fooddelivery.util.Constants;
import com.akshay.fooddelivery.util.FirebaseUtil;
import com.akshay.fooddelivery.util.MenuUtil;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Akshay on 13-05-2018.
 */


public class MenuItemRecyclerAdapter extends FirebaseRecyclerAdapter<MenuItems, MenuItemRecyclerAdapter.MenuItemRecyclerViewHolder> {

    static private MenuItemListClickListener mOnClickListener;
    String key;
    RestaurantInfo restaurantInfo;

    /**
     * @param modelClass      Firebase will marshall the data at a location into
     *                        an instance of a class that you provide
     * @param modelLayout     This is the layout used to represent a single item in the list.
     *                        You will be responsible for populating an instance of the corresponding
     *                        view with the data from an instance of modelClass.
     * @param viewHolderClass The class that hold references to all sub-views in an instance modelLayout.
     * @param ref             The Firebase location to watch for data changes. Can also be a slice of a location,
*                        using some combination of {@code limit()}, {@code startAt()}, and {@code endAt()}.
     * @param restaurantInfo
     */
    public MenuItemRecyclerAdapter(Class<MenuItems> modelClass,
                                   int modelLayout,
                                   Class<MenuItemRecyclerViewHolder> viewHolderClass, Query ref,
                                   MenuItemListClickListener mOnClickListener, String key, RestaurantInfo restaurantInfo) {
        super(modelClass, modelLayout, viewHolderClass, ref);
        this.mOnClickListener = mOnClickListener;
        this.key = key;
        this.restaurantInfo = restaurantInfo;
    }

    @Override
    protected void populateViewHolder(final MenuItemRecyclerViewHolder viewHolder, final MenuItems menuItems, int position) {

        /*if (!cartItemsList.isEmpty() && cartItemsList.contains(menuItems.getMenuId())){
            viewHolder.btAddItem.setEnabled(false);
        }*/

        if (key.equals(Constants.KEY_DIALOG_ORDERED_ITEMS)){
            viewHolder.mMenuItemName.setText(menuItems.getMenuName());
            viewHolder.mMenuItemPrice.setText("x" + String.valueOf(menuItems.getQuantity()));
        }else{
            viewHolder.mMenuItemName.setText(menuItems.getMenuName());
            viewHolder.mMenuItemPrice.setText(String.valueOf(menuItems.getPrice()));
        }

        viewHolder.btAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MenuUtil.addItemToCart(menuItems, restaurantInfo);
                viewHolder.btAddItem.setText("Added");
                viewHolder.btAddItem.setEnabled(false);
            }
        });
    }

    public interface MenuItemListClickListener {
        void onMenuItemListItemClick(int clickedItemIndex);
    }

    static public class MenuItemRecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView mMenuItemName, mMenuItemPrice;
        Button btAddItem;
        public MenuItemRecyclerViewHolder(View itemView) {
            super(itemView);

            mMenuItemName = (TextView) itemView.findViewById(R.id.tv_menu_item_name);
            mMenuItemPrice = (TextView) itemView.findViewById(R.id.tv_menu_item_price);
            btAddItem = (Button) itemView.findViewById(R.id.bt_add_item);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            mOnClickListener.onMenuItemListItemClick(getAdapterPosition());
        }
    }
}