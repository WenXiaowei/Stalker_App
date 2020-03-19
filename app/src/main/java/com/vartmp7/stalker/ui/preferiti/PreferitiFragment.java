package com.vartmp7.stalker.ui.preferiti;

import android.opengl.Visibility;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.FirebaseFirestore;
import com.vartmp7.stalker.R;
import com.vartmp7.stalker.gsonbeans.Organizzazione;
import com.vartmp7.stalker.model.FavoritesRepository;
import com.vartmp7.stalker.model.FirebaseFavoritesRepository;
import com.vartmp7.stalker.model.OrganizationsRepository;
import com.vartmp7.stalker.model.RESTOrganizationsRepository;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;


/**
 * @author Xiaowei Wen, Lorenzo Taschin
 */
public class PreferitiFragment extends Fragment {
    public static final String TAG ="com.vartmp7.stalker.ui.preferiti.PreferitiFragment";

    private PreferitiViewModel favViewModel;
    private PreferitiViewAdapter favViewAdapter;
    private RecyclerView favRecyclerView;
    private ProgressBar favPbLoading;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_preferiti, container, false);

        this.favRecyclerView = (RecyclerView) root.findViewById(R.id.preferitiRecyclerView);
        this.favPbLoading = (ProgressBar) root.findViewById(R.id.pb_loadingPreferiti);
        /*favViewModel =
                  new ViewModelProvider(getActivity()).get(PreferitiViewModel.class);*/
         //TODO le seguenti righe vanno riviste
        OkHttpClient httpClient= new OkHttpClient();
        String serverUrl="";
        OrganizationsRepository orgRepo = new RESTOrganizationsRepository(httpClient,serverUrl);
        FavoritesRepository preferitiRepository = new FirebaseFavoritesRepository("1",orgRepo, FirebaseFirestore.getInstance());
        //fine del todo

        this.favViewModel = new PreferitiViewModel(preferitiRepository);

        //final TextView textView = root.findViewById(R.id.text_notifications);



        showProgressBar();
        favViewModel.init();
        favViewModel.getOrganizzazioni().observe(getViewLifecycleOwner(), new Observer<List<Organizzazione>>() {
            @Override
            public void onChanged(List<Organizzazione> organizzazioni) {
                Log.d(TAG,"organizzazioni:");
                favViewAdapter.setOrganizzazioni(organizzazioni);
                organizzazioni.forEach(o->Log.d(TAG,"o:"+o.getId()));
                favViewAdapter.notifyDataSetChanged();
                hideProgressBar();
            }
        });



        initRecyclerView();
        return root;
    }

    private void initRecyclerView(){
        if(favViewModel.getOrganizzazioni().getValue()==null) Log.d(TAG,"è null!");
       favViewAdapter = new PreferitiViewAdapter(getContext(), new ArrayList<Organizzazione>()/*favViewModel.getOrganizzazioni().getValue()*/);
        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        favRecyclerView.setLayoutManager(linearLayoutManager);
        favRecyclerView.setAdapter(favViewAdapter);
    }

    private void showProgressBar(){
        favPbLoading.setVisibility(View.VISIBLE);
    }
    private void hideProgressBar(){
        favPbLoading.setVisibility(View.GONE);
    }


}
