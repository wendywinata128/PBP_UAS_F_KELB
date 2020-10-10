package com.kelompokbpbp.projecttugasbesarkelompokbrestaurant;

import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toolbar;

import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.adapter.GridFoodAdapter;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.databinding.FragmentFoodBinding;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.model.Menu;

import java.util.ArrayList;

public class CartFragment extends Fragment {

    private FragmentFoodBinding fragmentFoodBinding;
    private RecyclerView recyclerView;
    private GridFoodAdapter gridFoodAdapter;
    private RecyclerView.LayoutManager gridLayoutManager;
    private ArrayList<Menu> ListMenu;

    public CartFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cart, container, false);
    }
}