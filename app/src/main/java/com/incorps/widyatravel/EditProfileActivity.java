package com.incorps.widyatravel;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class EditProfileActivity extends AppCompatActivity {
    public SessionLogin sessionLogin;
    Button saveProfile;
    private TextInputEditText txtNama, txtNoHP, txtEmail, txtPassword, txtAlamat;
    public String mId, mName, mNoHp, mEmail, mPassword, mAlamat, mFoto;
    private String URL_SIMPAN = "http://widyatravel.000webhostapp.com/api/api_edit_profile.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        sessionLogin = new SessionLogin(this);

        TextView txtNamaCustomer = findViewById(R.id.nama_customer);

        txtNama = findViewById(R.id.nama_lengkap);
        txtNoHP = findViewById(R.id.no_handphone);
        txtEmail = findViewById(R.id.email);
        txtPassword = findViewById(R.id.password);
        txtAlamat = findViewById(R.id.alamat);
        saveProfile = findViewById(R.id.save_profile);

        HashMap<String, String> user = sessionLogin.getUserDetail();
        mId = user.get(sessionLogin.ID);
        mName = user.get(sessionLogin.NAME);
        mNoHp = user.get(sessionLogin.NOHP);
        mEmail = user.get(sessionLogin.EMAIL);
        mPassword = user.get(sessionLogin.PASSWORD);
        mAlamat = user.get(sessionLogin.ALAMAT);
        mFoto = user.get(sessionLogin.FOTOPROFIL);

        txtNamaCustomer.setText(mName);

        txtNama.setText(mName);
        txtNoHP.setText(mNoHp);
        txtEmail.setText(mEmail);
        txtPassword.setText(mPassword);
        txtAlamat.setText(mAlamat);

        saveProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mNama = txtNama.getText().toString().trim();
                String mNoHp = txtNoHP.getText().toString().trim();
                String mEmail = txtEmail.getText().toString().trim();
                String mPassword = txtPassword.getText().toString().trim();
                String mAlamat = txtAlamat.getText().toString().trim();

                if (mNama.isEmpty()) {
                    txtNama.setError("Please insert name");
                } else if (mNoHp.isEmpty()) {
                    txtNoHP.setError("Please insert phone number");
                } else if (mEmail.isEmpty()) {
                    txtEmail.setError("Please insert email");
                } else if (mPassword.isEmpty()) {
                    txtPassword.setError("Please insert password");
                } else if (mAlamat.isEmpty()) {
                    txtAlamat.setError("Please insert address");
                } else {
                    Simpan(mId);
                }
            }
        });
    }

    private void Simpan(final String Id){

        final String nama = this.txtNama.getText().toString().trim();
        final String noHp = this.txtNoHP.getText().toString().trim();
        final String email = this.txtEmail.getText().toString().trim();
        final String password = this.txtPassword.getText().toString().trim();
        final String alamat = this.txtAlamat.getText().toString().trim();

        final ProgressDialog progressDialog =  new ProgressDialog(this);
        progressDialog.setMessage("Saving...");
        progressDialog.show();

        StringRequest stringRequest =  new StringRequest(Request.Method.POST, URL_SIMPAN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            progressDialog.dismiss();

                            JSONObject jsonObject =  new JSONObject(response);
                            String success = jsonObject.getString("success");

                            if (success.equals("1")) {

                                sessionLogin.createSession(Id, nama, noHp, email, password, alamat,null);

                                Toast.makeText(EditProfileActivity.this, "Profile Berhasil Disimpan!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(EditProfileActivity.this, DashboardActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(EditProfileActivity.this, "Penyimpanan Gagal! ", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            progressDialog.dismiss();

                            e.printStackTrace();
                            Toast.makeText(EditProfileActivity.this, "Saving Error!" + e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(EditProfileActivity.this, "Saving Error!" + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id", mId);
                params.put("nama", nama);
                params.put("noHp", noHp);
                params.put("email", email);
                params.put("password", password);
                params.put("alamat", alamat);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
