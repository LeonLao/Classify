package com.example.goodslist;



import android.app.Activity;
import android.graphics.Paint;
import android.os.Bundle;
import android.renderscript.Program.TextureType;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnScrollListener{
	private int lastItem = 0;	
	private LayoutParams MlayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
	private LayoutParams LlayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT);
	
	private ProgressBar progressBar;
	
	private int page=1;
	private int pageCount=5;
	
	//private String headtext;
	private MyListAdapter adapter = new MyListAdapter();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//setHeadText("headtext");
		
		LinearLayout layout = new LinearLayout(this);
		layout.setOrientation(LinearLayout.HORIZONTAL);
		
		
//		progressBar = new ProgressBar(this);
//		progressBar.setPadding(0, 0, 15, 0);		
//		layout.addView(progressBar, MlayoutParams);
		
		TextView lodaingText = new TextView(this);
		lodaingText.setText("加载中...");
		lodaingText.setGravity(Gravity.CENTER_VERTICAL);
		
		layout.addView(lodaingText, LlayoutParams);
		layout.setGravity(Gravity.CENTER);
		
		LinearLayout loadingLayout = new LinearLayout(this);
	
		loadingLayout.addView(layout);
		loadingLayout.setGravity(Gravity.CENTER);
		
		//用listview显示条目
//		ListView listview = new ListView(this);
		ListView listview = (ListView)findViewById(R.id.listView1);
		listview.addFooterView(loadingLayout);
				
		registerForContextMenu(listview);
		
		
		lodaingText.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(page<pageCount&&pageCount!=0){
				adapter.count += 10;
				
				//Log.i(TAG, "clicklistener:"+adapter.count);
				adapter.notifyDataSetChanged();
				Toast.makeText(MainActivity.this, ""+page, Toast.LENGTH_LONG).show();
				
				}
				else{
					Toast.makeText(MainActivity.this, "加载哦完毕",Toast.LENGTH_LONG).show();
				}
				
			}
		});
		
		listview.setAdapter(adapter);
		listview.setOnScrollListener(this);
		
	
		
	}
	
	private class MyListAdapter extends BaseAdapter{
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
			convertView = View.inflate(MainActivity.this, R.layout.list_item, null);
			
			TextView title = (TextView) convertView.findViewById(R.id.title);
			TextView price = (TextView) convertView.findViewById(R.id.price);
			TextView discount = (TextView) convertView.findViewById(R.id.discount);
			
			title.setText("法兰都鹏男士商务休闲"+position);
			title.setTextSize(25);
			title.setSingleLine(true);
			
			discount.setText("¥9998");
			
			//设置中划线/加清晰  |Paint.ANTI_ALIAS_FLAG
			discount.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
//			TextView view = new TextView(MainActivity.this);
//			view.setText("test"+position);
			
			return convertView;
		}
		
	}
	
	
	
	//顶部标题
	private void setHeadText(String text) {
		((TextView)findViewById(R.id.head_text)).setText(text);
		
	}

	public void onTopClick(View view){
		switch(view.getId()){
		case R.id.head_back:
			finish();
			break;
		case R.id.head_home:
			//返回首页
			finish();
			break;
		default:
			break;
		}
	}
	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		if(lastItem ==adapter.count
				&& scrollState == OnScrollListener.SCROLL_STATE_IDLE&&page<pageCount){
			adapter.count +=10;
			page+=1;
			adapter.notifyDataSetChanged();
			
		}
		
	}
	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		lastItem = firstVisibleItem+visibleItemCount-1;
		System.out.print("lastItem"+lastItem);
		
	}
	
	
	

	
}
