package com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.adapter;

import android.app.Activity;
import android.location.Address;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.EditAddAddressFragment;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.R;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.model.Alamat;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.MyViewHolder> {
    private ArrayList<Alamat> listData;

    public AddressAdapter(List<Alamat> listData) {
        this.listData = new ArrayList<>();
        this.listData.clear();
        this.listData.addAll(listData);
        notifyDataSetChanged();
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
                AppCompatActivity context = (AppCompatActivity) holder.itemView.getContext();
                EditAddAddressFragment fragment = new EditAddAddressFragment();
                Bundle bundle = new Bundle();
                bundle.putBoolean("Edit Address" , true);
                bundle.putSerializable("Address Data",data);
                fragment.setArguments(bundle);

                context.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_profile,fragment).commit();
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
}
