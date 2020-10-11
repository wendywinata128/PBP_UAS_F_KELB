package com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.R;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.databinding.CartItemLayoutBinding;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.model.Keranjang;

import java.util.List;

public class CartAdapter extends ListAdapter<Keranjang,CartAdapter.CartViewHolder> {
    private Context context;
    private List<Keranjang> listKeranjang;
    private CartInterface cartInterface;

    public CartAdapter(CartInterface cartInterface) {
        super(Keranjang.itemCallback);
        this.cartInterface = cartInterface;
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
        holder.cartItemLayoutBinding.setKeranjang(getItem(position));
        holder.cartItemLayoutBinding.executePendingBindings();
        /*holder.txt_jumlah.setNumber(String.valueOf(listKeranjang.get(position).getJumlah()));

        holder.txt_jumlah.setOnValueChangeListener(new ElegantNumberButton.OnValueChangeListener() {
            @Override
            public void onValueChange(ElegantNumberButton view, int oldValue, int newValue) {
                Keranjang keranjang = listKeranjang.get(position);
                keranjang.setJumlah(newValue);

                Common.cartRepository.updateCart(keranjang);
            }
        }); */
    }

    /*@Override
    public int getItemCount() {
        return listKeranjang.size();
    }*/

    class CartViewHolder extends RecyclerView.ViewHolder{
        CartItemLayoutBinding cartItemLayoutBinding;

        public CartViewHolder(@NonNull CartItemLayoutBinding cartItemLayoutBinding) {
            super(cartItemLayoutBinding.getRoot());
            this.cartItemLayoutBinding = cartItemLayoutBinding;

            cartItemLayoutBinding.deleteProductButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cartInterface.deleteItem(getItem(getAdapterPosition()));
                }
            });
            cartItemLayoutBinding.quantitySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    int jumlah = position + 1;
                    if (jumlah == getItem(getAdapterPosition()).getJumlah()) {
                        return;
                    }
                    cartInterface.changeQuantity(getItem(getAdapterPosition()), jumlah);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            //this.binding = binding;
        }
    }

    public interface CartInterface {
        void deleteItem(Keranjang keranjang);
        void changeQuantity(Keranjang keranjang, int jumlah);
    }
}
