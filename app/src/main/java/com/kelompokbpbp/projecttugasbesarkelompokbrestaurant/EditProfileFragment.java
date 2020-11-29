package com.kelompokbpbp.projecttugasbesarkelompokbrestaurant;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.api.RetrofitClient;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.api.response.UserResponse;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.model.User;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;

public class EditProfileFragment extends Fragment {

    private final static int PHOTO_CAPTURE_CODE = 1003;
    private TextInputEditText editName, editUsername, editEmail;
    private TextInputLayout editPhoneLayout, editNameLayout;
    private MaterialButton updateBtn, cancelBtn, captureBtn;
    private CircleImageView editProfilePhoto;
    private User user;
    private String photoSave = null;
    private int storage_requestCode = 1002;
    Uri image_uri;


    public EditProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        container.removeAllViews();
        View view = inflater.inflate(R.layout.fragment_edit_profile, container, false);
        user = (User) getArguments().getSerializable("user_profile");
        captureBtn = view.findViewById(R.id.btn_edit_profilePhoto);
        editName = view.findViewById(R.id.input_name);
        editNameLayout = view.findViewById(R.id.input_name_layout);
        editUsername = view.findViewById(R.id.input_username);
        editEmail = view.findViewById(R.id.input_email);
        editPhoneLayout = view.findViewById(R.id.input_email_layout);
        editProfilePhoto = view.findViewById(R.id.edit_profile_photo);
        updateBtn = view.findViewById(R.id.btn_update);
        cancelBtn = view.findViewById(R.id.btn_cancel);

        try {
            if (user.getNama() != null) {
                editName.setText(user.getNama());
            } else {
                editName.setText("-");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            if (user.getUsername() != null) {
                editUsername.setText(user.getUsername());
            } else {
                editUsername.setText("-");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            if(user.getEmail() != null) {
                editEmail.setText(user.getEmail());
            } else {
                editEmail.setText("-");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            if (!user.getPhotoProfile().equals("-")) {
                Glide.with(getContext())
                        .load("https://pbp.dbappz.top/img/"+user.getPhotoProfile())
                        .into(editProfilePhoto);
            }
            if (user.getPhotoProfile().equals("-")) {
                Glide.with(getContext())
                        .load(R.drawable.ic_baseline_account_circle_24)
                        .into(editProfilePhoto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        captureBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // system os >= marshmallow
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                        requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, storage_requestCode);
                    } else {
                        // permission granted
                        capturePhoto();
                    }
                } else {
                    // system os < marshmallow
                    capturePhoto();
                }
            }
        });

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CheckValidation()) {
                    user.setNama(String.valueOf(editName.getText()));
                    user.setUsername(String.valueOf(editUsername.getText()));
//                    user.setNohp(String.valueOf(editPhoneNumber.getText()));
                    updateProfile(user);
                }
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_profile, new ProfilFragment()).commit();
            }
        });
    }

    private void updateProfile(final User user) {
//        Toast.makeText(getActivity().getApplicationContext(), "Your profile is updated!", Toast.LENGTH_SHORT).show();
//        FragmentTransaction transaction = getFragmentManager().beginTransaction();
//        transaction.replace(R.id.fragment_profile, new ProfilFragment()).commit();

        Call<UserResponse> client = RetrofitClient.getRetrofit().getUserAfterEdit(String.valueOf(user.getId()),
                editName.getText().toString(),
                "No Address",
                user.getEmail(),
                photoSave);

        client.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if(response.isSuccessful()){
                    Toast.makeText(getContext(),response.body().getMessage(),Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getContext(),"Edit User Failed",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Toast.makeText(getContext(),"Network Failure ? Try again!",Toast.LENGTH_SHORT).show();
            }
        });

    }

    private boolean CheckValidation() {
        boolean validation = true;

        if (editName.getText().toString().isEmpty()) {
            editNameLayout.setError("Full name cannot be empty");
            validation = false;
        }

        if (editEmail.getText().toString().isEmpty() ||
                String.valueOf(editEmail.getText()).length() < 8) {
            editPhoneLayout.setError("Phone number must be 8 or more digits");
            validation = false;
        }
        return validation;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == storage_requestCode) {
            if (grantResults[0] == PackageManager.PERMISSION_DENIED) {
                Toast.makeText(getContext(), "Storage permission denied", Toast.LENGTH_LONG).show();
            } else {
                capturePhoto();
            }
        }
    }

    private void capturePhoto() {
//        ContentValues values = new ContentValues();
//        values.put(MediaStore.Images.Media.TITLE, "New Profile Photo");
//        values.put(MediaStore.Images.Media.DESCRIPTION, "From the Camera");
//        image_uri = getContext().getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
        //Camera intent
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, image_uri);
        startActivityForResult(cameraIntent, PHOTO_CAPTURE_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);


        //Called when photo was captured from camera
        if (resultCode == RESULT_OK) {
            try{
                Bitmap bitmap = (Bitmap) data.getExtras().get("data");

                Glide.with(getContext())
                        .load(bitmap)
                        .into(editProfilePhoto);


                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG,100,baos);

                String base64code = Base64.encodeToString(baos.toByteArray(),Base64.DEFAULT);

                photoSave = base64code;

            }catch(Exception e){
                e.printStackTrace();
            }

        }
    }


}