package com.HyperSync.hypersync.ui.adminTool;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.HyperSync.hypersync.R;

import java.util.List;

public class EmployeeRVAdapter extends RecyclerView.Adapter<EmployeeRVAdapter.ViewHolder> {



    private final List<WorkersData> mData;
    private final LayoutInflater mInflater;
    private ItemClickListener mClickListener;
//    private Context context;

    // data is passed into the constructor
    EmployeeRVAdapter(Context context, List<WorkersData> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
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


    // stores and recycles views as they are scrolled off screen
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
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    WorkersData getItem(int id) {
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
