package com.vartmp7.stalker.ui.organizations;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.vartmp7.stalker.R;
import com.vartmp7.stalker.component.gsonbeans.Organizzazione;

import java.util.List;

/**
 * @author Xiaowei Wen, Lorenzo Taschin
 */
public class OrganizationAdapter extends RecyclerView.Adapter<OrganizationAdapter.ViewHolder> {
    private List<Organizzazione> listaOrganizzazione;
    private Context context;
    private static int currentPosition = -1;

    public OrganizationAdapter(Context context, List<Organizzazione> list) {
        listaOrganizzazione = list;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_organization_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.btnTrackMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = viewHolder.getAdapterPosition();
                Organizzazione org = listaOrganizzazione.get(position);

//                NavController navController = Navigation.findNavController(parent);
//                Bundle b = new Bundle();
//                b.putLong("ID_ORG", org.getId());
//                navController.saveState();
//                navController.navigate(R.id.navigation_status, b);
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Organizzazione org = listaOrganizzazione.get(position);

        holder.nomeOrganizzazione.setText(org.getName() + " " + org.getId());
        holder.tipoOrganizzazione.setText("tipo: " + org.getType());
        holder.tvIndirizzo.setText(org.getOrgInfo());


        if (currentPosition == position && holder.llHidingInfo.getVisibility()==View.INVISIBLE) {
            Animation slideDown = AnimationUtils.loadAnimation(context, R.anim.slide_down_animation);
            holder.llHidingInfo.setVisibility(View.VISIBLE);
            holder.tvIndirizzo.setVisibility(View.VISIBLE);
            holder.llHidingInfo.startAnimation(slideDown);
        }else if(currentPosition==position && holder.llHidingInfo.getVisibility()==View.VISIBLE){
            Animation closing = AnimationUtils.loadAnimation(context, R.anim.closing_animation);
            holder.llHidingInfo.setVisibility(View.INVISIBLE);
            holder.tvIndirizzo.setVisibility(View.INVISIBLE);
            holder.llHidingInfo.startAnimation(closing);
            currentPosition=-1;
        }
        holder.nomeOrganizzazione.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //getting the position of the item to expand it
                currentPosition = position;

                //reloding the list
                notifyDataSetChanged();
            }
        });
//            holder.numeroLuoghi = org.get
    }

    @Override
    public int getItemCount() {
        return listaOrganizzazione.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView nomeOrganizzazione;
        TextView tipoOrganizzazione, tvIndirizzo;
        Button btnTrackMe;
        LinearLayout llHidingInfo;

        public ViewHolder(@NonNull View itemView) {
            // dati dell'organizzazione
            super(itemView);
            nomeOrganizzazione = itemView.findViewById(R.id.tvNomeOrganizzazione);
            tipoOrganizzazione = itemView.findViewById(R.id.tvTipoOrganizzazione);
            btnTrackMe = itemView.findViewById(R.id.btnTrackMe);
            tvIndirizzo = itemView.findViewById(R.id.tvIndirizzo);
            llHidingInfo = itemView.findViewById(R.id.llHidingInformation);

        }
    }


}
