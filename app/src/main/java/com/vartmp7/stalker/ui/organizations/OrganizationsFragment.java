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
    public static final String TAG ="com.vartmp7.stalker.ui.organizations.OrganizationsFragment";

    private OrganizationsViewModel dashboardViewModel;
    private RecyclerView recyclerView;
    private OrganizationViewAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private Button btnAggiorna;
    ArrayList<Organizzazione> list;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel = ViewModelProviders.of(this).get(OrganizationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_organizations, container, false);
        init_data();

        dashboardViewModel.initData(new RESTOrganizationsRepository(Tools.getUnsafeOkHttpClient(),""),
                );
        recyclerView = root.findViewById(R.id.rvListaOrganizzazioni);

        setUpRecyclerView();
        dashboardViewModel.updateData();
        dashboardViewModel.getOrganizationList().observe(getViewLifecycleOwner(), new Observer<List<Organizzazione>>() {
            @Override
            public void onChanged(List<Organizzazione> list) {
//                    setUpRecyclerView(list);
                        mAdapter.notifyDataSetChanged();
            }
        });
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        btnAggiorna= root.findViewById(R.id.btnAggiorna);
        btnAggiorna.setOnClickListener(v->{
//            dashboardViewModel.updateData();
            dashboardViewModel.aggiungiOrganizzazione(new Organizzazione().setId(1).setName("UNIPD").setType("Both").setAddress("via Trieste "));
        });

//        recyclerView.addItemDecoration(new LineDividerItemDecoration(this, R.drawable.line_divider));

        return root;
    }
    private void setUpRecyclerView(){
        mAdapter = new OrganizationViewAdapter(getContext(), Navigation.findNavController(getActivity(),
                R.id.nav_host_fragment),dashboardViewModel.getOrganizationList().getValue());
        recyclerView.setAdapter(mAdapter);

    }
    private  void init_data(){
        list = new ArrayList<>();
        for (int i=0; i<10; i++){
            Organizzazione organizzazione = new Organizzazione().setId(i).setName("UNIPD"+i).setType("Both").setAddress("via Trieste "+i);
            list.add(organizzazione);
        }
    }




}
