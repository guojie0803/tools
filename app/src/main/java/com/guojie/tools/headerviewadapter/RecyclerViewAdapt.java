package com.guojie.tools.headerviewadapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.guojie.tools.R;

import java.util.List;

public class RecyclerViewAdapt extends RecyclerView.Adapter<RecyclerViewAdapt.ViewHolder> implements View.OnClickListener, View.OnLongClickListener {

    private List<String> mList;
    private OnItemClickListener mOnItemClickListener;
    public RecyclerViewAdapt(List<String> list){
        mList = list;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).
                inflate(R.layout.item_menu,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        view.setOnClickListener(this);
        view.setOnLongClickListener(this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.itemView.setTag(position);
        holder.text.setText(mList.get(position));
    }


    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public void onClick(View v) {
        if(mOnItemClickListener!=null){
            mOnItemClickListener.onItemClick(v, (int) v.getTag());
        }
    }

    @Override
    public boolean onLongClick(View v) {
        if(mOnItemClickListener!=null){
            mOnItemClickListener.onItemLongClick(v, (int) v.getTag());
        }
        return false;
    }

    public void notifyDataSetChanged(List<String> stringList) {
        mList=stringList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView text;

        public ViewHolder(View view){
            super(view);
            image=view.findViewById(R.id.image);
            text=view.findViewById(R.id.tv_text);
        }
    }

    public interface OnItemClickListener{
        void onItemClick(View view,int position);
        void onItemLongClick(View view,int position);
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.mOnItemClickListener=onItemClickListener;
    }
}
