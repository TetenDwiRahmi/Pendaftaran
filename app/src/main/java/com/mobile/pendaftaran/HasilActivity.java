package com.mobile.pendaftaran;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.mobile.pendaftaran.databinding.ActivityHasilBinding;
import com.mobile.pendaftaran.databinding.ActivityMainBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class HasilActivity extends AppCompatActivity {
    private ActivityHasilBinding binding;
    List<Hasil> hasilList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHasilBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        AndroidNetworking.initialize(this);

        show_hasil();
        binding.recyclerHasil.setHasFixedSize(true);
        binding.recyclerHasil.setLayoutManager(new LinearLayoutManager(this));

        binding.imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HasilActivity.this, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
        });
    }

    private void show_hasil() {
        AndroidNetworking.post(Server.site + "get_hasil.php")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject h = null;
                                h = response.getJSONObject(i);
                                hasilList.add(new Hasil(
                                        h.getInt("id"),
                                        h.getString("nama"),
                                        h.getString("alamat"),
                                        h.getString("hp"),
                                        h.getString("jenis_kelamin"),
                                        h.getString("lokasi_pendaftaran"),
                                        h.getString("foto")
                                ));
                                HasilAdapter adapter = new HasilAdapter(HasilActivity.this, hasilList);
                                binding.recyclerHasil.setAdapter(adapter);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.d("tag", "error hasil : " + anError);
                    }
                });
    }
}