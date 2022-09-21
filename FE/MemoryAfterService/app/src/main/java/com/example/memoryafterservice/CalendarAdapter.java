package com.example.memoryafterservice;


import android.graphics.Color;
import android.icu.util.LocaleData;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.util.ArrayList;

public class CalendarAdapter extends RecyclerView.Adapter<CalendarViewHolder> {

    private final ArrayList<LocalDate> daysOfMonth;

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

        if(day == null){
            holder.dayOfMonth.setText("");
        }else{
            holder.dayOfMonth.setText(String.valueOf(day.getDayOfMonth()));

            if(day.equals(CalendarUtil.selectedDate)){
                holder.parentView.setBackgroundColor(Color.rgb(236, 236, 236));
            }
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int iYear = day.getYear();
                int iMonth = day.getMonthValue();
                int iDay = day.getDayOfMonth();

                String yearMonDay = iYear + "년" + iMonth + "월" + iDay + "일";
                Toast.makeText(holder.itemView.getContext(), yearMonDay, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return daysOfMonth.size();
    }

    public interface OnItemListener {
        void onItemClick(String dayText);
    }
}