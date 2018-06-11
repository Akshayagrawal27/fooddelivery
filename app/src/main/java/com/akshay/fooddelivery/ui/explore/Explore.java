package com.akshay.fooddelivery.ui.explore;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.akshay.fooddelivery.R;
import com.akshay.fooddelivery.model.RestaurantInfo;
import com.akshay.fooddelivery.model.RestaurantsInfo;
import com.akshay.fooddelivery.ui.MainActivity;
import com.akshay.fooddelivery.ui.home.HomeRecyclerAdapter;
import com.akshay.fooddelivery.util.Constants;
import com.akshay.fooddelivery.util.FirebaseUtil;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Akshay on 21-02-2018.
 */

public class Explore extends Fragment implements ExploreRecyclerAdapter.RestaurantSearchListClickListener{

    private static final String TAG = "MainActivity";
    private static View view;

    private DatabaseReference databaseReference;

    private String mInput;
    EditText etSearchBox;
    private RecyclerView mRecyclerSearchList;
    private ExploreRecyclerAdapter mExploreRecyclerAdater;
    private Explore explore = this;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //Log.wtf(TAG, "ExploreFragment: onCreateView");
        view = inflater.inflate(R.layout.nav_explore, container, false);

        databaseReference = FirebaseUtil.getRestaurantInfoReference();

        etSearchBox = (EditText) view.findViewById(R.id.et_search);
        mRecyclerSearchList = (RecyclerView) view.findViewById(R.id.recycler_search_list);

        LinearLayoutManager layoutManager =
                new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

        mRecyclerSearchList.setLayoutManager(layoutManager);

        etSearchBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                /* Get the input after every textChanged event and transform it to lowercase */
                mInput = etSearchBox.getText().toString().toLowerCase();

                /* Clean up the old adapter */
                if (mExploreRecyclerAdater != null) mExploreRecyclerAdater.cleanup();

                /* Nullify the adapter data if the input length is less than 2 characters */
                if (mInput.equals("") || mInput.length() < 2) {
                    mRecyclerSearchList.setAdapter(null);

                    /* Define and set the adapter otherwise. */
                } else {
                        mExploreRecyclerAdater = new ExploreRecyclerAdapter(RestaurantInfo.class,
                                R.layout.list_searched_restaurant,
                                ExploreRecyclerAdapter.ExploreRecyclerViewHolder.class,
                                databaseReference.orderByChild(Constants.FIREBASE_PROPERTY_RESTAURANT_NAME).startAt(mInput).endAt(mInput + "~").limitToFirst(5),
                                explore);

                        mRecyclerSearchList.setAdapter(mExploreRecyclerAdater);
                }
            }
        });
        return view;
    }

    @Override
    public void onRestaurantSearchListItemClick(int clickedItemIndex) {

    }
}
