package com.vartmp7.stalker.ui.organizations;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.vartmp7.stalker.MainActivity;
import com.vartmp7.stalker.R;
import com.vartmp7.stalker.Tools;
import com.vartmp7.stalker.model.RESTOrganizationsRepository;


public class OrganizationsFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    public static final String TAG = "com.vartmp7.stalker.ui.organizations.OrganizationsFragment";

    private OrganizationsViewModel organizzazioneViewModel;
    private RecyclerView recyclerView;
    private OrganizationViewAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_organizations, container, false);
        swipeRefreshLayout = root.findViewById(R.id.srfl);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeColors(Color.BLACK,
                Color.GREEN, Color.MAGENTA);




        recyclerView = root.findViewById(R.id.rvListaOrganizzazioni);
//        btnAggiorna.setOnClickListener(v -> {
//            organizzazioneViewModel.aggiungiOrganizzazione(new Organizzazione().setId(1).setName("UNIPD").setType("Both").setAddress("via Trieste "));
//            mAdapter.notifyDataSetChanged();
//        });

        organizzazioneViewModel = new ViewModelProvider(getActivity()).get(OrganizationsViewModel.class);

        organizzazioneViewModel.initData(new RESTOrganizationsRepository(Tools.getUnsafeOkHttpClient(), "http://adasdasd"));

        setUpRecyclerView();
        organizzazioneViewModel.getOrganizationList().observe(getActivity(), list -> {
            mAdapter.notifyDataSetChanged();
            swipeRefreshLayout.setRefreshing(false);
        });
        return root;
    }

    private void setUpRecyclerView() {
        mAdapter = new OrganizationViewAdapter(getContext(), Navigation.findNavController(getActivity(),
                R.id.nav_host_fragment), organizzazioneViewModel.getOrganizationList().getValue());
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);
    }


    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(true);
        organizzazioneViewModel.updateData();
    }

}
