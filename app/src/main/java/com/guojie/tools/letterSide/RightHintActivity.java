package com.guojie.tools.letterSide;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.guojie.tools.R;
import com.guojie.tools.letterSide.CustomView.AZWaveSideBarView;
import com.guojie.tools.letterSide.azlist.AZItemEntity;
import com.guojie.tools.letterSide.azlist.AZTitleDecoration;
import com.guojie.tools.letterSide.azlist.LettersComparator;
import com.pilot.common.utils.PinyinUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RightHintActivity extends AppCompatActivity {


    private Context           mContext;
    private RecyclerView mRecyclerView;
    private AZWaveSideBarView mBarList;
    private ItemAdapter       mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_right_hint);
        initView();
        initEvent();
        initData();
    }

    private void initView() {
        mRecyclerView = findViewById(R.id.recycler_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.addItemDecoration(new AZTitleDecoration(new AZTitleDecoration.TitleAttributes(mContext)));
        mBarList = findViewById(R.id.bar_list);
    }

    private void initEvent() {
        mBarList.setOnLetterChangeListener(new AZWaveSideBarView.OnLetterChangeListener() {
            @Override
            public void onLetterChange(String letter) {
                int position = mAdapter.getSortLettersFirstPosition(letter);
                if (position != -1) {
                    if (mRecyclerView.getLayoutManager() instanceof LinearLayoutManager) {
                        LinearLayoutManager manager = (LinearLayoutManager) mRecyclerView.getLayoutManager();
                        manager.scrollToPositionWithOffset(position, 0);
                    } else {
                        mRecyclerView.getLayoutManager().scrollToPosition(position);
                    }
                }
            }
        });
    }

    private void initData() {
        List<AZItemEntity<String>> dateList = fillData(getResources().getStringArray(R.array.region));
        Collections.sort(dateList, new LettersComparator());
        mRecyclerView.setAdapter(mAdapter = new ItemAdapter(dateList));
    }

    private List<AZItemEntity<String>> fillData(String[] date) {
        List<AZItemEntity<String>> sortList = new ArrayList<>();
        for (String aDate : date) {
            AZItemEntity<String> item = new AZItemEntity<>();
            item.setValue(aDate);
            //汉字转换成拼音
            String pinyin = PinyinUtils.getPingYin(aDate);
            //取第一个首字母
            String letters = pinyin.substring(0, 1).toUpperCase();
            // 正则表达式，判断首字母是否是英文字母
            if (letters.matches("[A-Z]")) {
                item.setSortLetters(letters.toUpperCase());
            } else {
                item.setSortLetters("#");
            }
            sortList.add(item);
        }
        return sortList;

    }
}