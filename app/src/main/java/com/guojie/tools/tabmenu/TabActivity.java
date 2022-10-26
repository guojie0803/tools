package com.guojie.tools.tabmenu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.guojie.tools.R;

public class TabActivity extends AppCompatActivity {
    TabMenu mTabMenu;
    ViewPager viewPager;
    String[] mText = {"1","2","3","4"};
    int[] mIcon = {R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher};
    int[] mSelectIcon = {R.mipmap.skip,R.mipmap.skip,R.mipmap.skip,R.mipmap.skip};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);

        mTabMenu = findViewById(R.id.tab_menu);
        viewPager = findViewById(R.id.viewpager);

        mTabMenu.setTexts(mText);
        mTabMenu.setImageBg(mIcon,mSelectIcon);


        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                switch(position){
                    case 0:
                        return Fragment1.newInstance();
                    case 1:
                        return Fragment2.newInstance();
                    case 2:
                        return Fragment3.newInstance();
                    case 3:
                        return Fragment4.newInstance();
                    default:
                        return Fragment1.newInstance();
                }
            }

            @Override
            public int getCount() {
                return 4;
            }
        });
        mTabMenu.setViewPager(viewPager);
    }
}