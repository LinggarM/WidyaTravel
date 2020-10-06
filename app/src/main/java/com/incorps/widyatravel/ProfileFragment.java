package com.incorps.widyatravel;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ProfileFragment extends Fragment {
    DashboardActivity mainActivity;
    LinearLayout editProfile, help, termsConditions, aboutUs;
    TextView nama, email;
    Button logout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //just change the fragment_dashboard
        //with the fragment you want to inflate
        //like if the class is HomeFragment it should have R.layout.home_fragment
        //if it is DashboardFragment it should have R.layout.fragment_dashboard
        View v = inflater.inflate(R.layout.fragment_profile, null);
        mainActivity = (DashboardActivity) getActivity();

        editProfile = v.findViewById(R.id.edit_profile);
        help = v.findViewById(R.id.bantuan);
        termsConditions = v.findViewById(R.id.syarat_dan_ketentuan);
        aboutUs = v.findViewById(R.id.tentang_kami);
        logout = v.findViewById(R.id.logout);

        nama = v.findViewById(R.id.nama);
        email = v.findViewById(R.id.email);
        nama.setText(mainActivity.mName);
        email.setText(mainActivity.mEmail);

        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentHelp = new Intent(getActivity(), EditProfileActivity.class);
                startActivity(intentHelp);
            }
        });

        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentHelp = new Intent(getActivity(), HelpActivity.class);
                startActivity(intentHelp);
            }
        });

        termsConditions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentTerms = new Intent(getActivity(), TermsAndConditionsActivity.class);
                startActivity(intentTerms);
            }
        });

        aboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentAbout = new Intent(getActivity(), AboutUsActivity.class);
                startActivity(intentAbout);
            }
        });

        logout.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                mainActivity.sessionLogin.logout();
            }
        });

        return v;
    }
}