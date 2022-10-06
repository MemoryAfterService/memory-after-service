package com.example.memoryafterservice;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.memoryafterservice.dto.DayTalk;
import com.example.memoryafterservice.dto.Line;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class DaytalkAnalysisFragment extends Fragment {
    private PieChart pieChart;
    private View view;
    private JSONArray json;
    private ArrayList<DayTalk> words;

    public DaytalkAnalysisFragment() {
        // Required empty public constructor
        super(R.layout.fragment_daytalk_analysis);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_daytalk_analysis, container, false);
        pieChart = view.findViewById(R.id.ConsumptionAnalysisPie);
        String rawJSON = getActivity().getIntent().getStringExtra("dayTalk");
        try{
            json = new JSONArray(rawJSON);

            words = new ArrayList<>();

            initPieChart();
            showPieChart();
        } catch (JSONException e) {
            e.printStackTrace();

            Toast.makeText(getContext(), "데이터가 없습니다.", Toast.LENGTH_SHORT).show();
        }

        return view;
    }

    private void initPieChart(){
        pieChart.setHoleRadius(30.0f);

        //remove the description label on the lower left corner, default true if not set
        pieChart.getDescription().setEnabled(false);
        pieChart.setUsePercentValues(false);

        //enabling the user to rotate the chart, default true
        pieChart.setRotationEnabled(true);
        //adding friction when rotating the pie chart
        pieChart.setDragDecelerationFrictionCoef(0.9f);
        //setting the first entry start from right hand side, default starting from top
        pieChart.setRotationAngle(0);

        //highlight the entry when it is tapped, default true if not set
        pieChart.setHighlightPerTapEnabled(true);
        //adding animation so the entries pop up from 0 degree
        pieChart.animateY(1400, Easing.EaseInOutQuad);
        //setting the color of the hole in the middle, default white
        pieChart.setHoleColor(Color.parseColor("#FFFFFF"));

    }

    private void showPieChart(){

        ArrayList<PieEntry> pieEntries = new ArrayList<>();
        String label = "type";
        HashMap<String, Integer> map = new HashMap<>();
//        String title = "가장 많이 말한 사람";
//        pieChart.setCenterText(title);
//        pieChart.setCenterTextSize(20f);
//        pieChart.setDrawCenterText(true);
        // input data 넣기
        try {
            for (int i = 1; i< json.length(); i++) {
                JSONObject h = json.getJSONObject(i);
                words.add(new DayTalk(
                        h.getString("user_name"),
                        Integer.parseInt(h.getString("count"))));
            }

            // 상위 6개만 표시
            for (int i = 0; i < 6; i++) {
                map.put(words.get(i).name, words.get(i).count);
            }
            // entry 입력
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //initializing colors for the entries
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.parseColor("#304567"));
        colors.add(Color.parseColor("#309967"));
        colors.add(Color.parseColor("#476567"));
        colors.add(Color.parseColor("#890567"));
        colors.add(Color.parseColor("#a35567"));
        colors.add(Color.parseColor("#ff5f67"));
        colors.add(Color.parseColor("#3ca567"));

        //input data and fit data into pie chart entry
        for(String type: map.keySet()){
            pieEntries.add(new PieEntry(map.get(type).floatValue(), type));
        }

        //collecting the entries with label name
        PieDataSet pieDataSet = new PieDataSet(pieEntries,label);

        //setting text size of the value
        pieDataSet.setValueTextSize(20f);
        //providing color list for coloring different entries
        pieDataSet.setColors(colors);
        //grouping the data set from entry to chart
        PieData pieData = new PieData(pieDataSet);
        //using percentage as values instead of amount
        pieData.setValueFormatter(new PercentFormatter(pieChart));
        pieChart.setUsePercentValues(true);
        //showing the value of the entries, default true if not set
        pieData.setDrawValues(true);

        pieChart.setData(pieData);
        pieChart.invalidate();
    }
}