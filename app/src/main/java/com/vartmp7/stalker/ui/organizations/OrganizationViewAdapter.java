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
import androidx.lifecycle.LiveData;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;

import com.vartmp7.stalker.R;
import com.vartmp7.stalker.gsonbeans.Organizzazione;

import java.util.List;

/**
 * @author Xiaowei Wen, Lorenzo Taschin
 */
public class OrganizationViewAdapter extends RecyclerView.Adapter<OrganizationViewAdapter.ViewHolder> {
    public static final String TAG ="com.vartmp7.stalker.ui.organizations.OrganizationAdapter";
    private  List<Organizzazione> listaOrganizzazione;
    private Context context;
    private static int currentPosition = -1;
    private NavController navController;
    public OrganizationViewAdapter(Context context, NavController controller, List<Organizzazione> list) {
        this.listaOrganizzazione = list;
        this.context = context;
        this.navController = controller;
    }

    public void setData(List<Organizzazione> newData){
        this.listaOrganizzazione = newData;
        this.notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_organization_list, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Organizzazione org = listaOrganizzazione.get(position);

        holder.nomeOrganizzazione.setText(org.getName() + " " + org.getId());
        holder.tipoOrganizzazione.setText("tipo: " + org.getType());
        holder.tvIndirizzo.setText(org.getOrgInfo());
        holder.btnTrackMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Organizzazione org = listaOrganizzazione.get(position);

                Bundle b = new Bundle();
                b.putSerializable("org", org);
                navController.navigate(R.id.action_navigation_organizations_to_navigation_status,b);

            }
        });

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
               // notifyDataSetChanged();
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
