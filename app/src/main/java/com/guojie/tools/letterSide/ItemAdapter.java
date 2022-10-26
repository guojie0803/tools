package com.guojie.tools.letterSide;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.guojie.tools.R;
import com.guojie.tools.letterSide.azlist.AZBaseAdapter;
import com.guojie.tools.letterSide.azlist.AZItemEntity;

import java.util.List;

public class ItemAdapter extends AZBaseAdapter<String, ItemAdapter.ItemHolder> {

	public ItemAdapter(List<AZItemEntity<String>> dataList) {
		super(dataList);
	}

	@Override
	public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		return new ItemHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_adapter, parent, false));
	}

	@Override
	public void onBindViewHolder(ItemHolder holder, int position) {
		holder.mTextName.setText(mDataList.get(position).getValue());
	}

	static class ItemHolder extends RecyclerView.ViewHolder {

		TextView mTextName;

		ItemHolder(View itemView) {
			super(itemView);
			mTextName = itemView.findViewById(R.id.text_item_name);
		}
	}
}
