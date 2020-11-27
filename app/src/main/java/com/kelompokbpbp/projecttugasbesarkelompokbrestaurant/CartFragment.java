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
        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

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
//                List<Keranjang> userCart = DatabaseClient.getInstance(getContext())
//                        .getAppDatabase()
//                        .keranjangDAO()
//                        .getAllKeranjang(username);
//                return userCart;
                return null;
            }
        }

        GetKeranjang get = new GetKeranjang();
        get.execute();
    }
}