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

package com.vartmp7.stalker.ui.favorite;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.firebase.auth.FirebaseAuth;
import com.vartmp7.stalker.MainActivity;
import com.vartmp7.stalker.R;
import com.vartmp7.stalker.component.NotLogged;
import com.vartmp7.stalker.datamodel.Organization;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import lombok.AccessLevel;
import lombok.Getter;


/**
 * @author Xiaowei Wen, Lorenzo Taschin
 */
public class FavoritesFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    public static final String TAG = "com.vartmp7.stalker.ui.preferiti.PreferitiFragment";

    private FavoritesViewModel favViewModel;
    private FavoritesViewAdapter favViewAdapter;
    private RecyclerView favRecyclerView;
    @Getter(AccessLevel.PUBLIC)
    private MutableLiveData<List<Organization>> listMutableLiveData;
    private SwipeRefreshLayout preferitiSwipeLayout;

    private boolean isUserLogged() {
        return FirebaseAuth.getInstance().getCurrentUser() != null || GoogleSignIn.getLastSignedInAccount(requireContext()) != null;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_preferiti, container, false);
        if (!isUserLogged()) {
            TextView tv = root.findViewById(R.id.tvHeaderFavorites);
            tv.setText(R.string.should_be_logged_for_favorites);
            return root;
        }
        listMutableLiveData = new MutableLiveData<>(new ArrayList<>());

        this.favRecyclerView = root.findViewById(R.id.preferitiRecyclerView);

//        OrganizationsRepository orgRepo = OrganizationsRepository.getInstance();

        this.favViewModel = new ViewModelProvider(requireActivity()).get(FavoritesViewModel.class);

        preferitiSwipeLayout = root.findViewById(R.id.srflPreferiti);
        preferitiSwipeLayout.setOnRefreshListener(this);

        favViewModel.init(MainActivity.repository);
        initRecyclerView();

        favViewModel.getOrganizzazioni().observe(getViewLifecycleOwner(), organizzazioni -> {
            List<Organization> orgs = organizzazioni;

//            Log.e(TAG,"trigger");
//            organizzazioni
//                    .stream()
//                    .filter(Organization::isFavorite)
//                    .forEach(o-> Log.d(TAG, "org: "+o.getId()+" "+o.getName()+" "+o.isTracking()));
            favViewAdapter.setOrganizzazioni(organizzazioni.stream().filter(Organization::isFavorite).distinct().collect(Collectors.toList()));
//            Log.e(TAG," triggered");
            preferitiSwipeLayout.setRefreshing(false);
        });


        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                Organization o = favViewAdapter.getOrganizationAt(viewHolder.getAdapterPosition());
                int id;
                try {
                    favViewModel.removeFromPreferiti(o);
                    favViewAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
                    id = R.string.organizzazione_removed_from_favorite;
                } catch (NotLogged notLogged) {
                    id = R.string.not_logged_yet;
//                    notLogged.printStackTrace();
                }


                Toast.makeText(requireContext(), getString(id), Toast.LENGTH_SHORT).show();

            }
        }).attachToRecyclerView(favRecyclerView);
        return root;
    }

    private void initRecyclerView() {
//        if (favViewModel.getOrganizzazioni().getValue() == null) Log.d(TAG, "è null!");
        favViewAdapter = new FavoritesViewAdapter(getContext(), favViewModel,
                new ArrayList<>(), Navigation.findNavController(requireActivity(),
                R.id.nav_host_fragment));
        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        favRecyclerView.setLayoutManager(linearLayoutManager);
        favRecyclerView.setAdapter(favViewAdapter);
    }

    @Override
    public void onRefresh() {
//        preferitiSwipeLayout.setRefreshing(true);
        favViewModel.refresh();
//        Log.e(TAG, "onRefresh: ");
    }
}
