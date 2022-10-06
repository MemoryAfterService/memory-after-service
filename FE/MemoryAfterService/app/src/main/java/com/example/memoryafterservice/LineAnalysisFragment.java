package com.example.memoryafterservice;

import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.memoryafterservice.dto.Line;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.renderer.XAxisRenderer;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class LineAnalysisFragment extends Fragment {
    private BarChart barChart;
    private View view;
    private JSONArray json;
    private ArrayList<Line> words;

    public LineAnalysisFragment() {
        // Required empty public constructor
        super(R.layout.fragment_line_analysis);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_line_analysis, container, false);
        barChart = view.findViewById(R.id.ChattingAnalysisBar);
        String rawJSON = getActivity().getIntent().getStringExtra("line");
        try{
            json = new JSONArray(rawJSON);

            words = new ArrayList<>();

            initBarChart();
            showBarChart();
        } catch (JSONException e) {
            e.printStackTrace();

            Toast.makeText(getContext(), "데이터가 없습니다.", Toast.LENGTH_SHORT).show();
        }
        return view;
    }

    private void initBarChart(){
        //hiding the grey background of the chart, default false if not set
        barChart.setDrawGridBackground(false);
        //remove the bar shadow, default false if not set
        barChart.setDrawBarShadow(false);
        //remove border of the chart, default false if not set
        barChart.setDrawBorders(false);

        //remove the description label text located at the lower right corner
        Description description = new Description();
        description.setEnabled(false);
        barChart.setDescription(description);

        //setting animation for y-axis, the bar will pop up from 0 to its value within the time we set
        barChart.animateY(1000);
        //setting animation for x-axis, the bar will pop up separately within the time we set
        barChart.animateX(1000);

        barChart.setTouchEnabled(false);
        barChart.setExtraBottomOffset(25f);


        XAxis xAxis = barChart.getXAxis();
        //change the position of x-axis to the bottom
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        //set the horizontal distance of the grid line
        xAxis.setGranularity(1f);
        //hiding the x-axis line, default true if not set
        xAxis.setDrawAxisLine(true);
        //hiding the vertical grid lines, default true if not set
        xAxis.setDrawGridLines(false);


        xAxis.setTextSize(15f);

        YAxis leftAxis = barChart.getAxisLeft();
        //hiding the left y-axis line, default true if not set
        leftAxis.setDrawAxisLine(false);

        YAxis rightAxis = barChart.getAxisRight();
        //hiding the right y-axis line, default true if not set
        rightAxis.setDrawAxisLine(false);

        Legend legend = barChart.getLegend();
        //setting the shape of the legend form to line, default square shape
        legend.setForm(Legend.LegendForm.LINE);
        //setting the text size of the legend
        legend.setTextSize(20f);
        //setting the alignment of legend toward the chart
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        //setting the stacking direction of legend
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        //setting the location of legend outside the chart, default false if not set
        legend.setDrawInside(false);

    }

    private void initBarDataSet(BarDataSet barDataSet){
        //initializing colors for the entries
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.parseColor("#304567"));
        colors.add(Color.parseColor("#309967"));
        colors.add(Color.parseColor("#476567"));
        colors.add(Color.parseColor("#890567"));
        colors.add(Color.parseColor("#a35567"));
        colors.add(Color.parseColor("#ff5f67"));
        colors.add(Color.parseColor("#3ca567"));
        //Changing the color of the bar
        barDataSet.setColors(colors);
        //Setting the size of the form in the legend
        barDataSet.setFormSize(24f);
        //showing the value of the bar, default true if not set
        //setting the text size of the value of the bar
        barDataSet.setValueTextSize(18f);
    }

    private void showBarChart(){
        ArrayList<Double> yAxis = new ArrayList<Double>();
        ArrayList<String> entry =  new ArrayList<>();
        ArrayList<BarEntry> entries = new ArrayList<>();
        String title = "WordCount";


        // input data 넣기
        try {
            System.out.println();
            for (int i = 1; i< json.length(); i++) {
                JSONObject h = json.getJSONObject(i);
                words.add(new Line(
                        h.getString("date"),
                        h.getString("user_name"),
                        h.getString("room_name"),
                        h.getString("hour"),
                        Integer.parseInt(h.getString("count"))));
            }

            words.sort((word, t1) -> t1.count - word.count);

            // 상위 6개만 표시
            for (int i = 0; i < 6; i++) {
                yAxis.add((double) words.get(i).count);
                entry.add(words.get(i).name);
            }
            // entry 입력
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //fit the data into a bar
        for (int i = 0; i < yAxis.size(); i++) {
            BarEntry barEntry = new BarEntry(i, yAxis.get(i).floatValue());
            entries.add(barEntry);
        }

        BarDataSet barDataSet = new BarDataSet(entries, title);

        initBarDataSet(barDataSet);
        BarData data = new BarData(barDataSet);

        barChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(entry));

        barChart.setData(data);
        barChart.invalidate();
    }

    public class CustomXAxisRenderer extends XAxisRenderer {
        public CustomXAxisRenderer(ViewPortHandler viewPortHandler, XAxis xAxis, Transformer trans) {
            super(viewPortHandler, xAxis, trans);
        }

        @Override
        protected void drawLabel(Canvas c, String formattedLabel, float x, float y, MPPointF anchor, float angleDegrees) {
            String line[] = formattedLabel.split("\n");
            Utils.drawXAxisValue(c, line[0], x, y, mAxisLabelPaint, anchor, angleDegrees);
            Utils.drawXAxisValue(c, line[1], x + mAxisLabelPaint.getTextSize(), y + mAxisLabelPaint.getTextSize(), mAxisLabelPaint, anchor, angleDegrees);
        }
    }
}