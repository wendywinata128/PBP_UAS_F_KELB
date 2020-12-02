package com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.button.MaterialButton;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.R;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.databinding.CartItemLayoutBinding;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.model.Keranjang;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {
    private Context context;
    private List<Keranjang> listKeranjang;

    public CartAdapter(Context context,List<Keranjang>listKeranjang) {
        this.listKeranjang = listKeranjang;
        this.context = context;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        CartItemLayoutBinding cartItemLayoutBinding = CartItemLayoutBinding.inflate(layoutInflater, parent, false);
        return new CartViewHolder(cartItemLayoutBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        Keranjang keranjang = listKeranjang.get(position);
        Glide.with(context).load("https://pbp.dbappz.top/img/"+keranjang.fotoMakanan)
                .error(R.drawable.ic_baseline_broken_image_24)
                .into(holder.foto);
        holder.txtNama.setText(keranjang.getNamaMakanan());
        holder.txtharga.setText(keranjang.getHarga());
        holder.tvCount.setText(String.valueOf(keranjang.getJumlah()));

        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
            }
        });

        holder.btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                keranjang.jumlah--;
                holder.tvCount.setText(String.valueOf(keranjang.jumlah));
            }
        });

        holder.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                keranjang.jumlah++;
                holder.tvCount.setText(String.valueOf(keranjang.jumlah));
            }
        });

    }

    @Override
    public int getItemCount() {
        return listKeranjang.size();
    }

    class CartViewHolder extends RecyclerView.ViewHolder{
        TextView txtNama,txtharga,tvCount;
        ImageView foto;
        ImageButton deleteButton;
        MaterialButton btnAdd,btnMinus;

        public CartViewHolder(@NonNull CartItemLayoutBinding binding) {
            super(binding.getRoot());
            txtNama = binding.txtProdukName;
            txtharga = binding.txtProdukHarga;
            foto = binding.imgCart;
            deleteButton = binding.deleteProductButton;
            btnAdd = binding.menuAdd;
            btnMinus = binding.menuMinus;
            tvCount = binding.menuCount;
        }
    }
}
