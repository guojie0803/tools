package com.guojie.tools.banner;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.guojie.tools.R;
import com.youth.banner.adapter.BannerAdapter;

import java.util.List;

public class MyBannerAdapter extends BannerAdapter<DateBean, MyBannerAdapter.BannerViewHolder> {

    private List<DateBean> mDates;

    public MyBannerAdapter(List<DateBean> datas) {
        super(datas);

        mDates=datas;
    }

    @Override
    public BannerViewHolder onCreateHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.banner_view_item, parent, false);
        //将view传递给我们自定义的ViewHolder
       MyBannerAdapter.BannerViewHolder holder = new BannerViewHolder(view);

        //返回这个MyHolder实体
        return holder;
    }

    @Override
    public void onBindView(BannerViewHolder holder, DateBean data, int position, int size) {
        DateBean dateBean=mDates.get(position);
        holder.textView_big.setText(dateBean.titleBig);
        holder.textView_small.setText(dateBean.titleSmall);
    }

    public class BannerViewHolder extends RecyclerView.ViewHolder{
        TextView textView_big;
        TextView textView_small;
        public BannerViewHolder(@NonNull View itemView) {
            super(itemView);
            textView_big=itemView.findViewById(R.id.tv_title_big);
            textView_small=itemView.findViewById(R.id.tv_title_small);
        }
    }
}
