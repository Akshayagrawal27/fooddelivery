package com.akshay.fooddelivery.ui.account;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.akshay.fooddelivery.R;
import com.akshay.fooddelivery.model.ConfirmOrder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.Query;

/**
 * Created by Akshay on 21-02-2018.
 */

public class OrderRecyclerAdapter extends FirebaseRecyclerAdapter<ConfirmOrder, OrderRecyclerAdapter.OrderRecyclerViewHolder> {

    static private OrderListClickListener mOnClickListener;

    /**
     * @param confirmOrder      Firebase will marshall the data at a location into
     *                        an instance of a class that you provide
     * @param modelLayout     This is the layout used to represent a single item in the list.
     *                        You will be responsible for populating an instance of the corresponding
     *                        view with the data from an instance of modelClass.
     * @param viewHolderClass The class that hold references to all sub-views in an instance modelLayout.
     * @param ref             The Firebase location to watch for data changes. Can also be a slice of a location,
*                        using some combination of {@code limit()}, {@code startAt()}, and {@code endAt()}.
     * @param mOnClickListener
     */
    public OrderRecyclerAdapter(Class<ConfirmOrder> confirmOrder, int modelLayout,
                                Class<OrderRecyclerViewHolder> viewHolderClass, Query ref, OrderListClickListener mOnClickListener) {
        super(confirmOrder, modelLayout, viewHolderClass, ref);
        this.mOnClickListener = mOnClickListener;
    }

    @Override
    protected void populateViewHolder(OrderRecyclerViewHolder viewHolder, ConfirmOrder confirmOrder, int position) {

        viewHolder.tvRestaurantName.setText(confirmOrder.getOrderId());
        viewHolder.tvRestaurantCuisine.setText(confirmOrder.getOrderStatus());
        viewHolder.tvExpectedPrice.setText(String.valueOf(confirmOrder.getOrderAmount()));
    }

    public interface OrderListClickListener {
        void onOrderListItemClick(int clickedItemIndex);
    }

    public static class OrderRecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView tvRestaurantName, tvRestaurantCuisine, tvRatings, tvExpectedTime, tvExpectedPrice;

        public OrderRecyclerViewHolder(View itemView) {
            super(itemView);
            tvRestaurantName = (TextView) itemView.findViewById(R.id.tv_restaurant_name);
            tvRestaurantCuisine = (TextView) itemView.findViewById(R.id.tv_restaurant_cuisine);
            tvRatings = (TextView) itemView.findViewById(R.id.tv_restaurant_rating);
            tvExpectedTime = (TextView) itemView.findViewById(R.id.tv_restaurant_delivery_time);
            tvExpectedPrice = (TextView) itemView.findViewById(R.id.tv_restaurant_price);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            mOnClickListener.onOrderListItemClick(getAdapterPosition());
        }
    }
}
