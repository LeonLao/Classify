package com.example.viewtext;


import java.util.ArrayList;

import android.R.string;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.support.v4.app.Fragment;


public class MainActivity extends Activity {
	private String toolslist[];
//	private ScrollView scrollView;
	
	private ShopAdapter shopAdapter;
//	private LayoutInflater inflater;
//	private View views[];
//	private ViewPager list_pager;
	private Type type;
	private ArrayList<Type> list;
	private static String[] nanbaos = {"钱包","钥匙包","手提包","双肩包","单肩包","腰包","公文包","斜挎包","手包","名片/卡夹"};
	
	
 
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		ListView showlist = (ListView)findViewById(R.id.listView1);
		showlist.setAdapter(new MyListAdapter());
	//	setview();
		
		shopAdapter =new ShopAdapter(this);
		
	}
	
	
	private class MyListAdapter extends BaseAdapter{
		public MyListAdapter(){
			super();
		}

		@Override
		public int getCount() {
			
			return showlist.length;
		}

		@Override
		public Object getItem(int position) {
			
			return showlist[position];
		}

		@Override
		public long getItemId(int position) {
			
			return position;
		}

		@Override
		public View getView(int index, View convertView, ViewGroup parent) {
			
						
			convertView = View.inflate(MainActivity.this, R.layout.from, null);
			TextView toptype = (TextView) convertView.findViewById(R.id.toptype);
			GridView listgridview = (GridView)convertView.findViewById(R.id.listView);
			//convertView.setTag(listview);
			
			toptype.setText(showlist[index].title);
			
			
			
			
//			 shopAdapter =new ShopAdapter(this);
			
			
			
			
			listgridview.setAdapter(shopAdapter);
			
			
			return convertView;
			
		}
		
	}
	
	
	
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
	
	
	private static final ShowList[] showlist ={
		new ShowList("品牌男包", nanbaos),
		new ShowList("品牌女包", null),
		new ShowList("功能箱包", null),
		new ShowList("男鞋", null),
		new ShowList("女鞋", null)
		
	};
	
	
	private static class ShowList{
		private final String title;
		private final String[] names;
		
		public ShowList(String title,String[] names){
			this.title = title;
			this.names = names;
		}
	}
	
	



	
}
