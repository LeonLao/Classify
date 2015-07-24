package com.example.faxian;



import android.R.string;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	private ListView view;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		view = (ListView)findViewById(R.id.listView1);
		// 添加ListItem，设置事件响应
		view.setAdapter(new MyListAdapter());
		view.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int index, long id) {
				// TODO Auto-generated method stub
				onListItemClick(index);
			}
			
		});
	}
	
	protected void onListItemClick(int index){
		Intent intent = null;
		intent = new Intent(MainActivity.this,demos[index].demoClass);
		intent.putExtra("URL", demos[index].url);
		this.startActivity(intent);
		//MainActivity.this.finish();
	}
	
	private static final DemoInfo[] demos={
		new DemoInfo(R.string.xitongdaili,"http://119.29.19.221:8080/?mobile=",WebPage.class),
		new DemoInfo(R.string.xiazai,"http://app.lba.cn/",WebPage.class),
		new DemoInfo(R.string.ketang,"http://m.lba.cn/helpDetail.php?classId=18",WebPage.class),
		new DemoInfo(R.string.xiadan,"http://m.lba.cn/helpDetail.php?classId=19",WebPage.class),
		new DemoInfo(R.string.peisong,"http://m.lba.cn/helpDetail.php?classId=20",WebPage.class),
		new DemoInfo(R.string.shouhou,"http://m.lba.cn/helpDetail.php?classId=21",WebPage.class),
		new DemoInfo(R.string.wenti,"http://m.lba.cn/helpDetail.php?classId=22",WebPage.class),
		new DemoInfo(R.string.daili,"http://m.lba.cn/helpDetail.php?classId=23",WebPage.class),
		new DemoInfo(R.string.dongtai,"http://m.lba.cn/helpDetail.php?classId=36",WebPage.class)
	};
	
	private class MyListAdapter extends BaseAdapter{
		
		public MyListAdapter(){
			super();
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return demos.length;
		}

		@Override
		public Object getItem(int index) {
			// TODO Auto-generated method stub
			return demos[index];
		}

		@Override
		public long getItemId(int id) {
			// TODO Auto-generated method stub
			return id;
		}

		@Override
		public View getView(int index, View convertView, ViewGroup parent) {
						// TODO Auto-generated method stub
			convertView = View.inflate(MainActivity.this, R.layout.from, null);
			TextView title = (TextView) convertView.findViewById(R.id.item);
			title.setText(demos[index].title);
			title.setTextSize(20);
			return convertView;
		}
		
	}
	
	
	private static class DemoInfo{
		private final int title;
		private final String url;

		private final Class<? extends android.app.Activity> demoClass;
		
		public DemoInfo(int title,String url,
				Class<? extends android.app.Activity> demoClass){
			this.title = title;
			this.url = url;
			this.demoClass = demoClass;
		}
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
