package com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.R;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.model.Keranjang;

import java.util.ArrayList;
import java.util.List;

public class CartTransactionAdapter extends RecyclerView.Adapter<CartTransactionAdapter.ViewHolder> {
    private List<Keranjang> listData = new ArrayList<>();

    public CartTransactionAdapter(List<Keranjang> listData){
        this.listData.clear();
        this.listData.addAll(listData);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cart_transaction_confirmation,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Keranjang keranjang = listData.get(position);

        if(position == 0){
            holder.tvNama.setText("Nama Menu");
            holder.tvJumlah.setText("Jumlah");
            holder.tvTotal.setText("Total Harga");
        }else{
            holder.tvNama.setText(keranjang.getNamaMakanan());
            holder.tvJumlah.setText(String.valueOf(keranjang.getJumlah()));
            holder.tvTotal.setText(String.valueOf(keranjang.getTotalHarga()));
        }

    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNama,tvTotal,tvJumlah;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNama = itemView.findViewById(R.id.tv_menu_nama);
            tvTotal = itemView.findViewById(R.id.tv_menu_total);
            tvJumlah = itemView.findViewById(R.id.tv_menu_jumlah);
        }
    }
}
