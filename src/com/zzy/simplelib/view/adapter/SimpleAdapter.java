package com.zzy.simplelib.view.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public abstract class SimpleAdapter<T> extends BaseAdapter {

	private List<T> data;
	public LayoutInflater mInflater;
	public Context context;

	public SimpleAdapter(Context context, List<T> data) {
		mInflater = LayoutInflater.from(context);
		this.context = context;
		this.data = data;
	}

	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public T getItem(int position) {
		return data.get(position);
	}

	@Override
	public long getItemId(int id) {
		return id;
	}

	@Override
	public View getView(int position, View itemView, ViewGroup viewGroup) {
		return getItemView(position, itemView, viewGroup);
	}

	public abstract View getItemView(int position, View itemView,
			ViewGroup viewGroup);

}
