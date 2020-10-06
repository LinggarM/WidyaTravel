package com.incorps.widyatravel;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.graphics.PorterDuff;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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

public class IndividualTripActivity extends AppCompatActivity {

    Calendar newCalendar;
    DatePickerDialog datePickerDialog;
    SimpleDateFormat dateFormatter;
    String kotaAwal, kotaAkhir;
    int dayRental, monthRental, yearRental, spinnerRuteID, spinnerKotaAwalID, spinnerKotaAkhirID, spinnerWaktuID;
    Spinner spinnerRute, spinnerKotaJemput, spinnerKotaTujuan, setWaktu;
    TextInputEditText lokasiJemput, jumlahOrang;
    Button setTanggal, pesan;
    TextView textDate;
    private String URL_ORDER_CAR_RENT = "http://widyatravel.000webhostapp.com/api/api_individual_trip.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual_trip);

        final SessionLogin sessionLogin = new SessionLogin(this);
        final HashMap<String, String> user = sessionLogin.getUserDetail();

        spinnerRute = findViewById(R.id.spinner_rute);
        spinnerRute.getBackground().setColorFilter(getResources().getColor(R.color.colorBlueThree), PorterDuff.Mode.SRC_ATOP);
        spinnerKotaJemput = findViewById(R.id.spinner_kota_jemput);
        spinnerKotaJemput.getBackground().setColorFilter(getResources().getColor(R.color.colorBlueThree), PorterDuff.Mode.SRC_ATOP);
        spinnerKotaTujuan = findViewById(R.id.spinner_kota_tujuan);
        spinnerKotaTujuan.getBackground().setColorFilter(getResources().getColor(R.color.colorBlueThree), PorterDuff.Mode.SRC_ATOP);
        setWaktu = findViewById(R.id.set_waktu);
        setWaktu.getBackground().setColorFilter(getResources().getColor(R.color.colorBlueThree), PorterDuff.Mode.SRC_ATOP);

        spinnerRute.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinnerRuteID = position;

