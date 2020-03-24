package com.vartmp7.stalker.ui.tracking;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vartmp7.stalker.R;
import com.vartmp7.stalker.gsonbeans.Organizzazione;

import java.util.ArrayList;


/**
 * @author Xiaowei Wen, Lorenzo Taschin
 */
public class TrackingFragment extends Fragment {

    private final static String TAG = "com.vartmp7.stalker.ui.home.HomeFragment";

    private TrackingViewModel trackingViewModel;
    private String id_newOrg;

    private TrackingViewAdapter mAdapter;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;

    private ArrayList<Organizzazione> list;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (list == null) {
            init_data();
        }
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle b) {
        View root = inflater.inflate(R.layout.fragment_status, container, false);
        recyclerView = root.findViewById(R.id.trackingRecycleView);

        trackingViewModel = new ViewModelProvider(requireActivity()).get(TrackingViewModel.class);
        trackingViewModel.initi(list);

        mAdapter = new TrackingViewAdapter(getContext(), trackingViewModel.getListaOrganizzazione().getValue());
        trackingViewModel.getListaOrganizzazione().observe(getViewLifecycleOwner(), list -> mAdapter.notifyDataSetChanged());

        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);
        addingNewOrganization();
        return root;
    }

    public void addingNewOrganization() {
        if (getArguments() != null) {

            Organizzazione org = (Organizzazione) getArguments().getSerializable("org");
            if (org != null) {
//            Log.d(TAG, "onCreateView: "+org);
                trackingViewModel.addTrackingOrganizzazione(org);
//            mAdapter = new TrackingViewAdapter(getContext(), list);
//            recyclerView.setAdapter(mAdapter);
            }
        }
    }

    public void init_data() {
        list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Organizzazione org = new Organizzazione()
                    .setId(i)
                    .setName("UNIPD " + i)
                    .setAddress("Via trieste " + i)
                    .setPreferito(false)
                    .setImage_url("https://images.unsplash.com/photo-1504639725590-34d0984388bd?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=800&q=60");
            list.add(org);
        }
    }


}
