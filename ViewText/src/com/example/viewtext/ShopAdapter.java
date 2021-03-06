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
	//private Type type;
	
	//private String[] nanbaos = {"钱包","钥匙包","手提包","双肩包","单肩包","腰包","公文包","斜挎包","手包","名片/卡夹"};
	
	
	public ShopAdapter(Context context ){
		super();
	
		this.context = context;
		
	}
	

	public void setText(ArrayList<Type> list){
		this.list = list;
		mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
		
		return position;
	}

	@Override
	public View getView(int index, View convertView, ViewGroup parent) {
		 MyView view;
		if(convertView == null){
			
			convertView = mInflater.inflate(R.layout.item, null);
			view = new MyView();
			view.button = (Button)convertView.findViewById(R.id.button1);
			
			convertView.setTag(view);
		}else{
			view = (MyView) convertView.getTag();
		}
		Type type = list.get(index);
		if(type!=null&&list.size()>0){
						
				view.button.setText(type.getBtntext());
			
		}
		
		
		return convertView;
	}
	
	
	
	
	private class MyView{
		Button button;
	}

}
