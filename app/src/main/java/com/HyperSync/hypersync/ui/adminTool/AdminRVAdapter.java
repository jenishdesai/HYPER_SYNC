package com.HyperSync.hypersync.ui.adminTool;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.HyperSync.hypersync.R;

import java.util.List;

public class AdminRVAdapter extends RecyclerView.Adapter<AdminRVAdapter.ViewHolder> {

    private final List<WorkersData> mData;
    private final LayoutInflater mInflater;
    private AdminRVAdapter.ItemClickListener mClickListener;

    AdminRVAdapter(Context context, List<WorkersData> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    @NonNull
    @Override
    public AdminRVAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recycler_admin, parent, false);
        return new AdminRVAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AdminRVAdapter.ViewHolder holder, int position) {
        WorkersData employee = mData.get(position);
        holder.myText1.setText(employee.mGmail);
        holder.myText2.setText(employee.mId);
        holder.myText3.setText(employee.mDesignation);

    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView myText1, myText2, myText3;

        ViewHolder(View itemView) {
            super(itemView);
            myText1 = itemView.findViewById(R.id.tvgmail);
            myText2 = itemView.findViewById(R.id.tvID);
            myText3 = itemView.findViewById(R.id.tvDesignation);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mClickListener != null) mClickListener.onItemClick(v, getAdapterPosition());
        }
    }

    WorkersData getItem(int id) {
        return mData.get(id);
    }

    void setClickListener(AdminRVAdapter.ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}