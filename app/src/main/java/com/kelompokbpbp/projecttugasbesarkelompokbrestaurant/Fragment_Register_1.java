package com.kelompokbpbp.projecttugasbesarkelompokbrestaurant;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.button.MaterialButton;

public class Fragment_Register_1 extends Fragment {
    private OnActivityChanged onActivityChanged;
    private MaterialButton btnContinue;

    public Fragment_Register_1() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        onActivityChanged = (OnActivityChanged) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment__register_1, container, false);
        btnContinue = view.findViewById(R.id.btnContinue);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onActivityChanged.onContinueRegisterClicked();
            }
        });
    }

    public interface OnActivityChanged{
        void onContinueRegisterClicked();
    }
}