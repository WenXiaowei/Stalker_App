package com.vartmp7.stalker.ui.organizations;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.vartmp7.stalker.R;
import com.vartmp7.stalker.Tools;

import com.vartmp7.stalker.gsonbeans.Organizzazione;
import com.vartmp7.stalker.model.FileOrganizationsLocalSource;
import com.vartmp7.stalker.model.OrganizationsLocalSource;
import com.vartmp7.stalker.model.OrganizationsRepository;
import com.vartmp7.stalker.model.OrganizationsWebSource;
import com.vartmp7.stalker.model.RESTOrganizationsWebSource;

import java.util.ArrayList;

import okhttp3.OkHttpClient;

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
        OrganizationsLocalSource localSource = new FileOrganizationsLocalSource("orgs.json",getContext());
//        OkHttpClient httpClient = new OkHttpClient();
        OrganizationsWebSource webSource = new RESTOrganizationsWebSource(Tools.getUnsafeOkHttpClient(),"https://asdasd.com");
        OrganizationsRepository repository = new OrganizationsRepository(getViewLifecycleOwner(),localSource,webSource);
        organizzazioneViewModel = new ViewModelProvider(getActivity()).get(OrganizationsViewModel.class);
        organizzazioneViewModel.initData(repository);

        organizzazioneViewModel.refresh();
        View root = inflater.inflate(R.layout.fragment_organizations, container, false);
        swipeRefreshLayout = root.findViewById(R.id.srfl);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeColors(Color.BLACK,
                Color.GREEN, Color.MAGENTA);


        recyclerView = root.findViewById(R.id.rvListaOrganizzazioni);


        recyclerView = root.findViewById(R.id.rvListaOrganizzazioni);

        organizzazioneViewModel = new ViewModelProvider(getActivity()).get(OrganizationsViewModel.class);

        organizzazioneViewModel.initData(new OrganizationsRepository(getViewLifecycleOwner(),
                new FileOrganizationsLocalSource("orgs.json", getContext()),
                new RESTOrganizationsWebSource(Tools.getUnsafeOkHttpClient(),"https://casdasd")));


        setUpRecyclerView();
        organizzazioneViewModel.getOrganizationList().observe(getActivity(), list -> {
            mAdapter.notifyDataSetChanged();

            mAdapter.setData(list);
            swipeRefreshLayout.setRefreshing(false);
        });
        Log.e(TAG,"ou"+Thread.currentThread().getId());
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
        organizzazioneViewModel.refresh();
    }

}
