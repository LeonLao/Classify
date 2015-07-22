package com.example.mycollect;


import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.TextView;

public class ButtonLoadingActivity extends ListActivity{
	private static final String TAG = "ButtonLoadingActivity";
	
	private MyListAdapter adapter = new MyListAdapter();
	
	//设置布局显示目标有多大就多大
	private LayoutParams sylayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
	
	//设置布局显示目标最大化
	private LayoutParams maxlayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
	
		super.onCreate(savedInstanceState);
		LinearLayout layout = new LinearLayout(this);
		layout.setOrientation(LinearLayout.HORIZONTAL);
		
		
		Button textview = new Button(this);	
		textview.setText("点击查看更多收藏...");
		textview.setGravity(Gravity.CENTER_VERTICAL);
		
		layout.addView(textview, maxlayoutParams);
		layout.setGravity(Gravity.CENTER);
		LinearLayout loadingLayout = new LinearLayout(this);
		loadingLayout.addView(layout, sylayoutParams);
		loadingLayout.setGravity(Gravity.CENTER);
		
		
		ListView listView = getListView();
		
		listView.addFooterView(loadingLayout);
		
		textview.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				adapter.count += 5;  
                Log.i(TAG, "setOnClickListener:" +  adapter.count);
                adapter.notifyDataSetChanged();
				
			}
		});
		setListAdapter(adapter);
	
	
	}
	
	public void onScrollStateChanged(AbsListView v,int s){
		Log.i(TAG,"onScrollStateChanged(AbsListView v, int s)");
	}
	
	private class MyListAdapter extends BaseAdapter{
		
		int count = 10;

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return count;
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			TextView textView = new TextView(ButtonLoadingActivity.this);
            textView.setHeight(80);
            textView.setTextSize(20);
            textView.setText("加载测试 " + position);
            Log.i(TAG, "getView:pos:" + position);
           return textView;
			
			
			
			
//			convertView = View.inflate(ButtonLoadingActivity.this, R.layout.list_item, null);
//			ImageView img = (ImageView) convertView.findViewById(R.id.imageView1);
//			TextView title = (TextView) convertView.findViewById(R.id.textView1);
//			TextView price = (TextView) convertView.findViewById(R.id.textView3);
//			
//			img.setImageResource(goods[position].pic);
//			title.setText(goods[position].title);
//			price.setText(goods[position].price);
//			
//			
//			
//			return convertView;
		}
		
	}
	
	private static class ShowGoods{
		private final int pic;
		private final int title;
		private final int price;
		private final Class<? extends android.app.Activity> showgoods;
		
		public ShowGoods(int pic, int title,int price,
				Class<? extends android.app.Activity> showgoods){
			this.pic = pic;
			this.price = price;
			this.title = title;
			this.showgoods = showgoods;
		}
	}
	
	private static final ShowGoods[] goods ={
		new ShowGoods(R.drawable.test1,R.string.goodstitle2,R.string.goodsprice3,
				null),
		new ShowGoods(R.drawable.test1,R.string.goodstitle2,R.string.goodsprice3,
				null),
		new ShowGoods(R.drawable.test1,R.string.goodstitle2,R.string.goodsprice3,
				null),
		new ShowGoods(R.drawable.test1,R.string.goodstitle2,R.string.goodsprice3,
				null),
		new ShowGoods(R.drawable.test1,R.string.goodstitle2,R.string.goodsprice3,
				null),
		new ShowGoods(R.drawable.test1,R.string.goodstitle2,R.string.goodsprice3,
				null),
		new ShowGoods(R.drawable.test1,R.string.goodstitle2,R.string.goodsprice3,
				null),
		new ShowGoods(R.drawable.test1,R.string.goodstitle2,R.string.goodsprice3,
				null),
		new ShowGoods(R.drawable.test1,R.string.goodstitle2,R.string.goodsprice3,
				null),
		new ShowGoods(R.drawable.test1,R.string.goodstitle2,R.string.goodsprice3,
				null),
		new ShowGoods(R.drawable.test1,R.string.goodstitle2,R.string.goodsprice3,
				null),
		new ShowGoods(R.drawable.test1,R.string.goodstitle2,R.string.goodsprice3,
				null),
		new ShowGoods(R.drawable.test1,R.string.goodstitle2,R.string.goodsprice3,
				null),
		new ShowGoods(R.drawable.test1,R.string.goodstitle2,R.string.goodsprice3,
				null),
		new ShowGoods(R.drawable.test1,R.string.goodstitle2,R.string.goodsprice3,
				null),
		new ShowGoods(R.drawable.test1,R.string.goodstitle2,R.string.goodsprice3,
				null),
		new ShowGoods(R.drawable.test1,R.string.goodstitle2,R.string.goodsprice3,
				null),
		new ShowGoods(R.drawable.test1,R.string.goodstitle2,R.string.goodsprice3,
				null),
		new ShowGoods(R.drawable.test1,R.string.goodstitle2,R.string.goodsprice3,
				null)
		
	};
	
	
	
	
}
