package com.vartmp7.stalker.ui.organizations;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vartmp7.stalker.MainActivity;
import com.vartmp7.stalker.R;
import com.vartmp7.stalker.gsonbeans.Organizzazione;

import java.util.ArrayList;


public class OrganizationsFragment extends Fragment {
    public static final String TAG ="com.vartmp7.stalker.ui.organizations.OrganizationsFragment";

    private OrganizationsViewModel dashboardViewModel;
    private RecyclerView recyclerView;
    private OrganizationViewAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    ArrayList<Organizzazione> list;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(OrganizationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_organizations, container, false);

        init_data();



        NavController navController = Navigation.findNavController(MainActivity.activity, R.id.nav_host_fragment);


        mAdapter = new OrganizationViewAdapter(getContext(),navController,list);
        recyclerView = root.findViewById(R.id.rvListaOrganizzazioni);

        layoutManager = new LinearLayoutManager(getContext());

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(mAdapter);



//        recyclerView.addItemDecoration(new LineDividerItemDecoration(this, R.drawable.line_divider));

        return root;
    }

    private  void init_data(){
        list = new ArrayList<>();
        for (int i=0; i<10; i++){
            Organizzazione organizzazione = new Organizzazione().setId(i).setName("UNIPD"+i).setType("Both").setAddress("via Trieste "+i);
            list.add(organizzazione);
        }
    }


}
