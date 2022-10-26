package com.guojie.tools;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.bottomsheet.BottomSheetDialog;

//从底部滑出布局
public class BottomSheetDialogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_sheet_dialog);


    }

    public void click(View view) {
        initDialog();
    }

    private void initDialog() {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetDialog.setContentView(R.layout.dialog_goods_sku_layout);
        //给布局设置透明背景色，让图片突出来
        bottomSheetDialog.getDelegate().findViewById(R.id.design_bottom_sheet)
                .setBackgroundColor(getResources().getColor(android.R.color.transparent));
        bottomSheetDialog.show();
    }
}