package com.HyperSync.hypersync;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Dataadapter extends RecyclerView.Adapter<Dataadapter.dataholder> {

    String[] city;

    Activity activity;
    public Dataadapter(Activity activity, String[] city) {
        this.activity = activity;
        this.city=city;

    }

    @NonNull
    @Override
    public dataholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.announcement_rv, parent, false);
        return new dataholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull dataholder holder, int position) {
        holder.textView.setText(city[position]);
    }

    @Override
    public int getItemCount() {
        return city.length;
    }

    class dataholder extends RecyclerView.ViewHolder {

        TextView textView;

        public dataholder(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.announcement);
        }
    }
}