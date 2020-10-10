package com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.R;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.databinding.CartItemLayoutBinding;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.model.Keranjang;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.model.Menu;

import java.util.ArrayList;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MyViewHolder> {
    private Context context;
    private List<Keranjang> listKeranjang;
    private List<Keranjang> listKeranjangAr = new ArrayList<>();

    public CartAdapter(){}
    public CartAdapter(Context context, List<Keranjang> listKeranjang){
        this.context = context;
        this.listKeranjang = listKeranjang;
        listKeranjangAr.addAll(listKeranjang);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CartItemLayoutBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),R.layout.cart_item_layout,parent,false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Keranjang keranjang = listKeranjang.get(position);
        holder.Mybinding(keranjang);
    }

    @Override
    public int getItemCount() {
        return listKeranjang.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        CartItemLayoutBinding binding;

        public MyViewHolder(@NonNull CartItemLayoutBinding binding){
            super(binding.getRoot());
            this.binding = binding;
        }

        public void Mybinding(Keranjang obj) {
            binding.setKeranjang(obj);
        }
    }
}
