package com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.admin_fragment;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.MenuRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.provider.MediaStore;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.R;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.activity.admin_activity.SetMenuActivity;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.api.RetrofitClient;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.api.response.MenuResponse;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.api.response.MessageResponse;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.api.response.UserResponse;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.model.Menu;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.model.User;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;

public class AddEditMenuFragment extends Fragment {

    private TextInputEditText inputMenuName, inputMenuPrice, inputMenuType;
    private ImageView ivGambar;
    private MaterialButton btnSave, btnCancel, btnUpload;
    private Bitmap bitmap;
    private Menu menu;
    private View view;
    private Uri selectedImage = null;
    private String idMenu, status, selected;
    private static final int PERMISSION_CODE = 1000;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_add_menu, container, false);
        init();
        setAttribut();
        return view;
    }

    public void init() {
        menu = (Menu) getArguments().getSerializable("menu");
        inputMenuName = view.findViewById(R.id.inputMenuName);
        inputMenuPrice = view.findViewById(R.id.inputMenuPrice);
        inputMenuType = view.findViewById(R.id.inputMenuType);
        ivGambar = view.findViewById(R.id.ivGambar);
        btnCancel = view.findViewById(R.id.btnCancelAddMenu);
        btnSave = view.findViewById(R.id.btnSaveAddMenu);
        btnUpload = view.findViewById(R.id.btnUpload);

        //Isi data yang akan diedit
        status = getArguments().getString("status");
        if(status.equals("edit")) {
            idMenu = menu.getId();
            inputMenuName.setText(menu.getNama());
            inputMenuPrice.setText(menu.getHarga());
            inputMenuType.setText(menu.getJenis());
            Glide.with(view.getContext())
                    .load("https://pbp.dbappz.top/img/"+menu.getFotoMenu())
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .skipMemoryCache(true)
                    .into(ivGambar);
        }
    }

    private void setAttribut() {
        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater layoutInflater = LayoutInflater.from(view.getContext());
                View view = layoutInflater.inflate(R.layout.choose_media, null);

                final AlertDialog alertD = new AlertDialog.Builder(view.getContext()).create();

                Button btnKamera = (Button) view.findViewById(R.id.btnKamera);
                Button btnGaleri = (Button) view.findViewById(R.id.btnGaleri);

                btnKamera.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        selected="kamera";
                        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M)
                        {
                            if(getActivity().checkSelfPermission(Manifest.permission.CAMERA)==
                                    PackageManager.PERMISSION_DENIED ||
                                    getActivity().checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)==
                                            PackageManager.PERMISSION_DENIED){
                                String[] permission = {Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE};
                                requestPermissions(permission,PERMISSION_CODE);
                            }
                            else{
                                openCamera();
                            }
                        }
                        else{
                            openCamera();
                        }
                        alertD.dismiss();
                    }
                });

                btnGaleri.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        selected="galeri";
                        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M)
                        {
                            if(getActivity().checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)==
                                    PackageManager.PERMISSION_DENIED){
                                String[] permission = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
                                requestPermissions(permission,PERMISSION_CODE);
                            }
                            else{
                                openGallery();
                            }
                        }
                        else{
                            openGallery();
                        }
                        alertD.dismiss();
                    }
                });

                alertD.setView(view);
                alertD.show();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String menuName  = inputMenuName.getText().toString();
                String menuPrice = inputMenuPrice.getText().toString();
                String menuType = inputMenuType.getText().toString();
                String photoMenu;
                if(bitmap != null)
                    photoMenu = imagetoString(bitmap);
                else
                    photoMenu = null;

                if(menuName.isEmpty() || menuPrice.isEmpty() || menuType.isEmpty())
                    Toast.makeText(getContext(), "Please fill the data!", Toast.LENGTH_SHORT).show();
                else{
                    menu = new Menu(menuName, menuPrice, menuType, photoMenu);
                    if(status.equals("add"))
                        addMenu(menuName, menuPrice, menuType, photoMenu);
                    else
                        updateMenu(idMenu,menuName, menuPrice, menuType, photoMenu);
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SetMenuActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
    }

    private void openGallery(){
        Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(i, 1);
    }

    private void openCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,2);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case PERMISSION_CODE:{
                if(grantResults.length > 0 && grantResults[0] ==
                        PackageManager.PERMISSION_GRANTED){
                    if(selected.equals("kamera"))
                        openCamera();
                    else
                        openGallery();
                }else{
                    Toast.makeText(getContext() ,"Permision denied",Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 1)
        {
            selectedImage = data.getData();
            try {
                InputStream inputStream = getActivity().getContentResolver().openInputStream(selectedImage);
                bitmap = BitmapFactory.decodeStream(inputStream);
            } catch (Exception e) {
                Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }
            ivGambar.setImageBitmap(bitmap);
            bitmap = getResizedBitmap(bitmap, 512);
        }
        else if(resultCode == RESULT_OK && requestCode == 2)
        {
            Bundle extras = data.getExtras();
            bitmap = (Bitmap) extras.get("data");
            ivGambar.setImageBitmap(bitmap);
            bitmap = getResizedBitmap(bitmap, 512);
        }
    }

    public Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float)width / (float) height;
        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);
    }

    private String imagetoString(Bitmap bitmap)
    {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] imageType = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(imageType, Base64.DEFAULT);
    }

    private void addMenu(String name, String price, String type, String photo) {
        if(photo == null) {
            Toast.makeText(getContext(), "Photo must be uploaded!", Toast.LENGTH_SHORT).show();
            return;
        }
        Call<MessageResponse> addMenu = RetrofitClient.getRetrofit().insertMenu(name, price, type, photo);
        addMenu.enqueue(new Callback<MessageResponse>() {
            @Override
            public void onResponse(Call<MessageResponse> call, Response<MessageResponse> response) {
                if(response.isSuccessful()){
                    Toast.makeText(getContext(),response.body().getMessage(),Toast.LENGTH_SHORT).show();
                    Intent backToListMenu = new Intent(getContext(), SetMenuActivity.class);
                    startActivity(backToListMenu);
                    getActivity().finish();
                }else{
                    Toast.makeText(getContext(),"Insert menu failed",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<MessageResponse> call, Throwable t) {
                Toast.makeText(getContext(),"Network Failure? Try again!",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateMenu(String id, String name, String price, String type, String photo) {
        Call<MessageResponse> updateMenu = RetrofitClient.getRetrofit().updateMenu(id, name, price, type, photo);
        updateMenu.enqueue(new Callback<MessageResponse>() {
            @Override
            public void onResponse(Call<MessageResponse> call, Response<MessageResponse> response) {
                if(response.isSuccessful()){
                    Toast.makeText(getContext(),response.body().getMessage(),Toast.LENGTH_SHORT).show();
                    Intent backToListMenu = new Intent(getContext(), SetMenuActivity.class);
                    startActivity(backToListMenu);
                    getActivity().finish();
                }else{
                    Toast.makeText(getContext(),"Edit menu failed",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<MessageResponse> call, Throwable t) {
                Toast.makeText(getContext(),"Network Failure? Try again!",Toast.LENGTH_SHORT).show();
            }
        });
    }
}