package com.example.memoryafterservice;


import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.memoryafterservice.utils.CalendarUtil;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;

public class CalendarAdapter extends RecyclerView.Adapter<CalendarViewHolder> {

    int selectedPosition=-1;

    private final ArrayList<LocalDate> daysOfMonth;
    private HashSet<LocalDate> pickedDate = new HashSet<LocalDate>();

    public CalendarAdapter(ArrayList<LocalDate> daysOfMonth) {
        this.daysOfMonth = daysOfMonth;
    }

    @NonNull
    @Override
    public CalendarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.calendar_cell, parent, false);
        // 화면에 달력을 맞추기 위한 코드
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = (int) (parent.getHeight() * 0.166666666);
        return new CalendarViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CalendarViewHolder holder, int position) {
        LocalDate day = daysOfMonth.get(position);
        if(selectedPosition==position)
            holder.itemView.setBackgroundColor(Color.parseColor("#2A9EAD"));
//        else
//            holder.itemView.setBackgroundColor(Color.parseColor("#FFFFFFF"));

        if(day == null){
            holder.dayOfMonth.setText("");
        }else{
            holder.dayOfMonth.setText(String.valueOf(day.getDayOfMonth()));

            if(day.equals(CalendarUtil.selectedDate) && selectedPosition==-1){
//                holder.parentView.setBackgroundColor(Color.rgb(236, 236, 236));
                holder.itemView.setBackgroundColor(Color.rgb(236, 236, 236));
            }
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedPosition=position;
                notifyDataSetChanged();
                if (day != null) {
                    if (pickedDate.size() < 2) {
                        pickedDate.add(day);
                    } else {
                        pickedDate.clear();
                        pickedDate.add(day);
                    }
                }
//                int iYear = day.getYear();
//                int iMonth = day.getMonthValue();
//                int iDay = day.getDayOfMonth();
//
//                String yearMonDay = iYear + "년" + iMonth + "월" + iDay + "일";
//                Toast.makeText(holder.itemView.getContext(), yearMonDay, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return daysOfMonth.size();
    }

    public HashSet<LocalDate> getPicked() {
        return pickedDate;
    }
}