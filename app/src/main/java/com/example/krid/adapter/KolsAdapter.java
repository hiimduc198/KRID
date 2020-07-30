package com.example.krid.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.krid.R;
import com.example.krid.model.Kol;

import java.util.ArrayList;


public class KolsAdapter extends RecyclerView.Adapter<KolsAdapter.ViewHolder> {
    private Activity activity;
    private ArrayList<Kol> kolList;

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtName;
        private TextView txtDescription;
        private ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.kols_name);
            txtDescription = itemView.findViewById(R.id.kols_description);
            image = itemView.findViewById(R.id.kols_image);
        }
    }
     public KolsAdapter(Activity activity, ArrayList<Kol> kolList){
        this.kolList = kolList;
        this.activity = activity;

     }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(activity);
        View kolItemView = inflater.inflate(R.layout.item_kols, parent, false);
        ViewHolder viewHolder = new ViewHolder(kolItemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull KolsAdapter.ViewHolder holder, int position) {
        final Kol Kol = kolList.get(position);
        holder.txtName.setText(Kol.getName());
        holder.txtDescription.setText(Kol.getDescription());

        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
            }
        });

    }

    @Override
    public int getItemCount() {return kolList.size(); }
}
