package com.incorps.widyatravel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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

public class DetailCarRentDipesanActivity extends AppCompatActivity {
    private String URL_DAPAT_PLATNO_CAR_RENT = "http://widyatravel.000webhostapp.com/api/api_get_platno_car_rent.php";
    TextView tanggal_jemput, waktu_jemput, lokasi_jemput, kota, lama, harga, driver, plat_no;
    private String URL_CANCEL = "http://widyatravel.000webhostapp.com/api/api_cancel_carrent.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_car_rent_dipesan);

        tanggal_jemput = findViewById(R.id.tanggal_pinjam);
        waktu_jemput = findViewById(R.id.waktu_pinjam);
        lokasi_jemput = findViewById(R.id.lokasi_pinjam);
        kota = findViewById(R.id.kota_pinjam);
        lama = findViewById(R.id.lama_pinjam);
        harga = findViewById(R.id.harga);
        driver = findViewById(R.id.driver);
        plat_no = findViewById(R.id.plat_no);
        Button cancel = findViewById(R.id.cancel);

        if (getIntent().getStringExtra("statusTransaksi").equals("1")) {
            cancel.setVisibility(View.VISIBLE);
        } else {
            cancel.setVisibility(View.INVISIBLE);
        }
        Log.d("statusTrans : ", getIntent().getStringExtra("statusTransaksi"));

        tanggal_jemput.setText(getIntent().getStringExtra("tanggal"));
        waktu_jemput.setText(getIntent().getStringExtra("waktuPeminjaman"));
        lokasi_jemput.setText(getIntent().getStringExtra("lokasiPeminjaman"));
        kota.setText(getIntent().getStringExtra("kotaPeminjaman"));
        lama.setText(getIntent().getStringExtra("lamaPeminjaman") + " Hari");
        harga.setText("Rp. " + getIntent().getStringExtra("harga"));
        driver.setText(getIntent().getStringExtra("namaDriver"));

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_DAPAT_PLATNO_CAR_RENT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonObject =  new JSONObject(response);
                            String success = jsonObject.getString("success");

                            if (success.equals("1")) {
                                String platno = jsonObject.getString("plat_no");
                                plat_no.setText(platno);
                            } else {
                                plat_no.setVisibility(TextView.INVISIBLE);
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
                params.put("kode_transaksi", getIntent().getStringExtra("kodeTransaksi"));
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                StringRequest stringRequestCancel = new StringRequest(Request.Method.POST, URL_CANCEL,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try{
                                    JSONObject jsonObject =  new JSONObject(response);
                                    String success = jsonObject.getString("success");

                                    if (success.equals("1")) {
                                        Toast.makeText(DetailCarRentDipesanActivity.this, "Pesanan Dibatalkan!", Toast.LENGTH_LONG).show();
                                        finish();
                                    } else {
                                        Toast.makeText(DetailCarRentDipesanActivity.this, "Pesanan Tidak Bisa Dibatalkan!", Toast.LENGTH_LONG).show();
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
                        params.put("kode_transaksi", getIntent().getStringExtra("kodeTransaksi"));
                        return params;
                    }
                };

                RequestQueue requestQueueCancel = Volley.newRequestQueue(DetailCarRentDipesanActivity.this);
                requestQueueCancel.add(stringRequestCancel);
            }
        });

    }
}
