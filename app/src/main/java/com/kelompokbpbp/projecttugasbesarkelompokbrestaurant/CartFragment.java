package com.kelompokbpbp.projecttugasbesarkelompokbrestaurant;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.adapter.CartAdapter;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.databinding.FragmentCartBinding;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.model.Keranjang;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.viewmodel.cartViewModel;

import java.util.ArrayList;
import java.util.List;

public class CartFragment extends Fragment implements CartAdapter.CartInterface{
    private static final String TAG = "CartFragment";
    cartViewModel cartViewModel;
    FragmentCartBinding fragmentCartBinding;
    NavController navController;


    public CartFragment(){
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentCartBinding = FragmentCartBinding.inflate(inflater, container, false);
        return fragmentCartBinding.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);

        final CartAdapter cartAdapter = new CartAdapter(this);
        fragmentCartBinding.recyclerCart.setAdapter(cartAdapter);

        cartViewModel = new ViewModelProvider(requireActivity()).get(cartViewModel.class);
        cartViewModel.getCart().observe(getViewLifecycleOwner(), new Observer<List<Keranjang>>() {
            @Override
            public void onChanged(List<Keranjang> keranjang) {
                cartAdapter.submitList(keranjang);
                fragmentCartBinding.btnCheckOut.setEnabled(keranjang.size() > 0);
            }
        });

        cartViewModel.getTotalHarga().observe(getViewLifecycleOwner(), new Observer<Double>() {
            @Override
            public void onChanged(Double aDouble) {
                fragmentCartBinding.txtFinalPrice.setText(aDouble.toString());
            }
        });

        fragmentCartBinding.btnCheckOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //navController.navigate(R.id.action_cartFragment_to_foodFragment);
            }
        });

    }
    @Override
    public void deleteItem(Keranjang keranjang) {
        cartViewModel.removeItemFromCart(keranjang);
    }

    @Override
    public void changeQuantity(Keranjang keranjang, int jumlah) {
        cartViewModel.changeQuantity(keranjang, jumlah);
    }
}