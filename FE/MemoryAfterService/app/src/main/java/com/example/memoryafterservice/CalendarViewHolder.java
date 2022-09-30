package com.example.memoryafterservice;


import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CalendarViewHolder extends RecyclerView.ViewHolder{
    public final TextView dayOfMonth;

    View parentView;

    public CalendarViewHolder(@NonNull View itemView){
        super(itemView);
        dayOfMonth = itemView.findViewById(R.id.cellDayText);
        parentView = itemView.findViewById(R.id.parentView);
    }
}