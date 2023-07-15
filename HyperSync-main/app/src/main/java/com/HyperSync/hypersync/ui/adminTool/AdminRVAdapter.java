package com.HyperSync.hypersync.ui.adminTool;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.HyperSync.hypersync.model.Worker;
import com.HyperSync.hypersync.R;

import java.util.List;

public class AdminRVAdapter extends RecyclerView.Adapter<AdminRVAdapter.ViewHolder> {

    private List<Worker> mData;
    private final LayoutInflater mInflater;
    private AdminRVAdapter.ItemClickListener mClickListener;

    AdminRVAdapter(Context context, List<Worker> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    public void updateData(List<Worker> list) {
        mData = list;
    }

    @NonNull
    @Override
    public AdminRVAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recycler_admin, parent, false);
        return new AdminRVAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AdminRVAdapter.ViewHolder holder, int position) {
        Worker employee = mData.get(position);
        holder.myText1.setText(employee.getEmail());
        holder.myText2.setText(employee.getId());

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView myText1, myText2;

        ViewHolder(View itemView) {
            super(itemView);
            myText1 = itemView.findViewById(R.id.tvgmail);
            myText2 = itemView.findViewById(R.id.tvID);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mClickListener != null) mClickListener.onItemClick(v, getAdapterPosition());
        }
    }

    Worker getItem(int id) {
        return mData.get(id);
    }

    void setClickListener(AdminRVAdapter.ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}