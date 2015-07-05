package com.example.viewtext;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

public class ShopAdapter extends BaseAdapter{
	
	//定义context
	private LayoutInflater mInflater;
	private Context context;
	private ArrayList<Type> list;
	private Type type;
	//private String[] nanbaos = {"钱包","钥匙包","手提包","双肩包","单肩包","腰包","公文包","斜挎包","手包","名片/卡夹"};
	
	
	public ShopAdapter(Context context){
		super();
		this.context = context;
	}

	

	



	@Override
	public int getCount() {
		if(list!=null&&list.size()>0)
			return list.size();
		else
			return 0;
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		
		return 0;
	}

	@Override
	public View getView(int index, View convertView, ViewGroup parent) {
		final MyView view;
		if(convertView == null){
			view = new MyView();
			convertView = mInflater.inflate(R.layout.item, null);
			view.button = (Button)convertView.findViewById(R.id.button1);
			convertView.setTag(view);
		}else{
			view = (MyView) convertView.getTag();
		}
		type = list.get(index);
		if(type!=null&&list.size()>0){
						
				view.button.setText(type.getBtnname());
			
		}
		
		
		return convertView;
	}
	
	
	
	
	private class MyView{
		Button button;
	}

}
