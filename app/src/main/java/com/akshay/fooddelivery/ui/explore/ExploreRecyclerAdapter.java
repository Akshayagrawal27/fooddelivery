package com.akshay.fooddelivery.ui.explore;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.akshay.fooddelivery.R;
import com.akshay.fooddelivery.model.RestaurantInfo;
import com.akshay.fooddelivery.model.RestaurantsInfo;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.Query;

/**
 * Created by Akshay on 22-02-2018.
 */

class ExploreRecyclerAdapter extends FirebaseRecyclerAdapter<RestaurantInfo, ExploreRecyclerAdapter.ExploreRecyclerViewHolder> {

    static private RestaurantSearchListClickListener mOnClickListener;
    /**
     * @param modelClass      Firebase will marshall the data at a location into
     *                        an instance of a class that you provide
     * @param modelLayout     This is the layout used to represent a single item in the list.
     *                        You will be responsible for populating an instance of the corresponding
     *                        view with the data from an instance of modelClass.
     * @param viewHolderClass The class that hold references to all sub-views in an instance modelLayout.
     * @param ref             The Firebase location to watch for data changes. Can also be a slice of a location,
     *                        using some combination of {@code limit()}, {@code startAt()}, and {@code endAt()}.
     */
    public ExploreRecyclerAdapter(Class<RestaurantInfo> modelClass, int modelLayout,
                                  Class<ExploreRecyclerViewHolder> viewHolderClass, Query ref,
                                  RestaurantSearchListClickListener mOnClickListener) {
        super(modelClass, modelLayout, viewHolderClass, ref);
        this.mOnClickListener = mOnClickListener;
    }

    public interface RestaurantSearchListClickListener {
        void onRestaurantSearchListItemClick(int clickedItemIndex);
    }

    @Override
    protected void populateViewHolder(ExploreRecyclerViewHolder viewHolder, RestaurantInfo restaurantsInfo, int position) {

        viewHolder.tvSearchedRestaurantItem.setText(restaurantsInfo.getRestaurantName());
    }

    static public class ExploreRecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tvSearchedRestaurantItem;
        public ExploreRecyclerViewHolder(View itemView) {
            super(itemView);
            tvSearchedRestaurantItem = (TextView)itemView.findViewById(R.id.tv_searched_restaurant_item);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            mOnClickListener.onRestaurantSearchListItemClick(getAdapterPosition());
        }
    }
}
