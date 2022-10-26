package com.guojie.tools;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;

public class DYLoadingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dyloading);
    }

    public void clickDY(View view) {
        DYLoadingViewDialog dyLoadingViewDialog =new DYLoadingViewDialog(this);
        dyLoadingViewDialog.show();

        new Handler(getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                dyLoadingViewDialog.dismiss();
            }
        },10000);
    }
}