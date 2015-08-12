package com.example.mycollect;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	private static final String TAG = "MainActivity";
	
	//顶部3控件
	private ImageButton btnback ;
	private TextView title;
	private ImageButton btnhome;
	
	private ListView mylist =null;
	
	final String[] mItems = {"取消关注","加入购物车"};
	
	private int lastItem = 0;
	
	
	
	private MyListAdapter adapter = new MyListAdapter();
	
	//设置布局显示目标有多大就多大
	
	private LayoutParams sylayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT );
	
	//设置布局显示目标最大化
	private LayoutParams maxlayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT);
	
	private ProgressBar progressbar;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//线性布局
		LinearLayout layout = new LinearLayout(this);
		
		//设置布局水平方向
		layout.setOrientation(LinearLayout.HORIZONTAL);
		
		//进度条
		//progressbar = new ProgressBar(this);
		//进度条显示位置
	//	progressbar.setPadding(0, 0, 15, 0);
		
		
	//	layout.addView(progressbar, sylayoutParams);
		
		
//		TextView textview = new TextView(this);
//		textview.setText("加载中...");
//		textview.setGravity(Gravity.CENTER_VERTICAL);
//		
//		layout.addView(textview, maxlayoutParams);
//		layout.setGravity(Gravity.CENTER);
//		
//		LinearLayout loadingLayout = new LinearLayout(this);
//		loadingLayout.addView(layout, sylayoutParams);
//		loadingLayout.setGravity(Gravity.CENTER);
		
		//得到一个ListView用来显示条目
	//	ListView listView = getListView();
		
		//添加到页脚显示
	//	registerForContextMenu(listView);
		
	//	setListAdapter(adapter);
	//	listView.setOnScrollListener(this);
		
		
		//顶部3控件
		btnback = (ImageButton)findViewById(R.id.back);
		title = (TextView)findViewById(R.id.title);
		btnhome = (ImageButton)findViewById(R.id.home);
		
//		btnback.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				//点击返回按钮
//				
//			}
//		});
//		
//		//顶部标题
//		title.setText("我的收藏");
//		
//		
//		//主页按钮
//		btnhome.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				//点击主页面按钮
//				
//			}
//		});
		
		
		
		
		
		mylist = (ListView) findViewById(R.id.listView1);
		
		//得到一个ListView显示条目
		//ListView mylist = getListView();
		//添加到页脚显示
		//mylist.addFooterView(loadingLayout);
		
	//	registerForContextMenu(mylist);
		
		mylist.setAdapter(adapter);
		
		
		mylist.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int index, long id) {
				onListItemClick(index);				
			}
		});
		
		
		mylist.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				//操作对话框
				AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
				builder.setTitle("操作");
				builder.setItems(mItems, new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						switch (which){
						case 0:
							//取消关注
						
						break;
						case 1:
							//加入购物车
						
						break;
						}													
					}
				});
				builder.show();
				return false;
			}
		});
		
		
	}
	
	
	
	
	
	
	private class MyListAdapter extends BaseAdapter{
		
		
		int count = goods.length;
		
//		public MyListAdapter(){
//			super();
//		}

		@Override
		public int getCount() {
		
			//return goods.length;
			return count;
		}

		@Override
		public Object getItem(int position) {
			
			return goods[position];
		}

		@Override
		public long getItemId(int id) {
			
			return id;
		}

		@SuppressLint("ViewHolder")
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			convertView = View.inflate(MainActivity.this, 
					R.layout.list_item,null);
			
			ImageView img = (ImageView) convertView.findViewById(R.id.imageView1);
			TextView title = (TextView) convertView.findViewById(R.id.textView1);
			TextView price  = (TextView) convertView.findViewById(R.id.textView3);
			
			img.setImageResource(goods[position].pic);
			title.setText(goods[position].title);
			//title.setSingleLine(true);
			//title.setEllipsize(TextUtils.TruncateAt.valueOf("MARQUEE"));
			price.setText(goods[position].price);
			return convertView;
		}
		
	}
	
	private static class ShowGoods{
		private final int pic;
		private final int title;
		private final int price;
		private final Class<? extends android.app.Activity> showgoods;
		
		public ShowGoods(int pic,int title,int price,
				Class<? extends android.app.Activity> showgoods){
			this.pic = pic;
			this.title = title;
			this.price = price;
			this.showgoods = showgoods;
		}
	}
	
	
	
	private static final ShowGoods[] goods ={
		new ShowGoods(R.drawable.test1,R.string.goodstitle1, R.string.goodsprice1,
				null),
		new ShowGoods(R.drawable.test2,R.string.goodstitle2, R.string.goodsprice2,
				null),
		new ShowGoods(R.drawable.test3,R.string.goodstitle3, R.string.goodsprice3,
				null),
		new ShowGoods(R.drawable.test3,R.string.goodstitle3, R.string.goodsprice3,
				null),
		new ShowGoods(R.drawable.test3,R.string.goodstitle3, R.string.goodsprice3,
				null),
		new ShowGoods(R.drawable.test3,R.string.goodstitle3, R.string.goodsprice3,
				null),
		new ShowGoods(R.drawable.test3,R.string.goodstitle3, R.string.goodsprice3,
				null),
		new ShowGoods(R.drawable.test3,R.string.goodstitle3, R.string.goodsprice3,
				null),
		new ShowGoods(R.drawable.test3,R.string.goodstitle3, R.string.goodsprice3,
				null),
		new ShowGoods(R.drawable.test3,R.string.goodstitle3, R.string.goodsprice3,
				null),
		new ShowGoods(R.drawable.test3,R.string.goodstitle3, R.string.goodsprice3,
				null),
		new ShowGoods(R.drawable.test3,R.string.goodstitle3, R.string.goodsprice3,
				null)		
				
		
	};
	
	

	protected void onListItemClick(int index) {
		
		//显示商品详细信息
//		Intent intent = null;
//		intent = new Intent(MainActivity.this,goods[index].showgoods);
//		this.startActivity(intent);
		
	}	
	
}


