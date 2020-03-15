package com.vartmp7.stalker.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.vartmp7.stalker.R;


public class HomeFragment extends Fragment {

    private final static String TAG = "com.vartmp7.stalker.ui.home.HomeFragment";

    private HomeViewModel homeViewModel;
    private String id_newOrg;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle b) {


        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_status, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle b) {
        super.onViewCreated(view, b);
        if (b != null) {
            id_newOrg = b.getString("ID_ORG");
            Log.d(TAG, "onCreateView: " + id_newOrg);
        }
    }
}
