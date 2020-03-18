package com.vartmp7.stalker.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Size;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.vartmp7.stalker.R;
import com.vartmp7.stalker.gsonbeans.Organizzazione;

import java.util.List;

/**
 * @author Xiaowei Wen, Lorenzo Taschin
 */
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

    @Size
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Organizzazione org = listOrganizzazione.get(position);

        RotateAnimation arrowOpenRotation = new RotateAnimation(0, -90f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        arrowOpenRotation.setDuration(500);
        arrowOpenRotation.setInterpolator(new LinearInterpolator());
        RotateAnimation arrowCloseRotation = new RotateAnimation(0, 90f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        arrowCloseRotation.setDuration(500);
        arrowCloseRotation.setInterpolator(new LinearInterpolator());


//        holder.ibtnPreferito.setOnDragListener(new View.OnDragListener() {
//            @Override
//            public boolean onDrag(View v, DragEvent event) {
////                Log.d("DRAGEVENT", "onDrag: " + event);
//                switch (event.getAction()) {
//                    case DragEvent.ACTION_DRAG_STARTED:
//                        Log.d("DRAGEVENT", "onDrag: started");
//                        break;
//                    case DragEvent.ACTION_DRAG_ENDED:
//
//                        Log.d("DRAGEVENT", "onDrag: ended");
//                        break;
//                }
//                return false;
//            }
//        });
//        holder.ibtnPreferito.setOnTouchListener(new View.OnTouchListener() {
//
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                if (event.getAction() == MotionEvent.ACTION_DOWN) {
//                    ClipData data = ClipData.newPlainText("", "");
//                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(holder.ibtnPreferito);
//
//                    holder.ibtnPreferito.startDrag(data, shadowBuilder, holder.ibtnPreferito, 0);
//                    holder.ibtnPreferito.setVisibility(View.INVISIBLE);
//                    return true;
//                } else {
//                    return false;
//                }
//            }
//
//        });
//        holder.ibtnPreferito.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                ClipData.Item item = new ClipData.Item((CharSequence)v.getTag());
//                String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};
//
//                ClipData dragData = new ClipData(v.getTag().toString(),mimeTypes, item);
//                View.DragShadowBuilder myShadow = new View.DragShadowBuilder(holder.ibtnPreferito);
//
//                v.startDrag(dragData,myShadow,null,0);
//                return true;
//            }
//        });


        // fixme la rotazione freccia non è corretta.
        holder.tvNomeOrganizzazione.setText(org.getName());
        holder.ibtnExpandArrow.setImageResource(R.drawable.angular_arrow_left);
        holder.ibtnExpandArrow.setOnClickListener(v -> {
            if (holder.llInformationToHide.getVisibility() != View.VISIBLE) {
                holder.ibtnExpandArrow.setImageResource(R.drawable.angular_arrow_down);
                Animation animation = AnimationUtils.loadAnimation(context, R.anim.slide_down_animation);
                holder.llTitle.setBackground(null);
                holder.llInformationToHide.setVisibility(View.VISIBLE);
                holder.llInformationToHide.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                holder.ibtnExpandArrow.startAnimation(arrowOpenRotation);
                holder.llInformationToHide.startAnimation(animation);
            } else {
                Animation animation = AnimationUtils.loadAnimation(context, R.anim.closing_animation);
                holder.ibtnExpandArrow.startAnimation(arrowCloseRotation);
                holder.ibtnExpandArrow.setImageResource(R.drawable.angular_arrow_left);
                holder.llInformationToHide.setVisibility(View.GONE);
                holder.llTitle.setBackgroundResource(R.drawable.tracking_item_title_background);
                holder.llInformationToHide.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0));
                holder.llInformationToHide.startAnimation(animation);
            }
        });

//        holder.tvElencoLuoghi =
        holder.tvIndirizzo.setText(org.getAddress());
        holder.ibtnPreferito.setImageResource(org.getPreferito() ? R.drawable.icon_preferito_si : R.drawable.icon_preferito_no);

        holder.ibtnPreferito.setOnClickListener(v -> {
            holder.ibtnPreferito.setImageResource(!org.getPreferito() ? R.drawable.icon_preferito_si : R.drawable.icon_preferito_no);
            RotateAnimation rotate1 = new RotateAnimation(0, 216, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            rotate1.setDuration(500);
            rotate1.setInterpolator(new LinearInterpolator());
            holder.ibtnPreferito.startAnimation(rotate1);
            org.setPreferito(!org.getPreferito());
//            notifyItemChanged(position);

//            Animation slidingOut = AnimationUtils.loadAnimation(context, R.anim.sliding_out_anim);
//            holder.cvTrackingitem.setVisibility(View.INVISIBLE);
//            holder.cvTrackingitem.startAnimation(slidingOut);
////            holder.llTrackingitem.setVisibility(View.INVISIBLE);
//            listOrganizzazione.remove(position);
//
//

//


        });


    }

    @Override
    public int getItemCount() {
        return listOrganizzazione.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvNomeOrganizzazione, tvStatusCorrente, tvElencoLuoghi, tvIndirizzo;
        Button btnTracciami, btnLoginLDAP;
        ImageButton ibtnPreferito, ibtnExpandArrow;
        Switch sAnonimo;
        CardView cvTrackingitem;
        LinearLayout llInformationToHide, llTitle;

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
            cvTrackingitem = v.findViewById(R.id.llTrackingItem);
            llInformationToHide = v.findViewById(R.id.llHidingInformation);
            llTitle = v.findViewById(R.id.llTitle);
            ibtnExpandArrow = v.findViewById(R.id.ibtnExpand);
        }
    }

}
