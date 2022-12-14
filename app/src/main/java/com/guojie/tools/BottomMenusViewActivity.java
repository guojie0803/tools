package com.guojie.tools;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.zheng.bottommenus.bean.Menu;
import com.zheng.bottommenus.view.BottomMenusView;

import java.util.ArrayList;
import java.util.List;


//Android 仿微信底部菜单栏实现
public class BottomMenusViewActivity extends AppCompatActivity {

    private BottomMenusView bottomMenusView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_menus_view);

        initBottomMenus();
    }

    public void initBottomMenus() {
        bottomMenusView = findViewById(R.id.view_bottom_menus);
        List<Menu> listMenu = new ArrayList<>();
        listMenu.add(new Menu("路飞", R.drawable.lufei));
        listMenu.add(new Menu("罗宾", R.drawable.luobin));
        listMenu.add(new Menu("索隆", R.drawable.suolong));
        listMenu.add(new Menu("布鲁克", R.drawable.buluke));
        listMenu.add(new Menu("雷利", R.drawable.leili));
        listMenu.add(new Menu("萨波", R.drawable.sabo));
        listMenu.add(new Menu("路飞", R.drawable.lufei));
        listMenu.add(new Menu("罗宾", R.drawable.luobin));
        listMenu.add(new Menu("索隆", R.drawable.suolong));
        listMenu.add(new Menu("布鲁克", R.drawable.buluke));
        listMenu.add(new Menu("雷利", R.drawable.leili));
        listMenu.add(new Menu("萨波", R.drawable.sabo));
        bottomMenusView.setDatas(listMenu);
        bottomMenusView.setOnMenuListener(new BottomMenusView.OnMenuListener() {
            @Override
            public void onClickMenu(Menu menu) {
                Toast.makeText(BottomMenusViewActivity.this, "点击" + menu.getName(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClickMenu(Menu menu) {
                Toast.makeText(BottomMenusViewActivity.this, "长按" + menu.getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}