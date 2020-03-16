package com.vartmp7.stalker.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vartmp7.stalker.R;
import com.vartmp7.stalker.component.gsonbeans.Organizzazione;
import com.vartmp7.stalker.ui.organizations.OrganizationAdapter;

import java.util.List;

public class TrackingViewAdapter extends RecyclerView.Adapter<TrackingViewAdapter.ViewHolder> {
    private List<Organizzazione> listOrganizzazione;
    private Context context;

    public TrackingViewAdapter(Context context, List<Organizzazione> list) {
        super();
        listOrganizzazione = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_tracking, parent, false);
        TrackingViewAdapter.ViewHolder viewHolder = new TrackingViewAdapter.ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Organizzazione org = listOrganizzazione.get(position);

        holder.tvNomeOrganizzazione.setText(org.getName());
//        holder.tvElencoLuoghi =
        holder.tvIndirizzo.setText(org.getAddress());
        holder.ibtnPreferito.setImageResource(org.getPreferito() ? R.drawable.icon_preferito_si : R.drawable.icon_preferito_no);

        holder.ibtnPreferito.setOnClickListener(v -> {
            holder.ibtnPreferito.setImageResource(!org.getPreferito() ? R.drawable.icon_preferito_si : R.drawable.icon_preferito_no);
            org.setPreferito(!org.getPreferito());


        });


    }

    @Override
    public int getItemCount() {
        return listOrganizzazione.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvNomeOrganizzazione, tvStatusCorrente, tvElencoLuoghi, tvIndirizzo;
        Button btnTracciami, btnLoginLDAP;
        ImageButton ibtnPreferito;
        Switch sAnonimo;
        LinearLayout llTrackingitem;


        public ViewHolder(@NonNull View v) {
            super(v);
            tvIndirizzo = v.findViewById(R.id.tvIndirizzo);
            tvNomeOrganizzazione = v.findViewById(R.id.tvNomeOrganizzazione);
            tvStatusCorrente = v.findViewById(R.id.tvCurrentStatus);
            tvElencoLuoghi = v.findViewById(R.id.tvElencoLuoghi);
            btnTracciami = v.findViewById(R.id.btnTrackMe);
            btnLoginLDAP = v.findViewById(R.id.btnLoginLDAP);
            ibtnPreferito = v.findViewById(R.id.ibtnAddToPreferiti);
            sAnonimo = v.findViewById(R.id.sAnonymousSwitch);
            llTrackingitem = v.findViewById(R.id.llTrackingItem);
        }
    }

}
