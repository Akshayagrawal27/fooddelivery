package com.akshay.fooddelivery.ui.home;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.akshay.fooddelivery.R;
import com.akshay.fooddelivery.model.RestaurantInfo;
import com.akshay.fooddelivery.util.HomeUtil;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.Query;

/**
 * Created by Akshay on 21-02-2018.
 */

public class HomeRecyclerAdapter extends FirebaseRecyclerAdapter<RestaurantInfo, HomeRecyclerAdapter.HomeRecyclerViewHolder> {

    private ShimmerFrameLayout shimmerFrameLayout;
    static private RestaurantListClickListener mOnClickListener;
    public static View view;

    /**
     * @param restaurantsInfo      Firebase will marshall the data at a location into
     *                        an instance of a class that you provide
     * @param modelLayout     This is the layout used to represent a single item in the list.
     *                        You will be responsible for populating an instance of the corresponding
     *                        view with the data from an instance of modelClass.
     * @param viewHolderClass The class that hold references to all sub-views in an instance modelLayout.
     * @param ref             The Firebase location to watch for data changes. Can also be a slice of a location,
*                        using some combination of {@code limit()}, {@code startAt()}, and {@code endAt()}.
     * @param mOnClickListener
     * @param view
     * @param mShimmerFrameLayout
     */
    public HomeRecyclerAdapter(Class<RestaurantInfo> restaurantsInfo, int modelLayout,
                               Class<HomeRecyclerViewHolder> viewHolderClass, Query ref, RestaurantListClickListener mOnClickListener, View view, ShimmerFrameLayout mShimmerFrameLayout) {
        super(restaurantsInfo, modelLayout, viewHolderClass, ref);
        this.mOnClickListener = mOnClickListener;
        this.view = view;
        this.shimmerFrameLayout = mShimmerFrameLayout;
    }

    @Override
    protected void populateViewHolder(HomeRecyclerViewHolder viewHolder, RestaurantInfo restaurantsInfo, int position) {

        /*viewHolder.tvRestaurantName.setText(restaurantsInfo.getRestaurantName());
        viewHolder.tvRestaurantCuisine.setText(restaurantsInfo.getRestaurantCuisine());
        viewHolder.tvRatings.setText(restaurantsInfo.getRatings());
        viewHolder.tvExpectedTime.setText(restaurantsInfo.getExpectedTime());
        viewHolder.tvExpectedPrice.setText(restaurantsInfo.getExpectedPrice());*/

        restaurantsInfo = HomeUtil.getRestaurantInfo(restaurantsInfo);

        viewHolder.tvRestaurantName.setText(restaurantsInfo.getRestaurantName());
        viewHolder.tvRestaurantCuisine.setText(restaurantsInfo.getAddress());

        /*if (super.getItemCount()-1 == position){
            shimmerFrameLayout.stopShimmerAnimation();
            shimmerFrameLayout.setVisibility(View.GONE);
            viewHolder.constraintLayout.setVisibility(View.VISIBLE);
        }*/
    }

    public interface RestaurantListClickListener {
        void onRestaurantListItemClick(int clickedItemIndex);
    }

    public static class HomeRecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView tvRestaurantName, tvRestaurantCuisine, tvRatings, tvExpectedTime, tvExpectedPrice;
        public ConstraintLayout constraintLayout, cardLayout;

        public HomeRecyclerViewHolder(View itemView) {
            super(itemView);

            tvRestaurantName = (TextView) itemView.findViewById(R.id.tv_restaurant_name);
            tvRestaurantCuisine = (TextView) itemView.findViewById(R.id.tv_restaurant_cuisine);
            tvRatings = (TextView) itemView.findViewById(R.id.tv_restaurant_rating);
            tvExpectedTime = (TextView) itemView.findViewById(R.id.tv_restaurant_delivery_time);
            tvExpectedPrice = (TextView) itemView.findViewById(R.id.tv_restaurant_price);

            constraintLayout = (ConstraintLayout) view.findViewById(R.id.container_address);
            cardLayout = (ConstraintLayout) itemView.findViewById(R.id.card_restaurant);

            cardLayout.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            mOnClickListener.onRestaurantListItemClick(getAdapterPosition());
        }
    }
}
