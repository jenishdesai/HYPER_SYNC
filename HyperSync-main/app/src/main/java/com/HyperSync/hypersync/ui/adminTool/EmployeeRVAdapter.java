package com.HyperSync.hypersync.ui.adminTool;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.HyperSync.hypersync.model.Worker;
import com.HyperSync.hypersync.R;

import java.util.List;

public class EmployeeRVAdapter extends RecyclerView.Adapter<EmployeeRVAdapter.ViewHolder> {

    private List<Worker> mData;
    private final LayoutInflater mInflater;
    private ItemClickListener mClickListener;
//    private Context context;

    // data is passed into the constructor
    EmployeeRVAdapter(Context context, List<Worker> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    public void updateData(List<Worker> list) {
        mData = list;
    }

    // inflates the row layout from xml when needed
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recycler_employee, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Worker employee = mData.get(position);
        holder.myText1.setText(employee.getEmail());
        holder.myText2.setText(employee.getId());

    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView myText1, myText2;

        ViewHolder(View itemView) {
            super(itemView);
            myText1 = itemView.findViewById(R.id.tvgmail);
            myText2 = itemView.findViewById(R.id.tvID);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    Worker getItem(int id) {
        return mData.get(id);
    }

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
