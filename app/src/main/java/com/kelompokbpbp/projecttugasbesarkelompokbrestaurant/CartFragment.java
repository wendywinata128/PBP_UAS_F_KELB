package com.kelompokbpbp.projecttugasbesarkelompokbrestaurant;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.adapter.CartAdapter;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.api.RetrofitClient;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.api.response.CartResponse;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.database.AppPreference;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.database.DatabaseClient;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.databinding.FragmentCartBinding;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.model.Keranjang;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.payment.PaymentActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartFragment extends Fragment{
    //private static final String TAG = "CartFragment";
    RecyclerView recyclerView;
    FragmentCartBinding fragmentCartBinding;
    MaterialButton checkOut;
    CartAdapter adapter;
    private ProgressBar pbCart;
    private int PAYMENT_REQUEST = 101;
    private boolean emptyCart = true;

    public CartFragment(){
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        fragmentCartBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_cart,container,false);
        View view = fragmentCartBinding.getRoot();

        recyclerView = fragmentCartBinding.recyclerCart;
        pbCart = fragmentCartBinding.pbCart;

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        getKeranjang();

        checkOut = view.findViewById(R.id.btn_checkOut);
        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        checkOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(emptyCart) {
                    Toast.makeText(getContext(), "Cart is still empty!", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent(getContext(), PaymentActivity.class);
                startActivityForResult(intent,PAYMENT_REQUEST);
            }
        });

    }
    public void getKeranjang(){
        pbCart.setVisibility(View.VISIBLE);
        AppPreference appPreference = new AppPreference(getContext());

        Call<CartResponse> client = RetrofitClient.getRetrofit().getCartsByUsername(appPreference.getLoginUsername());

        client.enqueue(new Callback<CartResponse>() {
            @Override
            public void onResponse(Call<CartResponse> call, Response<CartResponse> response) {
                if(response.isSuccessful()){
                    if(response.body().getData() != null) {
                        emptyCart = false;
                        pbCart.setVisibility(View.GONE);
                        adapter = new CartAdapter(getContext(),response.body().getData());
                        recyclerView.setAdapter(adapter);
                    }else{
                        pbCart.setVisibility(View.GONE);
                        Toast.makeText(getContext(),response.body().getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<CartResponse> call, Throwable t) {
                Toast.makeText(getContext(),"Kesalahan Jaringan ? ",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PAYMENT_REQUEST){
            adapter.refreshAdapter();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        getKeranjang();
    }
}