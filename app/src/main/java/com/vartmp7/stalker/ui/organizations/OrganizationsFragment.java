package com.vartmp7.stalker.ui.organizations;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.vartmp7.stalker.R;
import com.vartmp7.stalker.Tools;

import com.vartmp7.stalker.gsonbeans.Organizzazione;
import com.vartmp7.stalker.repository.FileOrganizationsLocalSource;
import com.vartmp7.stalker.repository.OrganizationsLocalSource;
import com.vartmp7.stalker.repository.OrganizationsRepository;
import com.vartmp7.stalker.repository.OrganizationsWebSource;
import com.vartmp7.stalker.repository.RESTOrganizationsWebSource;

import java.util.ArrayList;
import java.util.List;

public class OrganizationsFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    public static final String TAG = "com.vartmp7.stalker.ui.organizations.OrganizationsFragment";

    private OrganizationsViewModel organizzazioneViewModel;
    private RecyclerView recyclerView;
    private OrganizationViewAdapter mAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private MutableLiveData<List<Organizzazione>> list;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        list = new MutableLiveData<>();
        list.setValue(new ArrayList<>());
        OrganizationsLocalSource localSource = new FileOrganizationsLocalSource("orgs.json", getContext(),list);

        OrganizationsWebSource webSource = new RESTOrganizationsWebSource(Tools.getUnsafeOkHttpClient(), list,"https://asdasd.com");
        OrganizationsRepository repository = new OrganizationsRepository(getViewLifecycleOwner(), localSource, webSource);
        organizzazioneViewModel = new ViewModelProvider(getActivity()).get(OrganizationsViewModel.class);
        organizzazioneViewModel.initData(repository);
//        organizzazioneViewModel.refresh();

        View root = inflater.inflate(R.layout.fragment_organizations, container, false);
        swipeRefreshLayout = root.findViewById(R.id.srfl);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeColors(Color.BLACK, Color.GREEN, Color.MAGENTA);

        recyclerView = root.findViewById(R.id.rvListaOrganizzazioni);

        setUpRecyclerView();
        organizzazioneViewModel.getOrganizationList().observe(getActivity(), list -> {
//            Log.d(TAG,"arrivo qua"+list);
            mAdapter.notifyDataSetChanged();
            mAdapter.setData(list);
            swipeRefreshLayout.setRefreshing(false);
        });
//        onRefresh();
        return root;
    }

    private void setUpRecyclerView() {
        mAdapter = new OrganizationViewAdapter(getContext(), Navigation.findNavController(getActivity(),
                R.id.nav_host_fragment), organizzazioneViewModel.getOrganizationList().getValue());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(true);
        organizzazioneViewModel.refresh();
    }

}
