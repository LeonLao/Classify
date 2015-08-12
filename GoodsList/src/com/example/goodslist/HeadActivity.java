package com.example.goodslist;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class HeadActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.head_bar);
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}
	public void onTopClick(View view){
		switch(view.getId()){
		case R.id.head_back:
			finish();
			break;
		case R.id.head_home:
			//·µ»ØÊ×Ò³¡£¡£
			finish();
			break;
		default:
			break;
		}
	}
	
	public void setHeadText(String text){
		((TextView)findViewById(R.id.head_text)).setText(text);
	}
	public void initHeadView(String text){
		setHeadText(text);
	}

}
