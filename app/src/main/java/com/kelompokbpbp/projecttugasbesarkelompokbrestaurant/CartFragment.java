package com.kelompokbpbp.projecttugasbesarkelompokbrestaurant;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.adapter.CartAdapter;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.database.AppPreference;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.database.DatabaseClient;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.databinding.FragmentCartBinding;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.model.Keranjang;

import java.util.List;

public class CartFragment extends Fragment{
    //private static final String TAG = "CartFragment";
    RecyclerView recyclerView;
    FragmentCartBinding fragmentCartBinding;
    NavController navController;
    Keranjang keranjang;
    MaterialButton checkOut;
    CartAdapter adapter;

    public CartFragment(){
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        fragmentCartBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_cart,container,false);
        View view = fragmentCartBinding.getRoot();

        recyclerView = fragmentCartBinding.recyclerCart;

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        getKeranjang();

        checkOut = view.findViewById(R.id.btn_checkOut);
//        checkOut.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                ArrayList<Keranjang> keranjangs = new ArrayList<>();
//                for(int i=0;i<adapter.getItemCount();i++){
//                    keranjangs.add(adapter.getItem(i));
//                }
//
//                Bundle bundle = new Bundle();
//                bundle.putSerializable("arraylist",keranjangs);
//
//                /*if (adapter.getItemCount()>0)
//                    Navigation.findNavController(view).navigate(R.id.action_navigation);
//                else Toast.makeText(getContext(),"Cart is Empty",
//                        Toast.LENGTH_SHORT).show();*/
//            }
//        });
        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        /*//navController = Navigation.findNavController(view);

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
        });*/

    }
    public void getKeranjang(){
        AppPreference appPreference = new AppPreference(getContext());
        final String username = appPreference.getLoginUsername();
        class GetKeranjang extends AsyncTask<Void,Void,List<Keranjang>>{
            @Override
            protected void onPostExecute(List<Keranjang>keranjangList){
                super.onPostExecute(keranjangList);
                adapter = new CartAdapter(getContext(),keranjangList);
                recyclerView.setAdapter(adapter);
                if(keranjangList.isEmpty()){
                    Toast.makeText(getContext(),"Empty List",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            protected List<Keranjang> doInBackground(Void... voids){
                List<Keranjang> userCart = DatabaseClient.getInstance(getContext())
                        .getAppDatabase()
                        .keranjangDAO()
                        .getAllKeranjang(username);
                return userCart;
            }
        }

        GetKeranjang get = new GetKeranjang();
        get.execute();
    }
}