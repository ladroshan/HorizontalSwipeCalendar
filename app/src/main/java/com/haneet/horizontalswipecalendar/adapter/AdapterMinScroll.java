package com.haneet.horizontalswipecalendar.adapter;

import android.app.Activity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;


import com.haneet.horizontalswipecalendar.R;
import com.haneet.horizontalswipecalendar.RecyclerViewClickListener;
import com.haneet.horizontalswipecalendar.databinding.CalenderMntCellBinding;
import com.haneet.horizontalswipecalendar.model.calendar.MinModel;

import java.util.List;

public class AdapterMinScroll extends RecyclerView.Adapter<AdapterMinScroll.MyViewHolder> {

    private List<MinModel> list;
    private RecyclerViewClickListener listener;
    private Activity context;
    private int selectedItem = -1;

    public AdapterMinScroll(List<MinModel> list, RecyclerViewClickListener listener, Activity activity) {
        this.list = list;
        this.listener = listener;
        this.context = activity;
    }


    @NonNull
    @Override
    public AdapterMinScroll.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CalenderMntCellBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.calender_mnt_cell, parent, false);
        return new AdapterMinScroll.MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterMinScroll.MyViewHolder holder, int position) {
        if (selectedItem != -1 && selectedItem == position) {
            list.get(position).setSetSelected(true);
        } else list.get(position).setSetSelected(false);

        if (position % 2 == 0) {
            holder.binding.setIfEven(true);
        } else holder.binding.setIfEven(false);

        holder.binding.setModel(list.get(position));
        DisplayMetrics displayMetrics = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;
        holder.binding.layout.setLayoutParams(new LinearLayout.LayoutParams((width / 7), ViewGroup.LayoutParams.WRAP_CONTENT));
    }


    @Override
    public int getItemCount() {
        return list.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        private CalenderMntCellBinding binding;

        public MyViewHolder(@NonNull CalenderMntCellBinding binding) {
            super(binding.getRoot());
            this.binding = binding;


        }
    }


    public void setSelecteditem(int selecteditem) {
        Log.d("POSITION", String.valueOf(selecteditem));
        this.selectedItem = selecteditem;
        notifyItemChanged(selecteditem);
    }

    public void setUnSelecteditem(int selecteditem) {
        Log.d("POSITION", String.valueOf(selecteditem));

        notifyItemChanged(selecteditem);
    }

    public int getlastSelectedItem() {
        return selectedItem;
    }

    public String getSelectedMin() {
        if (selectedItem < list.size())
            return list.get(selectedItem).getMinute();
        else return 0 + "";
    }

}
