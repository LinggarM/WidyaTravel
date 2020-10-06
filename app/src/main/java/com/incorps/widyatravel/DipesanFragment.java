package com.incorps.widyatravel;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DipesanFragment extends Fragment {
    DashboardActivity mainActivity;
    SessionLogin sessionLogin;
    private static String URL_DIPESAN_IT = "http://widyatravel.000webhostapp.com/api/api_dapat_individual_trip_dipesan.php";
    private static String URL_DIPESAN_CR = "http://widyatravel.000webhostapp.com/api/api_dapat_car_rent_dipesan.php";
    private RecyclerView rvDipesanIT, rvDipesanCR;
    ArrayList<IndividualTrip> listIT;
    ArrayList<CarRent> listCR;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //just change the fragment_dashboard
        //with the fragment you want to inflate
        //like if the class is HomeFragment it should have R.layout.home_fragment
        //if it is DashboardFragment it should have R.layout.fragment_dashboard
        View v = inflater.inflate(R.layout.fragment_dipesan, null);
        mainActivity = (DashboardActivity) getActivity();

        sessionLogin = new SessionLogin(mainActivity);
        HashMap<String, String> user = sessionLogin.getUserDetail();
        final String mId = user.get(sessionLogin.ID);

        rvDipesanIT = v.findViewById(R.id.rv_dipesan_it);
        rvDipesanCR = v.findViewById(R.id.rv_dipesan_cr);

        rvDipesanIT.setHasFixedSize(true);
        rvDipesanCR.setHasFixedSize(true);

        listIT = new ArrayList<>();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_DIPESAN_IT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            Log.e("response",response);
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            JSONArray jsonArray =  jsonObject.getJSONArray("order");

                            if (success.equals("1")) {

                                ArrayList<IndividualTrip> individualTripArrayList = new ArrayList<>();

                                for (int i = 0; i < jsonArray.length(); i++) {

                                    JSONObject object = jsonArray.getJSONObject(i);

                                    String kodeTransaksi = object.getString("kode_transaksi").trim();
                                    String namaDriver = object.getString("nama_driver").trim();
                                    String kotaPenjemputan = object.getString("kota_penjemputan").trim();
                                    String lokasiPenjemputan = object.getString("lokasi_penjemputan").trim();
                                    String kotaTujuan = object.getString("kota_tujuan").trim();
                                    String tanggalPenjemputan = object.getString("tanggal_penjemputan").trim();
                                    String waktuPenjemputan = object.getString("waktu_penjemputan").trim();
                                    String harga = object.getString("harga").trim();
                                    String jumlahOrang = object.getString("jumlah_orang").trim();

                                    IndividualTrip individualTrip = new IndividualTrip(kodeTransaksi, namaDriver, kotaPenjemputan, lokasiPenjemputan, kotaTujuan, tanggalPenjemputan, waktuPenjemputan, harga, jumlahOrang, "1");
                                    individualTripArrayList.add(individualTrip);

                                }

                                listIT.addAll(individualTripArrayList);
                                rvDipesanIT.setLayoutManager(new LinearLayoutManager(mainActivity));
                                rvDipesanIT.setNestedScrollingEnabled(false);
                                ListDipesanITAdapter listDipesanITAdapter = new ListDipesanITAdapter(listIT);
                                rvDipesanIT.setAdapter(listDipesanITAdapter);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id_customer", mId);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(mainActivity);
        requestQueue.add(stringRequest);

        listCR = new ArrayList<>();
        StringRequest stringRequestCR = new StringRequest(Request.Method.POST, URL_DIPESAN_CR,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            Log.e("response",response);
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            JSONArray jsonArray =  jsonObject.getJSONArray("order");

                            if (success.equals("1")) {

                                ArrayList<CarRent> carRentArrayList = new ArrayList<>();

                                for (int i = 0; i < jsonArray.length(); i++) {

                                    JSONObject object = jsonArray.getJSONObject(i);

                                    String kodeTransaksi = object.getString("kode_transaksi").trim();
                                    String namaDriver = object.getString("nama_driver").trim();
                                    String kotaPeminjaman = object.getString("kota_peminjaman").trim();
                                    String lokasiPeminjaman = object.getString("lokasi_peminjaman").trim();
                                    String tanggalPeminjaman = object.getString("tanggal_peminjaman").trim();
                                    String waktuPeminjaman = object.getString("waktu_peminjaman").trim();
                                    String lamaPeminjaman = object.getString("lama_peminjaman").trim();
                                    String harga = object.getString("harga").trim();

                                    CarRent carRent = new CarRent(kodeTransaksi, namaDriver, kotaPeminjaman, lokasiPeminjaman, tanggalPeminjaman, waktuPeminjaman, lamaPeminjaman, harga, "1", "0");
                                    carRentArrayList.add(carRent);

                                }

                                listCR.addAll(carRentArrayList);
                                rvDipesanCR.setLayoutManager(new LinearLayoutManager(mainActivity));
                                rvDipesanCR.setNestedScrollingEnabled(false);
                                ListDipesanCRAdapter listDipesanCRAdapter = new ListDipesanCRAdapter(listCR);
                                rvDipesanCR.setAdapter(listDipesanCRAdapter);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id_customer", mId);
                return params;
            }
        };

        RequestQueue requestQueueCR = Volley.newRequestQueue(mainActivity);
        requestQueueCR.add(stringRequestCR);

        return v;
    }
}