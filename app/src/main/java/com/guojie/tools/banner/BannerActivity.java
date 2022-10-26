package com.guojie.tools.banner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import com.guojie.tools.R;
import com.guojie.tools.fanShapedView.SectorLayout;
import com.youth.banner.Banner;
import com.youth.banner.config.IndicatorConfig;
import com.youth.banner.indicator.CircleIndicator;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

public class BannerActivity extends AppCompatActivity {
    private List<DateBean> dateBeanList;
    private Banner banner;

    private SectorLayout mSectorLayout;

    private int[] mImages = new int[]{
            R.drawable.ic_action_sms,
            R.drawable.ic_action_phone,
            R.drawable.ic_action_camera,
            R.drawable.ic_action_music,
            R.drawable.ic_action_name,};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner);

        banner = findViewById(R.id.banner);
        dateBeanList=new ArrayList<>();
        getList();

        banner.setIndicatorSelectedColor(getResources().getColor(R.color.purple_500));
        banner.setIndicatorNormalColor(getResources().getColor(R.color.white));
        banner.setIndicator(new CircleIndicator(this));
        banner.setIndicatorGravity( IndicatorConfig.Direction.CENTER);

        MyBannerAdapter myBannerAdapter=new MyBannerAdapter(dateBeanList);
        banner.setAdapter(myBannerAdapter);
//        banner.start();
        banner.isAutoLoop(false);
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(Object data, int position) {
                Log.d("TAG","position："+position);
            }
        });


        mSectorLayout = (SectorLayout) findViewById(R.id.sector);

        mSectorLayout.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return mImages.length;
            }

            @Override
            public Object getItem(int position) {
                return mImages[position];
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(final int position, View convertView, final ViewGroup parent) {
                Holder holder = null;
                if (convertView == null) {
                    holder = new Holder();
                    convertView = View.inflate(BannerActivity.this, R.layout.activity_item, null);
                    holder.mImage = (ImageView) convertView.findViewById(R.id.iv);
                    convertView.setTag(holder);
                } else {
                    holder = (Holder) convertView.getTag();
                }
                holder.mImage.setBackgroundResource(mImages[position]);
                if (position != (getCount() - 1)) {
                    holder.mImage.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(BannerActivity.this, "" + position, Toast.LENGTH_SHORT).show();
                        }
                    });
                }

                return convertView;
            }
        });
    }

    private void getList() {
        for(int i=0;i<10;i++){
            String titleBig="大标题"+i;
            String titleSmall="小标题"+i;
            DateBean dateBean=new DateBean(titleBig,titleSmall);
            dateBeanList.add(dateBean);
        }
    }


    public class Holder {
        ImageView mImage;
    }
}