package com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.admin_adapter;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.R;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.activity.admin_activity.SetMenuActivity;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.admin_fragment.AddEditMenuFragment;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.api.RetrofitClient;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.api.response.MenuResponse;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.api.response.MessageResponse;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.databinding.ItemMenuAdminBinding;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.model.Menu;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SetMenuAdapter extends RecyclerView.Adapter<SetMenuAdapter.MenuViewHolder> {
    private Context context;
    private List<Menu> listMenu;

    public SetMenuAdapter(Context context, List<Menu> listMenu) {
        this.context = context;
        this.listMenu = listMenu;
    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemMenuAdminBinding itemMenuAdminBinding = ItemMenuAdminBinding.inflate(layoutInflater, parent, false);
        return new MenuViewHolder(itemMenuAdminBinding);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull SetMenuAdapter.MenuViewHolder holder, int position) {
        Menu menu = listMenu.get(position);
        Glide.with(context).load("https://pbp.dbappz.top/img/" + menu.getFotoMenu())
                .error(R.drawable.ic_baseline_fastfood_24)
                .into(holder.imageMenu);
        holder.tvMenuName.setText(menu.getNama());
        holder.tvMenuPrice.setText("Rp " + menu.getHarga());
        holder.tvMenuType.setText(menu.getJenis());

        holder.btnDeleteMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(context);

                builder.setTitle("Confirmation");
                builder.setMessage("Are you sure you want to delete this menu?")
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        })
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                deleteMenuById(menu.getId(), position);
                            }
                        });
                builder.create().show();
            }
        });

        holder.itemMenuLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle data = new Bundle();
                data.putSerializable("menu", menu);
                data.putString("status", "edit");
                AddEditMenuFragment addEditMenuFragment = new AddEditMenuFragment();
                addEditMenuFragment.setArguments(data);

                AppCompatActivity activity = (AppCompatActivity) context;
                FragmentManager fragmentManager = activity.getSupportFragmentManager();
                fragmentManager .beginTransaction()
                        .replace(R.id.frame_layout_menu, addEditMenuFragment)
                        .commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listMenu.size();
    }

    class MenuViewHolder extends RecyclerView.ViewHolder {
        TextView tvMenuName, tvMenuPrice, tvMenuType;
        ImageView imageMenu;
        ImageButton btnDeleteMenu;
        LinearLayout itemMenuLayout;

        public MenuViewHolder(@NonNull ItemMenuAdminBinding menuAdminBinding) {
            super(menuAdminBinding.getRoot());
            tvMenuName = menuAdminBinding.tvMenuName;
            tvMenuPrice = menuAdminBinding.tvMenuPrice;
            tvMenuType = menuAdminBinding.tvMenuType;
            imageMenu = menuAdminBinding.imageUrl;
            btnDeleteMenu = menuAdminBinding.btnDeleteMenu;
            itemMenuLayout = menuAdminBinding.itemMenuLayout;
        }
    }

    private void deleteMenuById(String id, int position) {
        Call<MenuResponse> delete = RetrofitClient.getRetrofit().deleteMenu(id);

        delete.enqueue(new Callback<MenuResponse>() {
            @Override
            public void onResponse(Call<MenuResponse> call, Response<MenuResponse> response) {
                if(response.isSuccessful()) {
                    listMenu.remove(position);
                    notifyItemRemoved(position);
                    notifyItemRangeChanged(position,listMenu.size());
                    Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<MenuResponse> call, Throwable t) {
                Toast.makeText(context, "Kesalahan Jaringan", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
