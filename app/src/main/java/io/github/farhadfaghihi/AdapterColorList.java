package io.github.farhadfaghihi;

import android.support.v4.widget.TextViewCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.security.PublicKey;
import java.util.ArrayList;

/**
 * Created by farhad on 1/18/17.
 */

public class AdapterColorList extends RecyclerView.Adapter<AdapterColorList.ViewHolderColors> {

    private ArrayList<ColorData> listColorData ;
    private OnColorItemListener listener ;

    public AdapterColorList(ArrayList<ColorData> listColorData,OnColorItemListener listener){

        this.listColorData = listColorData ;
        this.listener = listener ;
    }

    @Override
    public ViewHolderColors onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem_colors,parent,false) ;

        return new ViewHolderColors(view) ;
    }

    @Override
    public void onBindViewHolder(ViewHolderColors holder, final int position) {

        holder.tvColorTitle.setText(listColorData.get(position).getTitle());

        holder.coloredView.setBackgroundColor(listColorData.get(position).getColor());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(listener != null){

                    listener.onColorItemClick(listColorData.get(position));
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return listColorData.size();
    }

    static class ViewHolderColors extends RecyclerView.ViewHolder {

        private CardView cardView ;
        private View coloredView ;
        private TextView tvColorTitle ;

        public ViewHolderColors(View itemView) {
            super(itemView);

            cardView = (CardView)itemView.findViewById(R.id.cardview) ;
            coloredView = itemView.findViewById(R.id.coloredView) ;
            tvColorTitle = (TextView)itemView.findViewById(R.id.tvColorTitle) ;
        }
    }

    public interface OnColorItemListener {

        void onColorItemClick(ColorData colorData) ;
    }
}
