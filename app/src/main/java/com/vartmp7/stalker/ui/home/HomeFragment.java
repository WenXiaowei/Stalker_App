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
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vartmp7.stalker.R;
import com.vartmp7.stalker.gsonbeans.Organizzazione;
import com.vartmp7.stalker.ui.organizations.OrganizationsFragment;
import com.vartmp7.stalker.ui.organizations.OrganizationsViewModel;

import java.util.ArrayList;
import java.util.List;


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
        homeViewModel =  new ViewModelProvider(getActivity()).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_status, container, false);

        init_data();
        homeViewModel.initi(list);
        mAdapter = new TrackingViewAdapter(getContext(),homeViewModel.getListaOrganizzazione().getValue());

        recyclerView = root.findViewById(R.id.trackingRecycleView);

        layoutManager = new LinearLayoutManager(getContext());

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(mAdapter);
        addingNewOrganization();

        return root;
    }

    public void addingNewOrganization(){
        Organizzazione org = (Organizzazione) getArguments().getSerializable("org");
        if (org!=null){
//            Log.d(TAG, "onCreateView: "+org);
            list.add(org);
            homeViewModel.addTrackingOrganizzazione(org);
//            mAdapter = new TrackingViewAdapter(getContext(), list);
//            recyclerView.setAdapter(mAdapter);
        }
    }

    public void init_data(){
        list = new ArrayList<>();
        for (int i=0; i<3; i++){
            Organizzazione org = new Organizzazione()
                    .setId(i)
                    .setName("Unipd "+i)
                    .setAddress("Via trieste "+i)
                    .setPreferito(false)
                    .setImage_url("https://images.unsplash.com/photo-1504639725590-34d0984388bd?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=800&q=60");
            list.add(org);
        }
    }


}
