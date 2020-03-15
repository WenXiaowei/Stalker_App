package com.vartmp7.stalker.ui.organizations;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.google.gson.internal.$Gson$Preconditions;
import com.vartmp7.stalker.MainActivity;
import com.vartmp7.stalker.R;
import com.vartmp7.stalker.component.gsonbeans.Organizzazione;
import com.vartmp7.stalker.ui.home.HomeFragment;

import java.util.List;

public class OrganizationAdapter  extends RecyclerView.Adapter<OrganizationAdapter.ViewHolder> {
    private List<Organizzazione> listaOrganizzazione;
    public OrganizationAdapter(List<Organizzazione> list){
            listaOrganizzazione= list;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_organization_list, parent, false);
        ViewHolder viewHolder=  new ViewHolder(view);
        viewHolder.tvTrackMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                NavController navController = Navigation.findNavController(parent);
                Bundle b = new Bundle();
//                aggiungere dati in b

                navController.navigate(R.id.navigation_status, b);

                int position = viewHolder.getAdapterPosition();


                Organizzazione org = listaOrganizzazione.get(position);
                Snackbar.make(parent, org.getName(), Snackbar.LENGTH_SHORT).show();
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            Organizzazione org = listaOrganizzazione.get(position);

            holder.nomeOrganizzazione.setText(org.getName());
            holder.tipoOrganizzazione.setText(org.getType());
//            holder.numeroLuoghi = org.get
    }

    @Override
    public int getItemCount() {
        return listaOrganizzazione.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView nomeOrganizzazione;
        TextView tipoOrganizzazione;
        Button tvTrackMe;
        public ViewHolder(@NonNull View itemView) {
            // dati dell'organizzazione
            super(itemView);
            nomeOrganizzazione = itemView.findViewById(R.id.tvNomeOrganizzazione);
            tipoOrganizzazione = itemView.findViewById(R.id.tvTipoOrganizzazione);
            tvTrackMe = itemView.findViewById(R.id.btnTrackMe);
        }
    }


}
