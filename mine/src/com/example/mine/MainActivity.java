package com.example.mine;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	//顶部用户信息
	private ImageView user_img;//用户头像
	private TextView user_name;//用户名称
	private TextView user_level;//用户级别
	private TextView user_integral;//用户积分
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		//顶部用户信息
		user_img = (ImageView)findViewById(R.id.imageView1);
		user_name = (TextView)findViewById(R.id.textView1);
		user_level = (TextView)findViewById(R.id.textView2);
		user_integral = (TextView)findViewById(R.id.textView3);
		
		
		
		
		
		ListView mylistview = (ListView)findViewById(R.id.listView1);
		mylistview.setAdapter(new MyListAdapter());
		mylistview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int index, long id) {
			//	onListItemClick(index);				
			}			
		});		
	}
	
	

	protected void onListItemClick(int index) {		
			Intent intent = null;
			intent = new Intent(MainActivity.this, showlist[index].listClass);
			this.startActivity(intent);			
	}



	private class MyListAdapter extends BaseAdapter{
		
		public MyListAdapter(){
			super();
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return showlist.length;
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return showlist[position];
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int index, View convertView, ViewGroup parent) {
			convertView = View.inflate(MainActivity.this, R.layout.list_item, null);
			ImageView views = (ImageView) convertView.findViewById(R.id.listimage);
			TextView listtext = (TextView) convertView.findViewById(R.id.listtext);
			views.setImageResource(showlist[index].imageview);
			listtext.setText(showlist[index].list);
			listtext.setTextSize(20);
			return convertView;
		}
		
	}
	
	
	private static final ShowList[] showlist={
		new ShowList(R.drawable.quanquan1,R.string.order,null),
		new ShowList(R.drawable.dizhi1,R.string.address,null),
		new ShowList(R.drawable.juan1,R.string.coupon,null),
		new ShowList(R.drawable.quanquan1,R.string.gift,null),
		new ShowList(R.drawable.juan1,R.string.vipcard,null),
		new ShowList(R.drawable.xing1,R.string.collect,null),
		new ShowList(R.drawable.xing1,R.string.password,null),
		new ShowList(R.drawable.xing1,R.string.record,null),
		new ShowList(R.drawable.xing1,R.string.exit,null)
		
	};
	
	
	
	private static class ShowList{
		private  final int imageview;
		private final int list;
		private final Class<? extends android.app.Activity> listClass;
		
		public ShowList(int imageview,int list,
				Class<?extends android.app.Activity> listClass){
			this.imageview = imageview;
			this.list = list;
			this.listClass = listClass;
			
		}
	}

	
}
