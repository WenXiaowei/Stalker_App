package com.vartmp7.stalker.ui.organizations;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.vartmp7.stalker.R;
import com.vartmp7.stalker.gsonbeans.Organizzazione;

import java.util.List;

/**
 * @author Xiaowei Wen, Lorenzo Taschin
 */
public class OrganizationViewAdapter extends RecyclerView.Adapter<OrganizationViewAdapter.ViewHolder> {
    public static final String TAG = "com.vartmp7.stalker.ui.organizations.OrganizationAdapter";
    private List<Organizzazione> listaOrganizzazione;
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
        int colorIndex = position % 5;
        Glide.with(context)
                .setDefaultRequestOptions(new RequestOptions().error(R.drawable.icon_stalker))
                .load(listaOrganizzazione.get(position).getImage_url())
                .into(holder.ivIconOrganizzazione);

//        holder.llHidingInfo.setAlpha(0.9f);
//        holder.llIconName.setAlpha(0.9f);
//        holder.llHidingInfo.setBackgroundColor(colors[colorIndex]);
//        holder.llIconName.setBackgroundColor(colors[colorIndex]);

        holder.nomeOrganizzazione.setText(org.getName());
        holder.tipoOrganizzazione.setText(org.getType());
        holder.tvIndirizzo.setText(org.getAddress());
        holder.tvElencoLuoghi.setText("\naaaaaaaaaaa\naaaaaaaaaaaaaaa\naaaaaaaaaaaaaa\naaaaaaaaaaaaaaaaa\naaaaaaaaaaaaaaaaaa\naaaaaaaaaaaaa");
        holder.btnTrackMe.setOnClickListener(v -> {
//                int position = holder.getAdapterPosition();
//                Organizzazione org = listaOrganizzazione.get(position);
                Bundle b = new Bundle();
                b.putSerializable("org", org);
                navController.navigate(R.id.action_navigation_organizations_to_navigation_status,b);

        });

        holder.btnShowDetails.setOnClickListener(v->{
            if (holder.llHidingInfo.getVisibility() == View.GONE) {
                Animation slideDown = AnimationUtils.loadAnimation(context, R.anim.slide_down_animation);
                slideDown.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        holder.llHidingInfo.setVisibility(View.VISIBLE);
                        ((Button) v).setText(R.string.nascondi);
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                holder.llHidingInfo.startAnimation(slideDown);
            } else {
                Animation closing = AnimationUtils.loadAnimation(context, R.anim.closing_animation);
                closing.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {}
                    @Override
                    public void onAnimationEnd(Animation animation) {
                        holder.llHidingInfo.setVisibility(View.GONE);
                        ((Button) v).setText(R.string.dettagli);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {}
                });
                holder.llHidingInfo.startAnimation(closing);
            }
        });

        if (currentPosition == position && holder.llHidingInfo.getVisibility() == View.INVISIBLE) {
            Animation slideDown = AnimationUtils.loadAnimation(context, R.anim.slide_down_animation);
            slideDown.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    holder.llHidingInfo.setVisibility(View.VISIBLE);
                    holder.tvIndirizzo.setVisibility(View.VISIBLE);
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            holder.llHidingInfo.startAnimation(slideDown);
        } else if (currentPosition == position && holder.llHidingInfo.getVisibility() == View.VISIBLE) {

            currentPosition = -1;
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
        TextView tipoOrganizzazione, tvIndirizzo, tvElencoLuoghi;
        Button btnTrackMe, btnShowDetails;
        LinearLayout llHidingInfo,llIconName;

        ImageView ivIconOrganizzazione;

        ViewHolder(@NonNull View itemView) {
            // dati dell'organizzazione
            super(itemView);
            nomeOrganizzazione = itemView.findViewById(R.id.tvNomeOrganizzazione);
            tipoOrganizzazione = itemView.findViewById(R.id.tvTipoOrganizzazione);
            btnTrackMe = itemView.findViewById(R.id.btnTrackMe);
            tvIndirizzo = itemView.findViewById(R.id.tvIndirizzo);
            llHidingInfo = itemView.findViewById(R.id.llHidingInformation);
            ivIconOrganizzazione = itemView.findViewById(R.id.ivIconOrganizzazione);
            btnShowDetails= itemView.findViewById(R.id.btnShowDetails);
            llIconName = itemView.findViewById(R.id.llIconName);
            tvElencoLuoghi = itemView.findViewById(R.id.tvElencoLuoghi);

        }
    }


}
