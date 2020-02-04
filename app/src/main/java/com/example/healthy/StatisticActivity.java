package com.example.healthy;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.healthy.Dangky.Item;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.view.LineChartView;

public class StatisticActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistic);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Thống Kê ");
        actionBar.setDisplayHomeAsUpEnabled(true);

        Calendar calendar = Calendar.getInstance();
        int m = calendar.get(Calendar.MONTH)+1;
        int d = calendar.get(Calendar.DAY_OF_MONTH);

        LineChartView lineChartView = findViewById(R.id.chart);
        String[] axisData = {(d-6)+"/"+m, (d-5)+"/"+m, (d-4)+"/"+m, (d-3)+"/"+m, (d-2)+"/"+m, (d-1) +"/"+m, d+"/"+m};

        Intent intent = getIntent();
        ArrayList<Integer> Data = intent.getIntegerArrayListExtra("label");


        ArrayList<Integer> DataLabel = new ArrayList<Integer>();
        if(Data.size()>7)
        {
            for(int i=0;i<7;i++)
            {
                DataLabel.add(Data.get(Data.size()-7+i));
            }
        }


        int[] yAxisData = {0,0,0,0,0,0,0};
        if(Data.size()>7) {

            for (int i = 0; i < DataLabel.size(); i++) {
                yAxisData[i] = DataLabel.get(i) * 100 / 7;
            }
        }
        else{
            for (int i = 0; i < Data.size(); i++) {
                yAxisData[6-i] = Data.get(i) * 100 / 7;
            }
        }


        List yAxisValues = new ArrayList();
        List axisValues = new ArrayList();
        Line line = new Line(yAxisValues).setColor(Color.BLUE);

        for(int i = 0; i < axisData.length; i++){
            axisValues.add(i, new AxisValue(i).setLabel(axisData[i]));
        }

        for (int i = 0; i < yAxisData.length; i++){
            yAxisValues.add(new PointValue(i, yAxisData[i]));
        }

        List lines = new ArrayList();
        lines.add(line);
        LineChartData data = new LineChartData();
        data.setLines(lines);

        lineChartView.setLineChartData(data);
        Axis axis = new Axis();
        axis.setValues(axisValues);
        data.setAxisXBottom(axis);

        Axis yAxis = new Axis();
        data.setAxisYLeft(yAxis);

        axis.setTextSize(16);
        axis.setTextColor(Color.parseColor("#0C8F7F"));

        yAxis.setTextColor(Color.parseColor("#0C8F7F"));
        yAxis.setTextSize(16);
        yAxis.setName("Tỷ lệ hài lòng (%)");
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                onBackPressed();
                return true;

            default:break;
        }

        return super.onOptionsItemSelected(item);
    }
}
