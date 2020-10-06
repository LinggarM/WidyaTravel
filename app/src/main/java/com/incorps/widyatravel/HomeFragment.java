package com.incorps.widyatravel;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class HomeFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //just change the fragment_dashboard
        //with the fragment you want to inflate
        //like if the class is HomeFragment it should have R.layout.home_fragment
        //if it is DashboardFragment it should have R.layout.fragment_dashboard
        View v = inflater.inflate(R.layout.fragment_home, null);
        DashboardActivity mainActivity = (DashboardActivity) getActivity();

        LinearLayout carRent = v.findViewById(R.id.car_rent);
        carRent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCarRent = new Intent(getActivity(), CarRentActivity.class);
                startActivity(intentCarRent);
            }
        });

        LinearLayout individualTrip = v.findViewById(R.id.individual_trip);
        individualTrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentIndividualTrip = new Intent(getActivity(), IndividualTripActivity.class);
                startActivity(intentIndividualTrip);
            }
        });

        return v;
    }
}