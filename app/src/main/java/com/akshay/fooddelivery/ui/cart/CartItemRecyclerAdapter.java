package com.akshay.fooddelivery.ui.cart;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.akshay.fooddelivery.R;
import com.akshay.fooddelivery.model.CartItems;
import com.akshay.fooddelivery.util.CartUtil;
import com.akshay.fooddelivery.util.Constants;
import com.akshay.fooddelivery.util.FirebaseUtil;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.Query;

/**
 * Created by Akshay on 22-02-2018.
 */

class CartItemRecyclerAdapter extends FirebaseRecyclerAdapter<CartItems, CartItemRecyclerAdapter.CartItemRecyclerViewHolder> {

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
    public CartItemRecyclerAdapter(Class<CartItems> modelClass, int modelLayout,
                                   Class<CartItemRecyclerViewHolder> viewHolderClass, Query ref) {
        super(modelClass, modelLayout, viewHolderClass, ref);
    }

    @Override
    protected void populateViewHolder(CartItemRecyclerViewHolder viewHolder, final CartItems cartItems, int position) {

        viewHolder.tvItemName.setText(cartItems.getItemName());
        viewHolder.tvQuantity.setText(String.valueOf(cartItems.getItemQuantity()));
        viewHolder.tvTotalPrice.setText("\u20B9" + String.valueOf(cartItems.getItemTotalPrice()));

        viewHolder.tvPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CartUtil.incrementItemQuantity(cartItems);
            }
        });

        viewHolder.tvMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CartUtil.decrementItemQuantity(cartItems);
            }
        });
    }

    static public class CartItemRecyclerViewHolder extends RecyclerView.ViewHolder{

        TextView tvItemName, tvQuantity, tvTotalPrice, tvPlus, tvMinus;
        public CartItemRecyclerViewHolder(View itemView) {
            super(itemView);

            tvItemName = (TextView) itemView.findViewById(R.id.tv_item_name);
            tvQuantity = (TextView) itemView.findViewById(R.id.tv_item_quantity);
            tvTotalPrice = (TextView) itemView.findViewById(R.id.tv_cart_item_price);
            tvPlus = (TextView) itemView.findViewById(R.id.tv_add);
            tvMinus = (TextView) itemView.findViewById(R.id.tv_minus);
        }
    }
}
