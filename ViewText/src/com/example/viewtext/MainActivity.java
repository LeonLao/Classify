package com.example.viewtext;


import java.util.ArrayList;

import android.R.string;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;



public class MainActivity extends Activity {
	private String toolslist[];
//	private ScrollView scrollView;
	
	private ShopAdapter shopAdapter;
	private LayoutInflater inflater;
//	private View views[];
//	private ViewPager list_pager;
	private Type type;
	private ArrayList<Type> list;
	private  String nanbaos[] ;//= {"钱包","钥匙包","手提包","双肩包","单肩包","腰包","公文包","斜挎包","手包","名片/卡夹"};
	//private GoodsView goodsview;
	private View views[];
	private TextView toolsTextViews[];
	private MyGridView myGridView;
	
	
	
	
 
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//LinearLayout layout = new LinearLayout(this);
		//组件排列方式
		//layout.setOrientation(LinearLayout.VERTICAL);
		
		LinearLayout layout =(LinearLayout)findViewById(R.id.tools);
		//布局管理器参数
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,ViewGroup.LayoutParams.FILL_PARENT);
		
		//组件的布局参数
		LinearLayout.LayoutParams textparams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
		
		
		
		for(int i = 0;i<10;i++){
		//TextView textview = (TextView)findViewById(R.id.toptype);
		//GridView gridview = (GridView)findViewById(R.id.listView);
		TextView textview = new TextView(this);
		MyGridView gridview = new MyGridView(this);
		gridview.setNumColumns(3);
		//ScrollView scrollview = new ScrollView(this);
		
				
		textview.setLayoutParams(textparams);
		gridview.setLayoutParams(textparams);
		textview.setText("男包");
		
		list = new ArrayList<Type>();
		
		list.add(new Type("钱包"));
		list.add(new Type("钥匙包"));
		list.add(new Type("手提包"));
		list.add(new Type("双肩包"));
		list.add(new Type("单肩包"));
		list.add(new Type("单肩包"));
		list.add(new Type("单肩包"));
		list.add(new Type("单肩包"));
		
		shopAdapter = new ShopAdapter(this);
		shopAdapter.setText(list);
		
		gridview.setAdapter(shopAdapter);
		
			layout.addView(textview, textparams);
			layout.addView(gridview,textparams);
			
			
			
//			super.setContentView(layout,params);
		
		}
		
		
		//RelativeLayout layout = new RelativeLayout(this);
		//layout.setOrientation(LinearLayout.VERTICAL);
		
		
		
		
		
		
		
//		inflater = LayoutInflater.from(this);
//		shopAdapter = new ShopAdapter(this);
//		shopAdapter.setText(list);
//		
//		
//		View view = inflater.inflate(R.layout.from, null);
//		TextView textView = (TextView) view.findViewById(R.id.toptype);
//		
//		GridView goodView = (GridView) view.findViewById(R.id.listView);
//		
//		list = new ArrayList<Type>();
//		
//		list.add(new Type("钱包"));
//		list.add(new Type("钥匙包"));
//		list.add(new Type("手提包"));
//		list.add(new Type("双肩包"));
//		list.add(new Type("单肩包"));
//		
//		textView.setText("男包");
//		goodView.setAdapter(shopAdapter);
//		goodView.setOnItemClickListener(new OnItemClickListener() {
//
//			@Override
//			public void onItemClick(AdapterView<?> parent, View view,
//					int position, long id) {
//				// TODO Auto-generated method stub
//				
//			}
//		});
//		layout.addView(view);
		
		
		
		
		
		
		
		
		//setItem();
		//showView();
		
//		 LinearLayout layout = new LinearLayout(this);
//		layout.setOrientation(LinearLayout.VERTICAL);
		

//		setContentView(layout);
		
		
//		ListView showlist = (ListView)findViewById(R.id.listView1);
//		showlist.setAdapter(new MyListAdapter());
//	//	setview();
		
		//shopAdapter =new ShopAdapter(getSupportFragmentManager());
		
	}














