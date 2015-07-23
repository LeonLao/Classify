package com.example.classifytext;

import java.util.List;

import com.tonicartos.widget.stickygridheaders.StickyGridHeadersSimpleAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MyAdapter extends BaseAdapter implements StickyGridHeadersSimpleAdapter{
	List<Type> list;
	LayoutInflater mInflater;
	int titleId;
	int textId;
	
	public  MyAdapter(Context context,List<Type> list,int titleId ,int textId){
		mInflater = LayoutInflater.from(context);
		this.list = list;
		this.textId = textId;
		this.titleId = titleId;
	}
	

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		MyView view;
		if(convertView  == null){
			convertView = mInflater.inflate(textId, parent, false);
			view = new MyView();
			view.textview = (TextView) convertView;
			convertView.setTag(view);
		}else{
			view = (MyView) convertView.getTag();
		}
		view.textview.setText(list.get(position).getItem());
		return convertView;
	}

	@Override
	public long getHeaderId(int position) {
		// TODO Auto-generated method stub
		return list.get(position).getTid();
	}

	@Override
	public View getHeaderView(int position, View convertView, ViewGroup parent) {
		TitleView view;
		if(convertView ==null){
			convertView = mInflater.inflate(titleId, parent, false);
			view = new TitleView();
			view.titleview = (TextView)convertView;
			convertView.setTag(view);
		}else{
			view = (TitleView) convertView.getTag();
		}
		view.titleview.setText(list.get(position).getTitle());
		return convertView;
	}
	
	public class MyView{
		TextView textview;
	}
	public class TitleView{
		TextView titleview;
	}

}
