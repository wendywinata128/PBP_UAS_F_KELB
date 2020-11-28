package com.kelompokbpbp.projecttugasbesarkelompokbrestaurant;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.adapter.GridFoodAdapter;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.api.response.MenuResponse;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.api.RetrofitClient;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.object.DaftarMenu;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.databinding.FragmentFoodBinding;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.model.Menu;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FoodFragment extends Fragment {

    private FragmentFoodBinding fragmentFoodBinding;
    private RecyclerView recyclerView;
    private GridFoodAdapter gridFoodAdapter;
    private RecyclerView.LayoutManager gridLayoutManager;
    private ArrayList<Menu> ListMenu;
    private SearchView searchView;

    public FoodFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentFoodBinding = FragmentFoodBinding.inflate(inflater, container, false);
        View view = fragmentFoodBinding.getRoot();
        searchView = fragmentFoodBinding.searchView;

        // Get data Menu
        ListMenu = new DaftarMenu().MENU;

        // Recycler view
        recyclerView = fragmentFoodBinding.recyclerViewMenu;
        gridFoodAdapter = new GridFoodAdapter(getContext(), ListMenu);
        gridLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(gridFoodAdapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                gridFoodAdapter.getFilter().filter(newText);
                return false;
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getDataMenu();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        fragmentFoodBinding = null;
    }

    private void getDataMenu(){
        Call<MenuResponse> client = RetrofitClient.getRetrofit().menuList();

        client.enqueue(new Callback<MenuResponse>() {
            @Override
            public void onResponse(Call<MenuResponse> call, Response<MenuResponse> response) {
                if(response.isSuccessful()){
                    gridFoodAdapter.setListMenu(response.body().getData());
                    Toast.makeText(getContext(),"Data loaded success",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getContext(),"Data Gagal Di Loaded",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<MenuResponse> call, Throwable t) {
                Toast.makeText(getContext(),"Kesalahan Jaringan",Toast.LENGTH_SHORT).show();
            }
        });
    }
}