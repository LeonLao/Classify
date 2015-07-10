package com.example.mycollect;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.renderscript.Program.TextureType;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {
	//顶部3控件
	private ImageButton btnback ;
	private TextView title;
	private ImageButton btnhome;
	
	private ListView mylist =null;
	
	final String[] mItems = {"取消关注","加入购物车"};
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		//顶部3控件
		btnback = (ImageButton)findViewById(R.id.back);
		title = (TextView)findViewById(R.id.title);
		btnhome = (ImageButton)findViewById(R.id.home);
		
		btnback.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//点击返回按钮
				
			}
		});
		
		//顶部标题
		title.setText("我的收藏");
		
		
		//主页按钮
		btnhome.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//点击主页面按钮
				
			}
		});
		
		
		
		
		
		mylist = (ListView) findViewById(R.id.listView1);
		
		mylist.setAdapter(new MyListAdapter());
		
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
		
		public MyListAdapter(){
			super();
		}

		@Override
		public int getCount() {
		
			return goods.length;
		}

		@Override
		public Object getItem(int position) {
			
			return goods[position];
		}

		@Override
		public long getItemId(int id) {
			
			return id;
		}

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
				null)
				
		
	};
	
	

	protected void onListItemClick(int index) {
		
		//显示商品详细信息
//		Intent intent = null;
//		intent = new Intent(MainActivity.this,goods[index].showgoods);
//		this.startActivity(intent);
		
	}
	
}


