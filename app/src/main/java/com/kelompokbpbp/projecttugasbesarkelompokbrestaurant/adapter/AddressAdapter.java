package com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.R;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.model.Alamat;

import java.util.ArrayList;
import java.util.List;

public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.MyViewHolder> {
    private ArrayList<Alamat> listData;
    private SetOnClickListen setOnClickListen;

    public AddressAdapter(List<Alamat> listData) {
        this.listData = new ArrayList<>();
        this.listData.clear();
        this.listData.addAll(listData);
        notifyDataSetChanged();
    }

    public void setOnClickListen(SetOnClickListen setOnClickListen){
        this.setOnClickListen = setOnClickListen;
    }

    @NonNull
    @Override
    public AddressAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_address_layout,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final AddressAdapter.MyViewHolder holder, int position) {
        final Alamat data = listData.get(position);

        holder.number.setText(String.valueOf(position+1));
        holder.addressName.setText(data.getAddressName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setOnClickListen.onClickItem(data);

            }
        });
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView number,addressName;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            number = itemView.findViewById(R.id.tvNumber);
            addressName = itemView.findViewById(R.id.tvName);
        }
    }

    public interface SetOnClickListen{
        void onClickItem(Alamat data);
    }
}
