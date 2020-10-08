package com.kelompokbpbp.projecttugasbesarkelompokbrestaurant;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.adapter.GridFoodAdapter;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.object.DaftarMenu;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.databinding.FragmentFoodBinding;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.model.Menu;

import java.util.ArrayList;

public class FoodFragment extends Fragment {

    private FragmentFoodBinding fragmentFoodBinding;
    private RecyclerView recyclerView;
    private GridFoodAdapter gridFoodAdapter;
    private RecyclerView.LayoutManager gridLayoutManager;
    private ArrayList<Menu> ListMenu;

    public FoodFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentFoodBinding = FragmentFoodBinding.inflate(inflater, container, false);
        View view = fragmentFoodBinding.getRoot();

        // Get data Menu
        ListMenu = new DaftarMenu().MENU;

        // Recycler view
        recyclerView = fragmentFoodBinding.recyclerViewMenu;
        gridFoodAdapter = new GridFoodAdapter(getContext(), ListMenu);
        gridLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(gridFoodAdapter);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        fragmentFoodBinding = null;
    }
}