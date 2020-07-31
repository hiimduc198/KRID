package com.example.krid.adapter;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.krid.R;
import com.example.krid.model.Kol;
import com.example.krid.ui.AdvertiserActivity;
import com.example.krid.ui.BookingDetailFragment;


import java.util.ArrayList;

public class BookKolsAdvAdapter extends RecyclerView.Adapter<BookKolsAdvAdapter.ViewHolder> {
    private Activity activity;
    private ArrayList<Kol> kolList;

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtName;
        private TextView txtDescription;
        private Button btnCall;
        private ImageView imgKol;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            btnCall = itemView.findViewById(R.id.btnCall);
            txtName = itemView.findViewById(R.id.txtName);
            txtDescription = itemView.findViewById(R.id.txtDescription);
            imgKol = itemView.findViewById(R.id.imgKol);
        }
    }

    public BookKolsAdvAdapter(Activity activity, ArrayList<Kol> kolList) {
        this.activity = activity;
        this.kolList = kolList;
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
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final Kol kol = kolList.get(position);
        holder.txtName.setText(kol.getName());
        holder.txtDescription.setText(kol.getDescription());

        Glide.with(activity)
                .load(kol.getImage())
                .fitCenter()
                .into(holder.imgKol);

        holder.imgKol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((AdvertiserActivity)activity).navigateToFragmentWithArgs(new BookingDetailFragment(), kol);
            }
        });

        holder.btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                call(activity, holder.itemView, kol.getPhone());
            }
        });sa
    }

    @Override
    public int getItemCount() {
        return kolList.size();
    }

    public void call(Activity activity, View view, String phoneNumber) {
        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            // You can use the API that requires the permission.
            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumber));
            activity.startActivity(intent);

        } else if (activity.shouldShowRequestPermissionRationale(Manifest.permission.CALL_PHONE)) {
            // In an educational UI, explain to the user why your app requires this
            // permission for a specific feature to behave as expected. In this UI,
            // include a "cancel" or "no thanks" button that allows the user to
            // continue using your app without granting the permission.
            // showInContextUI(...);
        } else {
            // You can directly ask for the permission.
            activity.requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, 400);
        }
    }

}
