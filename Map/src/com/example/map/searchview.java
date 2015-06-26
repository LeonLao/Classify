package com.example.map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class searchview extends Activity{
	
	private EditText city,key;
	private Button star;
	private ListView view;
	private String[] items = new String[]{"中餐","西餐","小吃","宾馆饭店","广场","超市","公园",
			"购物中心","公交站","旅游景点","邮局","医院","药店","KTV","银行"};
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.searchview);
		
		view = (ListView)findViewById(R.id.listView1);
		city = (EditText)findViewById(R.id.city);
		key = (EditText)findViewById(R.id.key);
		star = (Button)findViewById(R.id.star);
		
			
		view.setAdapter(new ArrayAdapter<String>(searchview.this,
				android.R.layout.simple_list_item_1, items));
		
		star.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String strcity = city.getText().toString();
				String strkey = key.getText().toString();
				try {
					
					if(strcity == null || strkey == null){
						Toast.makeText(searchview.this, "请输入搜索条件", Toast.LENGTH_LONG).show();
					}
					else{
					Intent intent = new Intent(searchview.this,poilist.class);
					intent.putExtra("intentcity", strcity);
					intent.putExtra("intentkey", strkey);
					String type1 = "1";
					intent.putExtra("type", type1);
					startActivity(intent);
					}
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				
			}
		});
		
		view.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				key.setText(items[position]);
			}
		});

		
	}
	
}
