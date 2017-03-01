package com.zq.weatherchart;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.zq.weatherchart.widget.PinChart;
import com.zq.weatherchart.widget.WeatherItem;
import com.zq.weatherchart.widget.WeatnerChartView;

import java.util.ArrayList;

/****
 * 饼状图和天气折线图
 */
public class MainActivity extends AppCompatActivity {

    private WeatnerChartView chart1;
    private WeatnerChartView chart2;
    private PinChart pinChart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }
    private void initView()
    {
        chart1= (WeatnerChartView) findViewById(R.id.weather_char1);
        chart2= (WeatnerChartView) findViewById(R.id.weather_char2);
        pinChart= (PinChart) findViewById(R.id.pinchart);
        initData();
    }
    private void initData()
    {
        ArrayList<WeatherItem> list= new ArrayList<WeatherItem>();
        list.add(new WeatherItem("",18));
        list.add(new WeatherItem("",40));
        list.add(new WeatherItem("", -1));
        list.add(new WeatherItem("",1));
        list.add(new WeatherItem("",6));
        list.add(new WeatherItem("",2));
        list.add(new WeatherItem("", 33));
        chart1.SetTuView(list, "最高温度：");//单位: 摄氏度
        chart1.invalidate();
        ArrayList<WeatherItem> list1= new ArrayList<WeatherItem>();
        list1.add(new WeatherItem("",1));
        list1.add(new WeatherItem("",15));
        list1.add(new WeatherItem("", -6));
        list1.add(new WeatherItem("",-2));
        list1.add(new WeatherItem("", 3));
        list1.add(new WeatherItem("",-1));
        list1.add(new WeatherItem("",11));
        chart2.SetTuView(list1, "最低温度：");
        chart2.invalidate();
    }

}