                if (spinnerRuteID == 0) {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(IndividualTripActivity.this, R.array.rute1, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerKotaJemput.setAdapter(adapter);
                    spinnerKotaTujuan.setAdapter(adapter);
                } else if (spinnerRuteID == 1) {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(IndividualTripActivity.this, R.array.rute2, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerKotaJemput.setAdapter(adapter);
                    spinnerKotaTujuan.setAdapter(adapter);
                } else if (spinnerRuteID == 2) {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(IndividualTripActivity.this, R.array.rute3, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerKotaJemput.setAdapter(adapter);
                    spinnerKotaTujuan.setAdapter(adapter);
                } else if (spinnerRuteID == 3) {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(IndividualTripActivity.this, R.array.rute4, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerKotaJemput.setAdapter(adapter);
                    spinnerKotaTujuan.setAdapter(adapter);
                } else if (spinnerRuteID == 4) {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(IndividualTripActivity.this, R.array.rute5, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerKotaJemput.setAdapter(adapter);
                    spinnerKotaTujuan.setAdapter(adapter);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerKotaJemput.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinnerKotaAwalID = position;
                if (spinnerRuteID == 0) {
                    kotaAwal = getResources().getStringArray(R.array.rute1)[position];
                } else if (spinnerRuteID == 1) {
                    kotaAwal = getResources().getStringArray(R.array.rute2)[position];
                } else if (spinnerRuteID == 2) {
                    kotaAwal = getResources().getStringArray(R.array.rute3)[position];
                } else if (spinnerRuteID == 3) {
                    kotaAwal = getResources().getStringArray(R.array.rute4)[position];
                } else if (spinnerRuteID == 4) {
                    kotaAwal = getResources().getStringArray(R.array.rute5)[position];
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerKotaTujuan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinnerKotaAkhirID = position;
                if (spinnerRuteID == 0) {
                    kotaAkhir = getResources().getStringArray(R.array.rute1)[position];
                } else if (spinnerRuteID == 1) {
                    kotaAkhir = getResources().getStringArray(R.array.rute2)[position];
                } else if (spinnerRuteID == 2) {
                    kotaAkhir = getResources().getStringArray(R.array.rute3)[position];
                } else if (spinnerRuteID == 3) {
                    kotaAkhir = getResources().getStringArray(R.array.rute4)[position];
                } else if (spinnerRuteID == 4) {
                    kotaAkhir = getResources().getStringArray(R.array.rute5)[position];
                }
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
        jumlahOrang = findViewById(R.id.jumlah_orang);
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
                int rute = spinnerRuteID + 1;
                String jumlah_orang = jumlahOrang.getText().toString().trim();
                int harga = 100000 * Integer.parseInt(jumlah_orang);
                int kota_jemput = getIdKota(kotaAwal);
                int kota_tujuan = getIdKota(kotaAkhir);
                int dari_semarang = 0;
                if ((kota_tujuan - kota_jemput) > 0) {
                    dari_semarang = 1;
                }
                String lokasi_jemput = lokasiJemput.getText().toString().trim();
                String tanggal = textDate.getText().toString().trim();
                int waktu_berangkat = spinnerWaktuID + 1;
                if (Integer.parseInt(jumlah_orang) < 1) {
                    Toast.makeText(IndividualTripActivity.this, "Jumlah Orang Minimal 1 Hari!!!", Toast.LENGTH_SHORT).show();
                } else if (kota_jemput == kota_tujuan) {
                    Toast.makeText(IndividualTripActivity.this, "Kota Awal dan Tujuan Tidak Boleh Sama!!!", Toast.LENGTH_SHORT).show();
                } else if (tanggal == "") {
                    Toast.makeText(IndividualTripActivity.this, "Tanggal Harus Diisi!!!", Toast.LENGTH_SHORT).show();
                } else {
                    // Toast.makeText(IndividualTripActivity.this, id_customer + " " + rute + " " + dari_semarang + " " + harga + " " + kota_jemput + " " + lokasi_jemput + " " + kota_tujuan + " " + tanggal + " " + waktu_berangkat + " " + jumlah_orang, Toast.LENGTH_LONG).show();
                    submit(id_customer, rute, dari_semarang, harga, kota_jemput, lokasi_jemput, kota_tujuan, tanggal, waktu_berangkat, jumlah_orang);
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

    private void submit(final String id_customer, final int rute, final int dari_semarang, final int harga, final int kota_jemput, final String lokasi_jemput, final int kota_tujuan, final String tanggal, final int waktu_berangkat, final String jumlah_orang) {

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
                                Toast.makeText(IndividualTripActivity.this, "Order Berhasil!", Toast.LENGTH_SHORT).show();
                                finish();
                            } else {
                                Toast.makeText(IndividualTripActivity.this, "Order Gagal! ", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            progressDialog.dismiss();

                            e.printStackTrace();
                            Toast.makeText(IndividualTripActivity.this, "Order Error!" + e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(IndividualTripActivity.this, "Order Error!" + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id_customer", id_customer);
                params.put("rute", String.valueOf(rute));
                params.put("dari_semarang", String.valueOf(dari_semarang));
                params.put("harga", String.valueOf(harga));
                params.put("kota_jemput", String.valueOf(kota_jemput));
                params.put("lokasi_jemput", lokasi_jemput);
                params.put("kota_tujuan", String.valueOf(kota_tujuan));
                params.put("tanggal", tanggal);
                params.put("waktu", String.valueOf(waktu_berangkat));
                params.put("jumlah_orang", jumlah_orang);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    public int getIdKota(String namaKota){
        switch (namaKota) {
            case "Banyumas" : return 1;
            case "Batang" : return 2;
            case "Boyolali" : return 3;
            case "Brebes" : return 4;
            case "Cilacap" : return 5;
            case "Demak" : return 6;
            case "Kebumen" : return 7;
            case "Kendal" : return 8;
            case "Kudus" : return 9;
            case "Magelang" : return 10;
            case "Pati" : return 11;
            case "Pekalongan" : return 12;
            case "Pemalang" : return 13;
            case "Purbalingga" : return 14;
            case "Purworejo" : return 15;
            case "Rembang" : return 16;
            case "Salatiga" : return 17;
            case "Semarang" : return 18;
            case "Surakarta" : return 19;
            case "Tegal" : return 20;
            case "Temanggung" : return 21;
            case "Wonosobo" : return 22;
            case "Yogyakarta" : return 23;
            case "Banjarnegara" : return 24;
        }
        return 0;
    }
}
