/*
 * MIT License
 *
 * Copyright (c) 2020 VarTmp7
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

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
import com.vartmp7.stalker.repository.OrganizationsRepository;

public class OrganizationsFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    public static final String TAG = "com.vartmp7.stalker.ui.organizations.OrganizationsFragment";

    private OrganizationsViewModel organizzazioneViewModel;
    private RecyclerView recyclerView;
    private OrganizationViewAdapter mAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private OrganizationsRepository organizationsRepository;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        int actionId = OrganizationsFragmentDirections.actionNavigationOrganizationsToNavigationTracking().getActionId();

//        NavDirections navDirections = OrganizationsFragmentDirections.actionNavigationOrganizationsToNavigationTracking();


    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_organizations, container, false);
        organizzazioneViewModel = new ViewModelProvider(requireActivity()).get(OrganizationsViewModel.class);

        organizzazioneViewModel.initData(MainActivity.repository);


        //organizationsRepository.getPreferiti();

        swipeRefreshLayout = root.findViewById(R.id.srfl);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeColors(Color.BLACK, Color.GREEN, Color.MAGENTA);
        recyclerView = root.findViewById(R.id.rvListaOrganizzazioni);

        setUpRecyclerView();
        organizzazioneViewModel.getOrganizationList().observe(getViewLifecycleOwner(), lista -> {
//            Log.e(TAG, "onCreateView: triggered");
//            Log.e(TAG, "stampa organizzazioni fetchate: ");
//            lista.forEach(o-> Log.e(TAG, "onCreateView: "+o.getId()+" name "+o.getName()+" isPreferito"+o.isFavorite()));
            mAdapter.setOrganizations(lista);
            mAdapter.notifyDataSetChanged();
            swipeRefreshLayout.setRefreshing(false);
        });

        return root;
    }

    private void setUpRecyclerView() {
        mAdapter = new OrganizationViewAdapter(getContext(), organizzazioneViewModel, Navigation.findNavController(requireActivity(),
                R.id.nav_host_fragment));
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);
        organizzazioneViewModel.refresh();
    }

    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(true);
        organizzazioneViewModel.refresh();
    }

}
