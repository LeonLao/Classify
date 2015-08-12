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
	
	//����3�ؼ�
	private ImageButton btnback ;
	private TextView title;
	private ImageButton btnhome;
	
	private ListView mylist =null;
	
	final String[] mItems = {"ȡ����ע","���빺�ﳵ"};
	
	private int lastItem = 0;
	
	
	
	private MyListAdapter adapter = new MyListAdapter();
	
	//���ò�����ʾĿ���ж��Ͷ��
	
	private LayoutParams sylayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT );
	
	//���ò�����ʾĿ�����
	private LayoutParams maxlayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT);
	
	private ProgressBar progressbar;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//���Բ���
		LinearLayout layout = new LinearLayout(this);
		
		//���ò���ˮƽ����
		layout.setOrientation(LinearLayout.HORIZONTAL);
		
		//������
		//progressbar = new ProgressBar(this);
		//��������ʾλ��
	//	progressbar.setPadding(0, 0, 15, 0);
		
		
	//	layout.addView(progressbar, sylayoutParams);
		
		
//		TextView textview = new TextView(this);
//		textview.setText("������...");
//		textview.setGravity(Gravity.CENTER_VERTICAL);
//		
//		layout.addView(textview, maxlayoutParams);
//		layout.setGravity(Gravity.CENTER);
//		
//		LinearLayout loadingLayout = new LinearLayout(this);
//		loadingLayout.addView(layout, sylayoutParams);
//		loadingLayout.setGravity(Gravity.CENTER);
		
		//�õ�һ��ListView������ʾ��Ŀ
	//	ListView listView = getListView();
		
		//��ӵ�ҳ����ʾ
	//	registerForContextMenu(listView);
		
	//	setListAdapter(adapter);
	//	listView.setOnScrollListener(this);
		
		
		//����3�ؼ�
		btnback = (ImageButton)findViewById(R.id.back);
		title = (TextView)findViewById(R.id.title);
		btnhome = (ImageButton)findViewById(R.id.home);
		
//		btnback.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				//������ذ�ť
//				
//			}
//		});
//		
//		//��������
//		title.setText("�ҵ��ղ�");
//		
//		
//		//��ҳ��ť
//		btnhome.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				//�����ҳ�水ť
//				
//			}
//		});
		
		
		
		
		
		mylist = (ListView) findViewById(R.id.listView1);
		
		//�õ�һ��ListView��ʾ��Ŀ
		//ListView mylist = getListView();
		//��ӵ�ҳ����ʾ
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
				//�����Ի���
				AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
				builder.setTitle("����");
				builder.setItems(mItems, new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						switch (which){
						case 0:
							//ȡ����ע
						
						break;
						case 1:
							//���빺�ﳵ
						
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
		
		//��ʾ��Ʒ��ϸ��Ϣ
//		Intent intent = null;
//		intent = new Intent(MainActivity.this,goods[index].showgoods);
//		this.startActivity(intent);
		
	}	
	
}


