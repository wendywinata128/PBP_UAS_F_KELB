package com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.activity.admin_activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.R;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.admin_adapter.SetMenuAdapter;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.admin_fragment.AddEditMenuFragment;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.api.RetrofitClient;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.api.response.MenuResponse;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.model.Menu;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SetMenuActivity extends AppCompatActivity {
    private ImageButton ibBack;
    private FloatingActionButton addMenu;
    private RecyclerView recyclerView;
    private SetMenuAdapter setMenuAdapter;
    private List<Menu> menu = new ArrayList<>();
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_menu);

        ibBack = findViewById(R.id.ibBack);
        ibBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backToHome = new Intent(SetMenuActivity.this, HomeAdminActivity.class);
                startActivity(backToHome);
            }
        });

        addMenu = findViewById(R.id.btn_add_menu);
        addMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle data = new Bundle();
                data.putString("status", "add");
                AddEditMenuFragment addEditMenuFragment = new AddEditMenuFragment();
                addEditMenuFragment.setArguments(data);

                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frame_layout_menu, addEditMenuFragment)
                        .commit();
            }
        });

        swipeRefreshLayout = findViewById(R.id.swipe_refresh);

        swipeRefreshLayout.setRefreshing(true);
        getDataMenu();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getDataMenu();
            }
        });
    }

    public void getDataMenu(){
        Call<MenuResponse> client = RetrofitClient.getRetrofit().menuList();

        client.enqueue(new Callback<MenuResponse>() {
            @Override
            public void onResponse(Call<MenuResponse> call, Response<MenuResponse> response) {
                if(response.isSuccessful()){
                    generateMenuList(response.body().getData());
                    swipeRefreshLayout.setRefreshing(false);
                    Toast.makeText(SetMenuActivity.this,"Data loaded success",Toast.LENGTH_SHORT).show();
                }else{
                    swipeRefreshLayout.setRefreshing(false);
                    Toast.makeText(SetMenuActivity.this,"Data loaded failed",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<MenuResponse> call, Throwable t) {
                Toast.makeText(SetMenuActivity.this,"Kesalahan Jaringan",Toast.LENGTH_SHORT).show();
                swipeRefreshLayout.setRefreshing(true);
            }
        });
    }

    private void generateMenuList(List<Menu> menuList) {
        recyclerView = findViewById(R.id.rv_menu_admin);
        setMenuAdapter = new SetMenuAdapter(this, menuList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(SetMenuActivity.this);
        int orientation = getResources().getConfiguration().orientation;
        if(orientation == Configuration.ORIENTATION_LANDSCAPE){
            layoutManager = new GridLayoutManager(SetMenuActivity.this, 2);
        }
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(setMenuAdapter);
    }
}