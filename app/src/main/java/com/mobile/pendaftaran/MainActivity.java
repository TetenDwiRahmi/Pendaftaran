package com.mobile.pendaftaran;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.mobile.pendaftaran.databinding.ActivityMainBinding;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    Bitmap bitmap, decoded;
    int PICK_IMAGE_REQUEST = 1;
    int bitmap_size = 60;
    String jk;
    PrefManager prefManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        AndroidNetworking.initialize(this);

        prefManager = new PrefManager(this);

        binding.lokasi.setText(prefManager.getAlamat());

        binding.rgJk.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.pria :
                        jk = "Pria";
                        break;
                    case R.id.wanita :
                        jk = "Wanita";
                        break;
                }
            }
        });

        binding.spName.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, binding.spName.getSelectedItem().toString() , Toast.LENGTH_SHORT).show();
            }
        });

        binding.foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFileChooser();
                binding.img.setVisibility(View.GONE);
            }
        });

        binding.submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.nama.getText().toString().isEmpty()) {
                    binding.nama.setError("Wajib diisi");
                } else if (binding.hp.getText().toString().isEmpty()) {
                    binding.hp.setError("Wajib diisi");
                }else if (binding.alamat.getText().toString().isEmpty()) {
                    binding.alamat.setError("Wajib diisi");
                }if (binding.rgJk.getCheckedRadioButtonId() == -1) {
                    new AlertDialog.Builder(MainActivity.this)
                            .setTitle("Perhatian")
                            .setMessage("Silahkan Pilih Jenis Kelamin")
                            .setCancelable(false)
                            .setNegativeButton("Oke", null)
                            .show();
                }
                else {
                    insert_data();
                }
            }
        });
    }

    private void insert_data() {
        if (binding.foto.getDrawable() == null) {
            new AlertDialog.Builder(this)
                    .setMessage("Silahkan Masukkan Foto")
                    .setCancelable(false)
                    .setNegativeButton("Ok", null)
                    .show();
        } else {
            AndroidNetworking.post(Server.site + "insert_data.php")
                    .addBodyParameter("nama", binding.nama.getText().toString())
                    .addBodyParameter("alamat", binding.alamat.getText().toString())
                    .addBodyParameter("hp", binding.hp.getText().toString())
                    .addBodyParameter("jenis_kelamin", jk)
                    .addBodyParameter("lokasi_pendaftaran", prefManager.getAlamat())
                    .addBodyParameter("foto", getStringImage(decoded))
                    .setPriority(Priority.MEDIUM)
                    .build()
                    .getAsJSONObject(new JSONObjectRequestListener() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                if (response.getString("kode").equals("1")) {
                                    Intent i = new Intent(MainActivity.this, HasilActivity.class);
                                    startActivity(i);
                                    Toast.makeText(MainActivity.this, response.getString("response"), Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(MainActivity.this, response.getString("response"), Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onError(ANError anError) {
                            Log.d("tag", "error di main : " + anError);
                        }
                    });
        }
    }

    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    public String getStringImage(Bitmap bmp) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, bitmap_size, baos);

        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }


    private void setToImageView(Bitmap bmp) {
        //compress image
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, bitmap_size, bytes);
        decoded = BitmapFactory.decodeStream(new ByteArrayInputStream(bytes.toByteArray()));
        //menampilkan gambar yang dipilih dari camera/gallery ke ImageView
        binding.foto.setImageBitmap(decoded);
    }

    // fungsi resize image
    public Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float) width / (float) height;
        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            try {
                //mengambil gambar dari Gallery
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                // 512 adalah resolusi tertinggi setelah image di resize, bisa di ganti.
                setToImageView(getResizedBitmap(bitmap, 512));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}