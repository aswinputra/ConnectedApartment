package com.sepgroup4.connectedapartment.Controllers;

import android.accounts.NetworkErrorException;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.sepgroup4.connectedapartment.Model.Booking;
import com.sepgroup4.connectedapartment.Model.FacilityList;
import com.sepgroup4.connectedapartment.Model.LoginSession;
import com.sepgroup4.connectedapartment.Model.RequestResponse;
import com.sepgroup4.connectedapartment.R;
import com.sepgroup4.connectedapartment.Rest.Handlers.RestResponseHandler;
import com.sepgroup4.connectedapartment.Rest.RestClientManager;
import com.sepgroup4.connectedapartment.Utilities;

import java.util.ArrayList;

/**
 * Created by kiman on 16/10/2016.
 */

public class BookingAdapter extends RecyclerView.Adapter<BookingAdapter.BookingViewHolder> {

    private ArrayList<Booking> mBookingArrayList;
    private FacilityList mFacilityList;
    private Context mContext;

    public BookingAdapter(ArrayList<Booking> mBookingArrayList, FacilityList facilityList, Context mContext) {
        this.mBookingArrayList = mBookingArrayList;
        this.mContext = mContext;
        this.mFacilityList = facilityList;
    }

    @Override
    public BookingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
//        View view = LayoutInflater.from(mContext).inflate(R.layout.adapter_facility_booking, parent, false);
        return new BookingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BookingViewHolder holder, int position) {
        Booking booking = mBookingArrayList.get(position);
        holder.mBookedTimeTv.setText(booking.getStartTime());
        holder.mFacilityNameTv.setText(mFacilityList.getFacilityName(booking.getId()));
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class BookingViewHolder extends RecyclerView.ViewHolder implements RestResponseHandler{

        private TextView mFacilityNameTv;
        private TextView mBookedTimeTv;
        private Button mCancelBtn;

        public BookingViewHolder(View itemView) {
            super(itemView);

//            mFacilityNameTv = (TextView) itemView.findViewById(R.id.adapter_facility_booking_name);
//            mBookedTimeTv = (TextView) itemView.findViewById(R.id.adapter_facility_booking_time);
//            mCancelBtn = (Button) itemView.findViewById(R.id.adapter_facility_cancel_button);
            mCancelBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int selectedPos = getAdapterPosition();
                    String facilityId = mBookingArrayList.get(selectedPos).getFacilityId().toString();
                    String bookingId = mBookingArrayList.get(selectedPos).getId().toString();
                    try {
                        if (LoginSession.bm) {

                            RestClientManager.getInstance(mContext).getPersonController().cancelBooking(facilityId, bookingId, BookingViewHolder.this);
                        }
                    } catch (NetworkErrorException e) {
                        Utilities.displayToast(mContext, e.getMessage());
                    }

                }
            });
        }


        @Override
        public void onResponseSuccess(RequestResponse requestResponse) {

        }

        @Override
        public void onResponseFailure(String errorMessage) {

        }
    }
}
