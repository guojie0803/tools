package com.guojie.tools.percentage;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.guojie.tools.R;

import java.util.ArrayList;
import java.util.List;

public class PercentageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_percentagr);


        PercentView percentView =  findViewById(R.id.percentView);

        // 添加的是颜色
        List<Integer> colorList = new ArrayList<>();
        colorList.add(Color.RED);
        colorList.add(Color.BLUE);
        colorList.add(Color.BLACK);
        colorList.add(Color.GRAY);

        //  添加的是百分比
        List<Float> rateList = new ArrayList<>();
        rateList.add(10f);
        rateList.add(20f);
        rateList.add(30f);
        rateList.add(40f);
        percentView.setShow(colorList, rateList,false,true);
    }
}