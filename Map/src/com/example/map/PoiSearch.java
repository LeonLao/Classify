package com.example.map;

import android.R.integer;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class PoiSearch extends Activity{
	
	private Button search = null;
	private EditText ray = null;
	private EditText key = null;
	private String strkey ;
	private int intray;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.poisearch);
		
		ray = (EditText)findViewById(R.id.ray);
		key = (EditText)findViewById(R.id.key);
		search = (Button)findViewById(R.id.btnlist);
		
		search.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				intray = Integer.parseInt(ray.getText().toString());
				strkey = key.getText().toString();
				String type2 = "2";
				Intent intent = new Intent(PoiSearch.this,poilist.class);
				intent.putExtra("intentray", intray);
				intent.putExtra("intentkey", strkey);
				intent.putExtra("type", type2);
				startActivity(intent);
				finish();
			}
		});
		
		
		
		
		
		
		
		
		
		
		
	}
	

}
