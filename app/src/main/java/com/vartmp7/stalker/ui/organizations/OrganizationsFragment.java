package com.vartmp7.stalker.ui.organizations;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vartmp7.stalker.R;
import com.vartmp7.stalker.component.gsonbeans.Organizzazione;

import java.util.ArrayList;


public class OrganizationsFragment extends Fragment {

    private OrganizationsViewModel dashboardViewModel;
    private RecyclerView recyclerView;
    private OrganizationAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    ArrayList<Organizzazione> list;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(OrganizationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_organizations, container, false);

        init_data();


        mAdapter = new OrganizationAdapter(list);
        recyclerView = root.findViewById(R.id.rvListaOrganizzazioni);

        layoutManager = new LinearLayoutManager(getContext());

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(mAdapter);


        return root;
    }

    private  void init_data(){
        list = new ArrayList<>();
        Organizzazione organizzazione = new Organizzazione().setName("UNIPD").setType("Both");
        list.add(organizzazione);
        organizzazione = new Organizzazione().setName("UNIPD2").setType("Both");
        list.add(organizzazione);
        organizzazione = new Organizzazione().setName("UNIPD3").setType("Both");
        list.add(organizzazione);
        organizzazione = new Organizzazione().setName("UNIPD3").setType("Both");
        list.add(organizzazione);
        organizzazione = new Organizzazione().setName("UNIPD3").setType("Both");
        list.add(organizzazione);
        organizzazione = new Organizzazione().setName("UNIPD3").setType("Both");
        list.add(organizzazione);
        organizzazione = new Organizzazione().setName("UNIPD3").setType("Both");
        list.add(organizzazione);
        organizzazione = new Organizzazione().setName("UNIPD3").setType("Both");
        list.add(organizzazione);
        organizzazione = new Organizzazione().setName("UNIPD3").setType("Both");
        list.add(organizzazione);
        organizzazione = new Organizzazione().setName("UNIPD3").setType("Both");
        list.add(organizzazione);
        organizzazione = new Organizzazione().setName("UNIPD3").setType("Both");
        list.add(organizzazione);
        organizzazione = new Organizzazione().setName("UNIPD3").setType("Both");
        list.add(organizzazione);
        organizzazione = new Organizzazione().setName("UNIPD3").setType("Both");
        list.add(organizzazione);
        organizzazione = new Organizzazione().setName("UNIPD3").setType("Both");
        list.add(organizzazione);
        organizzazione = new Organizzazione().setName("UNIPD3").setType("Both");
        list.add(organizzazione);
        organizzazione = new Organizzazione().setName("UNIPD3").setType("Both");
        list.add(organizzazione);
        organizzazione = new Organizzazione().setName("UNIPD3").setType("Both");
        list.add(organizzazione);
        organizzazione = new Organizzazione().setName("UNIPD3").setType("Both");
        list.add(organizzazione);
        organizzazione = new Organizzazione().setName("UNIPD3").setType("Both");
        list.add(organizzazione);
        organizzazione = new Organizzazione().setName("UNIPD3").setType("Both");
        list.add(organizzazione);
        organizzazione = new Organizzazione().setName("UNIPD3").setType("Both");
        list.add(organizzazione);
        organizzazione = new Organizzazione().setName("UNIPD3").setType("Both");
        list.add(organizzazione);
        organizzazione = new Organizzazione().setName("UNIPD3").setType("Both");
        list.add(organizzazione);
        organizzazione = new Organizzazione().setName("UNIPD3").setType("Both");
        list.add(organizzazione);
        organizzazione = new Organizzazione().setName("UNIPD3").setType("Both");
        list.add(organizzazione);
        organizzazione = new Organizzazione().setName("UNIPD3").setType("Both");
        list.add(organizzazione);
        organizzazione = new Organizzazione().setName("UNIPD3").setType("Both");
        list.add(organizzazione);
        organizzazione = new Organizzazione().setName("UNIPD3").setType("Both");
        list.add(organizzazione);
        organizzazione = new Organizzazione().setName("UNIPD3").setType("Both");
        list.add(organizzazione);
        organizzazione = new Organizzazione().setName("UNIPD3").setType("Both");
        list.add(organizzazione);
        organizzazione = new Organizzazione().setName("UNIPD3").setType("Both");
        list.add(organizzazione);
        organizzazione = new Organizzazione().setName("UNIPD3").setType("Both");
        list.add(organizzazione);
    }
}
