/**
 
package com.example.goodslist;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.TextView;

public class LoadingButton extends Activity{
	private static final String TAG = "LoadingButton Activity";
	private loadViewAdapter adapter = new loadViewAdapter();
	//内容填充
	private LayoutParams RlayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
			LinearLayout.LayoutParams.WRAP_CONTENT);
	//布局填充
	private LayoutParams FlayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT,
			LinearLayout.LayoutParams.FILL_PARENT);
	@Override
	protected void onCreate(Bundle savedInstanceState) {		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		LinearLayout layout = new LinearLayout(this);
		layout.setOrientation(LinearLayout.HORIZONTAL);
		
		Button button = new Button(this);
		button.setText("点击加载更多。。。");
		button.setGravity(Gravity.CENTER_VERTICAL);
		
		layout.addView(button, FlayoutParams);
		layout.setGravity(Gravity.CENTER);
		LinearLayout loadingLayout = new LinearLayout(this);
		loadingLayout.addView(layout, RlayoutParams);
		loadingLayout.setGravity(Gravity.CENTER);
		
		ListView listView = (ListView)findViewById(R.id.listView1);
		listView.addFooterView(loadingLayout);
		
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				adapter.count += 10;
				Log.i(TAG, "clicklistener:"+adapter.count);
				adapter.notifyDataSetChanged();
			}
		});
		listView.setAdapter(adapter);
		
	}
	
	private class loadViewAdapter extends BaseAdapter{
		int count = 10;

		@Override
		public int getCount() {
			
			return count;
		}

		@Override
		public Object getItem(int position) {
			
			return position;
		}

		@Override
		public long getItemId(int position) {
			
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			TextView textview = new TextView(LoadingButton.this);
			textview.setHeight(80);
			textview.setTextSize(20);
			textview.setText("加载测试"+position);
			return textview;
		}
		
	}

}
*/