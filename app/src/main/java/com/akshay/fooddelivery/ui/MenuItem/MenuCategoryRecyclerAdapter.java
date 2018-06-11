package com.akshay.fooddelivery.ui.MenuItem;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.akshay.fooddelivery.R;
import com.akshay.fooddelivery.model.MenuCategory;
import com.akshay.fooddelivery.model.MenuItems;
import com.akshay.fooddelivery.model.RestaurantInfo;
import com.akshay.fooddelivery.util.Constants;
import com.akshay.fooddelivery.util.FirebaseUtil;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.Query;

/**
 * Created by Akshay on 13-05-2018.
 */

class MenuCategoryRecyclerAdapter extends FirebaseRecyclerAdapter<MenuCategory, MenuCategoryRecyclerAdapter.MenuCategoryRecyclerViewHolder>
        implements MenuItemRecyclerAdapter.MenuItemListClickListener{

    static private MenuCategoryListClickListener mOnClickListener;
    Context context;
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
    public MenuCategoryRecyclerAdapter(Class<MenuCategory> modelClass,
                                       int modelLayout,
                                       Class<MenuCategoryRecyclerViewHolder> viewHolderClass, Query ref,
                                       MenuCategoryListClickListener mOnClickListener, Context context, RestaurantInfo restaurantInfo) {
        super(modelClass, modelLayout, viewHolderClass, ref);
        this.mOnClickListener = mOnClickListener;
        this.context = context;
        this.restaurantInfo = restaurantInfo;
    }

    @Override
    protected void populateViewHolder(final MenuCategoryRecyclerViewHolder viewHolder, final MenuCategory menuCategory, int position) {

        viewHolder.mMenuCategory.setText(menuCategory.getCategoryName());

        viewHolder.mMenuCategoryContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (viewHolder.mRecyclerMenuItemList.getVisibility() == View.VISIBLE){
                    viewHolder.mRecyclerMenuItemList.setVisibility(View.GONE);
                    viewHolder.mArrowDown.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_keyboard_arrow_down_black_24dp));
                }else{
                    viewHolder.mRecyclerMenuItemList.setVisibility(View.VISIBLE);
                    viewHolder.mArrowDown.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_keyboard_arrow_up_black_24dp));
                    LinearLayoutManager layoutManager =
                            new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);

                    viewHolder.mRecyclerMenuItemList.setLayoutManager(layoutManager);

                    viewHolder.mMenuItemRecyclerAdapter = new MenuItemRecyclerAdapter(MenuItems.class,
                            R.layout.list_menu_items,
                            MenuItemRecyclerAdapter.MenuItemRecyclerViewHolder.class,
                            FirebaseUtil.getRestaurantMenuListReference().orderByChild(Constants.FIREBASE_PROPERTY_MENU_CATEGORY)
                            .equalTo(menuCategory.getCategoryName()), MenuCategoryRecyclerAdapter.this, "", restaurantInfo);

                    viewHolder.mRecyclerMenuItemList.setAdapter(viewHolder.mMenuItemRecyclerAdapter);
                }
            }
        });
    }

    @Override
    public void onMenuItemListItemClick(int clickedItemIndex) {

    }

    public interface MenuCategoryListClickListener {
        void onMenuCategoryListItemClick(int clickedItemIndex);
    }

    static public class MenuCategoryRecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView mMenuCategory;
        ImageView mArrowDown;
        RecyclerView mRecyclerMenuItemList;
        ConstraintLayout mMenuCategoryContainer;
        MenuItemRecyclerAdapter mMenuItemRecyclerAdapter;
        public MenuCategoryRecyclerViewHolder(View itemView) {
            super(itemView);

            mMenuCategory = (TextView) itemView.findViewById(R.id.tv_menu_category);
            mArrowDown = (ImageView) itemView.findViewById(R.id.iv_menu_category_arrow);
            mRecyclerMenuItemList = (RecyclerView) itemView.findViewById(R.id.recycler_menu_item);
            mMenuCategoryContainer = (ConstraintLayout) itemView.findViewById(R.id.menu_category_container);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            mOnClickListener.onMenuCategoryListItemClick(getAdapterPosition());
        }
    }
}