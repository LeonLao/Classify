package com.example.adapter;

import java.util.ArrayList;

import com.example.classify.R;
import com.example.classify.Type;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class Pro_type_adapter extends BaseAdapter{
	
	//定义Context
	private LayoutInflater mInflater;
	private ArrayList<Type> list;
	private Context context;
	private Type type;
	public Pro_type_adapter(Context context,ArrayList<Type> list){
		mInflater = LayoutInflater.from(context);
		this.list = list;
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
	public View getView(final int position, View convertView, ViewGroup parent) {
		final MyView view;
		if(convertView ==null){
			view = new MyView();
			convertView = mInflater.inflate(R.layout.list_pro_type_item, null);
			view.icon = (ImageView)convertView.findViewById(R.id.typeicon);
			view.name = (TextView)convertView.findViewById(R.id.typename);
			convertView.setTag(view);
		}else{
			view = (MyView) convertView.getTag();
		}
		if(list!=null&&list.size()>0){
			type = list.get(position);
			view.name.setText(type.getTypename());
			if(type.getTypename().contains("名牌鞋包")){
				view.icon.setBackgroundResource(R.drawable.diannao);
				
			}
			else if(type.getTypename().contains("美妆个护")){
				view.icon.setBackgroundResource(R.drawable.huazhuang);
				
			}
			else if(type.getTypename().contains("首饰配件")){
				view.icon.setBackgroundResource(R.drawable.nvxie);
			}
			else if(type.getTypename().contains("名品服饰")){
				view.icon.setBackgroundResource(R.drawable.nvzhuang);
			}
			else if(type.getTypename().contains("母婴用品")){
				view.icon.setBackgroundResource(R.drawable.shuji);
			}
			else if(type.getTypename().contains("家具生活")){
				view.icon.setBackgroundResource(R.drawable.wanju);
			}
			else if(type.getTypename().contains("食品保健")){
				view.icon.setBackgroundResource(R.drawable.yiyong);
			}
			else{
				view.icon.setBackgroundResource(R.drawable.nvzhuang);
			}
			
		}
		
		
		return convertView;
	}
	private class MyView{
		private ImageView icon;
		private TextView name;
	}

}
