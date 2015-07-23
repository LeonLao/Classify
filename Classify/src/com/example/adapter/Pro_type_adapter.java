package com.example.adapter;

import java.util.ArrayList;

import com.example.classify.R;
import com.example.classify.Type;
import com.tonicartos.widget.stickygridheaders.StickyGridHeadersSimpleAdapter;
import com.tonicartos.widget.stickygridheaders.StickyGridHeadersSimpleArrayAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.HeaderViewListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class Pro_type_adapter extends BaseAdapter implements StickyGridHeadersSimpleAdapter{
	
	//定义Context
	private LayoutInflater mInflater;
	private ArrayList<Type> list;
	private Context context;
	private Type type;
	
	int mHeadId;
	int mItemId;
	
	public Pro_type_adapter(Context context,ArrayList<Type> list,int mHeadId,int mItemId){
		mInflater = LayoutInflater.from(context);
		this.list = list;
		//this.context = context;
		this.mHeadId = mHeadId;
		this.mItemId = mItemId;
	}

	@Override
	public int getCount() {	
		
		return list.size();		
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
		 MyView view;
		if(convertView ==null){
			
//			convertView = mInflater.inflate(R.layout.list_pro_type_item, null);
//			view.icon = (ImageView)convertView.findViewById(R.id.typeicon);
//			view.name = (TextView)convertView.findViewById(R.id.typename);
//			convertView.setTag(view);
			
			convertView = mInflater.inflate(mItemId, parent, false);
			view = new MyView();
			view.itemView = (TextView) convertView;
			convertView.setTag(view);
			
		}else{
			view = (MyView) convertView.getTag();
		}
		
		view.itemView.setText(list.get(position).getItemtext());
		return convertView;
//		if(list!=null&&list.size()>0){
//			type = list.get(position);
//			view.name.setText(type.getTypename());
//			if(type.getTypename().contains("名牌鞋包")){
//				//view.icon.setBackgroundResource(R.drawable.diannao);
//				
//			}
//			else if(type.getTypename().contains("美妆个护")){
//				//view.icon.setBackgroundResource(R.drawable.huazhuang);
//				
//			}
//			else if(type.getTypename().contains("首饰配件")){
//				//view.icon.setBackgroundResource(R.drawable.nvxie);
//			}
//			else if(type.getTypename().contains("名品服饰")){
//				//view.icon.setBackgroundResource(R.drawable.nvzhuang);
//			}
//			else if(type.getTypename().contains("母婴用品")){
//				//view.icon.setBackgroundResource(R.drawable.shuji);
//			}
//			else if(type.getTypename().contains("家具生活")){
//				//view.icon.setBackgroundResource(R.drawable.wanju);
//			}
//			else if(type.getTypename().contains("食品保健")){
//				//view.icon.setBackgroundResource(R.drawable.yiyong);
//			}
//			else{
//				//view.icon.setBackgroundResource(R.drawable.nvzhuang);
//			}
//			
//		}
		
		
		
	}
	
	protected class TitleView{
		public TextView textView;		
	}
	protected class MyView{
		
		public TextView itemView;
	}
	
	@Override
	public long getHeaderId(int position) {
		
		return list.get(position).getTid();
	}

	@Override
	public View getHeaderView(int position, View convertView, ViewGroup parent) {
		TitleView view;
		if(convertView == null){
			convertView = mInflater.inflate(mHeadId, parent, false);
			view = new TitleView();
			view.textView  = (TextView) convertView;
			convertView.setTag(view);
		}else{
			view = (TitleView) convertView.getTag();
		}
		view.textView.setText(list.get(position).getTypename());
		return convertView;
	}
	
	
	
	
	

}
