package com.kelompokbpbp.projecttugasbesarkelompokbrestaurant;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.google.android.material.button.MaterialButton;

import java.util.Objects;

public class Fragment_FirstActivity_Setting extends Fragment implements CompoundButton.OnCheckedChangeListener {
    private OnChangeListener onChangeListener;
    private CheckBox cbNotification, cbLocation, cbCamera;
    private MaterialButton btnCheckAll;

    private static final int REQ_LOCATION = 1001;
    private static final int REQ_CAMERA = 1002;

    public Fragment_FirstActivity_Setting() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment__first_activity__setting, container, false);

        cbNotification = view.findViewById(R.id.cbNotification);
        cbCamera = view.findViewById(R.id.cbCamera);
        cbLocation = view.findViewById(R.id.cbLocation);
        btnCheckAll = view.findViewById(R.id.btnCheckAll);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        cbLocation.setOnCheckedChangeListener(this);
        cbCamera.setOnCheckedChangeListener(this);
        cbNotification.setOnCheckedChangeListener(this);
        btnCheckAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cbLocation.setChecked(true);
                cbCamera.setChecked(true);
                cbNotification.setChecked(true);
            }
        });

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        onChangeListener = (OnChangeListener) getActivity();
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        switch (compoundButton.getId()){
            case R.id.cbCamera:
                if (ActivityCompat.checkSelfPermission(getActivity(),
                        Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{Manifest.permission.CAMERA}, REQ_CAMERA);
                }
                break;
            case R.id.cbLocation:
                if (ActivityCompat.checkSelfPermission(getActivity(),
                        Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQ_LOCATION);
                }
                break;

            case R.id.cbNotification:

                break;
        }

        if (cbCamera.isChecked() && cbLocation.isChecked()) {
            onChangeListener.onContinueClicked(true);
        }else{
            onChangeListener.onContinueClicked(false);
        }
    }

    public interface OnChangeListener {
        void onContinueClicked(boolean data);
    }
}