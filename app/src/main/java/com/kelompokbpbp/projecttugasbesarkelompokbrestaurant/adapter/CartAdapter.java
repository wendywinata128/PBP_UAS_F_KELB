package com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
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
//        Glide.with(context).load(listKeranjang.get(position).getFotoMenu())
//                .into(holder.foto);
        holder.txtNama.setText("asdasd");
        holder.txtharga.setText(new StringBuilder("")
                .append(listKeranjang.get(position).getHarga() + listKeranjang.get(position).getTotalHarga()));

    }

    @Override
    public int getItemCount() {
        return listKeranjang.size();
    }

    class CartViewHolder extends RecyclerView.ViewHolder{
        TextView txtNama,txtharga;
        ImageView foto;
        private CartItemLayoutBinding binding;
        public CartViewHolder(@NonNull CartItemLayoutBinding binding) {
            super(binding.getRoot());
            txtNama = itemView.findViewById(R.id.txt_produk_name);
            txtharga = itemView.findViewById(R.id.txt_produk_harga);
            foto = itemView.findViewById(R.id.img_cart);
        //this.binding = binding;
        }
    }
}
