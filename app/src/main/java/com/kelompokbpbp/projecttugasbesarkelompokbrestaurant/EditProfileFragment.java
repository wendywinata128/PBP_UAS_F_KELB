package com.kelompokbpbp.projecttugasbesarkelompokbrestaurant;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.database.DatabaseClient;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.model.User;

import de.hdodenhof.circleimageview.CircleImageView;

public class EditProfileFragment extends Fragment {

    TextInputEditText editName, editUsername, editPhoneNumber;
    MaterialButton updateBtn, cancelBtn, captureBtn;
    CircleImageView profilePhoto;
    User user;

    public EditProfileFragment() {
        // Required empty public constructor
    }

    private void updateProfile(final User user) {
        class UpdateUserProfile extends AsyncTask<Void, Void, Void> {
            @Override
            protected Void doInBackground(Void... voids) {
                DatabaseClient.getInstance(getActivity().getApplicationContext())
                        .getAppDatabase()
                        .userDao()
                        .update(user);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(getActivity().getApplicationContext(), "Your profile is updated!", Toast.LENGTH_SHORT).show();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.hide(EditProfileFragment.this).commit();
            }
        }
        UpdateUserProfile updateUserProfile = new UpdateUserProfile();
        updateUserProfile.execute();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_profile, container, false);
        user = (User) getArguments().getSerializable("user_profile");

        profilePhoto = view.findViewById(R.id.edit_profile_photo);

        editName = view.findViewById(R.id.input_name);
        editUsername = view.findViewById(R.id.input_username);
        editPhoneNumber = view.findViewById(R.id.input_phone);

        updateBtn = view.findViewById(R.id.btn_update);
        cancelBtn = view.findViewById(R.id.btn_cancel);

        try {
            if(user.getNama() != null) {
                editName.setText(user.getNama());
            } else {
                editName.setText("-");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            if(user.getUsername() != null) {
                editUsername.setText(user.getUsername());
            } else {
                editUsername.setText("-");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            if(user.getNohp() != null) {
                editPhoneNumber.setText(user.getNohp());
            } else {
                editPhoneNumber.setText("-");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.setNama(String.valueOf(editName.getText()));
                user.setUsername(String.valueOf(editUsername.getText()));
                user.setNohp(String.valueOf(editPhoneNumber.getText()));
                updateProfile(user);
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.hide(EditProfileFragment.this).commit();
            }
        });
    }
}