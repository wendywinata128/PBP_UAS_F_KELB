package com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.activity.first_time_activity;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.messaging.FirebaseMessaging;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.R;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.activity.register_activity.RegisterActivity;

public class Fragment_FirstActivity_Setting extends Fragment implements CompoundButton.OnCheckedChangeListener {
    private CheckBox cbNotification, cbLocation, cbCamera;
    private MaterialButton btnContinue;

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
        btnContinue = view.findViewById(R.id.btnContinue);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        createChannel();

        cbLocation.setOnCheckedChangeListener(this);
        cbCamera.setOnCheckedChangeListener(this);
        cbNotification.setOnCheckedChangeListener(this);
        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cbLocation.isChecked() && cbCamera.isChecked()){
                    Intent intent = new Intent(getContext(),RegisterActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(Fragment_FirstActivity_Setting.this.getContext(),
                            "Required Must be Filled",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch(requestCode){
            case REQ_CAMERA :
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    cbCamera.setChecked(true);
                }else{
                    cbCamera.setChecked(false);
                }
                break;
            case REQ_LOCATION :
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    cbLocation.setChecked(true);
                }else{
                    cbLocation.setChecked(false);
                }
                break;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        switch (compoundButton.getId()){
            case R.id.cbCamera:
                if(cbCamera.isChecked()){
                    if (ActivityCompat.checkSelfPermission(getActivity(),
                            Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                        requestPermissions(new String[]{Manifest.permission.CAMERA}, REQ_CAMERA);
                    }
                }
                break;
            case R.id.cbLocation:
                if(cbLocation.isChecked()){
                    if (ActivityCompat.checkSelfPermission(getActivity(),
                            Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQ_LOCATION);
                    }
                }
                break;
            case R.id.cbNotification:
                if(cbNotification.isChecked()){
                    FirebaseMessaging.getInstance().subscribeToTopic("notification").addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(Fragment_FirstActivity_Setting.this.getContext(),"Notification Subscribes Success",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }else {
                    FirebaseMessaging.getInstance().unsubscribeFromTopic("notification").addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(Fragment_FirstActivity_Setting.this.getContext(),"Notification is Unsubcribes",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                break;
        }
    }

    private void createChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            String CHANNEL_ID = "Channel 1";
            CharSequence name = "Channel 1";
            String description = "This is Channel 1";

            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID,name,importance);
            notificationChannel.setDescription(description);

            NotificationManager notificationManager = getActivity().getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }

}