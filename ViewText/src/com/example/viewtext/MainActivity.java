package com.example.viewtext;


import java.util.ArrayList;

import android.app.Activity;
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

public class MainActivity extends Activity {
//	private String toolslist[];
//	private ScrollView scrollView;
//	private Button toolsButton[];
//	private ShopAdapter shopAdapter;
//	private LayoutInflater inflater;
//	private View views[];
//	private ViewPager list_pager;
	private Type type;
	private ArrayList<Type> list;
 
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		ListView showlist = (ListView)findViewById(R.id.listView1);
		showlist.setAdapter(new MyListAdapter());
		
		
		
//		scrollView = (ScrollView) findViewById(R.id.tools_scrlllview);
//		shopAdapter = new ShopAdapter(getSupportFragmentManager());
//		inflater = LayoutInflater.from(this);
//		showToolsView();
		//initPager();
		
	}
	
	
	private class MyListAdapter extends BaseAdapter{
		public MyListAdapter(){
			super();
		}

		@Override
		public int getCount() {
			
			return 0;
		}

		@Override
		public Object getItem(int position) {
			
			return null;
		}

		@Override
		public long getItemId(int position) {
			
			return 0;
		}

		@Override
		public View getView(int index, View convertView, ViewGroup parent) {
			
			convertView = View.inflate(MainActivity.this, R.layout.from, null);
			TextView toptype = (TextView) convertView.findViewById(R.id.toptype);
			GridView listview = (GridView)convertView.findViewById(R.id.listView);
			//convertView.setTag(listview);
			
			
			
			
			
			return convertView;
			
		}
		
	}

//	private void initPager() {
//		
//		list_pager = (ViewPager)findViewById(R.id.goods_pager);
//		list_pager.setAdapter(shopAdapter);
//		
//		
//		
//	}




//	private void showToolsView() {
//		toolslist = new String[]{"男包","女包","箱包","男鞋","女鞋"};
//		LinearLayout toolsLayout = (LinearLayout) findViewById(R.id.tools);
//		
//		//设置长度
//		toolsButton = new Button[toolslist.length];
//		views= new View[toolslist.length];
//		
//		
//		for (int i=0;i<toolslist.length;i++){
//			//每个子分类的View
//			View view = inflater.inflate(R.layout.from, null);
//			//view.setId(i);
//			toolsLayout.addView(view);
//			views[i] = view;
//			
//			
//		}
//		
//	}
//
//
//
//
//	private class ShopAdapter extends FragmentPagerAdapter{
//
//		public ShopAdapter(FragmentManager fm) {
//			super(fm);
//			
//		}
//
//		@Override
//		public Fragment getItem(int arg0) {
//			Fragment fragment =new Fragment_pro_type();
//			Bundle bundle=new Bundle();
//			String str=toolslist[arg0];
//			bundle.putString("typename",str);
//			fragment.setArguments(bundle);
//			return fragment;
//		}
//
//		@Override
//		public int getCount() {
//			
//			return 0;
//		}
//		
//	}

	
}
