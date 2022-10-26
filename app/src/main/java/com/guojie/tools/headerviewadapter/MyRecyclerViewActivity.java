package com.guojie.tools.headerviewadapter;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.guojie.tools.R;
import com.yanzhenjie.recyclerview.OnItemMenuClickListener;
import com.yanzhenjie.recyclerview.SwipeMenu;
import com.yanzhenjie.recyclerview.SwipeMenuBridge;
import com.yanzhenjie.recyclerview.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.SwipeMenuItem;
import com.yanzhenjie.recyclerview.SwipeRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyRecyclerViewActivity extends AppCompatActivity {

    @SuppressLint("NonConstantResourceId")
    SwipeRecyclerView recyclerView;

    private List<String> stringList;
    private RecyclerViewAdapt recyclerViewAdapt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_recycler_view);

        recyclerView=findViewById(R.id.recyclerView);

        stringList = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewAdapt = new RecyclerViewAdapt(createList());

        View hv2 = LayoutInflater.from(this).inflate(R.layout.layout_head_view, recyclerView, false);

        recyclerView.addHeaderView(hv2);

        SwipeMenuCreator mSwipeMenuCreator = new SwipeMenuCreator() {
            @Override
            public void onCreateMenu(SwipeMenu leftMenu, SwipeMenu rightMenu, int viewType) {

                int width = getResources().getDimensionPixelOffset(R.dimen.dp_100);
                int height = ViewGroup.LayoutParams.MATCH_PARENT;
                // 注意：哪边不想要菜单，那么不要添加即可。
                SwipeMenuItem addItem = new SwipeMenuItem(MyRecyclerViewActivity.this)
                        .setText("删除")
                        .setTextColor(Color.BLACK)
                        .setWidth(width)
                        .setHeight(height);
                rightMenu.addMenuItem(addItem); // 添加菜单到右侧。
            }
        };

        recyclerView.setOnItemMenuClickListener(mMenuItemClickListener);
        recyclerView.setSwipeMenuCreator(mSwipeMenuCreator);

        recyclerView.setAdapter(recyclerViewAdapt);
    }

    private OnItemMenuClickListener mMenuItemClickListener = new OnItemMenuClickListener() {
        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        public void onItemClick(SwipeMenuBridge menuBridge, int position) {
            menuBridge.closeMenu();

            int direction = menuBridge.getDirection(); // 左侧还是右侧菜单。
            int menuPosition = menuBridge.getPosition(); // 菜单在RecyclerView的Item中的Position。

            if (direction == SwipeRecyclerView.RIGHT_DIRECTION) {
                if (menuBridge.getPosition() == 0) {
                    recyclerViewAdapt.notifyItemRemoved(position);
                }

            }
        }
    };

    public void removeItem(int position) {
        stringList.remove(stringList.get(position));
        recyclerViewAdapt.notifyDataSetChanged(stringList);

    }

    private List<String> createList() {
        for (int i = 0; i < 20; i++) {
            stringList.add("这是第" + i + "item");
        }
        return stringList;
    }
}