package com.vartmp7.stalker.ui.organizations;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vartmp7.stalker.MainActivity;
import com.vartmp7.stalker.R;
import com.vartmp7.stalker.Tools;
import com.vartmp7.stalker.gsonbeans.Organizzazione;
import com.vartmp7.stalker.model.RESTOrganizationsRepository;

import java.util.ArrayList;
import java.util.List;


public class OrganizationsFragment extends Fragment {
    public static final String TAG = "com.vartmp7.stalker.ui.organizations.OrganizationsFragment";

    private OrganizationsViewModel dashboardViewModel;
    private RecyclerView recyclerView;
    private OrganizationViewAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private Button btnAggiorna;
    ArrayList<Organizzazione> list;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_organizations, container, false);

        btnAggiorna = root.findViewById(R.id.btnAggiorna);

        recyclerView = root.findViewById(R.id.rvListaOrganizzazioni);
        btnAggiorna.setOnClickListener(v -> {
//            dashboardViewModel.updateData();
            dashboardViewModel.aggiungiOrganizzazione(new Organizzazione().setId(1).setName("UNIPD").setType("Both").setAddress("via Trieste "));
        });

         dashboardViewModel = new ViewModelProvider(getActivity()).get(OrganizationsViewModel.class);

        dashboardViewModel.initData(new RESTOrganizationsRepository(Tools.getUnsafeOkHttpClient(), ""));
        init_data();
        setUpRecyclerView();
        dashboardViewModel.getOrganizationList().observe(getActivity(), new Observer<List<Organizzazione>>() {
            @Override
            public void onChanged(List<Organizzazione> list) {
                mAdapter.notifyDataSetChanged();
            }
        });




        return root;
    }

    private void setUpRecyclerView() {
        mAdapter = new OrganizationViewAdapter(getContext(), Navigation.findNavController(getActivity(),
                R.id.nav_host_fragment), dashboardViewModel.getOrganizationList().getValue());
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);

    }

    private void init_data() {
        list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Organizzazione organizzazione = new Organizzazione().setId(i).setName("UNIPD" + i).setType("Both").setAddress("via Trieste " + i);
            list.add(organizzazione);
        }
    }


}
