package com.kelompokbpbp.projecttugasbesarkelompokbrestaurant;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.database.DatabaseClient;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.model.User;

public class ProfilFragment extends Fragment {
    private TextView tvName, tvUsername, tvPhoneNumber;
    private MaterialButton btnEdit;

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
        tvPhoneNumber = view.findViewById(R.id.profile_phone);
        btnEdit = view.findViewById(R.id.btn_editProfile);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getUserProfile();
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditProfileFragment fragmentEditProfile = new EditProfileFragment();
                User user = new User(tvName.getText().toString(), tvPhoneNumber.getText().toString(), tvUsername.getText().toString(), null);
                Bundle profileData = new Bundle();
                profileData.putSerializable("user_profile", user);
                fragmentEditProfile.setArguments(profileData);
                getActivity().getSupportFragmentManager().beginTransaction().
                        replace(R.id.fragment_profile,fragmentEditProfile).addToBackStack(null).commit();
            }
        });
    }

    private void getUserProfile() {
        class GetUserProfie extends AsyncTask<Void, Void, User> {

            @Override
            protected User doInBackground(Void... voids) {
                User dataUser = DatabaseClient.getInstance(getActivity().getApplicationContext())
                        .getAppDatabase()
                        .userDao()
                        .getUserProfile();
                return dataUser;
            }

            @Override
            protected void onPostExecute(User user) {
                super.onPostExecute(user);
                tvName.setText(user.getNama());
                tvUsername.setText(user.getUsername());
                tvPhoneNumber.setText(user.getNohp());
            }
        }

        GetUserProfie getProfile = new GetUserProfie();
        getProfile.execute();
    }
}