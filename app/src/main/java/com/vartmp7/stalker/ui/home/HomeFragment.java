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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vartmp7.stalker.R;
import com.vartmp7.stalker.component.gsonbeans.Organizzazione;
import com.vartmp7.stalker.ui.organizations.OrganizationAdapter;

import java.util.ArrayList;


/**
 * @author Xiaowei Wen, Lorenzo Taschin
 */
public class HomeFragment extends Fragment {

    private final static String TAG = "com.vartmp7.stalker.ui.home.HomeFragment";

    private HomeViewModel homeViewModel;
    private String id_newOrg;

    private TrackingViewAdapter mAdapter;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;

    private ArrayList<Organizzazione> list;
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle b) {


        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_status, container, false);
//        final TextView textView = root.findViewById(R.id.text_home);
//        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        init_data();
        mAdapter = new TrackingViewAdapter(getContext(),list);
        recyclerView = root.findViewById(R.id.trackingRecycleView);

        layoutManager = new LinearLayoutManager(getContext());

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(mAdapter);

        return root;
    }
    public void init_data(){
        list = new ArrayList<>();
        for (int i=0; i<3; i++){
            Organizzazione org = new Organizzazione()
                    .setName("Unipd"+i)
                    .setAddress("Via trieste"+i)
                    .setPreferito(false);
            list.add(org);
        }
    }
}
