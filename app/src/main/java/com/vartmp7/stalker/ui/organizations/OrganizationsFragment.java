package com.vartmp7.stalker.ui.organizations;

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

import com.vartmp7.stalker.R;
import com.vartmp7.stalker.gsonbeans.Organizzazione;
import com.vartmp7.stalker.model.FileOrganizationsLocalSource;
import com.vartmp7.stalker.model.OrganizationsLocalSource;
import com.vartmp7.stalker.model.OrganizationsRepository;
import com.vartmp7.stalker.model.OrganizationsWebSource;
import com.vartmp7.stalker.model.RESTOrganizationsWebSource;

import java.util.ArrayList;

import okhttp3.OkHttpClient;


public class OrganizationsFragment extends Fragment {
    public static final String TAG = "com.vartmp7.stalker.ui.organizations.OrganizationsFragment";

    private OrganizationsViewModel dashboardViewModel;
    private RecyclerView recyclerView;
    private OrganizationViewAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private Button btnAggiorna;
    ArrayList<Organizzazione> list;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        Log.d(TAG,"ciaoo");


        OrganizationsLocalSource localSource = new FileOrganizationsLocalSource("orgs.json",getContext());
        OkHttpClient httpClient = new OkHttpClient();
        OrganizationsWebSource webSource = new RESTOrganizationsWebSource(httpClient,"asd");
        OrganizationsRepository repository = new OrganizationsRepository(getViewLifecycleOwner(),localSource,webSource);
        dashboardViewModel = new ViewModelProvider(getActivity()).get(OrganizationsViewModel.class);
        dashboardViewModel.initData(repository);

        View root = inflater.inflate(R.layout.fragment_organizations, container, false);

        btnAggiorna = root.findViewById(R.id.btnAggiorna);

        recyclerView = root.findViewById(R.id.rvListaOrganizzazioni);
        btnAggiorna.setOnClickListener(v -> {
            Log.d(TAG,"refresh");
            Toast.makeText(getContext(), "aggiornate", Toast.LENGTH_SHORT).show();
            dashboardViewModel.refresh();
            //dashboardViewModel.updateData();
            mAdapter.notifyDataSetChanged();
//            dashboardViewModel.aggiungiOrganizzazione(new Organizzazione().setId(1).setName("UNIPD").setType("Both").setAddress("via Trieste "));
            recyclerView.smoothScrollToPosition(dashboardViewModel.getOrganizationList().getValue().size()-1);
        });



        dashboardViewModel.aggiungiOrganizzazione(new Organizzazione().setId(13232));

        //init_data();
        setUpRecyclerView();
        dashboardViewModel.getOrganizationList().observe(getActivity(), list ->{
            Log.d(TAG,"orgs");
            list.forEach(o->Log.d(TAG,"org:"+o));
            mAdapter.setData(list);
            mAdapter.notifyDataSetChanged();
        });

        Log.e(TAG,"ou"+Thread.currentThread().getId());
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
