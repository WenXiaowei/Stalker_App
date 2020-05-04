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

package com.vartmp7.stalker.ui.history;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.vartmp7.stalker.MainActivity;
import com.vartmp7.stalker.R;
import com.vartmp7.stalker.datamodel.Organization;
import com.vartmp7.stalker.datamodel.TrackRecord;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @author Xiaowei Wen, Lorenzo Taschin
 */
public class HistoryFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    public static final String TAG = "com.vartmp7.stalker.ui.cronologia.CronologiaFragment";

    private HistoryViewModel historyViewModel;
    private RecyclerView organizationRecyclerView;
    private SwipeRefreshLayout refreshLayout;
    private TrackRecordAdapter adapter;
    private TextView tvMessageBox;
    private Spinner sPlaceNames;

    private List<String> placeNames;
    private List<TrackRecord> trackRecords;
    private LinearLayout llFilter;
    private Button btnRemoveFilter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_cronologia, container, false);
        historyViewModel = new ViewModelProvider(requireActivity()).get(HistoryViewModel.class);
        historyViewModel.init(MainActivity.repository);
        organizationRecyclerView = v.findViewById(R.id.rvListaOrganizzazioni);
        refreshLayout = v.findViewById(R.id.swipeToRefresh);
        tvMessageBox = v.findViewById(R.id.tvMessageBox);
        sPlaceNames = v.findViewById(R.id.sPlace);
        llFilter = v.findViewById(R.id.llFilter);

        btnRemoveFilter = v.findViewById(R.id.btnRemoveFilter);
        btnRemoveFilter.setOnClickListener(view->{
            sPlaceNames.setSelection(0);
            view.setEnabled(false);
        });

        sPlaceNames.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                btnRemoveFilter.setEnabled(position!=0);
                if (position == 0) {
                    adapter.updateTracks(trackRecords);
                    return;
                }
                adapter.updateTracks(trackRecords.stream().filter(trackRecord -> trackRecord.getPlaceName().equals(placeNames.get(position))).collect(Collectors.toList()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        historyViewModel.getTrackRecords().observe(getViewLifecycleOwner(), records -> {
            if (historyViewModel.getOrganizations().getValue().stream().noneMatch(Organization::isLogged)) {
                tvMessageBox.setText(R.string.devi_loggare_con_ldap);
                llFilter.setVisibility(View.GONE);
            } else if (historyViewModel.getTrackRecords().getValue().size() == 0) {
                tvMessageBox.setText(R.string.non_ci_sono_tracciamenti);
                llFilter.setVisibility(View.GONE);
            } else {
                llFilter.setVisibility(View.VISIBLE);
                tvMessageBox.setText(R.string.storico_tracciamenti);
                adapter.updateTracks(records);
                trackRecords = records;
                initSpinnerData(trackRecords);
            }

            refreshLayout.setRefreshing(false);
        });

        initOraganizationList();

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        onRefresh();
    }

    private void initSpinnerData(@NotNull List<TrackRecord> list) {
        placeNames = new ArrayList<>();
        placeNames.add("Selezione un luogo");
        list.forEach(trackRecord -> placeNames.add(trackRecord.getPlaceName()));
        placeNames =  placeNames.stream().distinct().collect(Collectors.toList());
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(requireContext(), android.R.layout.simple_spinner_dropdown_item,
                placeNames);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        sPlaceNames.setAdapter(adapter);

    }

    private void initOraganizationList() {
        adapter = new TrackRecordAdapter(new ArrayList<>());
        organizationRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        organizationRecyclerView.setAdapter(adapter);
    }


    @Override
    public void onRefresh() {
        if (historyViewModel.getOrganizations().getValue().stream().anyMatch(Organization::isLogged)) {
            refreshLayout.setRefreshing(true);
            historyViewModel.updateTrackHistories();
        } else {
            refreshLayout.setEnabled(false);
            Toast.makeText(requireContext(), R.string.devi_loggare_con_ldap, Toast.LENGTH_SHORT).show();
        }
    }
}
