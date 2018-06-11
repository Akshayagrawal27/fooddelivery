package com.akshay.fooddelivery.ui.TrackOrder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.akshay.fooddelivery.R;
import com.akshay.fooddelivery.model.TrackOrder;
import com.akshay.fooddelivery.util.FirebaseUtil;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class TrackOrderActivity extends AppCompatActivity {

    TextView tvOrderPreparing;
    ValueEventListener listener;
    DatabaseReference trackOrderReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_order);

        getSupportActionBar().setTitle("Track Order");

        tvOrderPreparing = (TextView) findViewById(R.id.tv_order_preparing);
        trackOrderReference = FirebaseUtil.getTrackOrderReference();

        listener = trackOrderReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if (dataSnapshot !=null){
                    TrackOrder trackOrder = dataSnapshot.getValue(TrackOrder.class);
                    tvOrderPreparing.setText(trackOrder.getOrderStatus());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (listener != null){
            trackOrderReference.removeEventListener(listener);
        }
    }
}
