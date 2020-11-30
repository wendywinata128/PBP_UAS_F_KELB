package com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.adapter;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.R;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.api.RetrofitClient;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.api.response.MenuResponse;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.database.AppPreference;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.database.DatabaseClient;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.databinding.ItemGridFoodBinding;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.model.Keranjang;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.model.Menu;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class GridFoodAdapter extends RecyclerView.Adapter<GridFoodAdapter.MyViewHolder> implements Filterable {
    private Context context;
    private List<Menu> listMenu = new ArrayList<>();
    private List<Menu> filterMenu;

    public GridFoodAdapter(Context context, List<Menu> listMenu) {
        this.context = context;
        this.listMenu.clear();
        this.listMenu.addAll(listMenu);
        filterMenu = new ArrayList<>();
        filterMenu.addAll(listMenu);
    }

    public void setListMenu(List<Menu> listMenu) {
        this.listMenu.clear();
        this.listMenu.addAll(listMenu);

        this.filterMenu.clear();
        this.filterMenu.addAll(listMenu);

        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        ItemGridFoodBinding itemGridFoodBinding = DataBindingUtil.inflate(layoutInflater, R.layout.item_grid_food, parent, false);

        return new MyViewHolder(itemGridFoodBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        final Menu menu = listMenu.get(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
        holder.bind(menu);
        holder.order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addItemToCart(menu);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listMenu.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ItemGridFoodBinding binding;
        MaterialButton order;

        public MyViewHolder(@NonNull ItemGridFoodBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            order = binding.orderbtn;
        }

        public void bind(Menu menu) {
            binding.setMenu(menu);
        }
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                FilterResults filterResults = new FilterResults();

                Log.d("MASUK", "Perform Filtering " + charSequence);

                if (charSequence == null || charSequence.length() == 0) {
                    filterResults.count = filterMenu.size();
                    filterResults.values = filterMenu;
                    Log.d("MASUK", "Filter Empty " + charSequence);
                } else {
                    Log.d("MASUK", "Filter loaded " + charSequence);
                    List<Menu> filterList = new ArrayList<>();
                    for (Menu menu : filterMenu) {
                        if (menu.getNama().toLowerCase().contains(charSequence.toString().toLowerCase())) {
                            filterList.add(menu);
                            Log.d("TEST", "MASUK");
                        }
                        Log.d("TEST", "LOOP");
                    }
                    filterResults.count = filterList.size();
                    filterResults.values = filterList;
                }
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                listMenu.clear();
                listMenu.addAll((List<Menu>) filterResults.values);
                notifyDataSetChanged();
            }
        };
    }

    private void addItemToCart(final Menu data) {
        AppPreference appPreference = new AppPreference(context);
        String username = appPreference.getLoginUsername();

        Call<MenuResponse> client = RetrofitClient.getRetrofit().insertCart(
                username,
                data.getNama(),
                "active",
                data.getHarga(),
                "1"
        );

        client.enqueue(new Callback<MenuResponse>() {
            @Override
            public void onResponse(Call<MenuResponse> call, Response<MenuResponse> response) {
                if(!response.isSuccessful()){
                    try {
                        JSONObject error = new JSONObject(response.errorBody().string());
                        Toast.makeText(context,error.getString("message"),Toast.LENGTH_SHORT).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else{
                    Toast.makeText(context, data.getNama() + " successfully added to your cart!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<MenuResponse> call, Throwable t) {
                Toast.makeText(context,"Network Problem",Toast.LENGTH_SHORT).show();
            }
        });
    }
}