package com.guojie.tools;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.Toast;

import com.guojie.tools.tabmenu.Fragment1;
import com.guojie.tools.tabmenu.Fragment2;
import com.guojie.tools.tabmenu.Fragment3;
import com.guojie.tools.tabmenu.Fragment4;
import com.guojie.tools.tabmenu.TabMenu;
import com.zheng.bottommenus.bean.Menu;
import com.zheng.bottommenus.view.BottomMenusView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }



}