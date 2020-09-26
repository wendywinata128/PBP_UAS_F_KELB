package com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.R;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.databinding.ItemGridFoodBinding;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.model.Menu;

import java.util.List;

public class GridFoodAdapter extends RecyclerView.Adapter<GridFoodAdapter.MyViewHolder> {
    private Context context;
    private List<Menu> listMenu;

    public GridFoodAdapter(Context context, List<Menu> listMenu){
        this.context = context;
        this.listMenu = listMenu;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        ItemGridFoodBinding itemGridFoodBinding = DataBindingUtil.inflate(layoutInflater, R.layout.item_grid_food, parent, false);

        return new MyViewHolder(itemGridFoodBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        final Menu menu = listMenu.get(position);
        holder.bind(menu);
    }

    @Override
    public int getItemCount() {
        return listMenu.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ItemGridFoodBinding binding;

        public MyViewHolder(@NonNull ItemGridFoodBinding binding){
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Menu menu) {
            binding.setMenu(menu);
        }
    }
}