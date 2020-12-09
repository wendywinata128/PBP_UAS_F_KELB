package com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.pdf;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.R;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.model.Transaksi;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

public class TransaksiAdapter extends RecyclerView.Adapter<TransaksiAdapter.TransaksiViewHolder>{
    private Context context;
    private View view;
    private Transaksi[] transaksis;
    private List<Transaksi> transaksiList;
    private List<Transaksi> transaksiListFilter;

    public TransaksiAdapter(Context context, List<Transaksi> transaksiList){
        this.context = context;
        this.transaksiList = transaksiList;
        this.transaksiListFilter = transaksiList;
    }

    @NonNull
    @Override
    public TransaksiAdapter.TransaksiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        view = layoutInflater.inflate(R.layout.adapter_transaksi,parent,false);
        return new TransaksiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TransaksiAdapter.TransaksiViewHolder holder, int position) {
        final Transaksi trans = transaksiListFilter.get(position);
        NumberFormat formatter = new DecimalFormat("#,###");
        holder.txtName.setText(trans.getCustomerName());
        holder.txtPrice.setText("Rp. "+ trans.getTotalPrice());
        holder.txtStatus.setText(trans.getStatus());
    }

    @Override
    public int getItemCount() {
        return (transaksiListFilter != null) ?
                transaksiListFilter.size() : 0;
    }

    public class TransaksiViewHolder extends RecyclerView.ViewHolder {
        private TextView txtName,txtPrice,txtStatus;
        public TransaksiViewHolder(@NonNull View itemView){
            super(itemView);
            txtName = itemView.findViewById(R.id.customerName);
            txtPrice = itemView.findViewById(R.id.totalPrice);
            txtStatus = itemView.findViewById(R.id.status);
        }
    }
}
