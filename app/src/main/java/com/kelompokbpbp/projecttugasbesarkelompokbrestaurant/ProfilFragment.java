package com.kelompokbpbp.projecttugasbesarkelompokbrestaurant;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.activity.edit_add_address_activity.EditAddAddressActivity;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.activity.login_activity.LoginActivity;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.adapter.AddressAdapter;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.api.RetrofitClient;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.api.response.UserResponse;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.database.AppPreference;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.database.DatabaseClient;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.model.Alamat;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.model.User;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfilFragment extends Fragment {
    public static final int EDIT_ADD_ADDRESS_REQ = 1123;
    private TextView tvName, tvUsername, tvEmail;
    private RecyclerView rvAddress;
    private AddressAdapter addressAdapter;
    private MaterialButton btnEdit, btnLogout, btnAddress;
    private CircleImageView photoProfile;
    private ProgressBar pbProfile;
    private ConstraintLayout profile_layout;
    private User dataUser;
    private Handler handler;

    public ProfilFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profil, container, false);
        tvName = view.findViewById(R.id.profile_name);
        tvUsername = view.findViewById(R.id.profile_username);
        tvEmail = view.findViewById(R.id.profile_email);
        photoProfile = view.findViewById(R.id.profile_photo);
        btnEdit = view.findViewById(R.id.btn_editProfile);
        btnLogout = view.findViewById(R.id.btn_logout);
        btnAddress = view.findViewById(R.id.btnAddress);
        rvAddress = view.findViewById(R.id.rvAddress);
        pbProfile = view.findViewById(R.id.pb_profile);
        profile_layout = view.findViewById(R.id.profile_layout);
        handler = new Handler();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getAllAddress();
        getUserProfile();

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditProfileFragment fragmentEditProfile = new EditProfileFragment();
                Bundle profileData = new Bundle();
                profileData.putSerializable("user_profile", dataUser);
                fragmentEditProfile.setArguments(profileData);
                getActivity().getSupportFragmentManager().beginTransaction().
                        replace(R.id.fragment_profile, fragmentEditProfile).commit();

            }
        });

        btnAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), EditAddAddressActivity.class);
                intent.putExtra("Edit Address", false);
                startActivityForResult(intent, EDIT_ADD_ADDRESS_REQ);
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertForLogout().show();
            }
        });

    }

    private void getUserProfile() {
        pbProfile.setVisibility(View.VISIBLE);
        profile_layout.setVisibility(View.GONE);

        AppPreference appPreference = new AppPreference(getContext());
        Call<UserResponse> client = RetrofitClient.getRetrofit().userDetails("Bearer " + appPreference.getUserToken());

        client.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if(getActivity() == null){
                    return;
                }

                if (response.isSuccessful()) {
                    pbProfile.setVisibility(View.GONE);
                    profile_layout.setVisibility(View.VISIBLE);

                    dataUser = response.body().getData();

                    if (dataUser != null) {
                        tvName.setText(dataUser.getNama());
                        tvUsername.setText(dataUser.getUsername());
                        tvEmail.setText(dataUser.getEmail());
                        if (!dataUser.getPhotoProfile().equals("-")) {
                            Glide.with(getContext())
                                    .load("https://pbp.dbappz.top/img/"+dataUser.getPhotoProfile())
                                    .into(photoProfile);
                        } else {
                            Glide.with(getContext())
                                    .load(R.drawable.ic_baseline_account_circle_24)
                                    .into(photoProfile);
                        }
                    }
                }
                else{
                    Toast.makeText(getContext(),"Load Gagal , Trying reload in 3 second",Toast.LENGTH_SHORT).show();

                    handler.postDelayed(() -> getUserProfile(),3000);

                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                if(getActivity() == null){
                    return;
                }

                Toast.makeText(getContext(),"Load Gagal , Trying reload in 3 second",Toast.LENGTH_SHORT).show();

                handler.postDelayed(() -> getUserProfile(),3000);
            }
        });
    }


    private void getAllAddress() {
        class GetAddress extends AsyncTask<Void, Void, List<Alamat>> {
            AppPreference appPreference = new AppPreference(ProfilFragment.this.getActivity().getApplicationContext());
            String username = appPreference.getLoginUsername();

            @Override
            protected List<Alamat> doInBackground(Void... voids) {

                List<Alamat> dataList = DatabaseClient.getInstance(ProfilFragment.this.getContext())
                        .getAppDatabase()
                        .addressDAO()
                        .getAllAlamat(username);
                return dataList;
            }

            @Override
            protected void onPostExecute(List<Alamat> alamats) {
                super.onPostExecute(alamats);
                addressAdapter = new AddressAdapter(alamats);
                rvAddress.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
                rvAddress.setAdapter(addressAdapter);
                addressAdapter.setOnClickListen(new AddressAdapter.SetOnClickListen() {
                    @Override
                    public void onClickItem(Alamat data) {
                        Intent intent = new Intent(getContext(), EditAddAddressActivity.class);
                        intent.putExtra("Edit Address", true);
                        intent.putExtra("Address Data", data);

                        startActivityForResult(intent, ProfilFragment.EDIT_ADD_ADDRESS_REQ);
                    }
                });
            }
        }

        GetAddress getAddress = new GetAddress();
        getAddress.execute();
    }

    public Dialog alertForLogout() {
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(getActivity());

        builder.setMessage(R.string.confirmation)
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        AppPreference appPreference = new AppPreference(getContext());
                        appPreference.setLoginUsername(null);
                        appPreference.setUserToken(null);
                        Intent toLogin = new Intent(ProfilFragment.this.getContext(), LoginActivity.class);
                        toLogin.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(toLogin);
                        getActivity().finish();
                    }
                });
        return builder.create();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == EDIT_ADD_ADDRESS_REQ) {
            getAllAddress();
        }
    }


    @Override
    public void onPause() {
        super.onPause();
        handler.removeCallbacksAndMessages(null);
    }

}