//	private void setItem() {
//		list = new ArrayList<Type>();
//		
//		list.add(new Type(1, "钱包"));
//		list.add(new Type(2, "钥匙包"));
//		list.add(new Type(3, "手提包"));
//		list.add(new Type(4, "双肩包"));
//		list.add(new Type(5, "单肩包"));
		
		
		
		
		
		
//		for(int i = 0;i<toolsTextViews.length;i++){			
//			
//		if(nanbaos[i].contains("钱包")){
//		//	打到这里type的gettypename应该转为toolsTextViews[i]？
//			list = new ArrayList<Type>();
//		//	type = new Type(i, btntext)
//			list.add(new Type(1, "钱包"));
//			list.add(new Type(2, "钥匙包"));
//			list.add(new Type(3, "手提包"));
//			list.add(new Type(4, "双肩包"));
//			list.add(new Type(5, "单肩包"));			
//		}
//		else{
//			list = new ArrayList<Type>();
//			
//			list.add(new Type(1, "钱包"));
//			list.add(new Type(2, "钥匙包"));
//			list.add(new Type(3, "手提包"));
//			list.add(new Type(4, "双肩包"));
//			list.add(new Type(5, "单肩包"));
//		}		
//		}
//	}







//	private void showView() {
//		nanbaos  = new String[]{"钱包","钥匙包","手提包","双肩包","单肩包","腰包","公文包","斜挎包","手包","名片/卡夹"};
//		nanbaos  = new String[]{"钱包",};
//		LinearLayout layout = new LinearLayout(this);
//		layout.setOrientation(LinearLayout.VERTICAL);
//		
//		//设置长度
//		toolsTextViews = new Button[nanbaos.length];
//		views = new View[nanbaos.length];
//		
//		for (int i=0; i<nanbaos.length;i++){
//			View view = inflater.inflate(R.layout.from, null);
//			//view.setId(i);
//			TextView textView = (TextView) view.findViewById(R.id.toptype);
//			GridView goodView = (GridView) view.findViewById(R.id.listView);
//			
//			//textView.setText(nanbaos[i]);
//			textView.setText("男包");
//			goodView.setAdapter(shopAdapter);
//			goodView.setOnItemClickListener(new OnItemClickListener() {
//
//				@Override
//				public void onItemClick(AdapterView<?> parent, View view,
//						int position, long id) {
//					// TODO Auto-generated method stub
//					
//				}
//			});
//			layout.addView(view);
//			//toolsTextViews[i]= textView;
//			//views[i]=view;
//			
//		}		
//	}
	
	
//	private class MyListAdapter extends BaseAdapter{
//		public MyListAdapter(){
//			super();
//		}
//
//		@Override
//		public int getCount() {
//			
//			return showlist.length;
//		}
//
//		@Override
//		public Object getItem(int position) {
//			
//			return showlist[position];
//		}
//
//		@Override
//		public long getItemId(int position) {
//			
//			return position;
//		}
//
//		@Override
//		public View getView(int index, View convertView, ViewGroup parent) {
//			
//						
//			convertView = View.inflate(MainActivity.this, R.layout.from, null);
//			TextView toptype = (TextView) convertView.findViewById(R.id.toptype);
//			GridView listgridview = (GridView)convertView.findViewById(R.id.listView);
//			//convertView.setTag(listview);
//			
//			toptype.setText(showlist[index].title);
			
			
			
			
		//	 shopAdapter =new ShopAdapter(this);
			
			
			
			
//			listgridview.setAdapter(shopAdapter);
//			
//			
//			return convertView;
//			
//		}
//		
//	}
//	
	
	
//	private void setview(){
//	nanbao = new Button[10];
//	
//	String[] nanbaos = {"钱包","钥匙包","手提包","双肩包","单肩包","腰包","公文包","斜挎包","手包","名片/卡夹"};
//	
//	for(int i = 0;i<nanbao.length;i++){
//		nanbao[i].setText(nanbaos[i]);
//	}
//	}
//	
	
	
//	private static final ShowList[] showlist ={
//		new ShowList("品牌男包", nanbaos),
//		new ShowList("品牌女包", null),
//		new ShowList("功能箱包", null),
//		new ShowList("男鞋", null),
//		new ShowList("女鞋", null)
//		
//	};
//	
//	
//	private static class ShowList{
//		private final String title;
//		private final String[] names;
//		
//		public ShowList(String title,String[] names){
//			this.title = title;
//			this.names = names;
//		}
//	}
//	
	



	
}
