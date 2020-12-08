package com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.payment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.R;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.adapter.CartTransactionAdapter;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.api.RetrofitClient;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.api.response.CartResponse;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.api.response.MessageResponse;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.database.AppPreference;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.model.Keranjang;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaymentActivity extends AppCompatActivity {

    RecyclerView rvCartTransaction;
    TextView tvTotal;
    MaterialButton btnBayar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        rvCartTransaction = findViewById(R.id.rvCartTransaction);
        rvCartTransaction.setLayoutManager(new LinearLayoutManager(this));
        rvCartTransaction.setHasFixedSize(true);

        tvTotal = findViewById(R.id.tvTotal);
        btnBayar = findViewById(R.id.btnBayar);

        AppPreference appPreference = new AppPreference(getApplicationContext());

        Call<CartResponse> client = RetrofitClient.getRetrofit().getCartsByUsername(appPreference.getLoginUsername());

        client.enqueue(new Callback<CartResponse>() {
            @Override
            public void onResponse(Call<CartResponse> call, Response<CartResponse> response) {
                if(response.isSuccessful()){
                    List<Keranjang> keranjangs = new ArrayList<Keranjang>();

                    keranjangs.add(null);
                    keranjangs.addAll(response.body().getData());

                    CartTransactionAdapter adapter = new CartTransactionAdapter(keranjangs);

                    rvCartTransaction.setAdapter(adapter);

                    DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
                    DecimalFormatSymbols formatRp = new DecimalFormatSymbols();

                    formatRp.setCurrencySymbol("Rp. ");
                    formatRp.setMonetaryDecimalSeparator(',');
                    formatRp.setGroupingSeparator('.');

                    kursIndonesia.setDecimalFormatSymbols(formatRp);

                    String totalPembayaran = "Total : " + kursIndonesia.format(Double.valueOf(response.body().getTotal()));

                    tvTotal.setText(totalPembayaran);
                }else{
                    Toast.makeText(PaymentActivity.this,"Failed",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CartResponse> call, Throwable t) {
                Toast.makeText(PaymentActivity.this,"Failed",Toast.LENGTH_SHORT).show();
            }
        });


        btnBayar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<MessageResponse> client = RetrofitClient.getRetrofit().payTransaction(appPreference.getLoginUsername(),"OVO");

                client.enqueue(new Callback<MessageResponse>() {
                    @Override
                    public void onResponse(Call<MessageResponse> call, Response<MessageResponse> response) {
                        if(response.isSuccessful()){
                            Toast.makeText(PaymentActivity.this,"Pembayaran Berhasil!",Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }

                    @Override
                    public void onFailure(Call<MessageResponse> call, Throwable t) {
                        Toast.makeText(PaymentActivity.this,"Kesalahan Jaringan",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}