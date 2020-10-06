package com.incorps.widyatravel;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.TextureView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class CarRentActivity extends AppCompatActivity {

    Calendar newCalendar;
    DatePickerDialog datePickerDialog;
    SimpleDateFormat dateFormatter;
    int dayRental, monthRental, yearRental, spinnerKotaID, spinnerWaktuID;
    Spinner spinnerKotaPinjam, setWaktu;
    TextInputEditText lokasiJemput, lamaPinjam;
    Button setTanggal, pesan;
    TextView textDate;
    private String URL_ORDER_CAR_RENT = "http://widyatravel.000webhostapp.com/api/api_car_rent.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_rent);

        final SessionLogin sessionLogin = new SessionLogin(this);
        final HashMap<String, String> user = sessionLogin.getUserDetail();

        spinnerKotaPinjam = findViewById(R.id.spinner_kota);
        spinnerKotaPinjam.getBackground().setColorFilter(getResources().getColor(R.color.colorBlueThree), PorterDuff.Mode.SRC_ATOP);
        setWaktu = findViewById(R.id.set_waktu);
        setWaktu.getBackground().setColorFilter(getResources().getColor(R.color.colorBlueThree), PorterDuff.Mode.SRC_ATOP);

        spinnerKotaPinjam.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinnerKotaID = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        setWaktu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinnerWaktuID = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        lokasiJemput = findViewById(R.id.lokasi_jemput);
        lamaPinjam = findViewById(R.id.lama_peminjaman);
        setTanggal = findViewById(R.id.set_tanggal);
        pesan = findViewById(R.id.pesan);
        textDate = findViewById(R.id.txt_date);

        dateFormatter = new SimpleDateFormat("EEE, dd MMM yyyy", Locale.US);

        setTanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog();
            }
        });

        pesan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id_customer = user.get(sessionLogin.ID);
                String lama_pinjam = lamaPinjam.getText().toString().trim();
                int harga = 500000 * Integer.parseInt(lama_pinjam);
                int kota_pinjam = spinnerKotaID + 1;
                String lokasi_pinjam = lokasiJemput.getText().toString().trim();
                String tanggal = textDate.getText().toString().trim();
                int waktu_berangkat = spinnerWaktuID + 1;
                if (Integer.parseInt(lama_pinjam) < 1) {
                    Toast.makeText(CarRentActivity.this, "Lama Peminjaman Minimal 1 Hari!!!", Toast.LENGTH_SHORT).show();
                } else {
                    submit(id_customer, harga, kota_pinjam, lokasi_pinjam, tanggal, waktu_berangkat, lama_pinjam);
                }
            }
        });

    }

    private void showDateDialog() {
        newCalendar = Calendar.getInstance();

        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                textDate.setText(dateFormatter.format(newDate.getTime()));
                yearRental = year;
                monthRental = monthOfYear;
                dayRental = dayOfMonth;
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);

        datePickerDialog.show();
    }

    private void submit(final String id_customer, final int harga, final int kota_pinjam, final String lokasi_pinjam, final String tanggal, final int waktu_berangkat, final String lama_pinjam) {

        final ProgressDialog progressDialog =  new ProgressDialog(this);
        progressDialog.setMessage("Ordering...");
        progressDialog.show();

        StringRequest stringRequest =  new StringRequest(Request.Method.POST, URL_ORDER_CAR_RENT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{

                            progressDialog.dismiss();

                            JSONObject jsonObject =  new JSONObject(response);
                            String success = jsonObject.getString("success");

                            if (success.equals("1")) {
                                Toast.makeText(CarRentActivity.this, "Order Berhasil!", Toast.LENGTH_SHORT).show();
                                finish();
                            } else {
                                Toast.makeText(CarRentActivity.this, "Order Gagal! ", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            progressDialog.dismiss();

                            e.printStackTrace();
                            Toast.makeText(CarRentActivity.this, "Order Error!" + e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(CarRentActivity.this, "Order Error!" + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id_customer", id_customer);
                params.put("harga", String.valueOf(harga));
                params.put("kota_pinjam", String.valueOf(kota_pinjam));
                params.put("lokasi_pinjam", lokasi_pinjam);
                params.put("tanggal", tanggal);
                params.put("waktu_berangkat", String.valueOf(waktu_berangkat));
                params.put("lama_pinjam", lama_pinjam);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
